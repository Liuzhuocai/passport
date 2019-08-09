package elf.m.passportsimple.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.format.Time
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.bigkoo.pickerview.view.TimePickerView
import elf.m.passportsimple.R
import elf.m.passportsimple.ui.Config
import elf.m.passportsimple.ui.country.CountryBean
import elf.m.passportsimple.ui.country.CountrySelectActivity
import elf.m.passportsimple.ui.fragment.base.BaseInformationFragment
import elf.m.passportsimple.ui.http.RetrofitManager
import elf.m.passportsimple.ui.http.data.result.UserProfileResultData
import elf.m.passportsimple.ui.view.PreferenceItemView
import kotlinx.android.synthetic.main.edit_dialog.view.*
import kotlinx.android.synthetic.main.fragment_my_information.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**

 * author：liuzuo on 19-4-29 15:41

 *

 */
open class MineFragment: BaseInformationFragment() {
    lateinit var timePickerView: TimePickerView
    lateinit var userProfile:UserProfileResultData
    lateinit var  pickerView: OptionsPickerView<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_my_information, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        RetrofitManager.userProfile(
            get(Config.USER_ID,""),
            get(Config.JWTTOKEN, "")
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                loadInfo(it.Result)
                Log.d("liuzuo99", "onNext=$it")
            }, {
                Log.d("liuzuo99", "onError=$it")
            }, {
                Log.d("liuzuo99", "onCompleted")
            })

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        EventBus.getDefault().register(this)

    }

    override fun onDetach() {
        super.onDetach()
        EventBus.getDefault().unregister(this)
    }
    private fun loadInfo(result: UserProfileResultData) {
        userProfile= result
        information_first_name.setTipText(result.first_name)
        information_last_name.setTipText(result.last_name)
        information_gender.setTipText(result.gender)
        information_birthday.setTipText(result.dob)
        information_location.setTipText(result.current_address)
        information_country.setTipText(result.gps_country)


        information_first_name.setOnClickListener{
            showEditDialog(it as PreferenceItemView)
        }
        information_last_name.setOnClickListener{
            showEditDialog(it as PreferenceItemView)
        }
        information_location.setOnClickListener{
            showEditDialog(it as PreferenceItemView)
        }
        information_gender.setOnClickListener {
            initGenderSelector(it as PreferenceItemView)
            pickerView.show()

        }
        information_birthday.setOnClickListener {

            initTimeSelector(it as PreferenceItemView)
            timePickerView.show()
        }
        information_country.setOnClickListener {
            val intentProvince = Intent(_mActivity, CountrySelectActivity::class.java)
            startActivityForResult(intentProvince, CountrySelectActivity.REQUEST_CODE)
        }
    }

    private fun initTimeSelector(preferenceItemView: PreferenceItemView) {
         timePickerView = TimePickerBuilder(_mActivity, OnTimeSelectListener { date, v ->
            val calendar = Time()
            calendar.set(date.time)
            val birthday = calendar.year.toString() + "-" + (calendar.month + 1) + "-" + calendar.monthDay
            userProfile.dob = birthday
            updateUserProfile(userProfile,preferenceItemView,birthday)

        }).build()
    }

    private fun initGenderSelector(preferenceItemView: PreferenceItemView) {
        val array = resources.getStringArray(R.array.gender_array)
         pickerView = OptionsPickerBuilder(_mActivity, OnOptionsSelectListener { options1, _, _, _ ->
             val s = array[options1]
             userProfile.gender = s
             updateUserProfile(userProfile,preferenceItemView,s)

        }).build()

    }

    private fun showEditDialog(preferenceItemView: PreferenceItemView) {
        val builder =  AlertDialog.Builder(_mActivity)

        val view= layoutInflater.inflate(R.layout.edit_dialog,null)
        val dialog= builder.create()
        dialog.show()
        dialog.window?.setContentView(view)
        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
        view.choosepage_cancel.setOnClickListener{
            dialog.dismiss()
        }
        view.choosepage_sure.setOnClickListener{
            val text = view.choosepage_edittext.text.toString()
            when (preferenceItemView){
                information_first_name -> userProfile.first_name = text
                information_last_name -> userProfile.last_name = text
                information_location -> userProfile.current_address = text
            }
            updateUserProfile(userProfile,preferenceItemView,text)
            dialog.dismiss()
        }
    }

    private fun updateUserProfile(
        userProfile: UserProfileResultData,
        preferenceItemView: PreferenceItemView,
        text: String
    ) {
        RetrofitManager.editUserProfile(
            get(Config.JWTTOKEN, ""),userProfile.first_name,userProfile.last_name,
            userProfile.gps_country,userProfile.dob,userProfile.gps_city,userProfile.martial_status
            ,userProfile.current_address,userProfile.gender,userProfile.language,userProfile.user_id,userProfile.image_url
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("liuzuo99", "onNext=$it")
                preferenceItemView.setTipText(text)
            },{
                Log.d("liuzuo99", "onError=$it")
            },{
                Log.d("liuzuo99","onCompleted")
            })
    }

    companion object {

        fun newInstance(): MineFragment {

            val args = Bundle()

            val fragment = MineFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onBusEvent(event: CountryBean) {
        userProfile.gps_country = event.country_name
        updateUserProfile(userProfile,information_country,event.country_name)
    }
}