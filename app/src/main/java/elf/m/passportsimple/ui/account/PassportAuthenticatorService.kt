package elf.m.passportsimple.ui.account

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**

 * author：liuzuo on 19-5-30 09:54

 *

 */
class PassportAuthenticatorService:Service() {
    var accountAuthenticator: AccountAuthenticator? = null
    override fun onCreate() {
        super.onCreate()
        accountAuthenticator = AccountAuthenticator(this)
    }
    override fun onBind(intent: Intent?): IBinder {
        return accountAuthenticator?.iBinder!!
    }
}