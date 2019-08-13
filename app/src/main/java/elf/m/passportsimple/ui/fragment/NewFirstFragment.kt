package elf.m.passportsimple.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import elf.m.passportsimple.R
import elf.m.passportsimple.ui.fragment.base.BaseBackFragment

/**

 * author：liuzuo on 19-4-29 15:41

 *

 */
open class NewFirstFragment:BaseBackFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_first_container, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (findChildFragment<HomeFragment>(HomeFragment::class.java) == null) {
            loadRootFragment(R.id.fl_first_container, HomeFragment.newInstance())
        }
    }

    companion object {

        fun newInstance(): NewFirstFragment {

            val args = Bundle()

            val fragment = NewFirstFragment()
            fragment.arguments = args
            return fragment
        }
    }
}