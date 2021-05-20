package com.dea.mymoviecatalogue.ui.favorite.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dea.myTvShowcatalogue.ui.favorite.tvshow.FavoriteTvShowViewModel
import com.dea.mymoviecatalogue.R
import com.dea.mymoviecatalogue.databinding.FragmentFavoriteTvShowBinding
import com.dea.mymoviecatalogue.ui.favorite.movie.FavoriteMovieAdapter
import com.google.android.material.snackbar.Snackbar
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
            if (it.isNotEmpty()){
                binding.tvWarning.visibility = View.INVISIBLE
                adapter = FavoriteTvShowAdapter()
                adapter.differ.submitList(it)
                binding.rvFavTvShow.apply {
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    adapter = this@FavoriteTvShowFragment.adapter
                    setHasFixedSize(true)
                }
            } else {
                binding.rvFavTvShow.visibility = View.INVISIBLE
                binding.tvWarning.visibility = View.VISIBLE
            }
        }
    }

    private fun touchHelper() {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val data = adapter.differ.currentList[position]

                favoriteTvShowViewModel.deleteFromFavorite(data)

                Snackbar.make(
                    binding.root,
                    "Movie Deleted From Favourite",
                    Snackbar.LENGTH_LONG
                ).apply {
                    setAction("UNDO") {
                        favoriteTvShowViewModel.insertToFavorite(data)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvFavTvShow)
        }
    }
}