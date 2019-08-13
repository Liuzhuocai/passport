package elf.m.passportsimple.ui.fragment.base

import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_my_information.*

/**

 * author：liuzuo on 19-8-2 14:02

 *

 */
abstract class BaseActionBarFragment: BaseBackFragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        id_toolbar.setBackButtonOnClickListener{ onBackPressedSupport()}
    }
}