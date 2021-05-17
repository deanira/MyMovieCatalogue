package com.dea.mymoviecatalogue.ui.tvshows

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dea.mymoviecatalogue.R
import com.dea.mymoviecatalogue.data.response.TvShowResultsItem
import com.dea.mymoviecatalogue.databinding.ItemGridTvShowsBinding
import com.dea.mymoviecatalogue.ui.detail.tvshow.DetailTvShowActivity
import com.dea.mymoviecatalogue.utils.Constant

class TvShowsAdapter : RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder>() {
    private var listTvShows = ArrayList<TvShowResultsItem>()

    fun setData(tvShows: List<TvShowResultsItem>?) {
        if (tvShows == null) return
        this.listTvShows.clear()
        this.listTvShows.addAll(tvShows)
        notifyDataSetChanged()
    }

    inner class TvShowsViewHolder(private val binding: ItemGridTvShowsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowResultsItem) {
            with(binding) {
                tvTitle.text = tvShow.name
                tvRating.text = tvShow.voteAverage.toString()

                ivTvShow.loadImage("${tvShow.posterPath}")

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvShowActivity::class.java)
                    intent.putExtra(DetailTvShowActivity.EXTRA_TV_SHOW_ID, tvShow.id)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    fun ImageView.loadImage(url: String?) {
        Glide.with(this.context)
            .load("${Constant.IMAGE_URL}$url")
            .placeholder(R.color.gray)
            .into(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder {
        val itemGridTvShowsBinding =
            ItemGridTvShowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowsViewHolder(itemGridTvShowsBinding)
    }

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
        val tvShow = listTvShows[position]
        holder.bind(tvShow)
//        holder.itemView.animation =
//            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.rv_animation)
    }

    override fun getItemCount(): Int = listTvShows.size
}