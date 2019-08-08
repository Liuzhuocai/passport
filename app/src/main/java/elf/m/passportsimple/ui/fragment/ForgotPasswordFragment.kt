package elf.m.passportsimple.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import elf.m.passportsimple.R
import elf.m.passportsimple.ui.activity.MainActivity
import elf.m.passportsimple.ui.fragment.base.BaseBackFragment
import elf.m.passportsimple.ui.http.RetrofitManager
import elf.m.passportsimple.ui.utils.toast
import kotlinx.android.synthetic.main.fragment_forgot_password.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**

 * author：liuzuo on 19-4-29 15:41

 *

 */
open class ForgotPasswordFragment:BaseBackFragment() {
    lateinit var mTimer : CountDownTimer
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_forgot_password, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ccp.registerPhoneNumberTextView(phone_number_edt)
        send_otp.setOnClickListener {
            attemptRegister()
        }
    }

    private fun attemptRegister() {


        // Reset errors.
        email.error = null
        phone_number_edt.error = null

        // Store values at the time of the login attempt.
        val emailText = email.text.toString()
        val phoneNum = ccp.fullNumberWithPlus.toString()

        var cancel = false
        var focusView: View? = null


        // Check for a valid phone number.
        if (TextUtils.isEmpty(emailText)) {
            email.error = getString(R.string.error_field_required)
            focusView = email
            cancel = true
        } else if (!isEmailValid(emailText)) {
            email.error = getString(R.string.error_invalid_email)
            focusView = email
            cancel = true
        }else if (TextUtils.isEmpty(phoneNum)) {
            phone_number_edt.error = getString(R.string.error_field_required)
            focusView = phone_number_edt
            cancel = true
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView!!.requestFocus()
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            //AnimationUtils.showProgress(true,login_form,login_progress)
            sendOtp(emailText,phoneNum)
        }
    }
    private fun sendOtp(emailText: String, phoneNum: String) {
        //请求

        (send_otp.tag==null).let {
            send_otp.alpha = 0.6f
            send_otp.tag = true
            /*RetrofitManager.userRegistration(
           "",
            "", Pushy.getDeviceCredentials(context).token,
            emailText,phoneNum,"", TDevice.getDeviceId(),
            "china","sz","english","Cai","Lz","2019.6.5",
            "","sz","M","98.41","52.24","5.0",
            "5.0","dz.condor.ThemeParc","condor", "Griffe T9 Plus"
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.Success)
                    enterMainActivity()
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
            })*/
                RetrofitManager.forgotPassword(
                    emailText,
                    phoneNum, ""
                )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("liuzuo99", "onNext=$it")
                    }, {
                        Log.d("liuzuo99", "onError=$it")
                    }, {
                        Log.d("liuzuo99", "onCompleted")
                    })
        //计时

        mTimer = object : CountDownTimer((resources.getInteger(R.integer.resent_otp_interval_time) * 1000).toLong(), 1000) {

            @SuppressLint("DefaultLocale")
            override fun onTick(millisUntilFinished: Long) {
                send_otp.text = String.format(
                    "%s%s%d%s",
                    resources.getString(R.string.register_verify_code_resend), "(", millisUntilFinished / 1000, ")"
                )
            }

            override fun onFinish() {
                send_otp.tag = null
                send_otp.text = resources.getString(R.string.action_sent_otp)
                send_otp.alpha = 1.0f
            }
        }.start()

        }
    }
    private fun enterMainActivity(){
        startActivity(Intent(_mActivity, MainActivity::class.java))
        _mActivity.finish()
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

        fun newInstance(): ForgotPasswordFragment {

            val args = Bundle()

            val fragment = ForgotPasswordFragment()
            fragment.arguments = args
            return fragment
        }
    }
}