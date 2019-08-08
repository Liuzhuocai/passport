package elf.m.passportsimple.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import elf.m.passportsimple.ui.fragment.account.AccountFirstPagerFragment

/**

 * author：liuzuo on 19-7-22 17:10

 *

 */

class HomePagerAdapter(fm: FragmentManager, private vararg val titles: String) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
            return AccountFirstPagerFragment.newInstance(titles[position],position)
    }
    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}