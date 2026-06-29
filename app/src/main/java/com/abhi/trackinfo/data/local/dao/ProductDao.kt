package com.abhi.trackinfo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.abhi.trackinfo.data.local.entities.FavouriteEntity
import com.abhi.trackinfo.data.local.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun observeAllProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products")
    suspend fun fetchAllProducts(): List<ProductEntity>

    @Query("SELECT * FROM products WHERE id = :productId")
    suspend fun fetchProduct(
        productId: Int
    ): ProductEntity

    @Query("""
    SELECT products.* FROM products
    INNER JOIN favourites
    ON products.id = favourites.productId
""")
    fun getFavoriteProducts(): Flow<List<ProductEntity>>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavourite(
        favouriteEntity: FavouriteEntity
    ):Long

    @Insert
    suspend fun addProduct(
        productEntity: ProductEntity
    ): Long

    @Insert
    suspend fun addProducts(
        productEntity: List<ProductEntity>
    ): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertProduct(
        productEntity: ProductEntity
    ):Long

    @Update
    suspend fun updateProduct(
        productEntity: ProductEntity
    ): Int

    @Query("DELETE FROM products WHERE id = :productId")
    suspend fun deleteProduct(
        productId: Int
    ): Int

    @Query("DELETE FROM products")
    suspend fun deleteAllProduct(): Int

    @Query("SELECT productId FROM favourites")
    fun observeFavouriteProductIds(): Flow<List<Int>>

    @Query("DELETE FROM favourites WHERE productId = :productId")
    suspend fun removeFavourite(productId: Int): Int

}
