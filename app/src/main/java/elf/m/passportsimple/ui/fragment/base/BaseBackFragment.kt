package elf.m.passportsimple.ui.fragment.base

import android.content.Context
import elf.m.passportsimple.ui.fragment.LoginFragment
import elf.m.passportsimple.ui.wrapper.IPrefs
import elf.m.passportsimple.ui.wrapper.wrapper
import me.yokeyword.fragmentation.SupportFragment

/**

 * author：liuzuo on 19-5-7 09:08

 *

 */
abstract class BaseBackFragment :SupportFragment(),IPrefs{

    private var _mBackToFirstListener :OnBackToFirstListener? = null
    private val sp by lazy {
        requireContext().getSharedPreferences(
            "sp", Context.MODE_PRIVATE
        ).wrapper()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnBackToFirstListener) {
            this._mBackToFirstListener = context
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
            if (this is LoginFragment) {   // 如果是 第一个Fragment 则退出app
                _mActivity.finish()
            } else {                                    // 如果不是,则回到第一个Fragment
                _mBackToFirstListener?.onBackToFirstFragment()
            }
        }
        return true
    }
    interface OnBackToFirstListener {
        fun onBackToFirstFragment()
    }

    override fun put(key: String, value: Any) {
        sp.put(key, value)
    }

    override fun <T : Any> get(key: String, defaultValue: T): T = sp.get(key, defaultValue)

}