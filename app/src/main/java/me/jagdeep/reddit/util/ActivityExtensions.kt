package me.jagdeep.reddit.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import me.jagdeep.reddit.inject.ViewModelFactory

inline fun <reified VM : ViewModel> FragmentActivity.activityViewModel(
    viewModelFactory: ViewModelFactory
): VM {
    return ViewModelProviders.of(this, viewModelFactory).get(VM::class.java)
}

inline fun <reified VM : ViewModel> Fragment.fragmentViewModel(
    viewModelFactory: ViewModelFactory
): VM {
    return ViewModelProviders.of(this, viewModelFactory).get(VM::class.java)
}
