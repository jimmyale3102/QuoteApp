package dev.alejo.quoteapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.alejo.quoteapp.data.database.dao.QuoteDao
import dev.alejo.quoteapp.data.database.entities.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuoteDatabase: RoomDatabase() {
    abstract fun quoteDao(): QuoteDao
}