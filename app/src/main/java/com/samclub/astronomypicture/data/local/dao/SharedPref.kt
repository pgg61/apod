package com.samclub.astronomypicture.data.local.dao

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.samclub.astronomypicture.data.excepions.GsonException
import com.samclub.astronomypicture.util.AppConstants

class SharedPref {
    companion object {
        private var sharedPref: SharedPreferences? = null
        private const val prefFile = AppConstants.shared_pref_file
        private var instance: SharedPref? = null

        fun getPref(context: Context): SharedPref? {
            sharedPref = context.getSharedPreferences(
                prefFile,
                Context.MODE_PRIVATE
            )
            if (instance == null) {
                instance = SharedPref()
            }
            return instance
        }
    }

    // Generic method for putting strinng data in shared Pref
    private fun putString(key: String, data: String) {
        val editor: SharedPreferences.Editor? = sharedPref?.edit()
        editor?.putString(key, data)
        editor?.apply()
        editor?.commit()
    }

    // Generic method for getting String data in shared Pref
    private fun getString(key: String): String? {
        return sharedPref?.getString(key, null)
    }

    //Generic method for putting JSON string in shared Pref
    @Throws(GsonException::class)
    private fun putJSON(key: String, data: Any) {
        try {
            putString(key, Gson().toJson(data))
        } catch (e: Exception) {
            throw GsonException(e.localizedMessage!!)
        }
    }

    // Generic method for setting  Object data in shared Pref
    @Throws(GsonException::class)
    fun putObject(key: String, classMapped: Any) {
        return putJSON(key, classMapped)
    }

    //Generic method for getting object data stored in shared Pref
    fun <T : Any> getObject(key: String, classMapped: Class<in T>): Any? {
        val jsonString: String = getString(key) ?: return null
        try {
            return Gson().fromJson(jsonString, classMapped)
        } catch (e: Exception) {
            throw  GsonException(e.localizedMessage!!)
        }
    }
}