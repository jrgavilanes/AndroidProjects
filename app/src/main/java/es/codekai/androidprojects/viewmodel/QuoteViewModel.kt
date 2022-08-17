package es.codekai.androidprojects.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.codekai.androidprojects.model.QuoteModel
import es.codekai.androidprojects.model.QuoteProvider

class QuoteViewModel : ViewModel() {
    val quoteModel = MutableLiveData<QuoteModel>()

    fun randomQuote() {
        quoteModel.postValue(QuoteProvider.random())
    }
}
