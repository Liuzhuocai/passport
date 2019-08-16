package elf.m.passportsimple.ui.fragment

import android.Manifest
import android.accounts.Account
import android.accounts.AccountManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import elf.m.passportsimple.R
import elf.m.passportsimple.ui.Config
import elf.m.passportsimple.ui.Config.ACCOUNT_TYPE
import elf.m.passportsimple.ui.Config.PARAM_USER_PASS
import elf.m.passportsimple.ui.activity.MainActivity
import elf.m.passportsimple.ui.fragment.base.BaseBackFragment
import elf.m.passportsimple.ui.http.RetrofitManager
import elf.m.passportsimple.ui.http.data.result.LoginResultData
import elf.m.passportsimple.ui.utils.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.email_login_form
import kotlinx.android.synthetic.main.fragment_login.email_sign_in_button
import kotlinx.android.synthetic.main.fragment_login.forget_password
import kotlinx.android.synthetic.main.fragment_login.login_progress
import kotlinx.android.synthetic.main.fragment_login.password_edt
import kotlinx.android.synthetic.main.fragment_login.sign_up
import kotlinx.android.synthetic.main.fragment_login.switch_login_type
import kotlinx.android.synthetic.main.fragment_login_phone.*
import me.pushy.sdk.Pushy
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**

 * author：liuzuo on 19-4-29 15:41

 *

 */
open class LoginFragment : BaseBackFragment() {
    private var mOpenType: Int = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mOpenType = arguments?.get(APP_OPEN_TYPE) as Int
        Log.d("liuzuo22", "mOpenType=$mOpenType")
        return if (mOpenType == APP_OPEN_TYPE_EMAIL) {
            inflater.inflate(R.layout.fragment_login, container, false)
        } else {
            inflater.inflate(R.layout.fragment_login_phone, container, false)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d("liuzuo22", "mOpenType2=$mOpenType")
        if (!isEmailType()) {
            ccp_login.registerPhoneNumberTextView(phone_number_edt)
        }
        Pushy.listen(_mActivity)
        if (ContextCompat.checkSelfPermission(
                _mActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                _mActivity,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                0
            )
        }
        initView()
    }

    private fun initView() {
        // Set up the login form.
        populateAutoComplete()

        password_edt.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })

        email_sign_in_button.setOnClickListener { attemptLogin() }
        sign_up.setOnClickListener { startRegisterFragment() }
        forget_password.setOnClickListener { startForgotPasswordFragment() }
        switch_login_type.setOnClickListener { switchLogin() }
    }

    private fun switchLogin() {
        if (isEmailType()) {
            start(LoginFragment.newInstance(APP_OPEN_TYPE_PHONE))
        } else {
            pop()
        }
    }

    private fun populateAutoComplete() {
        if (!mayRequestContacts()) {
            return
        }

    }

    private fun attemptLogin() {

        /* Pushy.getDeviceCredentials(context).token?.let {
             RetrofitManager.loginByPassword(
                 it,phone.text.trim().toString()*//*"137768480@qq.com"*//*,
                *//*"+8613410365214"*//*"",password.text.toString()*//*"123456"*//*,*//*"355431100001179,355431100001187"*//*TDevice.getDeviceId()
                ,"dz.condor.ThemeParc",*//*"Griffe T9 Plus"*//*Build.MODEL,"condor")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("liuzuo99", "onNext=$it")
                    put(Config.JWTTOKEN,it.Result.jwttoken)
                    put(Config.JWTREFRESHTOKEN,it.Result.jwtrefreshtoken)
                    //AccountManager.get(applicationContext).addAccountExplicitly(Account("passport","all"),get(Config.JWTTOKEN,""),Bundle())
                },{
                    Log.d("liuzuo99", "onError=$it")
                },{
                    Log.d("liuzuo99","onCompleted")
                })
        }*/

        //ThemePark
        /* Pushy.getDeviceCredentials(context).token?.let {
             RetrofitManager.loginByPassword(
                 it,phone.text.trim().toString(),
                 "+8615980904762",password.text.toString(),TDevice.getDeviceId()
                 ,"dz.condor.ThemeParc","Griffe T9 Plus","condor")
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe({
                     Log.d("liuzuo99", "onNext=$it")
                     put(Config.JWTTOKEN,it.Result.jwttoken)
                     put(Config.JWTREFRESHTOKEN,it.Result.jwtrefreshtoken)
                     //AccountManager.get(applicationContext).addAccountExplicitly(Account("passport","all"),get(Config.JWTTOKEN,""),Bundle())
                 },{
                     Log.d("liuzuo99", "onError=$it")
                 },{
                     Log.d("liuzuo99","onCompleted")
                 })
         }*/
        /* RetrofitManager.getToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjo0MCwiaWF0IjoxNTU4NTc5Nzg2LCJleHAiOjE1NTg1OTc3ODZ9.hqqHS-AO4VJXRSzzAEit8XrVuUGk8nBUbX-9pRHomG4"
         ,"40"

         ).subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe({
                 Log.d("liuzuo99", "onNext=$it")
             },{

                 Log.d("liuzuo99", "onError=$it")

             },{
                 Log.d("liuzuo99","onCompleted")
             })


 */


        /*RetrofitManager.changePassword(
            "xp12345678","xp1234567","40","1",token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("liuzuo99", "onNext=$it")
            },{
                Log.d("liuzuo99", "onError=$it")
            },{
                Log.d("liuzuo99","onCompleted")
            })*/
        // Reset errors.
        if (isEmailType()) {
            email.error = null
        } else {
            phone_number_edt.error = null
        }
        password_edt.error = null

        // Store values at the time of the login attempt.
        val accountStr = if (isEmailType()) {
            email.text.toString()
        } else {
            ccp_login.fullNumberWithPlus.toString()
        }
        val passwordStr = password_edt.text.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(passwordStr) && !isPasswordValid(passwordStr)) {
            password_edt.error = getString(R.string.error_invalid_password)
            focusView = password_edt
            cancel = true
        }

        // Check for a valid phone address.
        if (isEmailType()) {
            if (TextUtils.isEmpty(accountStr)) {
                email.error = getString(R.string.error_field_required)
                focusView = email
                cancel = true
            } else if (!isEmailValid(accountStr)) {
                email.error = getString(R.string.error_invalid_email)
                focusView = email
                cancel = true
            }
        } else {
            if (TextUtils.isEmpty(accountStr)) {
                phone_number_edt.error = getString(R.string.error_field_required)
                focusView = phone_number_edt
                cancel = true
            } else if (!isPhoneValid(accountStr)) {
                phone_number_edt.error = getString(R.string.error_invalid_phone)
                focusView = phone_number_edt
                cancel = true
            }
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView?.requestFocus()
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            AnimationUtils.showProgress(true, email_login_form, login_progress)
            Pushy.getDeviceCredentials(context).token?.let {
                RetrofitManager.loginByPassword(
                    it, if (isEmailType()) {
                        email.text.trim().toString()
                    } else {
                        null
                    }/*"137768480@qq.com"*/, if (isEmailType()) {
                        null
                    } else {
                        phone_number_edt.text.trim().toString()
                    },
                    password_edt.text.toString()/*"123456"*/,/*"355431100001179,355431100001187"*/TDevice.getDeviceId()
                    , "dz.condor.ThemeParc",/*"Griffe T9 Plus"*/Build.MODEL, "condor"
                )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("liuzuo99", "onNext=$it")

                        //AccountManager.get(applicationContext).addAccountExplicitly(Account("passport","all"),get(Config.JWTTOKEN,""),Bundle())
                        if (it.Success) {
                            put(Config.JWTTOKEN, it.Result.jwttoken)
                            put(Config.JWTREFRESHTOKEN, it.Result.jwtrefreshtoken)
                            put(Config.USER_ID, it.Result.user_id)
                            put(Config.SP_PHONE, it.Result.phone)

                            saveAccountInSystem(it.Result,passwordStr)
                            startActivity(Intent(_mActivity, MainActivity::class.java))
                            _mActivity.finish()
                        }
                        AnimationUtils.showProgress(false, email_login_form, login_progress)
                    }, {
                        Log.d("liuzuo99", "onError=${it.suppressed}")
                        Log.d("liuzuo99", "onError=${it.stackTrace}")
                        Log.d("liuzuo99", "onError=${it.toString()}")
                        AnimationUtils.showProgress(false, email_login_form, login_progress)
                    }, {
                        Log.d("liuzuo99", "onCompleted")
                        AnimationUtils.showProgress(false, email_login_form, login_progress)
                    })
            }
        }
    }

    private fun saveAccountInSystem(
        result: LoginResultData,
        passwordStr: String
    ) {
        val am = AccountManager.get(_mActivity)
        val intent = Intent()
        intent.putExtra(AccountManager.KEY_ACCOUNT_NAME, result.phone)
        intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, ACCOUNT_TYPE)
        intent.putExtra(AccountManager.KEY_AUTHTOKEN, result.jwttoken)
        intent.putExtra(PARAM_USER_PASS, passwordStr)

        val accountName = intent.getStringExtra(AccountManager.KEY_ACCOUNT_NAME)
        val accountPassword = intent.getStringExtra(PARAM_USER_PASS)
        val account = Account(accountName, intent.getStringExtra(AccountManager.KEY_ACCOUNT_TYPE))
//        if (_mActivity.intent.getBooleanExtra(ARG_IS_ADDING_NEW_ACCOUNT, false)) {
            val authtoken = intent.getStringExtra(AccountManager.KEY_AUTHTOKEN)
            val authtokenType = ACCOUNT_TYPE
            // Creating the account on the device and setting the auth token we got
            // (Not setting the auth token will cause another call to the server to authenticate the user)
            am.addAccountExplicitly(account, accountPassword, null)
            am.setAuthToken(account, authtokenType, authtoken)
//        } else {
            am.setPassword(account, accountPassword)
//        }
//        setAccountAuthenticatorResult(intent.extras)
//        setResult(ISupportFragment.RESULT_OK, intent)
    }

    private fun startRegisterFragment() {
        start(RegisterFragment.newInstance())
    }

    private fun startForgotPasswordFragment() {
        start(ForgotPasswordFragment.newInstance())
    }

    private fun mayRequestContacts(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }
        if (_mActivity.checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true
        }
        if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
            Snackbar.make(email, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                .setAction(
                    android.R.string.ok
                ) { requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_READ_CONTACTS) }
        } else {
            requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_READ_CONTACTS)
        }
        return false
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete()
            }
        }
    }

    private fun isEmailType(): Boolean {
        return mOpenType == APP_OPEN_TYPE_EMAIL
    }


    object ProfileQuery {
        val PROJECTION = arrayOf(
            ContactsContract.CommonDataKinds.Email.ADDRESS,
            ContactsContract.CommonDataKinds.Email.IS_PRIMARY
        )
        val ADDRESS = 0
        val IS_PRIMARY = 1
    }

    companion object {

        fun newInstance(type: Int): LoginFragment {

            val args = Bundle()
            args.putInt(APP_OPEN_TYPE, type)
            val fragment = LoginFragment()
            fragment.arguments = args
            return fragment
        }

        /**
         * Id to identity READ_CONTACTS permission request.
         */
        private val REQUEST_READ_CONTACTS = 0
        private val APP_OPEN_TYPE = "app_open_type"

        val APP_OPEN_TYPE_EMAIL = 1
        val APP_OPEN_TYPE_PHONE = 2

        /**
         * A dummy authentication store containing known user names and passwords.
         * TODO: remove after connecting to a real authentication system.
         */
        private val DUMMY_CREDENTIALS = arrayOf("foo@example.com:hello", "bar@example.com:world")
    }
}