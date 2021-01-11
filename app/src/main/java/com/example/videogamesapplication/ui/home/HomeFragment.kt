package com.example.videogamesapplication.ui.home

import android.app.Activity
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videogamesapplication.R
import com.example.videogamesapplication.databinding.FragmentHomeBinding
import com.example.videogamesapplication.model.GameModels
import com.example.videogamesapplication.ui.BaseFragment
import com.example.videogamesapplication.ui.details.DetailsActivity
import com.example.videogamesapplication.utils.observe
import com.example.videogamesapplication.utils.viewBinding
import com.example.videogamesapplication.utils.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private lateinit var homeViewModel: HomeViewModel
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private lateinit var adapterHomeRecyclerView: HomeRecyclerViewAdapter
    private lateinit var adapterHomeViewPager: HomeViewPagerAdapter
    private val coroutineCallView = CoroutineScope(Dispatchers.Main)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentComponent().inject(this)
        homeViewModel = viewModel(viewModelFactory) {
            observe(gameList, ::gameListChanged)
            observe(controlUIVisibilities, ::arrangeUIVisibilities)
            observe(controlReloadButtonVisibility, ::arrangeUIReloadButtonVisibility)
            observe(controlProgressBarVisibility, ::arrangeUIProgressBarVisibility)
            observe(controlViewPagerVisibility, ::arrangeViewPagerVisibility)
            observe(controlRecyclerViewVisibility, ::arrangeRecyclerViewVisibility)
        }
        binding.lifecycleOwner = this
        binding.viewModel = homeViewModel
        homeViewModel.gatherGameList()
        arrangeUI()
    }

    private fun arrangeUI() {
        arrangeRecyclerView()
        arrangeSearchView()
        arrangeViewPager()
    }

    private fun arrangeViewPager() {
        this.context?.let { context ->
            homeViewModel.gameList.value?.let {
                adapterHomeViewPager = HomeViewPagerAdapter(
                    context,
                    it
                )
                adapterHomeViewPager.onItemSelected = { position, item ->
                    item?.id?.let { id ->
                        startDetailsActivity(id.toString())
                    }
                }

                //These methods are for search in view-pager.
                /* adapterHomeViewPager.onRefreshSearchState = {
                     homeViewModel.gameList.value?.let { originList ->
                         coroutineCallView.launch {
                             reArrangeViewPagerForSearch(originList)
                         }
                     }
                 } */
                /*   adapterHomeViewPager.setCurrentItemPosition = {
                       coroutineCallView.launch {
                           binding.viewpager.currentItem = it
                       }
                   } */

                /*  adapterHomeViewPager.arrangeViewPagerVisibility = { size ->
                      coroutineCallView.launch {
                          if (size != 0) {
                              binding.viewpager.visibility = View.VISIBLE

                          } else {
                              binding.viewpager.visibility = View.GONE
                          }
                          homeViewModel.controlUIVisibilities.postValue(true)
                      }
                  } */
                binding.viewpager.adapter = adapterHomeViewPager

            }
        }
        binding.viewpager.currentItem = 0
    }

    private fun arrangeRecyclerView() {
        this.context?.let { context ->
            homeViewModel.gameList.value?.let {
                adapterHomeRecyclerView =
                    HomeRecyclerViewAdapter(
                        context,
                        it,
                        it
                    )
                with(binding.recyclerView) {
                    adapter = adapterHomeRecyclerView
                    adapterHomeRecyclerView.onItemSelected = { position, item ->
                        item?.id?.let { id ->
                            startDetailsActivity(id.toString())
                        }
                    }
                    adapterHomeRecyclerView.onRefreshSearchState = {
                        homeViewModel.gameList.value?.let { originList ->
                            reArrangeRecyclerViewForSearch(originList)
                        }
                    }
                    adapterHomeRecyclerView.arrangeRecyclerViewVisibility = { size ->
                        coroutineCallView.launch {
                            if (size != 0) {
                                binding.recyclerView.visibility = View.VISIBLE
                            } else {
                                binding.recyclerView.visibility = View.GONE
                            }
                            homeViewModel.controlUIVisibilities.postValue(true)
                        }

                    }

                    adapterHomeRecyclerView.arrangeViewPagerVisibility = {
                        coroutineCallView.launch {
                            arrangeViewPagerVisibility(false)
                        }
                    }
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                }
            }
        }
    }

    private fun gameListChanged(dataChanged: MutableList<GameModels.GameListResultModel?>?) {
        dataChanged?.let {
            val mutableListForViewPager: MutableList<GameModels.GameListResultModel?> =
                mutableListOf()
            val mutableListForRecyclerView: MutableList<GameModels.GameListResultModel?> =
                mutableListOf()
            for ((index, listItems) in it.withIndex()) {
                if (index > 2) {
                    mutableListForRecyclerView.add(listItems)
                } else {
                    mutableListForViewPager.add(listItems)
                }
            }
            adapterHomeViewPager.updateDataSource(mutableListForViewPager)
            adapterHomeRecyclerView.updateDataSource(mutableListForRecyclerView, it)
        }
    }

    private fun gameListChangedRecyclerView(dataChanged: MutableList<GameModels.GameListResultModel?>?) {
        dataChanged?.let {
            val mutableListForRecyclerView: MutableList<GameModels.GameListResultModel?> =
                mutableListOf()
            for ((index, listItems) in it.withIndex()) {
                if (index > 2) {
                    mutableListForRecyclerView.add(listItems)
                }
            }
            adapterHomeRecyclerView.updateDataSource(mutableListForRecyclerView, it)
            arrangeViewPagerVisibility(true)
            arrangeRecyclerViewVisibility(true)
            arrangeUIVisibilities(true)
        }
    }

    //This method are for search in view-pager.
    /* private fun gameListChangedViewPager(dataChanged: MutableList<GameModels.GameListResultModel?>?) {
         dataChanged?.let {
             val mutableListForViewPager: MutableList<GameModels.GameListResultModel?> =
                 mutableListOf()
             for ((index, listItems) in it.withIndex()) {
                 if (index <= 2) {
                     mutableListForViewPager.add(listItems)
                 }
             }
             adapterHomeViewPager.updateDataSource(mutableListForViewPager)
         }
     }*/

    private fun arrangeSearchView() {
        context?.let {
            val searchEditText: EditText =
                binding.searchViewHome.findViewById(androidx.appcompat.R.id.search_src_text)
            val searchFrame: View =
                binding.searchViewHome.findViewById(androidx.appcompat.R.id.search_plate)
            searchEditText.setHintTextColor(ContextCompat.getColor(it, R.color.orange))
            searchFrame.background.setColorFilter(
                ContextCompat.getColor(it, R.color.orange),
                PorterDuff.Mode.SRC_OVER
            )
        }

        binding.searchViewHome.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(constraint: String?): Boolean {
//                adapterHomeViewPager.filter.filter(constraint)
                adapterHomeRecyclerView.filter.filter(constraint)
                return false
            }
        })
    }

    private fun reArrangeRecyclerViewForSearch(originList: MutableList<GameModels.GameListResultModel?>) {
        val originalList = mutableListOf<GameModels.GameListResultModel?>()
        originalList.addAll(originList)
        gameListChangedRecyclerView(originList)
    }

    //This method are for search in view-pager.
    /*
    private fun reArrangeViewPagerForSearch(originList: MutableList<GameModels.GameListResultModel?>) {
        val originalList = mutableListOf<GameModels.GameListResultModel?>()
        originalList.addAll(originList)
        gameListChangedViewPager(originList)
    }*/

    private fun startDetailsActivity(id: String) {
        val intent = Intent(context, DetailsActivity::class.java)
        startActivity(intent.apply {
            putExtra(DetailsActivity.GAME_LIST_PK, id)
        })
    }

    private fun arrangeUIVisibilities(shouldChangeVisibility: Boolean?) {
        if (shouldChangeVisibility == true) {
            if (binding.recyclerView.visibility == View.GONE && binding.viewpager.visibility == View.GONE) {
                binding.textViewHomeEmptySearch.visibility = View.VISIBLE
            } else {
                binding.textViewHomeEmptySearch.visibility = View.GONE
            }
        }
    }

    private fun arrangeUIReloadButtonVisibility(shouldVisible: Boolean?) {
        context?.let {
            (it as Activity).runOnUiThread {
                if (shouldVisible == true) {
                    binding.buttonHomeReload.visibility = View.VISIBLE
                    binding.textViewHomeReload.visibility = View.VISIBLE
                } else {
                    binding.buttonHomeReload.visibility = View.GONE
                    binding.textViewHomeReload.visibility = View.GONE
                }
            }
        }
    }

    private fun arrangeUIProgressBarVisibility(shouldVisible: Boolean?) {
        context?.let {
            (it as Activity).runOnUiThread {
                if (shouldVisible == true) {
                    binding.progressBarReload.visibility = View.VISIBLE
                } else {
                    binding.progressBarReload.visibility = View.GONE
                }
            }
        }
    }

    private fun arrangeViewPagerVisibility(shouldVisible: Boolean?) {
        shouldVisible?.let {
            if (it) {
                binding.viewpager.visibility = View.VISIBLE
                binding.pageIndicatorView.visibility = View.VISIBLE
            } else {
                binding.viewpager.visibility = View.GONE
                binding.pageIndicatorView.visibility = View.GONE
            }
        }
    }

    private fun arrangeRecyclerViewVisibility(shouldVisible: Boolean?) {
        shouldVisible?.let {
            if (it) {
                binding.recyclerView.visibility = View.VISIBLE
            } else {
                binding.recyclerView.visibility = View.GONE
            }
        }
    }
}