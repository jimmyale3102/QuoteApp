package dev.alejo.quoteapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes", primaryKeys = ["id"])
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "id") val id: Int = 0,
    @ColumnInfo(name= "quote") val quote: String,
    @ColumnInfo(name= "author") val author: String
)