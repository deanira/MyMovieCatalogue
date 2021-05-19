package com.dea.mymoviecatalogue.ui.favorite.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.dea.mymoviecatalogue.R
import com.dea.mymoviecatalogue.databinding.FragmentFavoriteMovieBinding

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
}