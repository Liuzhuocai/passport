package elf.m.passportsimple.ui.http

import elf.m.passportsimple.ui.bean.SpentBean
import elf.m.passportsimple.ui.http.data.result.*
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import rx.Observable

/**
 * author：liuzuo on 19-5-16 17:31
 *
 */
interface RetrofitPassportService {

    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @POST("spentList")
    fun spentList(@Header("Authorization") header: String, @Body requestBody: RequestBody): Observable<BaseHttpResultData1<SpentBean>>


    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @POST("checkBalance")
    fun checkBalance(@Header("Authorization") header: String, @Body requestBody: RequestBody): Observable<BaseHttpResultData<CheckBalanceResultData>>


    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @POST("creditTransfer")
    fun creditTransfer(@Header("Authorization") header: String, @Body requestBody: RequestBody): Observable<BaseHttpResultData<CreditTransferResultData>>

    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @POST("rechargelist")
    fun rechargelist(@Header("Authorization") header: String, @Body requestBody: RequestBody): Observable<BaseHttpResultData1<RechargeListResultData>>


    @Headers(
        "x-api-key:g4AK#kf\$Zv&p9-Ax",
        "Content-Type:application/json",
        "Accept-Language:en/ar/fr"
    )
    @POST("recharge")
    fun recharge(@Header("Authorization") header: String, @Body requestBody: RequestBody): Observable<BaseHttpResultData<RechargeResultData>>

}

