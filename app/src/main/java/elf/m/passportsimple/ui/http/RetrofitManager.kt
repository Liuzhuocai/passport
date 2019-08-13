package elf.m.passportsimple.ui.http

import elf.m.passportsimple.BuildConfig
import elf.m.passportsimple.ui.bean.SpentBean
import elf.m.passportsimple.ui.http.data.request.*
import elf.m.passportsimple.ui.http.data.result.*
import rx.Observable

/**

 * author：liuzuo on 19-5-20 15:50

 *

 */
class RetrofitManager {
    companion object {

        /**
         * 登录接口
         *@param device_token
         *@param email
         *@param phone
         *@param password
         *@param imei_no
         *@param app_package
         *@param model
         *@param brand
         */
        fun loginByPassword(
            device_token: String,
            email: String?,
            phone: String?,
            password: String,
            imei_no: String,
            app_package: String,
            model: String,
            brand: String
        ): Observable<BaseHttpResultData<LoginResultData>> {
            val request = if (email == null) {
                LoginRequestData(device_token, null, email, password, imei_no, app_package)
            } else {
                LoginRequestData(device_token, phone, null, password, imei_no, app_package)
            }

            return RetrofitHelper.getInstance()?.service!!.loginByPassword(HttpUtil.createRequestBody(request))
        }


        /**
         * 注册接口
         *@param device_token
         *@param email
         *@param phone
         *@param password
         *@param imei_no
         *@param app_package
         *@param model
         *@param brand
         */
        /* fun Register(
            device_token: String,
            phone: String,
            phone: String,
            password: String,
            imei_no: String,
            app_package: String,
            model: String,
            brand: String
        ): Observable<BaseHttpResultData<LoginResultData>> {
            val request = LoginRequestData(device_token,phone,phone,password,imei_no,app_package)
            return RetrofitHelper.getInstance()?.service!!.loginByPassword(HttpUtil.createRequestBody(request))
        }*/
        fun getToken(
            refresh_token: String,
            user_id: String
        ): Observable<BaseHttpResultData<LoginResultData>> {
            val request = TokenRequestData(refresh_token, user_id)
            return RetrofitHelper.getInstance()?.service!!.getToken(HttpUtil.createRequestBody(request))
        }


        fun changePassword(
            new_password: String,
            old_password: String,
            user_id: String,
            type: String,
            authorization: String
        ): Observable<BaseHttpResultData<LoginResultData>> {
            val request = ChangePasswordRequestData(new_password, old_password, user_id, type)
            return RetrofitHelper.getInstance()?.service!!.changePassword(
                authorization,
                HttpUtil.createRequestBody(request)
            )
        }

        fun checkVersionUpdate(
            authorization: String
        ): Observable<BaseHttpResultData<LoginResultData>> {
            val request = CheckUpdateRequestData("passport", BuildConfig.VERSION_NAME)
            return RetrofitHelper.getInstance()?.service!!.checkVersionUpdate(
                authorization,
                HttpUtil.createRequestBody(request)
            )
        }

        fun cityList(
            country_id: String,
            authorization: String
        ): Observable<BaseHttpResultData<LoginResultData>> {
            val request = CityListRequestData(country_id)
            return RetrofitHelper.getInstance()?.service!!.cityList(authorization, HttpUtil.createRequestBody(request))
        }

        fun Countrylist(
            authorization: String
        ): Observable<BaseHttpResultData<LoginResultData>> {
            return RetrofitHelper.getInstance()?.service!!.Countrylist(authorization)
        }

        fun editUserProfile(
            authorization: String,
            first_name: String,
            last_name: String,
            country: String,
            dob: String,
            city: String,
            martial_status: String,
            current_address: String,
            gender: String,
            language: String,
            user_id: String,
            image_url: String?
        ): Observable<BaseHttpResultData<EditUserProfileResultData>> {
            val request = EditUserProfileRequestData(
                first_name,
                last_name,
                country,
                dob,
                city,
                martial_status,
                current_address,
                gender,
                language,
                user_id,
                image_url
            )
            return RetrofitHelper.getInstance()?.service!!.editUserProfile(
                authorization,
                HttpUtil.createRequestBody(request)
            )
        }

        fun forgotPassword(
            email: String,
            phone: String,
            authorization: String
        ): Observable<BaseHttpResultData<LoginResultData>> {
            val request = ForgotPasswordRequestData(email, phone)
            return RetrofitHelper.getInstance()?.service!!.forgotPassword(
                authorization,
                HttpUtil.createRequestBody(request)
            )
        }


        fun userLogout(
            user_id: String,
            app_package: String,
            authorization: String
        ): Observable<BaseHttpResultData<LoginResultData>> {
            val request = LogoutRequestData(user_id, app_package)
            return RetrofitHelper.getInstance()?.service!!.userLogout(
                authorization,
                HttpUtil.createRequestBody(request)
            )
        }


        fun registerPhone(
            authorization: String,
            imei_no: String,
            brand: String,
            model: String,
            device_type: String,
            device_token: String,
            lat: String,
            long: String,
            ip: String,
            city: String,
            country: String,
            theme_version: String,
            passport_version: String,
            wifi_mac: String,
            sim1_mcc: String,
            sim2_mcc: String,
            sim1_carrier: String,
            sim2_carrier: String,
            platform: String,
            sdk: String,
            sw_build: String,
            hw_build: String
        ): Observable<BaseHttpResultData<LoginResultData>> {
            val request = RegisterPhoneRequestData(
                imei_no,
                brand,
                model,
                device_type,
                device_token,
                lat,
                long,
                ip,
                city,
                country,
                theme_version,
                passport_version,
                wifi_mac,
                sim1_mcc,
                sim2_mcc,
                sim1_carrier,
                sim2_carrier,
                platform,
                sdk,
                sw_build,
                hw_build
            )
            return RetrofitHelper.getInstance()?.service!!.registerPhone(
                authorization,
                HttpUtil.createRequestBody(request)
            )

        }

        fun resendOtp(
            email: String,
            phone: String,
            authorization: String
        ): Observable<BaseHttpResultData<LoginResultData>> {
            val request = ResendOTPRequestData(email, phone)
            return RetrofitHelper.getInstance()?.service!!.resendOtp(authorization, HttpUtil.createRequestBody(request))
        }


        fun resetPassword(
            type: String,
            user_id: String,
            password: String,
            authorization: String
        ): Observable<BaseHttpResultData<LoginResultData>> {
            val request = ResetPasswordRequestData(type, user_id, password)
            return RetrofitHelper.getInstance()?.service!!.resetPassword(
                authorization,
                HttpUtil.createRequestBody(request)
            )
        }


        fun switchLanguage(
            user_id: String,
            language: String,
            authorization: String
        ): Observable<BaseHttpResultData<LoginResultData>> {
            val request = SwitchLanguageRequestData(user_id, language)
            return RetrofitHelper.getInstance()?.service!!.switchLanguage(
                authorization,
                HttpUtil.createRequestBody(request)
            )
        }


        fun updateDetails(
            user_id: String,
            latitude: String,
            longitude: String,
            country: String,
            city: String,
            imei_no: String,
            authorization: String
        ): Observable<BaseHttpResultData<LoginResultData>> {
            val request = UpdateDetailsRequestData(user_id, latitude, longitude, country, city, imei_no)
            return RetrofitHelper.getInstance()?.service!!.updateDetails(
                authorization,
                HttpUtil.createRequestBody(request)
            )
        }


        fun userNotification(
            is_notification: String,
            user_id: String,
            app_package: String,
            authorization: String
        ): Observable<BaseHttpResultData<LoginResultData>> {
            val request = UserNotificationRequestData(is_notification, user_id, app_package)
            return RetrofitHelper.getInstance()?.service!!.userNotification(
                authorization,
                HttpUtil.createRequestBody(request)
            )
        }


        fun userProfile(
            user_id: String,
            authorization: String
        ): Observable<BaseHttpResultData<UserProfileResultData>> {
            val request = UserProfileRequestData(user_id)
            return RetrofitHelper.getInstance()?.service!!.userProfile(
                authorization,
                HttpUtil.createRequestBody(request)
            )
        }

        fun userRegistration(
            authorization: String,
            image_url: String,
            device_token: String,
            email: String,
            phone: String,
            password: String,
            imei_no: String,
            country: String,
            city: String?,
            language: String,
            first_name: String?,
            last_name: String?,
            dob: String?,
            martial_status: String?,
            current_address: String?,
            gender: String?,
            latitude: String?,
            longitude: String?,
            app_version_passport: String?,
            app_version_theme: String?,
            app_package: String,
            brand: String,
            model: String
        ): Observable<BaseHttpResultData<LoginResultData>> {
            val request = UserRegistrationRequestData(
                image_url,
                device_token,
                email,
                phone,
                password,
                imei_no,
                country,
                city,
                language,
                first_name,
                last_name,
                dob,
                martial_status,
                current_address,
                gender,
                latitude,
                longitude,
                app_version_passport,
                app_version_theme,
                app_package,
                " ",
                brand,
                model
            )
            return RetrofitHelper.getInstance()?.service!!.userRegistration(
                authorization,
                HttpUtil.createRequestBody(request)
            )

        }

        fun verifyOtp(
            otp: String,
            user_id: String,
            authorization: String
        ): Observable<BaseHttpResultData<LoginResultData>> {
            val request = VerifyOTPRequestData(otp, user_id)
            return RetrofitHelper.getInstance()?.service!!.verifyOtp(authorization, HttpUtil.createRequestBody(request))
        }


        fun verifyTransactionPassword(
            txn_password: String,
            user_id: String,
            authorization: String
        ): Observable<BaseHttpResultData<LoginResultData>> {
            val request = VerifyTransactionPasswordRequestData(txn_password, user_id)
            return RetrofitHelper.getInstance()?.service!!.verifyTransactionPassword(
                authorization,
                HttpUtil.createRequestBody(request)
            )
        }

        fun phoneAutoRegistration(
            brand: String,
            model: String,
            imeiNo: String,
            ipAddress: String,
            authorization: String
        ): Observable<BaseHttpResultData<LoginResultData>> {
            val request = RegisterPhoneByCondorTeamRequestData(brand, model, imeiNo, ipAddress)
            return RetrofitHelper.getInstance()?.service!!.phoneAutoRegistration(
                authorization,
                HttpUtil.createRequestBody(request)
            )
        }

        fun getAdmobPluginKey(
        ): Observable<BaseHttpResultData<LoginResultData>> {
            return RetrofitHelper.getInstance()?.service!!.getAdmobPluginKey()
        }

        fun serverTime(
            authorization: String
        ): Observable<BaseHttpResultData<Any>> {
            return RetrofitHelper.getInstance()?.service!!.serverTime(authorization)
        }

        fun spentList(
            user_id: String,
            page: String,
            authorization: String
        ): Observable<BaseHttpResultData1<SpentBean>> {
            val request = SpentListRequestData(user_id, page)
            return RetrofitHelper.getInstance()?.servicePassport!!.spentList(
                authorization,
                HttpUtil.createRequestBody(request)
            )
        }

        fun rechargelist(
            user_id: String,
            page: String,
            authorization: String
        ): Observable<BaseHttpResultData1<RechargeListResultData>> {
            val request = RechargeListRequestData(user_id, page)
            return RetrofitHelper.getInstance()?.servicePassport!!.rechargelist(
                authorization,
                HttpUtil.createRequestBody(request)
            )
        }

        fun checkBalance(
            user_id: String,
            authorization: String
        ): Observable<BaseHttpResultData<CheckBalanceResultData>> {
            val request = CheckBalanceRequestData(user_id)
            return RetrofitHelper.getInstance()?.servicePassport!!.checkBalance(
                authorization,
                HttpUtil.createRequestBody(request)
            )
        }


        fun recharge(
            user_id: String,
            brand: String,
            coupon_code: String,
            model: String,
            imei_no: String,
            authorization: String
        ): Observable<BaseHttpResultData<RechargeResultData>> {
            val request = RechargeRequestData(user_id, brand, coupon_code, model, imei_no)
            return RetrofitHelper.getInstance()?.servicePassport!!.recharge(
                authorization,
                HttpUtil.createRequestBody(request)
            )
        }

        fun creditTransfer(
            user_id: String,
            language: String,
            amount: String,
            phone: String,
            email: String?,
            txn_password: String,
            notes: String,
            authorization: String
        ): Observable<BaseHttpResultData<CreditTransferResultData>> {
            val request = CreditTransferRequestData(user_id,language,amount,phone, email, txn_password, notes)
            return RetrofitHelper.getInstance()?.servicePassport!!.creditTransfer(
                authorization,
                HttpUtil.createRequestBody(request)
            )
        }
    }
}