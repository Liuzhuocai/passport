package elf.m.passportsimple.ui.http.data.request

/**

 * author：liuzuo on 19-5-16 18:12

 *

 */
data class CreditTransferRequestData (var user_id:String,
                                      var language:String,
                                      var amount:String,
                                      var phone:String,
                                      var email:String?,
                                      var txn_password:String,
                                      var notes:String
)
