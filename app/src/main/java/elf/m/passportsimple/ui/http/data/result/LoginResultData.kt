package elf.m.passportsimple.ui.http.data.result

/**

 * author：liuzuo on 19-5-20 16:48

 *

 */
data class LoginResultData (val user_id:String,
                            val email:String,
                            val image_url:String ,
                            val first_name:String ,
                            val last_name:String ,
                            val current_balance:Int ,
                            val registration_amount:Int ,
                            val is_notification:String ,
                            val language:String ,
                            val is_verified:String ,
                            val dob:String ,
                            val phone:String ,
                            val martial_status:String ,
                            val current_address:String ,
                            val city_name:String ,
                            val ar_country_name:String ,
                            val fr_country_name:String ,
                            val en_country_name:String ,
                            val token:String ,
                            val en_last_transaction:String ,
                            val is_tp_created:Boolean ,
                            val jwttoken:String ,
                            val jwtrefreshtoken:String
)