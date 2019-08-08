package elf.m.passportsimple.ui.fragment.base

import android.content.Context
import elf.m.passportsimple.ui.fragment.NewFirstFragment
import me.yokeyword.fragmentation.SupportFragment

/**

 * author：liuzuo on 19-6-3 12:08

 *

 */
abstract class BaseMainFragment :SupportFragment(){
    private var _mBackToFirstListener: OnBackToFirstListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnBackToFirstListener) {
            _mBackToFirstListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnBackToFirstListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        _mBackToFirstListener = null
    }

    /**
     * 处理回退事件
     *
     * @return
     */
    override fun onBackPressedSupport(): Boolean {
        if (childFragmentManager.backStackEntryCount > 1) {
            popChild()
        } else {
            if (this is NewFirstFragment) {   // 如果是 第一个Fragment 则退出app
                _mActivity.finish()
            } else {                                    // 如果不是,则回到第一个Fragment
                _mBackToFirstListener!!.onBackToFirstFragment()
            }
        }
        return true
    }

    interface OnBackToFirstListener {
        fun onBackToFirstFragment()
    }
}