package com.nagarro.dealapplication.notification;

public enum NotificationKey {

    KEY_NOTIFICATION_TYPE("type");

    private final String value;

    NotificationKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
