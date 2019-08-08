package elf.m.passportsimple.ui.fragment.account

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import elf.m.passportsimple.R
import elf.m.passportsimple.ui.Config
import elf.m.passportsimple.ui.Config.ACCOUNT_TYPE_CREDIT_TRANSFER
import elf.m.passportsimple.ui.Config.ACCOUNT_TYPE_RECHARGE
import elf.m.passportsimple.ui.Config.ACCOUNT_TYPE_SPENT
import elf.m.passportsimple.ui.Config.ARGUMENT_TITLE
import elf.m.passportsimple.ui.Config.ARGUMENT_TYPE
import elf.m.passportsimple.ui.adapter.AccountCreditTransferAdapter
import elf.m.passportsimple.ui.adapter.AccountRechargeAdapter
import elf.m.passportsimple.ui.adapter.AccountSpentAdapter
import elf.m.passportsimple.ui.fragment.base.BaseBackFragment
import elf.m.passportsimple.ui.http.RetrofitManager
import kotlinx.android.synthetic.main.fragment_pager_account.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**

 * author：liuzuo on 19-7-23 10:20

 *

 */
class AccountFirstPagerFragment : BaseBackFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pager_account, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        when(arguments?.get(ARGUMENT_TYPE)){
            ACCOUNT_TYPE_SPENT -> {

                val accountSpentAdapter = AccountSpentAdapter(_mActivity)
                pager_recy.adapter = accountSpentAdapter
                getSpentList(accountSpentAdapter)

            }
            ACCOUNT_TYPE_CREDIT_TRANSFER ->  {

                val accountCreditTransferAdapter = AccountCreditTransferAdapter(_mActivity)
                pager_recy.adapter = accountCreditTransferAdapter
                getCreditTransferList(accountCreditTransferAdapter)
            }
            ACCOUNT_TYPE_RECHARGE ->  {
                val accountRechargeAdapter = AccountRechargeAdapter(_mActivity)
                pager_recy.adapter = accountRechargeAdapter
                getRechargeList(accountRechargeAdapter)

            }
        }
        pager_recy.layoutManager = LinearLayoutManager(_mActivity)
        //AnimationUtils.showProgress(true, pager_recy, login_progress)
    }


    private fun getRechargeList(adapter: AccountRechargeAdapter) {
        RetrofitManager.rechargelist(get(Config.USER_ID,""),"0",get(Config.JWTTOKEN,""))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.Success) {
                    Log.d("liuzuo99", "onNext=$it")
                    adapter.setData(it.Result)
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

    private fun getCreditTransferList(adapter: AccountCreditTransferAdapter) {
        RetrofitManager.spentList(get(Config.USER_ID,""),"0",get(Config.JWTTOKEN,""))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.Success) {
                    Log.d("liuzuo99", "onNext=$it")
                    adapter.setData(it.Result)
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

    private fun getSpentList(adapter: AccountSpentAdapter) {

        RetrofitManager.spentList(get(Config.USER_ID,""),"0",get(Config.JWTTOKEN,""))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.Success) {
                    Log.d("liuzuo99", "onNext=$it")
                    adapter.setData(it.Result)
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

        fun newInstance(title:String,type:Int): AccountFirstPagerFragment {
            val args = Bundle()
            val fragment = AccountFirstPagerFragment()
            args.putString(ARGUMENT_TITLE,title)
            args.putInt(ARGUMENT_TYPE,type)
            fragment.arguments = args
            return fragment
        }
    }
}

