package dev.alejo.quoteapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import dev.alejo.quoteapp.databinding.ActivityMainBinding
import dev.alejo.quoteapp.ui.viewmodel.QuoteViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val quoteViewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        quoteViewModel.onCreate()
        initUI()
        initObservers()
    }

    private fun initObservers() {
        quoteViewModel.quoteResponse.observe(this, Observer { currentQuote ->
            binding.quote.text = currentQuote.quote
            binding.author.text = currentQuote.author
        })
        quoteViewModel.isLoadingData.observe(this, Observer {
            binding.loadingProgressBar.isVisible = it
        })
    }

    private fun initUI() {
        binding.quoteContainer.setOnClickListener { quoteViewModel.getRandomQuote() }
    }

}