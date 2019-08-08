package elf.m.passportsimple.ui.http.data.result

/**

 * author：liuzuo on 19-5-16 18:12

 *

 */
data class RechargeListResultData (var recharge_id: Int,
                                   var refrence_number: String,
                                   var description: String,
                                   var description_ar: String,
                                   var description_fr: String,
                                   var notes: String,
                                   var amount: Int,
                                   var created_at:String

)
