package com.abhi.trackinfo.content_providers

import android.Manifest
import android.content.ContentUris
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import coil3.compose.SubcomposeAsyncImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator

class MediaImagesActivity : ComponentActivity() {

    // A thread-safe, snapshot-aware state list that Compose can observe automatically
    private val imagesList = mutableStateListOf<Image>()

    // 2. Register the multi-permission launcher with proper scoping
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val isFullAccessGranted = permissions[Manifest.permission.READ_MEDIA_IMAGES] ?: false
        val isPartialAccessGranted =
            permissions[Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED] ?: false
        val isLegacyGranted = permissions[Manifest.permission.READ_EXTERNAL_STORAGE] ?: false

        // If any form of read access is given, safely load the images
        if (isFullAccessGranted || isPartialAccessGranted || isLegacyGranted) {
            loadImagesAsync()
        } else {
            // Handle permission denied state gracefully here
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 3. Check permission state before aggressively querying the storage
        if (hasRequiredPermission()) {
            loadImagesAsync()
        } else {
            checkAndRequestMediaPermissions()
        }

        setContent {

            if (imagesList.isEmpty()) {
                // Show an empty state if no images match the criteria
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "No images found from the last 24 hours.")
                }
            } else {
                // The core scrolling grid
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 110.dp), // Auto-calculates columns based on size
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    // Using 'key' optimizes scroll performance during recomposition
                    items(imagesList, key = { it.id }) { image ->
                        ImageGridItem(image = image)
                    }
                }
            }
        }
    }

    /**
     * Helper to check permissions based on the active Android OS version
     */
    private fun hasRequiredPermission(): Boolean {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE -> {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_MEDIA_IMAGES
                ) == PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
                        ) == PackageManager.PERMISSION_GRANTED
            }

            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_MEDIA_IMAGES
                ) == PackageManager.PERMISSION_GRANTED
            }

            else -> {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            }
        }
    }

    /**
     * Properly triggers permission dialog sequence based on device API level
     */
    private fun checkAndRequestMediaPermissions() {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE -> {
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.READ_MEDIA_IMAGES,
                        Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
                    )
                )
            }

            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                requestPermissionLauncher.launch(arrayOf(Manifest.permission.READ_MEDIA_IMAGES))
            }

            else -> {
                requestPermissionLauncher.launch(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE))
            }
        }
    }

    /**
     * Launches a lifecycle-aware coroutine to update the UI state
     */
    private fun loadImagesAsync() {
        lifecycleScope.launch {
            val fetchedImages = fetchImagesFromYesterday()
            imagesList.clear()
            imagesList.addAll(fetchedImages)
        }
    }

    /**
     * Offloads MediaStore heavy lifting to the background I/O thread pool
     */
    private suspend fun fetchImagesFromYesterday(): List<Image> = withContext(Dispatchers.IO) {
        val images = mutableListOf<Image>()

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME
        )

        val millisYesterday = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_YEAR, -1)
        }.timeInMillis

        val selection = "${MediaStore.Images.Media.DATE_TAKEN} >= ?"
        val selectionArgs = arrayOf(millisYesterday.toString())
        val sortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"

        try {
            contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                sortOrder
            )?.use { cursor ->
                // Using getColumnIndexOrThrow is safer and avoids potential -1 evaluation bugs
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)

                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val name = cursor.getString(nameColumn)
                    val uri = ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        id
                    )
                    images.add(Image(id, name, uri))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return@withContext images
    }

    @Composable
    fun ImageGridItem(image: Image) {
        // SubcomposeAsyncImage allows placeholder/loading states out-of-the-box
        SubcomposeAsyncImage(
            model = image.uri,
            contentDescription = image.name,
            modifier = Modifier
                .aspectRatio(1f) // Makes the item a perfect square
                .clip(RoundedCornerShape(8.dp)), // Smooth out the corners
            contentScale = ContentScale.Crop, // Crops image to fill the square box snugly
            loading = {
                // Display a tiny loader while the image decodes
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(strokeWidth = 2.dp)
                }
            }
        )
    }
}

data class Image(
    val id: Long,
    val name: String,
    val uri: Uri
)

