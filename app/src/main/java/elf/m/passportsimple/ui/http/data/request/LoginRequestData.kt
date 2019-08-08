package elf.m.passportsimple.ui.http.data.request

/**

 * author：liuzuo on 19-5-20 16:23

 *

 */
data class LoginRequestData(var device_token: String,
                            var email: String?,
                            var phone: String?,
                            var password: String,
                            var imei_no: String,
                            var app_package: String
                              )