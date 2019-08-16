package elf.m.passportsimple.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import elf.m.passportsimple.R
import elf.m.passportsimple.ui.Config
import elf.m.passportsimple.ui.adapter.HomePagerAdapter
import elf.m.passportsimple.ui.fragment.base.BaseHomeItemFragment
import elf.m.passportsimple.ui.http.RetrofitManager
import kotlinx.android.synthetic.main.fragment_home_account.*
import kotlinx.android.synthetic.main.fragment_home_account.view.*
import kotlinx.android.synthetic.main.toolbar.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**

 * author：liuzuo on 19-4-29 15:41

 *

 */
open class AccountFragment: BaseHomeItemFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home_account, container, false)
        initTabLayout(view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar_normal.title = arguments?.getString("title")
        toolbar_normal.setBackButtonOnClickListener {
            onBackPressedSupport()
        }
        initAccount()
    }

    private fun initAccount() {
        RetrofitManager.checkBalance(get(Config.USER_ID,""),get(Config.JWTTOKEN,""))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({


                if (it.Success) {
                    Log.d("liuzuo99", "onNext=$it")
                    account_text.text =(it.Result.current_balance.toString()+"CP")
                }else{

                }
                //AnimationUtils.showProgress(false, pager_recy, login_progress)
            }, {
                Log.d("liuzuo99", "checkJwttoken onError=$it")
                //enterLoginActivity()
                // AnimationUtils.showProgress(false, pager_recy, login_progress)
            }, {
                Log.d("liuzuo99", "onCompleted")

            })
    }

    companion object {

        fun newInstance(title:String): AccountFragment {
            val args = Bundle()
            val fragment = AccountFragment()
            args.putString("title",title)
            fragment.arguments = args
            return fragment
        }
    }
    private fun initTabLayout(view: View) {
        val tab = view.tab
        tab.addTab(tab.newTab())
        tab.addTab(tab.newTab())

        view.viewPager.adapter = HomePagerAdapter(
            childFragmentManager,
            getString(R.string.spent), getString(R.string.creditTransfer), getString(R.string.recharge)
        )
        tab.setupWithViewPager(view.viewPager)
    }

}