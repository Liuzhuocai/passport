package elf.m.passportsimple.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import elf.m.passportsimple.R
import elf.m.passportsimple.ui.fragment.base.BaseBackFragment

/**

 * author：liuzuo on 19-8-7 11:36

 *

 */
class CountrySelectFragment : BaseBackFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_country_select, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
    private fun initViews() {

/*

        regionPresenter = CountryPresenter(this@CountrySelectActivity, this)
        mCountryAdapter = CountryAdapter(this@CountrySelectActivity, null, this)
        linearLayoutManager = LinearLayoutManager(this@CountrySelectActivity)
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL)
*/


    }
}