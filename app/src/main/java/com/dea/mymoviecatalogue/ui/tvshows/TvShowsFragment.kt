package com.dea.mymoviecatalogue.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dea.mymoviecatalogue.databinding.FragmentTvShowsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowsFragment : Fragment() {

    private lateinit var fragmentTvShowsBinding: FragmentTvShowsBinding
    private lateinit var carouselTvShowsAdapter: CarouselTvShowsAdapter
    private lateinit var topRatedTvShowsAdapter: TvShowsAdapter
    private lateinit var latestTvShowsAdapter: TvShowsAdapter
    private val tvShowsViewModel: TvShowsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentTvShowsBinding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            carouselTvShowsAdapter = CarouselTvShowsAdapter()
            topRatedTvShowsAdapter = TvShowsAdapter()
            latestTvShowsAdapter = TvShowsAdapter()

            fragmentTvShowsBinding.shimmerViewContainerOnAir.startShimmerAnimation()
            fragmentTvShowsBinding.shimmerViewContainerTopRated.startShimmerAnimation()

            loadPopularShows()
            loadOnTheAirShows()
            loadTopRatedShows()
        }
    }

    private fun loadPopularShows() {
        with(fragmentTvShowsBinding.rvTvShowsPopular) {
            adapter = carouselTvShowsAdapter
            set3DItem(true)
            setInfinite(true)
            setAlpha(true)
            layoutManager = fragmentTvShowsBinding.rvTvShowsPopular.getCarouselLayoutManager()
            setHasFixedSize(true)

            tvShowsViewModel.getPopularTvShows().observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    carouselTvShowsAdapter.setData(it)
                }
            }
        }
    }

    private fun loadOnTheAirShows() {
        with(fragmentTvShowsBinding.rvTvShowsOnTheAir) {
            adapter = latestTvShowsAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)

            tvShowsViewModel.getOnTheAirTvShows().observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    latestTvShowsAdapter.setData(it)
                    showLoadingOnAir(false)
                }
            }
        }
    }

    private fun loadTopRatedShows() {
        with(fragmentTvShowsBinding.rvTvShowsTopRated) {
            adapter = topRatedTvShowsAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)

            tvShowsViewModel.getTopRatedTvShows().observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    topRatedTvShowsAdapter.setData(it)
                    showLoadingTopRated(false)
                }
            }
        }
    }

    private fun showLoadingOnAir(state: Boolean) {
        if (state) {
            fragmentTvShowsBinding.shimmerViewContainerOnAir.visibility = View.VISIBLE
        } else {
            fragmentTvShowsBinding.shimmerViewContainerOnAir.stopShimmerAnimation()
            fragmentTvShowsBinding.shimmerViewContainerOnAir.visibility = View.GONE
        }
    }

    private fun showLoadingTopRated(state: Boolean) {
        if (state) {
            fragmentTvShowsBinding.shimmerViewContainerTopRated.visibility = View.VISIBLE
        } else {
            fragmentTvShowsBinding.shimmerViewContainerTopRated.stopShimmerAnimation()
            fragmentTvShowsBinding.shimmerViewContainerTopRated.visibility = View.GONE
        }
    }
}