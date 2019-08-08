package elf.m.passportsimple.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import elf.m.passportsimple.R
import elf.m.passportsimple.ui.Config
import elf.m.passportsimple.ui.fragment.LoginFragment
import elf.m.passportsimple.ui.fragment.LoginFragment.Companion.APP_OPEN_TYPE_EMAIL
import elf.m.passportsimple.ui.fragment.base.BaseActivity
import elf.m.passportsimple.ui.fragment.base.BaseBackFragment

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : BaseActivity() , BaseBackFragment.OnBackToFirstListener {
    override fun onBackToFirstFragment() {
        pop()
    }

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val jwttoken = get(Config.JWTTOKEN, "")
        val jwttokenReshtoken = get(Config.JWTREFRESHTOKEN, "")
        val userId = get(Config.USER_ID, "")
        if(!TextUtils.isEmpty(jwttoken)&&!TextUtils.isEmpty(jwttokenReshtoken)&&!TextUtils.isEmpty(userId)){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else{
            val fragment = findFragment(LoginFragment::class.java)
            if (fragment == null) {
                loadRootFragment(R.id.fl_first_container, LoginFragment.newInstance(APP_OPEN_TYPE_EMAIL))
            }
        }


    }
}
