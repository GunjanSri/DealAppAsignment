package com.nagarro.dealapplication.notification;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.nagarro.dealapplication.BuildConfig;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();
    private static final Object LOCK = new Object();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG , "onMessageReceived, from: " + remoteMessage.getFrom());
        if(!isValidPush(remoteMessage)){
            Log.d(TAG , "push not valid");
        }

        //If receiving multiple notification , process them in order
        synchronized (LOCK){
            NotificationType notificationType = getNotificationType(remoteMessage.getData());
            syncAndShowNotification(remoteMessage,notificationType);
        }

    }

    private boolean isValidPush(RemoteMessage remoteMessage){
        if(remoteMessage != null && BuildConfig.FCM_SENDER_ID.equals(remoteMessage.getFrom()) &&
                remoteMessage.getData() != null){
            return true;
        }else {
            return false;
        }
    }

    private NotificationType getNotificationType(Map<String , String> notificationData) {
        String type = notificationData.get(NotificationKey.KEY_NOTIFICATION_TYPE.getValue());
        if (type != null) {
            return NotificationType.getTypeFromString(type);
        }
        return NotificationType.UNKNOWN;
    }

    private void syncAndShowNotification(RemoteMessage message , NotificationType notificationType){

    }

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);

        //send token to server

    }
}
