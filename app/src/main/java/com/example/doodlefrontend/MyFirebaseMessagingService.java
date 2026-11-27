package com.example.doodlefrontend;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.doodlefrontend.services.WidgetUpdater;
import com.example.doodlefrontend.utils.SharedPrefManager;
import com.example.doodlefrontend.views.widget.Widget;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import dagger.hilt.EntryPoint;
import dagger.hilt.EntryPoints;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @EntryPoint
    @InstallIn(SingletonComponent.class)
    public interface MessagingServiceEntryPoint {
        Widget widget();
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        Log.d("hi", "onMessageReceived: "+message);
        String text = message.getData().get("text");
        String color = message.getData().get("color");
        String font = message.getData().get("font");


        if(color==null) color = "#77314d";
        else{
            color = String.format("#%06X", (0xFFFFFF & Integer.parseInt(color)));
        }


        if (text != null) {

            MessagingServiceEntryPoint entryPoint = EntryPoints.get(
                    getApplicationContext(),
                    MessagingServiceEntryPoint.class
            );

            Widget widget = entryPoint.widget();

            SharedPrefManager.INSTANCE.setText(text);
            WidgetUpdater.update(getApplicationContext(), widget, text, color,"dummy");
        }

    }
}
