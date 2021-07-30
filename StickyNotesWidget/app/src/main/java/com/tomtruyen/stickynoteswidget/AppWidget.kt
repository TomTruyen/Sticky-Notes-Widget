package com.tomtruyen.stickynoteswidget

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class AppWidget : AppWidgetProvider() {
    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        val launchIntent = Intent(context, MainActivity::class.java)
        launchIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;

        val pendingIntent = PendingIntent.getActivity(context, 0, launchIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val remoteViews = RemoteViews(context?.packageName, R.layout.widget_layout)

        remoteViews.setOnClickPendingIntent(R.id.widgetLayout, pendingIntent)

        appWidgetManager?.updateAppWidget(appWidgetIds, remoteViews)
    }
}