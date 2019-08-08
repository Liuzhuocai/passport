package elf.m.passportsimple.ui.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import elf.m.passportsimple.R
import elf.m.passportsimple.ui.Config
import elf.m.passportsimple.ui.fragment.base.BaseBackFragment
import elf.m.passportsimple.ui.http.RetrofitManager
import elf.m.passportsimple.ui.utils.TDevice
import kotlinx.android.synthetic.main.fragmet_test_container.*
import me.pushy.sdk.Pushy
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**

 * author：liuzuo on 19-4-29 15:41

 *

 */
open class TestFragment:BaseBackFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragmet_test_container, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val jwttoken = get(Config.JWTTOKEN, "")
        Log.d("RetrofitHelper", "jwttoken=$jwttoken")
        //检测版本
        checkVersionUpdate.setOnClickListener {
            RetrofitManager.checkVersionUpdate(
            get(Config.JWTTOKEN,""))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("liuzuo99", "onNext=$it")
            },{
                Log.d("liuzuo99", "onError=$it")
            },{
                Log.d("liuzuo99", "onCompleted")
            })
        }
        //获取城市列表
        cityList.setOnClickListener {
            RetrofitManager.cityList(
                "213",jwttoken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("liuzuo99", "onNext=$it")
                },{
                    Log.d("liuzuo99", "onError=$it")
                },{
                    Log.d("liuzuo99","onCompleted")
                })

        }
        //获取国家列表
        countryList.setOnClickListener {
            RetrofitManager.Countrylist(
                jwttoken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("liuzuo99", "onNext=$it")
                },{
                    Log.d("liuzuo99", "onError=$it")
                },{
                    Log.d("liuzuo99", "onCompleted")
                })


        }
        //修改个人信息
        editUserProfile.setOnClickListener {
            RetrofitManager.editUserProfile(
                get(Config.JWTTOKEN, ""),"Perry","Xiao",
                "india","2019-05-22T00:00:00.000Z","sz","single"
                ,"shenzhen","M","english","40",""
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("liuzuo99", "onNext=$it")
                },{
                    Log.d("liuzuo99", "onError=$it")
                },{
                    Log.d("liuzuo99","onCompleted")
                })

        }

        //忘记密码
        forgotPassword.setOnClickListener {
            RetrofitManager.forgotPassword(
                "13410365214@163.com",
                "+8613410365214", ""
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
        }


        //退出登录
        userLogout.setOnClickListener {
            RetrofitManager.userLogout(
                "40",
                "dz.condor.ThemeParc", get(Config.JWTTOKEN, "")
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
        }
        //注册账号
        registerPhone.setOnClickListener {
            RetrofitManager.registerPhone(
                "",TDevice.getDeviceId(),"condor", Build.MODEL,"SMARTPHONE",
                Pushy.getDeviceCredentials(context).token,
                "52.13","64.54","116.24.65.23","shenzhen","china","5.0","5.0","","86","","15980904762"
                ,"","debug","28","sda","asdf")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("liuzuo99", "onNext=$it")
                }, {
                    Log.d("liuzuo99", "onError=$it")
                }, {
                    Log.d("liuzuo99", "onCompleted")
                })


        }
        //重发验证码
        resendOtp.setOnClickListener {
            RetrofitManager.resendOtp(
                "13410365214@163.com",
                "+8613410365214", ""
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
        }
        switchLanguage.setOnClickListener {
            RetrofitManager.switchLanguage(
                "40",
                "english", get(Config.JWTTOKEN, "")
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

        }
        updateDetails.setOnClickListener {
            RetrofitManager.updateDetails(
                "40",
                "90.54", "52.64","china","sz","355431100001179,355431100001187",get(Config.JWTTOKEN, "")
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

        }
        userNotification.setOnClickListener {
            RetrofitManager.userNotification(
                "1",
                "40", "dz.condor.ThemeParc",get(Config.JWTTOKEN, "")
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

        }
        userProfile.setOnClickListener {

            RetrofitManager.userProfile(
                "40",
                get(Config.JWTTOKEN, "")
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

        }
        userRegistration.setOnClickListener {
            RetrofitManager.userRegistration(
                get(Config.JWTTOKEN, ""),
                "",Pushy.getDeviceCredentials(context).token,
                "137768480@qq.com","+8615980904762","778899",TDevice.getDeviceId(),
            "china","sz","english","Cai","Lz","2019.5.24",
                "","sz","M","98.41","52.24","5.0",
                "5.0","dz.condor.ThemeParc","condor",Build.MODEL
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
        }

        verifyOtp.setOnClickListener {
            RetrofitManager.verifyOtp(
               "","40", get(Config.JWTTOKEN, "")
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
        }
        verifyTransactionPassword.setOnClickListener {
            RetrofitManager.verifyTransactionPassword(
                "778899","40", get(Config.JWTTOKEN, "")
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
        }
        phoneAutoRegistration.setOnClickListener {
            RetrofitManager.phoneAutoRegistration(
                "condor",Build.MODEL, TDevice.getDeviceId(),"116.24.65.23",get(Config.JWTTOKEN, "")
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
        }
        serverTime.setOnClickListener {
            RetrofitManager.serverTime(
                get(Config.JWTTOKEN, "")
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

        }
        getAdmobPluginKey.setOnClickListener {
            RetrofitManager.getAdmobPluginKey(
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


        }
    }

    companion object {

        fun newInstance(): TestFragment {

            val args = Bundle()

            val fragment = TestFragment()
            fragment.arguments = args
            return fragment
        }
    }
}