package elf.m.passportsimple.ui.http.data.result

/**

 * author：liuzuo on 19-5-20 16:48

 *

 */
data class UserProfileResultData (val user_id:String,
                                  val email:String,
                                  val image_url:String,
                                  var first_name:String,
                                  var last_name:String,
                                  val current_balance:Int,
                                  val phone:String,
                                  var dob:String,
                                  val martial_status:String,
                                  var current_address:String,
                                  var gender:String,
                                  val language:String,
                                  val gps_city:String,
                                  var gps_country:String,
                                  val registration_amount:Int,
                                  val updated_at: String

)