package elf.m.passportsimple.ui.fragment

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import elf.m.passportsimple.R
import elf.m.passportsimple.ui.fragment.base.BaseBackFragment
import elf.m.passportsimple.ui.http.RetrofitManager
import elf.m.passportsimple.ui.utils.AnimationUtils
import elf.m.passportsimple.ui.utils.TDevice
import elf.m.passportsimple.ui.utils.toast
import kotlinx.android.synthetic.main.fragment_register.*
import me.pushy.sdk.Pushy
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
/**

 * author：liuzuo on 19-4-29 15:41

 *

 */
open class RegisterFragment:BaseBackFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ccp.registerPhoneNumberTextView(phone_number_edt)
        register_button.setOnClickListener {
            attemptRegister()
        }
    }

    private fun attemptRegister() {


        // Reset errors.
        email.error = null
        password_edt.error = null
        phone_number_edt.error = null

        // Store values at the time of the login attempt.
        val emailText = email.text.toString()
        val passwordText = password_edt.text.toString()
        val passwordTextVerify = verify_password.text.toString()
        val phoneNum = ccp.fullNumberWithPlus.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(passwordText) && !isPasswordValid(passwordText)) {
            password_edt.error = getString(R.string.error_invalid_password)
            focusView = password_edt
            cancel = true
        }

        // Check for a valid phone number.
        if (TextUtils.isEmpty(emailText)) {
            email.error = getString(R.string.error_field_required)
            focusView = email
            cancel = true
        } else if (!isEmailValid(emailText)) {
            email.error = getString(R.string.error_invalid_email)
            focusView = email
            cancel = true
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(phoneNum)) {
            phone_number_edt.error = getString(R.string.error_field_required)
            focusView = phone_number_edt
            cancel = true
        } else if (!isPasswordValid(emailText)) {
            email.error = getString(R.string.error_invalid_phone)
            focusView = password_edt
            cancel = true
        }else if (passwordTextVerify == passwordText) {
            email.error = getString(R.string.error_incorrect_password)
            focusView = verify_password
            cancel = true
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView!!.requestFocus()
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            AnimationUtils.showProgress(true,login_form,login_progress)
        }
        userRegistration(emailText,passwordText,phoneNum)
    }
    private fun userRegistration(emailText: String, passwordText: String, phoneNum: String) {
        RetrofitManager.userRegistration(
           "",
            "", Pushy.getDeviceCredentials(context).token,
            emailText,phoneNum,passwordText, TDevice.getDeviceId(),
            ccp.defaultCountryNameCode ,null,"english",null,null,null,
            null,null,null,null,null,null,
            null,"dz.condor.ThemeParc","condor", Build.MODEL
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.Success)
                    enterLoginActivity()
                else
                    registerError(it.Message)

                AnimationUtils.showProgress(false,login_form,login_progress)

                Log.d("liuzuo99", "onNext=$it")
            }, {
                it.message?.let { it1 -> registerError(it1) }
                AnimationUtils.showProgress(false,login_form,login_progress)
                Log.d("liuzuo99", "onError=$it")
            }, {
                Log.d("liuzuo99", "onCompleted")
            })

    }
    private fun enterLoginActivity(){
        /*startActivity(Intent(_mActivity, MainActivity::class.java))
        _mActivity.finish()*/
        toast(R.string.msg_please_login_in)
        pop()
    }
    private fun registerError(msg :String){
        toast(msg)
    }
    private fun isEmailValid(email: String): Boolean {
        return email.contains("@")
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
    companion object {

        fun newInstance(): RegisterFragment {

            val args = Bundle()

            val fragment = RegisterFragment()
            fragment.arguments = args
            return fragment
        }
    }
}