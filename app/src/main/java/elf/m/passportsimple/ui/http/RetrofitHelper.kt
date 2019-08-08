package elf.m.passportsimple.ui.http

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import elf.m.passportsimple.ui.Config
import elf.m.passportsimple.ui.PassportApplication
import okhttp3.*
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

/**

 * author：liuzuo on 19-5-16 16:30

 *

 */
class RetrofitHelper private constructor() {

    private var factory = GsonConverterFactory.create(GsonBuilder().create())
    private var mRetrofit: Retrofit? = null
    private var mRetrofitPassport: Retrofit? = null

    val service: RetrofitService
        get() = mRetrofit!!.create(RetrofitService::class.java)
    val servicePassport: RetrofitPassportService
        get() = mRetrofitPassport!!.create(RetrofitPassportService::class.java)
    init {
        init()
    }

    private fun init() {
        resetApp()
    }

    private fun resetApp() {
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(PassportInterceptor())
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()

        mRetrofit = Retrofit.Builder()
            .baseUrl(Config.HTTP_API_URL)
            //.addConverterFactory(new NobodyConverterFactory())
            .addConverterFactory(factory)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(client)
            .build()

        mRetrofitPassport = Retrofit.Builder()
            .baseUrl(Config.HTTP_API_URL_PASTPORT)
            //.addConverterFactory(new NobodyConverterFactory())
            .addConverterFactory(factory)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(client)
            .build()
    }

    inner class PassportInterceptor : Interceptor {

        private val UTF8 = Charset.forName("UTF-8")

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            if (request.method().equals("POST")) {
                request = addAndEncryptStoreParams(request)
            }

            Log.i(TAG, "request  url=" + request.url().toString())
            Log.i(TAG, "requestBody=" + printRequestBody(request.body())?.let { HttpUtil.decrypt(it) })


            var response = chain.proceed(request)
            response = decrypt(response)
            //            Log.i(TAG, "responseBody=" + response.body().string());
            Log.i(TAG, "responseBody=" + response.body()?.let { printResponseBody(it) })

            return response
        }

        @Throws(IOException::class)
        private fun addAndEncryptStoreParams(request: Request): Request {
            val requestBody = request.body()
            var jsonBody: JsonObject? = null
            if (requestBody != null) {
                val buffer = Buffer()
                requestBody.writeTo(buffer)

                var charset = UTF8
                val contentType = requestBody.contentType()

                if (contentType != null) {
                    charset = contentType.charset(UTF8)
                }
                val body = buffer.readString(charset)
                jsonBody = Gson().fromJson(body, JsonObject::class.java)
            }
            val newBody = HttpUtil.encrypt(HttpUtil.buildJsonRequestParams(jsonBody))
            return request.newBuilder().post(RequestBody.create(requestBody!!.contentType(), newBody)).build()
        }

        @Throws(IOException::class)
        private fun decrypt(response: Response): Response {
            var response = response
            if (response.isSuccessful) {
                //the response data
                val body = response.body()
                val source = body?.source()
                source?.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
                val buffer = source?.buffer()
                var charset = Charset.defaultCharset()
                val contentType = body?.contentType()
                if (contentType != null) {
                    charset = contentType.charset(charset)
                }
                val string = buffer?.clone()?.readString(charset)
                val bodyString = string?.let { HttpUtil.decrypt(it) }
                val responseBody = bodyString?.let { ResponseBody.create(contentType, it) }
                response = response.newBuilder().body(responseBody).build()
            }
            return response
        }

        @Throws(IOException::class)
        private fun printRequestBody(requestBody: RequestBody?): String? {
            var body: String? = null
            if (requestBody != null) {
                val buffer = Buffer()
                requestBody.writeTo(buffer)

                var charset = UTF8
                val contentType = requestBody.contentType()
                if (contentType != null) {
                    charset = contentType.charset(UTF8)
                }
                body = buffer.readString(charset)
            }
            return body
        }

        @Throws(IOException::class)
        private fun printResponseBody(responseBody: ResponseBody): String {
            val source = responseBody.source()
            source.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
            val buffer = source.buffer()
            var charset = Charset.defaultCharset()
            val contentType = responseBody.contentType()
            if (contentType != null) {
                charset = contentType.charset(charset)
            }
            return buffer.clone().readString(charset)
        }

    }

    companion object {

        private val TAG = "RetrofitHelper"
        private var instance: RetrofitHelper? = null

        fun getInstance(): RetrofitHelper? {
            if (instance == null) {
                synchronized(RetrofitHelper::class.java) {
                    if (instance == null) {
                        instance = PassportApplication.getInstance()?.let { RetrofitHelper() }
                    }
                }
            }

            return instance
        }
    }

}