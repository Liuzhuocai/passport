package elf.m.passportsimple.ui.http.data.result

/**

 * author：liuzuo on 19-5-16 18:12

 *

 */
data class BaseHttpResultData1<T> (var Success:Boolean,
                                   var StatusCode:Int,
                                   var Message:String,
                                   var Result:ArrayList<T>
)
