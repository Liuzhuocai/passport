package elf.m.passportsimple.ui.http.data.result

/**

 * author：liuzuo on 19-5-16 18:12

 *

 */
data class CheckBalanceResultData (var current_balance:Int,
                                   var registration_amount:Int,
                                   var en_last_transaction:String,
                                   var ar_last_transaction:String,
                                   var fr_last_transaction:String)
