package com.david0926.sunrinthon2021.view.onboard

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import java.util.*

class OnBoardViewModel : ViewModel() {
    var fragments: MutableList<Fragment> = ArrayList()
    var currentPage = MutableLiveData(0)
    var pageChangeRequest = MutableLiveData(0)
    val isFirstPage: Boolean
        get() = currentPage.value == 0

    fun previousPage() {
        var value = currentPage.value!!
        if (value > 0) {
            value--
            currentPage.value = value
            pageChangeRequest.value = value
        }
    }

    var pagerCallback: OnPageChangeCallback = object : OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            currentPage.value = position
        }
    }

}