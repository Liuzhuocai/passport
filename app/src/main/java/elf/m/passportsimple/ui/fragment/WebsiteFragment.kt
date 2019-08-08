package elf.m.passportsimple.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.just.agentweb.AgentWeb
import elf.m.passportsimple.R
import elf.m.passportsimple.ui.fragment.base.BaseBackFragment
import kotlinx.android.synthetic.main.fragment_web_container.*


/**

 * author：liuzuo on 19-4-29 15:41

 *

 */
open class WebsiteFragment:BaseBackFragment() {
 private lateinit var mAgentWeb :AgentWeb

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_web_container, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(fl_web_container as LinearLayout, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .createAgentWeb()
            .ready()
            .go("http://www.condor.dz")
    }

    companion object {

        fun newInstance(): WebsiteFragment {

            val args = Bundle()

            val fragment = WebsiteFragment()
            fragment.arguments = args
            return fragment
        }
    }
}