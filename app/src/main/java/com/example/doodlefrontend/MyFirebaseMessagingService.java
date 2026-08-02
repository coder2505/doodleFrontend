package com.example.doodlefrontend;

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

        String text = message.getData().get("text");
        if (text != null) {

            MessagingServiceEntryPoint entryPoint = EntryPoints.get(
                    getApplicationContext(),
                    MessagingServiceEntryPoint.class
            );

            Widget widget = entryPoint.widget();

            SharedPrefManager.INSTANCE.setText(text);
            WidgetUpdater.update(getApplicationContext(), widget);
        }

    }
}
