package com.example.videogamesapplication.ui.details

import android.os.Bundle
import android.text.Html
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.videogamesapplication.R
import com.example.videogamesapplication.databinding.ActivityDetailsBinding
import com.example.videogamesapplication.model.GameModels
import com.example.videogamesapplication.ui.BaseActivity
import com.example.videogamesapplication.utils.observe
import com.example.videogamesapplication.utils.toGameListModelItem
import com.example.videogamesapplication.utils.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DetailsActivity : BaseActivity() {

    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        detailsViewModel = viewModel(viewModelFactory) {
            observe(gameListItemData, ::gameListItemChanged)
            observe(isBackButtonClicked, ::backButtonClickAction)
            observe(likeButtonClicked, ::likeButtonClickAction)
            observe(gameListPk, ::gameListPkGathered)
        }
        binding.lifecycleOwner = this
        binding.viewModel = detailsViewModel
        intent.getStringExtra(GAME_LIST_PK)?.let {
            detailsViewModel.gameListPk.postValue(it)
        }
        arrangeUI()

    }

    private fun arrangeUI() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = ""
    }

    private fun gameListItemChanged(dataChanged: GameModels.GameListModelItemDetails?) {
        dataChanged?.let {
            Glide.with(this)
                .load(it.background_image)
                .placeholder(ContextCompat.getDrawable(this, R.drawable.ic_video_game_placeholder))
                .error(ContextCompat.getDrawable(this, R.drawable.ic_video_game_placeholder))
                .into(binding.imageViewDetails)
            binding.itemScrolling.textViewGameListItemDetailsTitle.text = it.name ?: ""
            binding.itemScrolling.textViewGameListItemDetailsReleaseDate.text = it.released ?: ""
            it.metacritic?.let { metacritic ->
                binding.itemScrolling.textViewGameListItemDetailsMetacriticDate.text =
                    metacritic.toString()
            }
            it.description?.let { desc ->
                if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                    binding.itemScrolling.textViewGameListItemDetailsDescription.setText(
                        Html.fromHtml(
                            desc
                        ), TextView.BufferType.SPANNABLE
                    )
                } else {
                    binding.itemScrolling.textViewGameListItemDetailsDescription.setText(
                        Html.fromHtml(
                            desc,
                            Html.FROM_HTML_MODE_LEGACY
                        ), TextView.BufferType.SPANNABLE
                    )
                }
            }
        }
    }

    private fun backButtonClickAction(isBackButtonClicked: Boolean?) {
        if (isBackButtonClicked == true) {
            finish()
        }
    }

    private fun likeButtonClickAction(likeButtonClicked: Boolean?) {
        try {
            if (likeButtonClicked == true) {
                val coroutineCallLikeAction = CoroutineScope(Dispatchers.IO)
                coroutineCallLikeAction.async {
                    detailsViewModel.controlGameStateFromDb()?.let {
                        detailsViewModel.removeLikedGamesFromDb(it)
                        coroutineCallLikeAction.launch {
                            arrangeThumbsUp(false)
                        }
                    } ?: run {
                        detailsViewModel.gameListItemData.value?.let { itemDetails ->
                            itemDetails.id?.let {
                                detailsViewModel.insertLikedGamesToDb(
                                    GameModels.GameListModelItemDetails()
                                        .toGameListModelItem(itemDetails)
                                )
                                coroutineCallLikeAction.launch {
                                    arrangeThumbsUp(true)
                                }
                            } ?: runOnUiThread {
                                Toast.makeText(
                                    this@DetailsActivity,
                                    this@DetailsActivity.resources.getString(R.string.offine_mode_like_error),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun arrangeThumbsUp(controlState: Boolean) {
        if (controlState) {
            binding.imageViewThumbsUp.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_video_game_thumb_up_teal
                )
            )
        } else {
            binding.imageViewThumbsUp.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_video_game_thumb_up_white
                )
            )
        }
    }

    private fun gameListPkGathered(gamePk: String?) {
        if (!gamePk.isNullOrEmpty()) {
            detailsViewModel.gatherGameListItemDetails()
            val coroutineCallThumbsUpState = CoroutineScope(Dispatchers.IO)
            coroutineCallThumbsUpState.async {
                detailsViewModel.controlGameStateFromDb()?.let {
                    coroutineCallThumbsUpState.launch {
                        arrangeThumbsUp(true)
                    }
                }
            }
        }
    }

    companion object {
        const val GAME_LIST_PK = "GameListPk"
    }
}