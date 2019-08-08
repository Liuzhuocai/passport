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
open class ThirdFragment:BaseBackFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_first_container, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (findChildFragment<WebsiteFragment>(WebsiteFragment::class.java) == null) {
            loadRootFragment(R.id.fl_first_container, WebsiteFragment.newInstance())
        }
    }

    companion object {

        fun newInstance(): ThirdFragment {

            val args = Bundle()

            val fragment = ThirdFragment()
            fragment.arguments = args
            return fragment
        }
    }
}