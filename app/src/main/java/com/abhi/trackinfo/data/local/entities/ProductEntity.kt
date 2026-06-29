package com.abhi.trackinfo.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abhi.trackinfo.domain.model.Product

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val price: Float,
    val stock: Int,
    val imageUrl: String,
    val desc: String,
    val rating: Float
)

fun ProductEntity.toDomainProduct(): Product {
    return Product(
        id = id,
        name = name,
        price = price,
        stock = stock,
        imageUrl = imageUrl,
        desc = desc,
        rating = rating
    )
}
