package elf.m.passportsimple.ui.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import elf.m.passportsimple.R
import elf.m.passportsimple.ui.Config
import elf.m.passportsimple.ui.fragment.base.BaseHomeItemFragment
import elf.m.passportsimple.ui.http.RetrofitManager
import elf.m.passportsimple.ui.utils.TDevice
import elf.m.passportsimple.ui.utils.isPasswordValid
import elf.m.passportsimple.ui.utils.toast
import kotlinx.android.synthetic.main.fragment_recharge.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**

 * author：liuzuo on 19-4-29 15:41

 *

 */
open class RechargeFragment: BaseHomeItemFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recharge, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAccount()
        commit_btn.setOnClickListener {
            codeCommit()
        }
    }

    private fun codeCommit() {
    var code = recharge_code.text.trim().toString()
        if (isPasswordValid(code)){
            RetrofitManager.recharge(get(Config.USER_ID,""),"condor",code,Build.MODEL,TDevice.getDeviceId(),get(Config.JWTTOKEN,""))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.Success) {

                        Log.d("liuzuo99", "onNext=$it")
                        toast(R.string.recharge_completed)
                        pop()
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
        }else{
            toast(R.string.recharge_code_error)
        }

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

        fun newInstance(title:String): RechargeFragment {
            val args = Bundle()
            val fragment = RechargeFragment()
            args.putString("title",title)
            fragment.arguments = args
            return fragment
        }
    }


}