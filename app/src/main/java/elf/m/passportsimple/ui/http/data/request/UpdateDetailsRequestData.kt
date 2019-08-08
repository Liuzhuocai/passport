package elf.m.passportsimple.ui.http.data.request

/**

 * author：liuzuo on 19-5-20 16:23

 *

 */
data class UpdateDetailsRequestData (var user_id: String,
                                     var latitude: String,
                                     var longitude: String,
                                     var country: String,
                                     var city: String,
                                     var imei_no: String
                              )