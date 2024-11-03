package com.talespalma.quitandamerkfrutas.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val productId : Int? = 0,
    val name : String,
    val price : Double,
)
