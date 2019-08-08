package elf.m.passportsimple.ui

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import me.pushy.sdk.Pushy
import java.net.URL


/**

 * author：liuzuo on 19-5-16 16:42

 *

 */
class PassportApplication: Application() {
     lateinit var deviceToken :String

    override fun onCreate() {
        _context = applicationContext
        instance = this
        super.onCreate()

        if (!Pushy.isRegistered(applicationContext)) {
            RegisterForPushNotificationsAsync().execute()
        }
    }

    private inner class RegisterForPushNotificationsAsync : AsyncTask<Void, Void, Exception>() {
        override fun doInBackground(vararg params: Void): Exception? {
            try {
                // Assign a unique token to this device
                 deviceToken = Pushy.register(applicationContext)

                // Log it for debugging purposes
                Log.d("MyApp", "Pushy device token: $deviceToken")

                // Send the token to your backend server via an HTTP GET request
                URL("https://{YOUR_API_HOSTNAME}/register/device?token=$deviceToken").openConnection()
            } catch (exc: Exception) {
                // Return exc to onPostExecute
                return exc
            }

            // Success
            return null
        }

        override fun onPostExecute(exc: Exception?) {
            // Failed?
            if (exc != null) {
                // Show error as toast message
                Toast.makeText(applicationContext, exc.toString(), Toast.LENGTH_LONG).show()
                return
            }

            // Succeeded, optionally do something to alert the user
        }
    }

companion object{
    @Synchronized
    fun context(): PassportApplication {
        return _context as PassportApplication
    }
    @SuppressLint("StaticFieldLeak")
    private var instance: PassportApplication? = null
    @SuppressLint("StaticFieldLeak")
    internal lateinit var _context: Context
    fun getInstance(): PassportApplication? {
        return instance
    }
}

}