package elf.m.passportsimple.ui.http.data.request

/**

 * author：liuzuo on 19-5-20 16:23

 *

 */
data class ChangePasswordRequestData (var new_password: String,
                                      var old_password: String,
                                      var user_id: String,
                                      var type: String
                              )