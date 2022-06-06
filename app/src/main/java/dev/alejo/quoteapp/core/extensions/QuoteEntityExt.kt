package dev.alejo.quoteapp.core.extensions

import dev.alejo.quoteapp.data.database.entities.QuoteEntity
import dev.alejo.quoteapp.domain.model.QuoteItem

fun QuoteEntity.toDomain() = QuoteItem(quote, author)