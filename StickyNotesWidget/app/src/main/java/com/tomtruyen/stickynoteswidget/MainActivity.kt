package com.tomtruyen.stickynoteswidget

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.RemoteViews
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tomtruyen.stickynoteswidget.models.StickyNote

class MainActivity : AppCompatActivity() {
    private lateinit var note : StickyNote
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)

        note = StickyNote(this)
        val text = note.getSticky()
        if(text != "") {
            editText.setText(text)
        }
    }

    override fun onStop() {
        note.setSticky(editText.text.toString())
        updateWidget()
        Toast.makeText(this, "Note has been updated", Toast.LENGTH_SHORT).show()

        super.onStop()
    }

    private fun updateWidget() {
        val appWidgetManager = AppWidgetManager.getInstance(this)
        val remoteViews = RemoteViews(this.packageName, R.layout.widget_layout)
        val widget = ComponentName(this, AppWidget::class.java)
        remoteViews.setTextViewText(R.id.widgetText, editText.text.toString())
        appWidgetManager.updateAppWidget(widget, remoteViews)
    }
}