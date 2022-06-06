package dev.alejo.quoteapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.alejo.quoteapp.data.database.entities.QuoteEntity

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quotes")
    suspend fun getAllQuotes(): List<QuoteEntity>

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertAllQuotes(quote: List<QuoteEntity>)

}