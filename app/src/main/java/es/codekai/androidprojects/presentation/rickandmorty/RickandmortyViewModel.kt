package es.codekai.androidprojects.presentation.rickandmorty

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.codekai.androidprojects.domain.RickandmortyUseCase
import es.codekai.androidprojects.domain.model.RickandmortyDomainModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RickandmortyViewModel @Inject constructor(
    private val rickandmortyUseCase: RickandmortyUseCase
) : ViewModel() {

    private val _characters = MutableLiveData<List<RickandmortyDomainModel>>()
    val characters: LiveData<List<RickandmortyDomainModel>> get() = _characters

    fun getRickandmortyCharacters() {
        viewModelScope.launch {
            val result = rickandmortyUseCase.getAllRickandmortyCharacters()
            Log.d("juanra", result.toString())
            if (result.isNotEmpty()) _characters.value = result
        }
    }
}
