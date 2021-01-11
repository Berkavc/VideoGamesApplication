package com.example.videogamesapplication.ui.home

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

class HomeRecyclerViewAdapter(
    private val context: Context,
    private var mutableListGames: MutableList<GameModels.GameListResultModel?>,
    private var mutableListGamesAll: MutableList<GameModels.GameListResultModel?>
) :
    RecyclerView.Adapter<HomeRecyclerViewAdapter.MainRecyclerViewHolder>(), Filterable {

    private var controlFilterState = false

    private var coroutineCallRecyclerView = CoroutineScope(Dispatchers.Main)

    private var mutableListGamesSearch = mutableListGames

    internal var onItemSelected: (position: Int, item: GameModels.GameListResultModel?) -> Unit =
        { _, _ -> }

    internal var onRefreshSearchState: () -> Unit =
        { }

    internal var arrangeRecyclerViewVisibility: (itemSize: Int) -> Unit =
        { _ -> }

    internal var arrangeViewPagerVisibility: () -> Unit =
        { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewHolder {
        val binding = ItemGameDetailsRecyclerViewBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainRecyclerViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return mutableListGames.size
    }

    override fun onBindViewHolder(holder: MainRecyclerViewHolder, position: Int) {
        mutableListGames[position]?.let {
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
            item: GameModels.GameListResultModel?,
            position: Int,
            onItemSelected: (Int, GameModels.GameListResultModel?) -> Unit
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
        newDataSource: MutableList<GameModels.GameListResultModel?>,
        originalSource: MutableList<GameModels.GameListResultModel?>
    ) {
        val newSource = arrayListOf<GameModels.GameListResultModel?>()
        newSource.addAll(newDataSource)
        this.mutableListGames = newSource
        this.mutableListGamesSearch = newSource
        this.mutableListGamesAll = originalSource
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return filter
    }

    private val filter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            var filteredList: MutableList<GameModels.GameListResultModel?>? = null
            constraint?.let {
                if (constraint.length < 3) {
                    if (!controlFilterState) {
                        controlFilterState = true
                        coroutineCallRecyclerView.launch {
                            onRefreshSearchState()
                        }
                    }

                } else {
                    filteredList = mutableListOf()
                    filteredList!!.addAll(mutableListGamesAll)
                    arrangeViewPagerVisibility()
                    controlFilterState = false
                    val filterPattern =
                        constraint.toString().toLowerCase(Locale.ROOT).trim()
                    mutableListGamesAll.forEach { item ->
                        item?.name?.toLowerCase(Locale.ROOT)?.let {
                            if (!it.contains(filterPattern)) {
                                filteredList!!.remove(item)
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
                mutableListGames = it as MutableList<GameModels.GameListResultModel?>
                notifyDataSetChanged()
                arrangeRecyclerViewVisibility(mutableListGames.size)
            }
        }
    }
}