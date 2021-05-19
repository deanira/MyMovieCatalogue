package com.dea.mymoviecatalogue.ui.favorite.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dea.myTvShowcatalogue.ui.favorite.tvshow.FavoriteTvShowViewModel
import com.dea.mymoviecatalogue.R
import com.dea.mymoviecatalogue.databinding.FragmentFavoriteTvShowBinding
import com.dea.mymoviecatalogue.ui.favorite.movie.FavoriteMovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteTvShowFragment : Fragment() {

    private val favoriteTvShowViewModel: FavoriteTvShowViewModel by viewModels()
    private lateinit var adapter: FavoriteTvShowAdapter
    private lateinit var binding: FragmentFavoriteTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        touchHelper()
        favoriteTvShowViewModel.getFavoriteTvShows().observe(viewLifecycleOwner) {
            adapter = FavoriteTvShowAdapter()
            adapter.differ.submitList(it)
            binding.rvFavTvShow.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = this@FavoriteTvShowFragment.adapter
                setHasFixedSize(true)
            }
        }
    }

    private fun touchHelper() {

    }
}