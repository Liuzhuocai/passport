package elf.m.passportsimple.ui.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.provider.Settings
import android.telephony.TelephonyManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import elf.m.passportsimple.ui.PassportApplication

/**

 * author：liuzuo on 19-5-17 10:50

 *

 */
object  SystemUtil {
    val TAG = "SystemUtil"

    fun getDeviceId(): String {
        var deviceId: String? = null
        if (ContextCompat.checkSelfPermission(
                PassportApplication.context(),
                Manifest.permission.READ_PHONE_STATE
            ) === PackageManager.PERMISSION_GRANTED
        ) {
            val tm = PassportApplication.context().getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            deviceId = tm.deviceId
        }

        if (deviceId == null) {
            deviceId = getAndroidId()
        }
        return deviceId
    }

    @SuppressLint("HardwareIds")
    fun getAndroidId(): String {
        return Settings.Secure.getString(PassportApplication.context().contentResolver, Settings.Secure.ANDROID_ID)
    }

    fun showSystemKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, 0)
    }

    fun hideSystemKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}