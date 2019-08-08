package elf.m.passportsimple.ui.fragment.base

import android.content.Context
import elf.m.passportsimple.ui.activity.BaseSupportActivity
import elf.m.passportsimple.ui.wrapper.IPrefs
import elf.m.passportsimple.ui.wrapper.wrapper

/**

 * author：liuzuo on 19-5-23 15:37

 *

 */
abstract class BaseActivity:BaseSupportActivity(),IPrefs {
    private val sp by lazy {
        applicationContext.getSharedPreferences(
            "sp", Context.MODE_PRIVATE
        ).wrapper()
    }

    override fun put(key: String, value: Any) {
        sp.put(key, value)
    }

    override fun <T : Any> get(key: String, defaultValue: T): T = sp.get(key, defaultValue)
}