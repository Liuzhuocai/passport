package elf.m.passportsimple.ui.bean

/**

 * author：liuzuo on 19-5-17 09:16

 *

 */
data class SpentBean (var spent_id: Int,
                      var refrence_number: String,
                      var spent_on: String,
                      var spent_on_ar: String,
                      var spent_on_fr: String,
                      var notes: String,
                      var amount: Int,
                      var spent_at:String) :AccountBaseBean()