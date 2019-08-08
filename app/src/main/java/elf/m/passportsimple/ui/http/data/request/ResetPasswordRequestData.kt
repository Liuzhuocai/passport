package elf.m.passportsimple.ui.http.data.request

/**

 * author：liuzuo on 19-5-20 16:23

 *

 */
data class ResetPasswordRequestData (var type: String,
                                     var user_id: String,
                                     var password: String
                              )