package com.example.videogamesapplication.utils

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.observe
import androidx.viewbinding.ViewBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.videogamesapplication.model.GameModels
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }

fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)

class FragmentViewBindingDelegate<T : ViewBinding>(
    val fragment: Fragment,
    val viewBindingFactory: (View) -> T
) : ReadOnlyProperty<Fragment, T> {
    private var binding: T? = null

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
                    viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                        override fun onDestroy(owner: LifecycleOwner) {
                            binding = null
                        }
                    })
                }
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val binding = binding
        if (binding != null) {
            return binding
        }

        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            throw IllegalStateException("Should not attempt to get bindings when Fragment views are destroyed.")
        }

        return viewBindingFactory(thisRef.requireView()).also { this.binding = it }
    }
}

inline fun <reified T : ViewModel> Fragment.viewModel(
    factory: ViewModelProvider.Factory,
    body: T.() -> Unit
): T {
//    ViewModelProvider(this).get(T::class.java)
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    vm.body()
    return vm
}

inline fun <reified T : ViewModel> AppCompatActivity.viewModel(
    factory: ViewModelProvider.Factory,
    body: T.() -> Unit
): T {
//    ViewModelProvider(this).get(T::class.java)
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    vm.body()
    return vm
}

fun GameModels.GameListModelItemDetails.toGameListModelItem(gameListModelItemDetails: GameModels.GameListModelItemDetails) =
    GameModels.GameListModelItem(
        gameListModelItemDetails.pk,
        gameListModelItemDetails.id,
        gameListModelItemDetails.slug,
        gameListModelItemDetails.name,
        gameListModelItemDetails.name_original,
        gameListModelItemDetails.description,
        gameListModelItemDetails.metacritic,
        gameListModelItemDetails.metacritic_platforms,
        gameListModelItemDetails.released,
        gameListModelItemDetails.tba,
        gameListModelItemDetails.updated,
        gameListModelItemDetails.background_image,
        gameListModelItemDetails.background_image_additional,
        gameListModelItemDetails.website,
        gameListModelItemDetails.rating,
        gameListModelItemDetails.rating_top,
        gameListModelItemDetails.ratings,
        gameListModelItemDetails.reactions,
        gameListModelItemDetails.added,
        gameListModelItemDetails.added_by_status,
        gameListModelItemDetails.playtime,
        gameListModelItemDetails.screenshots_count,
        gameListModelItemDetails.movies_count,
        gameListModelItemDetails.creators_count,
        gameListModelItemDetails.achievements_count,
        gameListModelItemDetails.parent_achievements_count,
        gameListModelItemDetails.reddit_url,
        gameListModelItemDetails.reddit_name,
        gameListModelItemDetails.reddit_description,
        gameListModelItemDetails.reddit_logo,
        gameListModelItemDetails.reddit_count,
        gameListModelItemDetails.twitch_count,
        gameListModelItemDetails.youtube_count,
        gameListModelItemDetails.reviews_text_count,
        gameListModelItemDetails.ratings_count,
        gameListModelItemDetails.suggestion_count,
        gameListModelItemDetails.alternative_names,
        gameListModelItemDetails.metacritic_url,
        gameListModelItemDetails.parents_count,
        gameListModelItemDetails.additions_count,
        gameListModelItemDetails.game_series_count,
        gameListModelItemDetails.user_game,
        gameListModelItemDetails.reviews_count,
        gameListModelItemDetails.saturated_color,
        gameListModelItemDetails.dominant_color,
        gameListModelItemDetails.parent_platforms,
        gameListModelItemDetails.platforms,
        gameListModelItemDetails.stores,
        gameListModelItemDetails.developers,
        gameListModelItemDetails.genres,
        gameListModelItemDetails.tags,
        gameListModelItemDetails.publishers,
        gameListModelItemDetails.esrb_rating,
        gameListModelItemDetails.clip,
        gameListModelItemDetails.description_raw
    )
