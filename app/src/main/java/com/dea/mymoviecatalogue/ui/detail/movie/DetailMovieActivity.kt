package com.dea.mymoviecatalogue.ui.detail.movie

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dea.mymoviecatalogue.R
import com.dea.mymoviecatalogue.data.favorite.FavoriteMovie
import com.dea.mymoviecatalogue.data.response.DetailMovieResponse
import com.dea.mymoviecatalogue.databinding.ActivityDetailMovieBinding
import com.dea.mymoviecatalogue.ui.favorite.movie.FavoriteMovieViewModel
import com.dea.mymoviecatalogue.utils.Constant
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private val detailMovieViewModel: DetailMovieViewModel by viewModels()
    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_MOVIE_ID)
            detailMovieViewModel.getMovie(movieId)
            detailMovieViewModel.movieDetail.observe(this) {
                var genres = ""
                for (genre in it.genres) {
                    genres += if (genre == it.genres[it.genres.lastIndex]) {
                        genre?.name.toString()
                    } else {
                        "${genre?.name.toString()}, "
                    }
                }
                val movie = FavoriteMovie(
                    null,
                    it.id,
                    it.title,
                    it.overview,
                    it.voteAverage,
                    genres,
                    it.releaseDate,
                    it.posterPath
                )
                populateDetails(it)

                setupFavorite(movie)
            }
        }
    }

    private fun setupFavorite(movie: FavoriteMovie) {
        favoriteMovieViewModel.isFavoriteMovie(movie.movieId)
        favoriteMovieViewModel.isFav.observe(this) { dataMovie ->
            with(binding) {
                if (dataMovie == null) {
                    fabFavorite.setImageResource(R.drawable.ic_favorite_border)
                    favoriteMovieViewModel.isFavoriteMovie(movie.movieId)
                    favoriteMovieViewModel.isFav.observe(this@DetailMovieActivity) {
                        fabFavorite.setOnClickListener {
                            fabFavorite.setImageResource(R.drawable.ic_favorite_filled)
                            favoriteMovieViewModel.insertToFavorite(movie)
                            Snackbar.make(
                                binding.root,
                                getString(R.string.saved_to_favorite),
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                    }
                } else {
                    fabFavorite.setImageResource(R.drawable.ic_favorite_filled)
                    favoriteMovieViewModel.isFavoriteMovie(movie.movieId)
                    favoriteMovieViewModel.isFav.observe(this@DetailMovieActivity) {
                        fabFavorite.setOnClickListener {
                            fabFavorite.setImageResource(R.drawable.ic_favorite_border)
                            favoriteMovieViewModel.deleteFromFavorite(dataMovie)
                            Snackbar.make(
                                binding.root,
                                getString(R.string.removed_from_favorite),
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun populateDetails(movie: DetailMovieResponse?) {
        with(binding) {
            if (movie != null) {
                tvDescription.text = movie.overview
                tvRating.text = movie.voteAverage.toString()
                var genres = ""
                for (genre in movie.genres) {
                    genres += if (genre == movie.genres[movie.genres.lastIndex]) {
                        genre?.name.toString()
                    } else {
                        "${genre?.name.toString()}, "
                    }
                }
                tvGenre.text = genres
                val releaseDate = movie.releaseDate.split("-")
                tvReleaseYear.text = releaseDate[0]
                tvTitle.text = movie.title
                Glide.with(this@DetailMovieActivity)
                    .load("${Constant.IMAGE_URL}${movie.posterPath}")
                    .into(ivDetail)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val EXTRA_MOVIE_ID = "EXTRA_ID"
    }
}