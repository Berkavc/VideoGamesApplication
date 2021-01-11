package com.example.videogamesapplication.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.videogamesapplication.ui.di.components.DaggerFragmentComponent
import com.example.videogamesapplication.ui.di.components.FragmentComponent
import com.example.videogamesapplication.ui.di.modules.FragmentModule
import javax.inject.Inject

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var mFragmentComponent: FragmentComponent? = null
    protected fun fragmentComponent(): FragmentComponent {
        if (mFragmentComponent == null) {
            mFragmentComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule(this))
                .activityComponent((activity as BaseActivity).activityComponent())
                .build()
        }

        return mFragmentComponent as FragmentComponent
    }
}