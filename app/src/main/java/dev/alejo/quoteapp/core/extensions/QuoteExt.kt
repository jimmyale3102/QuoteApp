package dev.alejo.quoteapp.core.extensions

import dev.alejo.quoteapp.data.database.entities.QuoteEntity
import dev.alejo.quoteapp.domain.model.Quote

fun Quote.toDatabase() = QuoteEntity(quote = quote, author = author)