package com.dea.mymoviecatalogue.ui.detail.tvshow

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dea.myTvShowcatalogue.ui.favorite.tvshow.FavoriteTvShowViewModel
import com.dea.mymoviecatalogue.R
import com.dea.mymoviecatalogue.data.favorite.FavoriteTvShow
import com.dea.mymoviecatalogue.data.response.DetailTvShowResponse
import com.dea.mymoviecatalogue.databinding.ActivityDetailTvShowBinding
import com.dea.mymoviecatalogue.utils.Constant
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailTvShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTvShowBinding
    private val detailTvShowViewModel: DetailTvShowViewModel by viewModels()
    private val favoriteTvShowViewModel: FavoriteTvShowViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getInt(EXTRA_TV_SHOW_ID)
            detailTvShowViewModel.getTvShow(tvShowId)
            detailTvShowViewModel.tvShowDetail.observe(this) {
                var genres = ""
                for (genre in it.genres) {
                    genres += if (genre == it.genres[it.genres.lastIndex]) {
                        genre?.name.toString()
                    } else {
                        "${genre?.name.toString()}, "
                    }
                }
                val releaseDate = it.firstAirDate.split("-")
                val tvShow = FavoriteTvShow(
                    null,
                    it.id,
                    it.name,
                    it.overview,
                    it.voteAverage,
                    genres,
                    releaseDate[0],
                    it.posterPath
                )
                populateDetails(it)

                setupFavorite(tvShow)
            }
        }
    }

    private fun setupFavorite(tvShow: FavoriteTvShow) {
        favoriteTvShowViewModel.isFavoriteTvShow(tvShow.tvShowId)
        favoriteTvShowViewModel.isFav.observe(this) { dataMovie ->
            with(binding) {
                if (dataMovie == null) {
                    fabFavorite.setImageResource(R.drawable.ic_favorite_border)
                    favoriteTvShowViewModel.isFavoriteTvShow(tvShow.tvShowId)
                    favoriteTvShowViewModel.isFav.observe(this@DetailTvShowActivity) {
                        fabFavorite.setOnClickListener {
                            fabFavorite.setImageResource(R.drawable.ic_favorite_filled)
                            favoriteTvShowViewModel.insertToFavorite(tvShow)
                            Snackbar.make(
                                binding.root,
                                getString(R.string.saved_to_favorite),
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                    }
                } else {
                    fabFavorite.setImageResource(R.drawable.ic_favorite_filled)
                    favoriteTvShowViewModel.isFavoriteTvShow(tvShow.tvShowId)
                    favoriteTvShowViewModel.isFav.observe(this@DetailTvShowActivity) {
                        fabFavorite.setOnClickListener {
                            fabFavorite.setImageResource(R.drawable.ic_favorite_border)
                            favoriteTvShowViewModel.deleteFromFavorite(dataMovie)
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

    private fun populateDetails(tvShow: DetailTvShowResponse?) {
        with(binding) {
            if (tvShow != null) {
                tvDescription.text = tvShow.overview
                tvRating.text = tvShow.voteAverage.toString()
                var genres = ""
                for (genre in tvShow.genres) {
                    if (genre == tvShow.genres[tvShow.genres.lastIndex]) {
                        genres += genre?.name.toString()
                    } else {
                        genres += "${genre?.name.toString()}, "
                    }
                }
                tvGenre.text = genres
                val releaseDate = tvShow.firstAirDate.split("-")
                tvReleaseYear.text = releaseDate[0]
                tvTitle.text = tvShow.name

                Glide.with(this@DetailTvShowActivity)
                    .load("${Constant.IMAGE_URL}${tvShow.posterPath}")
                    .into(ivDetail)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val EXTRA_TV_SHOW_ID = "EXTRA_ID"
    }
}