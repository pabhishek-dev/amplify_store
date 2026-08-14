package com.abhi.amplify_store.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.abhi.amplify_store.data.local.converters.Converters
import com.abhi.amplify_store.domain.model.Product

@Entity(tableName = "products")
@TypeConverters(Converters::class)
data class ProductEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val price: Float,
    val discountPrice: Float,
    val category: String,
    val rating: Float,
    val stock: Int,
    val reviewCount: Int,
    val isNewArrival: Boolean,
    val isFeatured: Boolean,
    val imageUrl: String,
    val galleryImages: List<String>,
    val desc: String,
    val tags: List<String>,
    val specifications: Map<String, String>
)

fun ProductEntity.toDomainProduct(): Product {
    return Product(
        id = id,
        name = name,
        price = price,
        discountPrice = discountPrice,
        category = category,
        rating = rating,
        stock = stock,
        reviewCount = reviewCount,
        isFeatured = isFeatured,
        isNewArrival = isNewArrival,
        imageUrl = imageUrl,
        galleryImages = galleryImages,
        desc = desc,
        tags = tags,
        specifications = specifications
    )
}
