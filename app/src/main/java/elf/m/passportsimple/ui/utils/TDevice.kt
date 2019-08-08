package elf.m.passportsimple.ui.utils

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.content.res.Resources
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat
import elf.m.passportsimple.ui.PassportApplication
import java.io.File

/**

 * author：liuzuo on 19-5-21 10:26

 *

 */
class TDevice {
    companion object{


        /**
         * Change SP to PX
         *
         * @param resources Resources
         * @param sp        SP
         * @return PX
         */
        fun spToPx(resources: Resources, sp: Float): Float {
            val metrics = resources.displayMetrics
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, metrics)
        }

        /**
         * Change Dip to PX
         *
         * @param resources Resources
         * @param dp        Dip
         * @return PX
         */
        fun dipToPx(resources: Resources, dp: Float): Float {
            val metrics = resources.displayMetrics
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics)
        }

        fun dp2px(dp: Float): Float {
            return dp * getDisplayMetrics().density
        }

        fun px2dp(px: Float): Float {
            return px / getDisplayMetrics().density
        }

        fun getDisplayMetrics(): DisplayMetrics {
            return PassportApplication.context().getResources().getDisplayMetrics()
        }

        fun getScreenHeight(): Float {
            return getDisplayMetrics().heightPixels.toFloat()
        }

        fun getScreenWidth(): Float {
            return getDisplayMetrics().widthPixels.toFloat()
        }


        fun isPortrait(): Boolean {
            return PassportApplication.context().getResources().getConfiguration()
                .orientation === Configuration.ORIENTATION_PORTRAIT
        }


        /*
    *首次进入应用 由于没有用户授权或者read_phone_state没授权，就把当前的AndroidID填充到deviceid
    *
    * */
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
            return "355431100001179,355431100001187"/*deviceId*/
        }

        fun getAndroidId(): String {
            return Settings.Secure.getString(
                PassportApplication.context().getContentResolver(),
                Settings.Secure.ANDROID_ID
            )
        }


        /**
         * 打开或关闭键盘
         */
        fun startOrCloseKeyboard(view: View) {
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            // 得到InputMethodManager的实例
            if (imm.isActive) {
                // 如果开启
                imm.toggleSoftInput(
                    InputMethodManager.SHOW_IMPLICIT,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        }

        fun closeKeyboard(view: EditText) {
            view.clearFocus()
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
        }

        fun openKeyboard(view: View) {
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
        }

        /**
         * 平板电脑?
         *
         * @return ??
         */
        fun isTablet(): Boolean {
            var s = PassportApplication.context().getResources().getConfiguration().screenLayout
            s = s and Configuration.SCREENLAYOUT_SIZE_MASK
            return s >= Configuration.SCREENLAYOUT_SIZE_LARGE
        }

        fun hideSoftKeyboard(view: View?) {
            if (view == null) return
            var mFocusView = view

            val context = view.context
            if (context != null && context is Activity) {
                mFocusView = context.currentFocus
            }
            if (mFocusView == null) return
            mFocusView.clearFocus()
            val manager = mFocusView.context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(mFocusView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }

        fun showSoftKeyboard(view: View?) {
            if (view == null) return
            view.isFocusable = true
            view.isFocusableInTouchMode = true
            if (!view.isFocused) view.requestFocus()

            val inputMethodManager = view.context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(view, 0)
        }

//    public static void gotoMarket(Context context, String pck) {
//        if (!isHaveMarket(context)) {
//            AppContext.showToast("你手机中没有安装应用市场！");
//            return;
//        }
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("market://details?id=" + pck));
//        if (intent.resolveActivity(context.getPackageManager()) != null) {
//            context.startActivity(intent);
//        }
//    }

        fun isHaveMarket(context: Context): Boolean {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_APP_MARKET)
            val pm = context.packageManager
            val infos = pm.queryIntentActivities(intent, 0)
            return infos.size > 0
        }

//    public static void openAppInMarket(Context context) {
//        if (context == null) return;
//        String pckName = context.getPackageName();
//        gotoMarket(context, pckName);
//    }

        fun getVersionCode(): Int {
            return getVersionCode(PassportApplication.context().getPackageName())
        }
        fun getPackageName(): String {
            return PassportApplication.context().getPackageName()
        }

        fun getVersionCode(packageName: String): Int {
            try {
                return PassportApplication.context()
                    .getPackageManager()
                    .getPackageInfo(packageName, 0)
                    .versionCode
            } catch (ex: PackageManager.NameNotFoundException) {
                return 0
            }

        }

        fun getVersionName(): String {
            try {
                return PassportApplication
                    .context()
                    .getPackageManager()
                    .getPackageInfo(PassportApplication.context().getPackageName(), 0)
                    .versionName
            } catch (ex: PackageManager.NameNotFoundException) {
                return "undefined version name"
            }

        }

        fun installAPK(context: Context, file: File?) {
            if (file == null || !file.exists())
                return
            val intent = Intent()
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.action = Intent.ACTION_VIEW
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive")
            context.startActivity(intent)
        }

        fun openAppActivity(
            context: Context,
            packageName: String,
            activityName: String
        ): Boolean {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            val cn = ComponentName(packageName, activityName)
            intent.component = cn
            try {
                context.startActivity(intent)
                return true
            } catch (e: ActivityNotFoundException) {
                return false
            }

        }

        fun isWifiOpen(): Boolean {
            val cm = PassportApplication
                .context().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = cm.activeNetworkInfo ?: return false
            if (!info.isAvailable || !info.isConnected) return false
            return if (info.type != ConnectivityManager.TYPE_WIFI) false else true
        }

//    @SuppressWarnings("deprecation")
//    public static void copyTextToBoard(String string) {
//        if (TextUtils.isEmpty(string)) return;
//        ClipboardManager clip = (ClipboardManager) StoreApplication.context()
//                .getSystemService(Context.CLIPBOARD_SERVICE);
//        clip.setText(string);
//        AppContext.showToast(R.string.copy_success);
//    }

        /**
         * 调用系统安装了的应用分享
         *
         * @param context
         * @param title
         * @param url
         */
        fun showSystemShareOption(
            context: Activity,
            title: String, url: String
        ) {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, "分享：$title")
            intent.putExtra(Intent.EXTRA_TEXT, "$title $url")
            context.startActivity(Intent.createChooser(intent, "选择分享"))
        }

        /**
         * Returns a themed color integer associated with a particular resource ID.
         * If the resource holds a complex [ColorStateList], then the default
         * color from the set is returned.
         *
         * @param resources Resources
         * @param id        The desired resource identifier, as generated by the aapt
         * tool. This integer encodes the package, type, and resource
         * entry. The value 0 is an invalid identifier.
         * @return A single color value in the form 0xAARRGGBB.
         */
        fun getColor(resources: Resources, id: Int): Int {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                resources.getColor(id, null)
            } else {
                resources.getColor(id)
            }
        }
    }
}