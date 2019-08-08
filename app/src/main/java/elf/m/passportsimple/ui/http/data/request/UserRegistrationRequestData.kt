package elf.m.passportsimple.ui.http.data.request

/**

 * author：liuzuo on 19-5-20 16:23

 *

 */
data class UserRegistrationRequestData(
    var image_url: String,
    var device_token: String,
    var email: String,
    var phone: String,
    var password: String,
    var imei_no: String,
    var country: String,
    var city: String?,
    var language: String,
    var first_name: String?,
    var last_name: String?,
    var dob: String?,
    var martial_status: String?,
    var current_address: String?,
    var gender: String?,
    var latitude: String?,
    var longitude: String?,
    var app_version_passport: String?,
    var app_version_theme: String?,
    var app_package: String,
    var ip: String?,
    var brand: String,
    var model: String
                              )