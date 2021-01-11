package com.example.videogamesapplication.utils

import android.content.Context
import android.widget.Toast
import com.example.videogamesapplication.R

class Utils {
    companion object {
        internal fun controlResponseCode(responseCode: Int, context: Context? = null): Boolean {
            if (responseCode in 200..299) {
                return true
            } else {
                context?.let {
                    Toast.makeText(
                        it,
                        it.resources.getString(R.string.request_failed),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            return false
        }
    }
}