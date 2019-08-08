package elf.m.passportsimple.ui.http.data.request

/**

 * author：liuzuo on 19-5-20 16:23

 *

 */
data class RegisterPhoneRequestData (var imei_no: String,
                                     var brand: String,
                                     var model: String,
                                     var device_type: String,
                                     var device_token: String,
                                     var lat: String,
                                     var long: String,
                                     var ip: String,
                                     var city: String,
                                     var country: String,
                                     var theme_version: String,
                                     var passport_version: String,
                                     var wifi_mac: String,
                                     var sim1_mcc: String,
                                     var sim2_mcc: String,
                                     var sim1_carrier: String,
                                     var sim2_carrier: String,
                                     var platform: String,
                                     var sdk: String,
                                     var sw_build: String,
                                     var hw_build: String
                              )