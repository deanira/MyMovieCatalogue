package com.dea.mymoviecatalogue.ui.favorite.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dea.mymoviecatalogue.data.favorite.FavoriteTvShow
import com.dea.mymoviecatalogue.databinding.ItemFavoriteBinding
import com.dea.mymoviecatalogue.utils.loadImage

class FavoriteTvShowAdapter :
    PagedListAdapter<FavoriteTvShow, FavoriteTvShowAdapter.FavoriteTvShowViewHolder>(DIFFER_CALLBACK) {
    companion object {
        private val DIFFER_CALLBACK = object : DiffUtil.ItemCallback<FavoriteTvShow>() {
            override fun areItemsTheSame(
                oldItem: FavoriteTvShow,
                newItem: FavoriteTvShow
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FavoriteTvShow,
                newItem: FavoriteTvShow
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    val differ = AsyncListDiffer(this, DIFFER_CALLBACK)

    inner class FavoriteTvShowViewHolder(val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTvShowViewHolder =
        FavoriteTvShowViewHolder(
            ItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: FavoriteTvShowViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.binding.apply {
            ivPoster.loadImage(currentItem.posterPath)
            tvTitle.text = currentItem.title
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}