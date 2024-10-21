package com.uvg.rickandmorty.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location")
data class Location(
    @PrimaryKey val id: Int,
    val name: String,
    val type: String,
    val dimension: String
)
