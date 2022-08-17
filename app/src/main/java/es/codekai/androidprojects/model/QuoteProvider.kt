package es.codekai.androidprojects.model

class QuoteProvider {
    companion object {
        fun random(): QuoteModel {
            val position = (quote.indices).random()
            return quote[position]
        }

        private val quote = listOf<QuoteModel>(
            QuoteModel("Cita 1", "Autor 1"),
            QuoteModel("Cita 2", "Autor 2"),
            QuoteModel("Cita 3", "Autor 3"),
            QuoteModel("Cita 4", "Autor 4"),
            QuoteModel("Cita 5", "Autor 5"),
            QuoteModel("Cita 6", "Autor 6"),
        )
    }
}
