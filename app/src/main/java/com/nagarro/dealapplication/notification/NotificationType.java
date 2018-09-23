package com.nagarro.dealapplication.notification;

public enum NotificationType {
    NEW_OFFER("new-offer"),
    UNKNOWN("unknown");

    private final String value;
    NotificationType(String value){
        this.value = value;
    }

    public static NotificationType getTypeFromString(String type){
        if(type != null){
            final String lowercaseType = type.toLowerCase();
            final NotificationType[] notificationTypes = NotificationType.values();
            for(NotificationType notificationType : notificationTypes){
                if(notificationType.value.equals(lowercaseType)){
                    return notificationType;
                }
            }
        }
        return UNKNOWN;
    }
}
