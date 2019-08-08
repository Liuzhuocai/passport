package elf.m.passportsimple.ui.utils

import android.widget.Toast
import androidx.annotation.StringRes
import elf.m.passportsimple.ui.PassportApplication.Companion._context

/**

 * author：liuzuo on 19-6-5 16:56

 *

 */

fun toast(msg: CharSequence?) {
    Toast.makeText(_context, msg, Toast.LENGTH_SHORT).show()
}

fun toast(@StringRes resId: Int) {
    Toast.makeText(_context, resId, Toast.LENGTH_SHORT).show()
}
 fun isEmailValid(email: String): Boolean {
    return email.contains("@")
}
fun isPhoneValid(password: String): Boolean {
    return password.length > 5
}
 fun isPasswordValid(password: String): Boolean {
    return password.length > 5
}