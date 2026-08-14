package com.abhi.amplify_store.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abhi.amplify_store.domain.model.Favourite

@Entity("favourites")
data class FavouriteEntity(
    @PrimaryKey val productId: Int,
    val timeStamp: Long
)

fun FavouriteEntity.toDomainFavourite(): Favourite{
    return Favourite(
        productId = productId,
        timeStamp = timeStamp
    )
}
