package elf.m.passportsimple.ui.http

import elf.m.passportsimple.ui.http.data.result.*
import okhttp3.RequestBody
import retrofit2.http.*
import rx.Observable

/**
 * author：liuzuo on 19-5-16 17:31
 *
 */
interface RetrofitService {
    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Authorization: ",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @POST("userLogin")
    fun loginByPassword(@Body requestBody: RequestBody): Observable<BaseHttpResultData<LoginResultData>>



    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @POST("getToken")
    fun getToken(@Body requestBody: RequestBody): Observable<BaseHttpResultData<LoginResultData>>



    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @POST("changePassword")
    fun changePassword(@Header("Authorization") header: String, @Body requestBody: RequestBody): Observable<BaseHttpResultData<LoginResultData>>


    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @POST("checkVersionUpdate")
    fun checkVersionUpdate(@Header("Authorization") header: String, @Body requestBody: RequestBody): Observable<BaseHttpResultData<LoginResultData>>


    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @POST("cityList")
    fun cityList(@Header("Authorization") header: String, @Body requestBody: RequestBody): Observable<BaseHttpResultData<LoginResultData>>


    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @GET("countryList")
    fun countryList(@Header("Authorization") header: String, @Body requestBody: RequestBody): Observable<BaseHttpResultData<LoginResultData>>


    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @POST("editUserProfile")
    fun editUserProfile(@Header("Authorization") header: String, @Body requestBody: RequestBody): Observable<BaseHttpResultData<EditUserProfileResultData>>

    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @POST("forgotPassword")
    fun forgotPassword(@Header("Authorization") header: String, @Body requestBody: RequestBody): Observable<BaseHttpResultData<LoginResultData>>


    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @POST("userLogout")
    fun userLogout(@Header("Authorization") header: String, @Body requestBody: RequestBody): Observable<BaseHttpResultData<LoginResultData>>


    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @POST("registerPhone")
    fun registerPhone(@Header("Authorization") header: String, @Body requestBody: RequestBody): Observable<BaseHttpResultData<LoginResultData>>


    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @POST("resendOtp")
    fun resendOtp(@Header("Authorization") header: String, @Body requestBody: RequestBody): Observable<BaseHttpResultData<LoginResultData>>


    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @POST("resetPassword")
    fun resetPassword(@Header("Authorization") header: String, @Body requestBody: RequestBody): Observable<BaseHttpResultData<LoginResultData>>


    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @GET("serverTime")
    fun serverTime(@Header("Authorization") header: String): Observable<BaseHttpResultData<Any>>


    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @POST("switchLanguage")
    fun switchLanguage(@Header("Authorization") header: String, @Body requestBody: RequestBody): Observable<BaseHttpResultData<LoginResultData>>


    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @POST("updateDetails")
    fun updateDetails(@Header("Authorization") header: String, @Body requestBody: RequestBody): Observable<BaseHttpResultData<LoginResultData>>


    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @POST("userNotification")
    fun userNotification(@Header("Authorization") header: String, @Body requestBody: RequestBody): Observable<BaseHttpResultData<LoginResultData>>


    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @POST("userProfile")
    fun userProfile(@Header("Authorization") header: String, @Body requestBody: RequestBody): Observable<BaseHttpResultData<UserProfileResultData>>


    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @POST("userRegistration")
    fun userRegistration(@Header("Authorization") header: String, @Body requestBody: RequestBody): Observable<BaseHttpResultData<LoginResultData>>

    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @POST("verifyOtp")
    fun verifyOtp(@Header("Authorization") header: String, @Body requestBody: RequestBody): Observable<BaseHttpResultData<LoginResultData>>


    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @POST("verifyTransactionPassword")
    fun verifyTransactionPassword(@Header("Authorization") header: String, @Body requestBody: RequestBody): Observable<BaseHttpResultData<LoginResultData>>


    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @GET("getAdmobPluginKey")
    fun getAdmobPluginKey(): Observable<BaseHttpResultData<LoginResultData>>


    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @POST("phoneAutoRegistration")
    fun phoneAutoRegistration(@Header("Authorization") header: String, @Body requestBody: RequestBody): Observable<BaseHttpResultData<LoginResultData>>

    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @GET("countryList")
    fun Countrylist(@Header("Authorization") header: String): Observable<BaseHttpResultData<LoginResultData>>



    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @POST("spentList")
    fun spentList(@Header("Authorization") header: String, @Body requestBody: RequestBody): Observable<BaseHttpResultData<SpentListResultData>>


}

