package es.codekai.androidprojects.presentation.rickandmorty

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.codekai.androidprojects.core.load
import es.codekai.androidprojects.databinding.ViewRickandmortyCharacterItemBinding
import es.codekai.androidprojects.domain.model.RickandmortyDomainModel

class RickandmortyAdapter(
    private val characters: List<RickandmortyDomainModel>,
    val itemClick: (RickandmortyDomainModel) -> Unit
) :
    RecyclerView.Adapter<RickandmortyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewRickandmortyCharacterItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position])
        holder.itemView.setOnClickListener { itemClick(characters[position]) }
    }

    override fun getItemCount(): Int = characters.size

    class ViewHolder(private val binding: ViewRickandmortyCharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: RickandmortyDomainModel) {
            binding.txtCharacterName.text = character.name
            binding.imgCharacter.load(character.image)
        }
    }
}
