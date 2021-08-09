package com.kim.deryk.byung.dogviewerapplication.ui.main

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kim.deryk.byung.dogviewerapplication.BundleKeys
import com.kim.deryk.byung.dogviewerapplication.R

class MainViewPagerAdapter (fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int = MainTab.values().size

    override fun createFragment(position: Int): Fragment {
        return BreedListFragment().apply {
            arguments = bundleOf(BundleKeys.MAIN_TAB to MainTab.values()[position])
        }
    }

}


enum class MainTab(val titleRes: Int) {
    ALL(R.string.main_tab_all),
    FAVED(R.string.main_tab_faved)
}