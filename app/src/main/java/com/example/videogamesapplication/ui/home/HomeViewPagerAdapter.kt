package com.example.videogamesapplication.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.videogamesapplication.R
import com.example.videogamesapplication.databinding.ItemGameDetailsViewPagerBinding
import com.example.videogamesapplication.model.GameModels
import timber.log.Timber
import java.util.*

class HomeViewPagerAdapter(
    private val context: Context,
    private var mutableListGames: MutableList<GameModels.GameListResultModel?>
) : PagerAdapter() {

//    private var mutableListGamesSearch = mutableListGames

    internal var onItemSelected: (position: Int, item: GameModels.GameListResultModel?) -> Unit =
        { _, _ -> }

    /*  internal var onRefreshSearchState: () -> Unit =
          { } */

    /*  internal var setCurrentItemPosition: (position: Int) -> Unit =
          { _ -> }*/

    /* internal var arrangeViewPagerVisibility: (itemSize: Int) -> Unit =
         { _ -> }*/

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }

    override fun getCount(): Int {
        return mutableListGames.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = ItemGameDetailsViewPagerBinding
            .inflate(LayoutInflater.from(context), container, false)
        Glide.with(context)
            .load(mutableListGames[position]?.background_image)
            .placeholder(ContextCompat.getDrawable(context, R.drawable.ic_video_game_placeholder))
            .error(ContextCompat.getDrawable(context, R.drawable.ic_video_game_placeholder))
            .into(binding.imageViewViewPager)
        container.addView(binding.root)
        binding.imageViewViewPager.setOnClickListener {
            if (mutableListGames.size > position) {
                onItemSelected(position, mutableListGames[position])
            }

        }
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }

    fun updateDataSource(
        newDataSource: MutableList<GameModels.GameListResultModel?>
    ) {
//        val newSource = arrayListOf<GameModels.GameListResultModel?>()
//        newSource.addAll(newDataSource)
        this.mutableListGames = newDataSource
//        this.mutableListGamesSearch = newSource
        notifyDataSetChanged()
    }

    //This methods for filtering in view-pager.
    /* override fun getFilter(): Filter {
         return filter
     }

     private val filter: Filter = object : Filter() {
         override fun performFiltering(constraint: CharSequence?): FilterResults {
             val filteredList: MutableList<GameModels.GameListResultModel?> = mutableListOf()
             filteredList.addAll(mutableListGamesSearch)
             try {
                 constraint?.let {
                     if (constraint.length < 3) {
                         onRefreshSearchState()
                     } else {
                         val filterPattern =
                             constraint.toString().toLowerCase(Locale.ROOT).trim()
                         var positionCounter = 0
                         var foundItem = false
                         mutableListGamesSearch.forEach { item ->
                             item?.name?.toLowerCase(Locale.ROOT)?.let {
                                 if (!it.contains(filterPattern)) {
                                     filteredList.remove(item)
                                 } else {
                                     if (!foundItem) {
                                         foundItem = true
                                         setCurrentItemPosition(positionCounter)
                                     } else {
                                         Timber.e("not an viewpager item!!")
                                     }
                                 }
                             }
                             positionCounter++
                         }
                     }
                 }
             } catch (e: Exception) {
                 e.printStackTrace()
             }
             val result = FilterResults()
             result.values = filteredList
             return result
         }


         override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
             results?.values?.let {
                 mutableListGames = it as MutableList<GameModels.GameListResultModel?>
                 notifyDataSetChanged()
                 arrangeViewPagerVisibility(mutableListGames.size)
             }
         }
     }
     */
}