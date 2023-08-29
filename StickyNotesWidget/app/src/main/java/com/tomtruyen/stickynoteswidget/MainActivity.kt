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
import com.tomtruyen.stickynoteswidget.databinding.ActivityMainBinding
import com.tomtruyen.stickynoteswidget.models.StickyNote

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private val note = StickyNote(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setupAds()

        val text = note.getSticky()
        if(text != null && text != "") {
            mBinding.editText.setText(text)
        }
    }

    override fun onStop() {
        note.setSticky(mBinding.editText.text.toString())
        updateWidget()

        super.onStop()
    }

    private fun setupAds() {
        MobileAds.initialize(this) {}

        val request = AdRequest.Builder().build()
        mBinding.adView.loadAd(request)
    }

    private fun updateWidget() {
        val appWidgetManager = AppWidgetManager.getInstance(this)
        val remoteViews = RemoteViews(this.packageName, R.layout.widget_layout)
        val widget = ComponentName(this, AppWidget::class.java)
        remoteViews.setTextViewText(R.id.widgetText, mBinding.editText.text.toString())
        appWidgetManager.updateAppWidget(widget, remoteViews)
    }
}