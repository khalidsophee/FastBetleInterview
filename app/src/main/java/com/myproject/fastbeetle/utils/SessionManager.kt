package com.myproject.fastbeetle.utils

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SessionManager @Inject constructor(
    private val context: Context
) {
    private val prefName = "${context.packageName}.session"

    fun setSession(
        session: Session
    ): Boolean {
        val (id, token,username,refreshToken, sessionId) = session
        return setId(id) && setToken(token) && setUserName(username) && setRefreshToken(refreshToken)
                && setSessionId(sessionId)
    }

    fun getSession(): Session? {
        val id = getId()?.toLongOrNull() ?: return null
        val token = getToken() ?: return null
        val userName = getUserName() ?: return null
        val sessionId = getSessionId() ?: return null
        val refreshToken = getRefreshToken() ?: return null
        return Session(id, token,userName, refreshToken, sessionId)
    }

    private fun setId(customerId: Long): Boolean {
        val preferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        val editor = preferences.edit()

        // val encryption = Encryption(context).apply { createMasterKey() }
        editor.putString(KEY_ID, customerId.toString())
        editor.apply()
        return editor.commit()
    }

    fun getId(): String? = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        .getString(KEY_ID, "")
    fun getUserName(): String? = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        .getString(User_NAME, "")

    private fun setToken(token: String): Boolean {
        val preferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        val editor = preferences.edit()

        //   val encryption = Encryption(context).apply { createMasterKey() }
        editor.putString(KEY_TOKEN, token)
        editor.apply()
        return editor.commit()
    }

    private fun getToken(): String? = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        .getString(KEY_TOKEN, "")


    private fun setSessionId(sessionId: String): Boolean {
        val preferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        val editor = preferences.edit()

        //  val encryption = Encryption(context).apply { createMasterKey() }
        editor.putString(KEY_SESSION_ID, sessionId)
        editor.apply()
        return editor.commit()
    }

    private fun setUserName(userName: String): Boolean {
        val preferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(User_NAME, userName)
        editor.apply()
        return editor.commit()
    }

    private fun getSessionId(): String? =
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            .getString(KEY_SESSION_ID, "")

    private fun setRefreshToken(refreshToken: String): Boolean {
        val preferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        val editor = preferences.edit()

        //      val encryption = Encryption(context).apply { createMasterKey() }
        editor.putString(KEY_REFRESH_TOKEN, refreshToken)
        editor.apply()
        return editor.commit()
    }

    private fun getRefreshToken(): String? =
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            .getString(KEY_REFRESH_TOKEN, "")

    companion object {
        private const val KEY_TOKEN = "token"
        private const val User_NAME = "user_name"
        private const val KEY_SESSION_ID = "session_id"
        private const val KEY_REFRESH_TOKEN = "refresh_token"
        private const val KEY_ID = "id"
        private const val IS_LOGIN = "IsLoggedIn"
    }

}

data class Session(
    val id: Long,
    val token: String,
    val username:String,
    val refreshToken: String,
    var sessionId: String = "",
)