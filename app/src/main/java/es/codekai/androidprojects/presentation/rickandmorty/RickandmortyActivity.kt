package es.codekai.androidprojects.presentation.rickandmorty

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import es.codekai.androidprojects.core.toast
import es.codekai.androidprojects.databinding.ActivityRickandmortyBinding

@AndroidEntryPoint
class RickandmortyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRickandmortyBinding
    private val exampleViewModel: RickandmortyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRickandmortyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUI()
    }

    private fun setUI() {

        exampleViewModel.getRickandmortyCharacters()

        binding.screen.setOnClickListener {
            exampleViewModel.getRickandmortyCharacters()
        }

        exampleViewModel.characters.observe(this) { characters ->
            binding.recyclerRickandmortyCharacters.adapter =
                RickandmortyAdapter(characters) { character ->
                    toast("Has pulsado sobre ${character.name}")
                }
        }
    }
}
