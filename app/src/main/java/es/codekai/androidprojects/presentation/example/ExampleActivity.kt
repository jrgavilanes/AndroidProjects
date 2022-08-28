package es.codekai.androidprojects.presentation.example

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import es.codekai.androidprojects.databinding.ActivityExampleBinding

@AndroidEntryPoint
class ExampleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExampleBinding
    private val exampleViewModel: ExampleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        exampleViewModel.onCreate()
        setUI()
    }

    private fun setUI() {
        exampleViewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.progress.visibility = View.VISIBLE
            } else {
                binding.progress.visibility = View.GONE
            }
        }

        exampleViewModel.example.observe(this) {
            binding.txtAuthor.text = it.author
            binding.txtQuote.text = it.quote
        }

        binding.screen.setOnClickListener {
            exampleViewModel.getRandomQuote()
        }
    }
}
