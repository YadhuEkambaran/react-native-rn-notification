// RnNotificationModule.java
/*
* Author: Yadhukrishnan Ekambaran
* */

package com.circle;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import androidx.core.app.NotificationCompat;

public class RnNotificationModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    private static final String NOTIFICATION_PLACEHOLDER_ICON_NAME = "ic_launcher";
    private static final String NOTIFICATION_PLACEHOLDER_ICON_TYPE = "mipmap";
    private static final String NOTIFICATION_ICON_NAME = "ic_notification";
    private static final String NOTIFICATION_ICON_TYPE = "drawable";

    private static final int NOTIFICATION_ID = 1;

    private final NotificationManager mNotificationManager;

    public RnNotificationModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        mNotificationManager = (NotificationManager) reactContext.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public String getName() {
        return "RnNotification";
    }

    @ReactMethod
    public void createNotificationChannel(String channelId, String channelName, int importance, String description) {
        if (TextUtils.isEmpty(channelId)) {
            throw new IllegalArgumentException("Channel Id can not be empty");
        }

        if (TextUtils.isEmpty(channelName)) {
            throw new IllegalArgumentException("Channel name can not be empty");
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
            notificationChannel.setShowBadge(true);
            notificationChannel.enableVibration(true);
            if (description != null) {
                notificationChannel.setDescription(description);
            }
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }

    @ReactMethod
    public void showNotification(String channelId, String title, String body) {
        if (TextUtils.isEmpty(channelId)) throw new IllegalArgumentException("Channel Id is empty");

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(reactContext, channelId);
        builder.setContentTitle(title);
        builder.setContentText(body);
        int smallIcon = getCurrentActivity().getResources()
                .getIdentifier(NOTIFICATION_ICON_NAME,
                        NOTIFICATION_ICON_TYPE,
                        getCurrentActivity().getPackageName());
        if (smallIcon == 0) {
            smallIcon = getCurrentActivity().getResources()
                    .getIdentifier(NOTIFICATION_PLACEHOLDER_ICON_NAME,
                            NOTIFICATION_PLACEHOLDER_ICON_TYPE,
                            getCurrentActivity().getPackageName());
            if (smallIcon == 0) {
                throw new IllegalArgumentException("ic_launcher could not be found");
            }
        }

        builder.setSmallIcon(smallIcon);

        mNotificationManager.notify(NOTIFICATION_ID, builder.build());

    }
}
