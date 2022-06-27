package com.thevarungupta.grocery_app.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.thevarungupta.grocery_app.fragments.ProductFragment
import com.thevarungupta.grocery_app.models.SubCategory

class MyFragmentAdapter(fm: FragmentManager):FragmentPagerAdapter(fm) {

    var mFragmentList: ArrayList<Fragment> = ArrayList()
    var mTitleList: ArrayList<String> = ArrayList();

    override fun getCount(): Int {
        return mTitleList.size
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitleList[position]
    }

    fun addFragment(subCategory: SubCategory){
        mFragmentList.add(ProductFragment.newInstance(subCategory.subId))
        mTitleList.add(subCategory.subName)
    }
}