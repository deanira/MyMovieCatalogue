package com.dea.mymoviecatalogue.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dea.mymoviecatalogue.R
import com.dea.mymoviecatalogue.data.response.MovieResultsItem
import com.dea.mymoviecatalogue.databinding.ItemGridMovieBinding
import com.dea.mymoviecatalogue.ui.detail.movie.DetailMovieActivity
import com.dea.mymoviecatalogue.utils.Constant
import com.dea.mymoviecatalogue.utils.loadImage

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    private var listMovies = ArrayList<MovieResultsItem>()

    fun setData(movies: List<MovieResultsItem>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
        notifyDataSetChanged()
    }

    inner class MoviesViewHolder(private val binding: ItemGridMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieResultsItem) {
            with(binding) {
                tvTitle.text = movie.title
                tvRating.text = movie.voteAverage.toString()
                val releaseYear = movie.releaseDate.split("-")
                tvReleaseYear.text = releaseYear[0]

                ivMovie.loadImage("${movie.posterPath}")

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE_ID, movie.id)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemGridMovieBinding = ItemGridMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemGridMovieBinding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
//        holder.itemView.animation =
//            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.rv_animation)
    }

    override fun getItemCount(): Int = listMovies.size
}