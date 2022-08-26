package es.codekai.androidprojects.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.codekai.androidprojects.core.load
import es.codekai.androidprojects.databinding.ViewMovieItemBinding
import es.codekai.androidprojects.domain.model.Movie

class MoviesAdapter(private val movies: List<Movie>, val itemClick: (Movie) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    // forma sin binding
    //    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class ViewHolder(private val binding: ViewMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.txtMovieTitle.text = movie.title
//            Glide.with(binding.root.context).load(movie.cover).into(binding.imgCover)
            binding.imgCover.load(movie.cover)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
// forma sin binding
//        val view = LayoutInflater
//            .from(parent.context)
//            .inflate(R.layout.view_movie_item, parent, false)
//        return ViewHolder(view)

        val binding = ViewMovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.itemView.setOnClickListener { itemClick(movies[position]) }
    }

    override fun getItemCount(): Int = movies.size
}
