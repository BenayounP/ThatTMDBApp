package eu.pbenayoun.thattmdbapp.presentation

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import eu.pbenayoun.thatdmdbapp.repository.model.TMDBMovie
import eu.pbenayoun.thattmdbapp.R
import eu.pbenayoun.thattmdbapp.databinding.ListItemMovieBinding

class PopularMoviesAdapter(val onRating: (tmdbMovie : TMDBMovie) -> Unit)  :
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
        (holder as PopularMovieViewHolder).onBind(getItem(position),onRating)
    }

    class PopularMovieViewHolder(
        val binding: ListItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root){

        fun onBind(tmdbMovie: TMDBMovie, onRating: (tmdbMovie: TMDBMovie) -> Unit){

            binding.title.text= tmdbMovie.title
            binding.releaseDate.text= tmdbMovie.releaseDate
            val posterView = binding.poster
            Glide.with(posterView.context)
                .load(tmdbMovie.posterUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(posterView)
            displayRating(tmdbMovie.userRating)

            binding.star1.setOnClickListener { setRating(1,tmdbMovie,onRating) }
            binding.star2.setOnClickListener { setRating(2,tmdbMovie,onRating) }
            binding.star3.setOnClickListener { setRating(3,tmdbMovie,onRating) }
            binding.star4.setOnClickListener { setRating(4,tmdbMovie,onRating) }
            binding.star5.setOnClickListener { setRating(5,tmdbMovie,onRating) }
        }

        private fun setRating(rating : Int,tmdbMovie: TMDBMovie,onRating: (tmdbMovie: TMDBMovie) -> Unit){
            displayRating(rating)
            tmdbMovie.userRating=rating
            onRating(tmdbMovie)
        }

        private fun displayRating(rating: Int){
            for (index in 1..5){
                setStarView(index,rating)
            }

        }


        private fun setStarView(index:Int, rating:Int) {
            when(index){
                1-> setStar(binding.star1,index,rating)
                2-> setStar(binding.star2,index,rating)
                3-> setStar(binding.star3,index,rating)
                4-> setStar(binding.star4,index,rating)
                5-> setStar(binding.star5,index,rating)
            }
        }

        private fun setStar(imageView: ImageView, index:Int, rating:Int){
            val isRated = rating>=index
            val imageResource = when(isRated){
                false -> R.drawable.ic_star
                true -> R.drawable.ic_star_rated
            }
            imageView.setImageResource(imageResource)
            val tintColor = if(isRated && rating==5){ R.color.golden_color}else{R.color.primary_text_color}
                imageView.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(imageView.context, tintColor)))
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