package com.example.videogamesapplication.ui.like

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videogamesapplication.R
import com.example.videogamesapplication.databinding.FragmentLikeBinding
import com.example.videogamesapplication.model.GameModels
import com.example.videogamesapplication.ui.BaseFragment
import com.example.videogamesapplication.ui.details.DetailsActivity
import com.example.videogamesapplication.utils.observe
import com.example.videogamesapplication.utils.viewBinding
import com.example.videogamesapplication.utils.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LikeFragment : BaseFragment(R.layout.fragment_like) {
    private lateinit var likeViewModel: LikeViewModel
    private val binding by viewBinding(FragmentLikeBinding::bind)
    private lateinit var adapterLikeRecyclerView: LikeRecyclerViewAdapter
    private val coroutineCallView = CoroutineScope(Dispatchers.Main)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentComponent().inject(this)
        likeViewModel = viewModel(viewModelFactory) {
            observe(gameList, ::gameListChanged)
            observe(controlUIVisibilities, ::arrangeUIVisibilities)
        }
        arrangeUI()
    }

    private fun arrangeUI() {
        arrangeRecyclerView()
        arrangeSearchView()
    }

    private fun arrangeRecyclerView() {
        this.context?.let { context ->
            likeViewModel.gameList.value?.let {
                adapterLikeRecyclerView =
                    LikeRecyclerViewAdapter(
                        context,
                        it
                    )
                with(binding.recyclerViewLike) {
                    adapter = adapterLikeRecyclerView
                    adapterLikeRecyclerView.onItemSelected = { position, item ->
                        val intent = Intent(
                            context,
                            DetailsActivity::class.java
                        )
                        startActivity(intent.apply {
                            putExtra(DetailsActivity.GAME_LIST_PK, item?.id.toString())
                        })
                    }

                    adapterLikeRecyclerView.onRefreshSearchState = {
                        likeViewModel.gameList.value?.let { originList ->
                            reArrangeRecyclerViewForSearch(originList)
                        }
                    }
                    adapterLikeRecyclerView.arrangeRecyclerViewVisibility = { size ->
                        coroutineCallView.launch {
                            if (size != 0) {
                                binding.recyclerViewLike.visibility = View.VISIBLE
                            } else {
                                binding.recyclerViewLike.visibility = View.GONE
                            }
                            likeViewModel.controlUIVisibilities.postValue(true)
                        }

                    }
                    layoutManager =
                        LinearLayoutManager(
                            context,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                }
            }
        }
    }

    private fun gameListChanged(dataChanged: MutableList<GameModels.GameListModelItem?>?) {
        dataChanged?.let {
            adapterLikeRecyclerView.updateDataSource(it)
            if (it.size == 0) {
                binding.recyclerViewLike.visibility = View.GONE
                binding.textViewLikeEmptyList.visibility = View.VISIBLE
            } else {
                binding.textViewLikeEmptyList.visibility = View.GONE
                binding.recyclerViewLike.visibility = View.VISIBLE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        likeViewModel.gatherLikedGameList()
    }

    private fun arrangeSearchView() {
        context?.let {
            val searchEditText: EditText =
                binding.searchViewLike.findViewById(androidx.appcompat.R.id.search_src_text)
            val searchFrame: View =
                binding.searchViewLike.findViewById(androidx.appcompat.R.id.search_plate)
            searchEditText.setHintTextColor(ContextCompat.getColor(it, R.color.orange))
            searchFrame.background.setColorFilter(
                ContextCompat.getColor(it, R.color.orange),
                PorterDuff.Mode.SRC_OVER
            )
        }

        binding.searchViewLike.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(constraint: String?): Boolean {
                adapterLikeRecyclerView.filter.filter(constraint)
                return false
            }
        })
    }

    private fun arrangeUIVisibilities(shouldChangeVisibility: Boolean?) {
        if (shouldChangeVisibility == true && binding.textViewLikeEmptyList.visibility == View.GONE) {
            if (binding.recyclerViewLike.visibility == View.GONE) {
                binding.textViewLikeEmptySearch.visibility = View.VISIBLE
            } else {
                binding.textViewLikeEmptySearch.visibility = View.GONE
            }
        }
    }

    private fun reArrangeRecyclerViewForSearch(originList: MutableList<GameModels.GameListModelItem?>) {
        val originalList = mutableListOf<GameModels.GameListModelItem?>()
        originalList.addAll(originList)
        adapterLikeRecyclerView.updateDataSource(originalList)
    }
}