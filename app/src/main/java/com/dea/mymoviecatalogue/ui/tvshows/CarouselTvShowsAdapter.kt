package com.dea.mymoviecatalogue.ui.tvshows

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dea.mymoviecatalogue.data.response.TvShowResultsItem
import com.dea.mymoviecatalogue.databinding.ItemRvCarouselBinding
import com.dea.mymoviecatalogue.ui.detail.tvshow.DetailTvShowActivity
import com.dea.mymoviecatalogue.utils.Constant

class CarouselTvShowsAdapter: RecyclerView.Adapter<CarouselTvShowsAdapter.TvShowsViewHolder>() {
    private var listTvShows = ArrayList<TvShowResultsItem>()

    fun setData(movies: List<TvShowResultsItem>?) {
        if (movies == null) return
        this.listTvShows.clear()
        this.listTvShows.addAll(movies)
        notifyDataSetChanged()
    }

    inner class TvShowsViewHolder(private val binding: ItemRvCarouselBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: TvShowResultsItem) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("${Constant.IMAGE_URL}${movie.posterPath}")
                    .into(poster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvShowActivity::class.java)
                    intent.putExtra(DetailTvShowActivity.EXTRA_TV_SHOW_ID, movie.id)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder {
        val itemRvCarouselBinding = ItemRvCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowsViewHolder(itemRvCarouselBinding)
    }

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
        val movie = listTvShows[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listTvShows.size
}