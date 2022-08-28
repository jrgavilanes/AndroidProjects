package es.codekai.androidprojects.presentation.example

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.codekai.androidprojects.domain.ExampleUseCase
import es.codekai.androidprojects.domain.model.ExampleDomainModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExampleViewModel @Inject constructor(
    private val exampleUseCase: ExampleUseCase
) : ViewModel() {
    private val _example = MutableLiveData<ExampleDomainModel>()
    val example: LiveData<ExampleDomainModel> get() = _example

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun onCreate() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val result = exampleUseCase.getAllExamples()
            if (result.isNotEmpty()) {
                _example.postValue(result.first())
                _isLoading.postValue(false)
            }
        }
    }

    fun getRandomQuote() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val randomQuote = exampleUseCase.getRandomExample()
            randomQuote?.let {
                _example.postValue(it)
            }
            _isLoading.postValue(false)
        }
    }
}
