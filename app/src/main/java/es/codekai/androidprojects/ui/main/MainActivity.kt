package es.codekai.androidprojects.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import es.codekai.androidprojects.core.toast
import es.codekai.androidprojects.databinding.ActivityMainBinding
import es.codekai.androidprojects.domain.model.Movie

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUI()
    }

    private fun setUI() {
//        mainViewModel.onCreate()
        binding.recycler.adapter = MoviesAdapter(
            listOf(
                Movie("titulo 1", "https://loremflickr.com/320/240?lock=1"),
                Movie("titulo 2", "https://loremflickr.com/320/240?lock=2"),
                Movie("titulo 3", "https://loremflickr.com/320/240?lock=3"),
                Movie("titulo 4", "https://loremflickr.com/320/240?lock=4"),
                Movie("titulo 5", "https://loremflickr.com/320/240?lock=5"),
                Movie("titulo 6", "https://loremflickr.com/320/240?lock=6"),
            )
        ) {
            toast("Has pulsado el ${it.title}")
//            Toast.makeText(this, "Has pulsado el ${it.title}", Toast.LENGTH_SHORT).show()
        }
    }
}
