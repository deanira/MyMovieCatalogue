package com.dea.mymoviecatalogue.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dea.mymoviecatalogue.data.response.MovieResultsItem
import com.dea.mymoviecatalogue.databinding.ItemRvCarouselBinding
import com.dea.mymoviecatalogue.ui.detail.movie.DetailMovieActivity
import com.dea.mymoviecatalogue.utils.Constant

class CarouselMoviesAdapter : RecyclerView.Adapter<CarouselMoviesAdapter.MoviesViewHolder>() {
    private var listMovies = ArrayList<MovieResultsItem>()

    fun setData(movies: List<MovieResultsItem>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
        notifyDataSetChanged()
    }

    inner class MoviesViewHolder(private val binding: ItemRvCarouselBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieResultsItem) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("${Constant.IMAGE_URL}${movie.posterPath}")
                    .into(poster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE_ID, movie.id)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemRvCarouselBinding = ItemRvCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemRvCarouselBinding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size
}