package dev.alejo.quoteapp.core.extensions

import dev.alejo.quoteapp.data.model.QuoteModel
import dev.alejo.quoteapp.domain.model.Quote

fun QuoteModel.toDomain() = Quote(quote, author)
