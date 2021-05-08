package eu.pbenayoun.thattmdbapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import eu.pbenayoun.thatdmdbapp.repository.model.TMDBMovie
import eu.pbenayoun.thattmdbapp.R
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
        (holder as PopularMovieViewHolder).onBind(getItem(position))
    }

    class PopularMovieViewHolder(
        val binding: ListItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root){

        fun onBind(tmdbMovie: TMDBMovie){

            binding.title.text= tmdbMovie.title
            binding.releaseDate.text= tmdbMovie.releaseDate
            val posterView = binding.poster
            Glide.with(posterView.context)
                .load(tmdbMovie.posterUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(posterView)
            binding.star1.setOnClickListener { setRating(1) }
            binding.star2.setOnClickListener { setRating(2) }
            binding.star3.setOnClickListener { setRating(3) }
            binding.star4.setOnClickListener { setRating(4) }
            binding.star5.setOnClickListener { setRating(5) }
        }

        fun setRating(rating : Int){
            for (index in 1..5){
                setStarView(index,rating)
            }
        }

        private fun setStarView(index:Int, rating:Int) {
            val isRated = rating>=index
            when(index){
                1-> setStar(binding.star1,isRated)
                2-> setStar(binding.star2,isRated)
                3-> setStar(binding.star3,isRated)
                4-> setStar(binding.star4,isRated)
                5-> setStar(binding.star5,isRated)
            }

        }

        private fun setStar(imageView: ImageView, isRated: Boolean){
            val imageResource = when(isRated){
                false -> R.drawable.ic_star
                true -> R.drawable.ic_star_rated
            }
            imageView.setImageResource(imageResource)
        }
    }
}

private class MovieDiffCallback : DiffUtil.ItemCallback<TMDBMovie>() {

    override fun areItemsTheSame(oldItem: TMDBMovie, newItem: TMDBMovie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TMDBMovie, newItem: TMDBMovie): Boolean {
        return oldItem == newItem
    }
}