package com.tomtruyen.stickynoteswidget

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.RemoteViews
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.tomtruyen.stickynoteswidget.models.StickyNote

class MainActivity : AppCompatActivity() {
    private lateinit var note : StickyNote
    private lateinit var editText: EditText
    private lateinit var mAdView: AdView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        editText = findViewById(R.id.editText)

        note = StickyNote(this)
        val text = note.getSticky()
        if(text != null && text != "") {
            editText.setText(text)
        }
    }

    override fun onStop() {
        note.setSticky(editText.text.toString())
        updateWidget()

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