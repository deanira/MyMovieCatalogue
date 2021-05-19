package com.dea.mymoviecatalogue.ui.favorite.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dea.mymoviecatalogue.data.favorite.FavoriteMovie
import com.dea.mymoviecatalogue.databinding.ItemFavoriteBinding
import com.dea.mymoviecatalogue.utils.loadImage

class FavoriteMovieAdapter :
    PagedListAdapter<FavoriteMovie, FavoriteMovieAdapter.FavoriteMovieViewHolder>(DIFFER_CALLBACK) {
    companion object {
        private val DIFFER_CALLBACK = object : DiffUtil.ItemCallback<FavoriteMovie>() {
            override fun areItemsTheSame(
                oldItem: FavoriteMovie,
                newItem: FavoriteMovie
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FavoriteMovie,
                newItem: FavoriteMovie
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    val differ = AsyncListDiffer(this, DIFFER_CALLBACK)

    inner class FavoriteMovieViewHolder(val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieViewHolder =
        FavoriteMovieViewHolder(
            ItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.binding.apply {
            ivPoster.loadImage(currentItem.posterPath)
            tvTitle.text = currentItem.title
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}