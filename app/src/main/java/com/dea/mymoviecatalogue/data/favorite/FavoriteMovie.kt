package com.dea.mymoviecatalogue.data.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteMovie(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val movieId: Int,
    val title: String,
    val description: String,
    val rating: Double,
    val genres: String,
    val releaseDate: String,
    val posterPath: String
)