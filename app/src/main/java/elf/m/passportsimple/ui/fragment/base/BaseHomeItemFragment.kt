package elf.m.passportsimple.ui.fragment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import elf.m.passportsimple.R

/**

 * author：liuzuo on 19-4-29 15:41

 *

 */
open class BaseHomeItemFragment:BaseBackFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_first_container, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


    override fun onBackPressedSupport(): Boolean {
        pop()
        return true
    }

}