package elf.m.passportsimple.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import elf.m.passportsimple.R
import elf.m.passportsimple.ui.adapter.HomeItemAdapter
import elf.m.passportsimple.ui.bean.HomeInfo
import elf.m.passportsimple.ui.fragment.base.BaseBackFragment
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

/**

 * author：liuzuo on 19-4-29 15:41

 *

 */
open class HomeFragment:BaseBackFragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()

        super.onActivityCreated(savedInstanceState)
    }

    private fun initView() {
        val arrayList = ArrayList<HomeInfo>()
        home_recycleview.layoutManager = GridLayoutManager(context,2)
        val array = resources.getStringArray(R.array.home_item)

        for (i in array.indices){
                when(i){
                    ACCOUNT_OPEN_TYPE ->  arrayList.add(HomeInfo(array[i],AccountFragment.newInstance(array[i])))
                    RECHARGE_OPEN_TYPE ->  arrayList.add(HomeInfo(array[i],RechargeFragment.newInstance(array[i])))
                    TRANSFER_ACCOUNTS_OPEN_TYPE ->  arrayList.add(HomeInfo(array[i],CreditTransferFragment.newInstance(array[i])))
                    AFTER_SALE_OPEN_TYPE ->  arrayList.add(HomeInfo(array[i],AccountFragment.newInstance(array[i])))
                    SCAN_QR_OPEN_TYPE ->  arrayList.add(HomeInfo(array[i],AccountFragment.newInstance(array[i])))
                    MY_QR_OPEN_TYPE ->  arrayList.add(HomeInfo(array[i],AccountFragment.newInstance(array[i])))
                }

        }
        home_recycleview.adapter = HomeItemAdapter(arrayList) {
            start(it.type)
        }
    }

    companion object {

        fun newInstance(): HomeFragment {

            val args = Bundle()

            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }

        const val ACCOUNT_OPEN_TYPE :Int = 0
        const val RECHARGE_OPEN_TYPE :Int = 1
        const val TRANSFER_ACCOUNTS_OPEN_TYPE :Int = 2
        const val AFTER_SALE_OPEN_TYPE :Int = 3
        const val SCAN_QR_OPEN_TYPE :Int = 4
        const val MY_QR_OPEN_TYPE :Int = 5
    }
}