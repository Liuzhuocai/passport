package elf.m.passportsimple.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import elf.m.passportsimple.R
import elf.m.passportsimple.ui.Config
import elf.m.passportsimple.ui.fragment.base.BaseHomeItemFragment
import elf.m.passportsimple.ui.http.RetrofitManager
import elf.m.passportsimple.ui.utils.isPasswordValid
import elf.m.passportsimple.ui.utils.isPhoneValid
import elf.m.passportsimple.ui.utils.toast
import kotlinx.android.synthetic.main.fragment_credit_transfer.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**

 * author：liuzuo on 19-4-29 15:41

 *

 */
open class CreditTransferFragment: BaseHomeItemFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_credit_transfer, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ccp_login.registerPhoneNumberTextView(phone_number_edt)
        initAccount()
        commit_btn.setOnClickListener {
            codeCommit()
        }
    }

    private fun codeCommit() {
    var phone = ccp_login.fullNumberWithPlus
    var amount = exact_account.text.trim().toString()
    var password = password_edt.text.trim().toString()
    var note = note.text.trim().toString()
        password_edt.error = null
        exact_account.error = null
        phone_number_edt.error = null
        if(isPasswordValid(password)){
            password_edt.error = getString(R.string.error_invalid_password)
        }else if(!TextUtils.isEmpty(amount)){
            exact_account.error = getString(R.string.error_incorrect_account)
        }else if(isPhoneValid(phone)){
            phone_number_edt.error = getString(R.string.error_invalid_phone)
        }else{
            RetrofitManager.creditTransfer(get(Config.USER_ID,""),"english",amount,phone,null,password,note,get(Config.JWTTOKEN,""))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.Success) {

                        Log.d("liuzuo99", "onNext=$it")
                        toast(R.string.transfer_completed)
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

        fun newInstance(title:String): CreditTransferFragment {
            val args = Bundle()
            val fragment = CreditTransferFragment()
            args.putString("title",title)
            fragment.arguments = args
            return fragment
        }
    }


}