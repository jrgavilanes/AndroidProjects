package es.codekai.androidprojects.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import es.codekai.androidprojects.databinding.ActivityMainBinding
import es.codekai.androidprojects.presentation.example.ExampleActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUI()
    }

    private fun setUI() {
        binding.btnExample.setOnClickListener {
            val exampleScreen = Intent(this, ExampleActivity::class.java)
            startActivity(exampleScreen)
        }
    }
}
