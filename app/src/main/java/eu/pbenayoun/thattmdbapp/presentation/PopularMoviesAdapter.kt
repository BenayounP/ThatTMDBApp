package eu.pbenayoun.thattmdbapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import eu.pbenayoun.thatdmdbapp.repository.model.TMDBMovie
import eu.pbenayoun.thattmdbapp.databinding.ListItemMovieBinding

class PopularMoviesAdapter  :
    ListAdapter<TMDBMovie, RecyclerView.ViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PopularMovieViewHolder(
            ListItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = getItem(position)
        (holder as PopularMovieViewHolder).binding.title.text= movie.title
        (holder as PopularMovieViewHolder).binding.releaseDate.text= movie.releaseDate
        val posterView = (holder as PopularMovieViewHolder).binding.poster
        Glide.with(posterView.context)
            .load(movie.posterUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(posterView)
    }

    class PopularMovieViewHolder(
        val binding: ListItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root)
}

private class MovieDiffCallback : DiffUtil.ItemCallback<TMDBMovie>() {

    override fun areItemsTheSame(oldItem: TMDBMovie, newItem: TMDBMovie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TMDBMovie, newItem: TMDBMovie): Boolean {
        return oldItem == newItem
    }
}