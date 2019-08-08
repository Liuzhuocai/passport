package elf.m.passportsimple.ui.http.data.request

/**

 * author：liuzuo on 19-5-20 16:23

 *

 */
data class EditUserProfileRequestData(
    var first_name: String,
    var last_name: String,
    var country: String,
    var dob: String,
    var city: String,
    var martial_status: String,
    var current_address: String,
    var gender: String,
    var language: String,
    var user_id: String,
    var image_url: String?
                              )