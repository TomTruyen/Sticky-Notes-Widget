package com.tomtruyen.stickynoteswidget.models

import android.content.Context
import java.io.*
import java.lang.StringBuilder

class StickyNote(private val context: Context) {
    fun getSticky() : String {
        val file = File(context.filesDir.path+"gfg.txt")

        val text = StringBuilder()
        try {
            val reader = BufferedReader(FileReader(file))

            var line = reader.readLine()
            while(line != null) {
                text.append(line)
                text.append("\n")

                line = readLine()
            }

            reader.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return text.toString()
    }

    fun setSticky(text: String) {
        var fos : FileOutputStream? = null

        try {
            fos = context.applicationContext.openFileOutput("gfg.txt", Context.MODE_PRIVATE)
            fos.write(text.toByteArray())
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if(fos != null) {
                try {
                fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}