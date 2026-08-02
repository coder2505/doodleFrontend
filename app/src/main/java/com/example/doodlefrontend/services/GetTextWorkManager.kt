package com.example.doodlefrontend.services

import android.content.Context
import android.util.Log
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.updateAll
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.doodlefrontend.repository.GetWidgetTextRepo
import com.example.doodlefrontend.utils.SharedPrefManager
import com.example.doodlefrontend.views.widget.Widget
import com.example.doodlefrontend.views.widget.WidgetReceiver
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import javax.inject.Inject

@HiltWorker
class GetTextWorkManager @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    val getWidgetTextRepo: GetWidgetTextRepo
    ) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val responseText = getWidgetTextRepo.getText()

        if(responseText.isSuccessful){
            Log.d("INSIDE WORKER", responseText.body().toString())
            SharedPrefManager.setText(responseText.body().toString())
            Widget().updateAll(applicationContext)
            return Result.success()
        }

        return Result.retry()
    }


}