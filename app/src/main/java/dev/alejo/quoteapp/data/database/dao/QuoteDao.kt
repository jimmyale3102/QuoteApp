package dev.alejo.quoteapp.data.database.dao

import androidx.room.*
import dev.alejo.quoteapp.data.database.entities.QuoteEntity

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quotes")
    suspend fun getAllQuotes(): List<QuoteEntity>

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertAllQuotes(quote: List<QuoteEntity>)

    @Query("DELETE FROM quotes")
    suspend fun deleteAllQuotes()

}