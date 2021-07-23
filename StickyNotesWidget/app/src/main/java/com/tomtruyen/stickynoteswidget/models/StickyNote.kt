package com.tomtruyen.stickynoteswidget.models

import android.content.Context
import android.content.SharedPreferences

class StickyNote(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("Notes", Context.MODE_PRIVATE)

    companion object {
        private const val PREF_KEY_NOTE = "note"
    }

    fun getSticky() : String? {
        return prefs.getString(PREF_KEY_NOTE, null)
    }

    fun setSticky(text: String) {
        val editor : SharedPreferences.Editor = prefs.edit()
        editor.putString(PREF_KEY_NOTE, text)
        editor.apply()
    }
}