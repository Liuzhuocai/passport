package elf.m.passportsimple.ui.http.data.result

import elf.m.passportsimple.ui.bean.UserBindInfo

/**

 * author：liuzuo on 19-5-16 18:14

 *

 */
class UserBindResultData {
    private var bindList: List<UserBindInfo>? = null

    fun getBindList(): List<UserBindInfo>? {
        return bindList
    }

    fun setBindList(bindList: List<UserBindInfo>) {
        this.bindList = bindList
    }
}