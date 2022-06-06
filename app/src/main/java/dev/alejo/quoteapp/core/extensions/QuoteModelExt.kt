package dev.alejo.quoteapp.core.extensions

import dev.alejo.quoteapp.data.model.QuoteModel
import dev.alejo.quoteapp.domain.model.QuoteItem

fun QuoteModel.toDomain() = QuoteItem(quote, author)
