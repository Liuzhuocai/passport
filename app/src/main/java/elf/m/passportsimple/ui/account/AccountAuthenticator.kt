package elf.m.passportsimple.ui.account

import android.accounts.AbstractAccountAuthenticator
import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import elf.m.passportsimple.ui.Config.ARG_ACCOUNT_NAME
import elf.m.passportsimple.ui.Config.ARG_ACCOUNT_TYPE
import elf.m.passportsimple.ui.Config.ARG_AUTH_TYPE
import elf.m.passportsimple.ui.Config.ARG_IS_ADDING_NEW_ACCOUNT
import elf.m.passportsimple.ui.activity.LoginActivity



/**

 * author：liuzuo on 19-5-29 15:39

 *

 */
class AccountAuthenticator(context: Context) : AbstractAccountAuthenticator(context) {
     val mContext:Context = context

    override fun confirmCredentials(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        options: Bundle?
    ): Bundle {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateCredentials(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        authTokenType: String?,
        options: Bundle?
    ): Bundle? {

        return null
    }

    override fun getAuthToken(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        authTokenType: String?,
        options: Bundle?
    ): Bundle {
        val am = AccountManager.get(mContext)

        var authToken = am.peekAuthToken(account, authTokenType)

        // Lets give another try to authenticate the user
        if (TextUtils.isEmpty(authToken)) {
            val password = am.getPassword(account)
            if (password != null) {
               // authToken = sServerAuthenticate.userSignIn(account.name, password, authTokenType)
            }
        }

        // If we get an authToken - we return it
        if (!TextUtils.isEmpty(authToken)) {
            val result = Bundle()
            result.putString(AccountManager.KEY_ACCOUNT_NAME, account?.name)
            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account?.type)
            result.putString(AccountManager.KEY_AUTHTOKEN, authToken)
            return result
        }

        // If we get here, then we couldn't access the user's password - so we
        // need to re-prompt them for their credentials. We do that by creating
        // an intent to display our AuthenticatorActivity.
        val intent = Intent(mContext, LoginActivity::class.java)
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
        if (account != null) {
            intent.putExtra(ARG_ACCOUNT_TYPE, account.type)
            intent.putExtra(ARG_ACCOUNT_NAME, account.name)
        }
        intent.putExtra(ARG_AUTH_TYPE, authTokenType)
        val bundle = Bundle()
        bundle.putParcelable(AccountManager.KEY_INTENT, intent)
         return bundle
    }

    override fun hasFeatures(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        features: Array<out String>?
    ): Bundle {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun editProperties(response: AccountAuthenticatorResponse?, accountType: String?): Bundle? {
        return null
    }

    override fun addAccount(
        response: AccountAuthenticatorResponse?,
        accountType: String?,
        authTokenType: String?,
        requiredFeatures: Array<out String>?,
        options: Bundle?
    ): Bundle {
        val intent = Intent(mContext, LoginActivity::class.java)
        intent.putExtra(ARG_ACCOUNT_TYPE, accountType)
        intent.putExtra(ARG_AUTH_TYPE, authTokenType)
        intent.putExtra(ARG_IS_ADDING_NEW_ACCOUNT, true)
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)

        val bundle = Bundle()
        bundle.putParcelable(AccountManager.KEY_INTENT, intent)
        return bundle
    }

    override fun getAuthTokenLabel(authTokenType: String?): String? {
      return authTokenType
    }
}