package com.dea.mymoviecatalogue.ui.detail.movie

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dea.mymoviecatalogue.data.response.DetailMovieResponse
import com.dea.mymoviecatalogue.databinding.ActivityDetailMovieBinding
import com.dea.mymoviecatalogue.utils.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private val detailMovieViewModel: DetailMovieViewModel by viewModels()

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
            //detailMovieViewModel.setSelectedMovie(movieId)
            detailMovieViewModel.getMovie(movieId)
            detailMovieViewModel.movieDetail.observe(this) {
                populateDetails(it)
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
                    if (genre == movie.genres[movie.genres.lastIndex]) {
                        genres += genre?.name.toString()
                    } else {
                        genres += "${genre?.name.toString()}, "
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