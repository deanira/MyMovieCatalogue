package com.dea.mymoviecatalogue.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dea.mymoviecatalogue.databinding.FragmentMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private lateinit var fragmentMovieBinding: FragmentMoviesBinding
    private lateinit var carouselMoviesAdapter: CarouselMoviesAdapter
    private lateinit var topRatedMoviesAdapter: MoviesAdapter
    private lateinit var nowPlayingMoviesAdapter: MoviesAdapter
    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentMovieBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentMovieBinding.shimmerViewContainerNowPlaying.startShimmerAnimation()
        fragmentMovieBinding.shimmerViewContainerTopRated.startShimmerAnimation()

        if (activity != null) {
            carouselMoviesAdapter = CarouselMoviesAdapter()
            nowPlayingMoviesAdapter = MoviesAdapter()
            topRatedMoviesAdapter = MoviesAdapter()

            loadPopularMovies()
            loadNowPlayingMovies()
            loadUpcomingMovies()
        }
    }

    private fun loadPopularMovies() {
        with(fragmentMovieBinding.rvMoviesPopular) {
            adapter = carouselMoviesAdapter
            set3DItem(true)
            setInfinite(true)
            setAlpha(true)
            layoutManager = fragmentMovieBinding.rvMoviesPopular.getCarouselLayoutManager()
            setHasFixedSize(true)

            moviesViewModel.getPopularMovies().observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    carouselMoviesAdapter.setData(it)
                }
            }
        }
    }

    private fun loadNowPlayingMovies() {
        with(fragmentMovieBinding.rvMoviesNowPlaying) {
            adapter = nowPlayingMoviesAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)

            moviesViewModel.getNowPlayingMovies().observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    nowPlayingMoviesAdapter.setData(it)
                    showLoadingNowPlaying(false)
                }
            }
        }
    }

    private fun loadUpcomingMovies() {
        with(fragmentMovieBinding.rvMoviesTopRated) {
            adapter = topRatedMoviesAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)

            moviesViewModel.getTopRatedMovies().observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    topRatedMoviesAdapter.setData(it)
                    showLoadingTopRated(false)
                }
            }
        }
    }

    private fun showLoadingNowPlaying(state: Boolean) {
        if (state) {
            fragmentMovieBinding.shimmerViewContainerNowPlaying.visibility = View.VISIBLE
        } else {
            fragmentMovieBinding.shimmerViewContainerNowPlaying.stopShimmerAnimation()
            fragmentMovieBinding.shimmerViewContainerNowPlaying.visibility = View.GONE
        }
    }

    private fun showLoadingTopRated(state: Boolean) {
        if (state) {
            fragmentMovieBinding.shimmerViewContainerTopRated.visibility = View.VISIBLE
        } else {
            fragmentMovieBinding.shimmerViewContainerTopRated.stopShimmerAnimation()
            fragmentMovieBinding.shimmerViewContainerTopRated.visibility = View.GONE
        }
    }
}