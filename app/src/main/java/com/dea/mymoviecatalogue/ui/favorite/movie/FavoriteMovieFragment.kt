package com.dea.mymoviecatalogue.ui.favorite.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dea.mymoviecatalogue.R
import com.dea.mymoviecatalogue.databinding.FragmentFavoriteMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteMovieFragment : Fragment() {

    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModels()
    private lateinit var adapter: FavoriteMovieAdapter
    private lateinit var binding: FragmentFavoriteMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        touchHelper()
        favoriteMovieViewModel.getFavoriteMovies().observe(viewLifecycleOwner) {
            adapter = FavoriteMovieAdapter()
            adapter.differ.submitList(it)
            binding.rvFavMovie.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = this@FavoriteMovieFragment.adapter
                setHasFixedSize(true)
            }
        }
    }

    private fun touchHelper() {

    }
}