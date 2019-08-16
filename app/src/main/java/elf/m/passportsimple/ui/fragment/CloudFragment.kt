package elf.m.passportsimple.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import elf.m.passportsimple.R
import elf.m.passportsimple.ui.Config
import elf.m.passportsimple.ui.fragment.base.BaseHomeItemFragment
import kotlinx.android.synthetic.main.fragment_home_cloud.*

/**

 * author：liuzuo on 19-4-29 15:41

 *

 */
open class CloudFragment: BaseHomeItemFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_cloud, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    private fun initView() {
        could_switcher_theme_park.isChecked = get(Config.SYN_THEME_PARK,false)
        could_switcher_note.isChecked = get(Config.SYN_NOTE,false)
        could_switcher_sms.isChecked = get(Config.SYN_SMS,false)
        could_switcher_contacts.isChecked = get(Config.SYN_CONTACTS,false)

        could_switcher_theme_park.setOnCheckedChangeListener { _, isChecked ->
            put(Config.SYN_THEME_PARK,isChecked)
        }
        could_switcher_note.setOnCheckedChangeListener { _, isChecked ->
            put(Config.SYN_NOTE,isChecked)
        }
        could_switcher_sms.setOnCheckedChangeListener { _, isChecked ->
            put(Config.SYN_SMS,isChecked)
        }
        could_switcher_contacts.setOnCheckedChangeListener { _, isChecked ->
            put(Config.SYN_CONTACTS,isChecked)
        }
        could_other_app.setOnClickListener {


        }
    }


    companion object {

        fun newInstance(): CloudFragment {
            val args = Bundle()
            val fragment = CloudFragment()
            fragment.arguments = args
            return fragment
        }
    }

}