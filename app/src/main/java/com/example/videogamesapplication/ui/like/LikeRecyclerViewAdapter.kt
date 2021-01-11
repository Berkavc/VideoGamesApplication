package com.example.videogamesapplication.ui.like

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.videogamesapplication.R
import com.example.videogamesapplication.databinding.ItemGameDetailsRecyclerViewBinding
import com.example.videogamesapplication.model.GameModels
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class LikeRecyclerViewAdapter(
    private val context: Context,
    private var mutableListLikedGames: MutableList<GameModels.GameListModelItem?>
) :
    RecyclerView.Adapter<LikeRecyclerViewAdapter.MainRecyclerViewHolder>(), Filterable {

    private var mutableListGamesSearch = mutableListLikedGames

    private var coroutineCallRecyclerView = CoroutineScope(Dispatchers.Main)

    internal var onItemSelected: (position: Int, item: GameModels.GameListModelItem?) -> Unit =
        { _, _ -> }

    internal var onRefreshSearchState: () -> Unit =
        { }

    internal var arrangeRecyclerViewVisibility: (itemSize: Int) -> Unit =
        { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewHolder {
        val binding = ItemGameDetailsRecyclerViewBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainRecyclerViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return mutableListLikedGames.size
    }

    override fun onBindViewHolder(holder: MainRecyclerViewHolder, position: Int) {
        mutableListLikedGames[position]?.let {
            holder.bindItems(
                holder,
                it,
                position,
                onItemSelected
            )
        }

    }

    class MainRecyclerViewHolder(private val binding: ItemGameDetailsRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(
            holder: MainRecyclerViewHolder,
            item: GameModels.GameListModelItem?,
            position: Int,
            onItemSelected: (Int, GameModels.GameListModelItem?) -> Unit
        ) {
            Glide.with(holder.itemView.context)
                .load(item?.background_image)
                .placeholder(
                    ContextCompat.getDrawable(
                        holder.itemView.context,
                        R.drawable.ic_video_game_placeholder
                    )
                )
                .error(
                    ContextCompat.getDrawable(
                        holder.itemView.context,
                        R.drawable.ic_video_game_placeholder
                    )
                )
                .into(binding.imageViewViewRecyclerView)
            binding.textViewGameListTitle.text = item?.name
            val description = item?.rating.toString() + " " + "-" + " " + item?.released
            binding.textViewGameListDescription.text = description
            binding.constraintLayoutRecyclerView.setOnClickListener {
                onItemSelected(position, item)
            }
        }
    }

    fun updateDataSource(
        newDataSource: MutableList<GameModels.GameListModelItem?>,
        position: Int? = null
    ) {
        val newSource = arrayListOf<GameModels.GameListModelItem?>()
        newSource.addAll(newDataSource)
        this.mutableListLikedGames = newSource
        this.mutableListGamesSearch = newSource
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return filter
    }

    private val filter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: MutableList<GameModels.GameListModelItem?> = mutableListOf()
            filteredList.addAll(mutableListGamesSearch)
            constraint?.let {
                if (constraint.length < 3) {
                    coroutineCallRecyclerView.launch {
                        onRefreshSearchState()
                    }
                } else {
                    val filterPattern =
                        constraint.toString().toLowerCase(Locale.ROOT).trim()
                    mutableListGamesSearch.forEach { item ->
                        item?.name?.toLowerCase(Locale.ROOT)?.let {
                            if (!it.contains(filterPattern)) {
                                filteredList.remove(item)
                            }
                        }
                    }
                }
            }
            val result = FilterResults()
            result.values = filteredList
            return result
        }


        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            results?.values?.let {
                mutableListLikedGames = it as MutableList<GameModels.GameListModelItem?>
                notifyDataSetChanged()
                arrangeRecyclerViewVisibility(mutableListLikedGames.size)
            }
        }
    }
}