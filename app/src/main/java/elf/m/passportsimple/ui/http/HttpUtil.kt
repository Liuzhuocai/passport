package elf.m.passportsimple.ui.http

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.util.Log
import com.google.gson.Gson
import elf.m.passportsimple.ui.PassportApplication
import elf.m.passportsimple.ui.http.data.request.BaseHttpRequestData
import okhttp3.MediaType
import okhttp3.RequestBody
import java.util.*

/**

 * author：liuzuo on 19-5-17 09:38

 *

 */
class HttpUtil {
    companion object{


    private var data: BaseHttpRequestData? = null

    private fun buildBaseHttpRequestData(): BaseHttpRequestData {
        if (data == null) {
            val context = PassportApplication.getInstance()


            var versionCode = 0
            var versionName = ""
            val pm = context?.packageManager
            var pi: PackageInfo? = null
            try {
                pi = pm?.getPackageInfo(context.packageName, 0)
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            if (pi != null) {
                versionName = pi.versionName
                versionCode = pi.versionCode
            }

//            data!!.version = versionCode.toString()
//            data!!.deviceId = SystemUtil.getDeviceId()
//            data!!.model = Build.MODEL
//            data!!.systemVersion = android.os.Build.VERSION.RELEASE
//            data!!.versionName = versionName
            data = BaseHttpRequestData("g4AK#kf\$Zv&p9-Ax","application/json","","en",null)
        }
        return data as BaseHttpRequestData
    }

    fun buildJsonRequestParams(body: Any?): String {
        var body = body
        val gson = Gson()
        val data = buildBaseHttpRequestData()
        if (body == null) {
            body = Any()
        }
        data.body = body
        return gson.toJson(body)
    }

    fun createRequestBody(request: Any): RequestBody {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), Gson().toJson(request))
    }

    fun encrypt(str: String?): String {
        /*if (str != null && str.length > 0) {
            val charArray = str.toCharArray()
            var j = 0
            for (i in charArray.indices) {
                charArray[i] = (charArray[i].toInt() xor 666 + j).toChar()
                if (j++ > 10000) {
                    j = 0
                }
            }
            rstStr = String(charArray)
        }*/
        Log.d("RetrofitHelper", "requestBody=$str")
        val base64info = String(Base64.getEncoder().encode(str?.toByteArray()))

        return "{\"inputparam\":\"$base64info\"}"
    }
        fun decryptRequest(str: String?): String {
            /*if (str != null && str.length > 0) {
                val charArray = str.toCharArray()
                var j = 0
                for (i in charArray.indices) {
                    charArray[i] = (charArray[i].toInt() xor 666 + j).toChar()
                    if (j++ > 10000) {
                        j = 0
                    }
                }
                rstStr = String(charArray)
            }*/
            val base64info = String(Base64.getDecoder().decode(str?.toByteArray()))

            return "{\"inputparam\":\"$base64info\"}"
        }
    /**
     * 字符串解密
     *
     * @param str 待解密字符串
     * @return 解密后的字符串
     */
    fun decrypt(str: String): String {
        return  str!!/*String(Base64.getDecoder().decode(str?.toByteArray()))*/
    }

    fun filterDownloadUrl(url: String): String {
        var url = url
        var index = -1
        index = url.lastIndexOf(".apk")
        if (index != -1) {
            url = url.substring(0, index + 4)
        }
        return url
    }
    }
}