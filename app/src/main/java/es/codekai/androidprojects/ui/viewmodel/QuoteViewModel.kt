package es.codekai.androidprojects.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.codekai.androidprojects.domain.GetQuotesUseCase
import es.codekai.androidprojects.domain.GetRandomQuoteUseCase
import es.codekai.androidprojects.domain.model.Quote
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase
) : ViewModel() {
    val quoteModel = MutableLiveData<Quote>()
    val isLoading = MutableLiveData(false)

    fun randomQuote() {
        viewModelScope.launch {
            val randomQuote = getRandomQuoteUseCase()
            randomQuote?.let {
                quoteModel.postValue(it)
            }
        }
    }

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getQuotesUseCase()
            if (result.isNotEmpty()) {
                quoteModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }
}
