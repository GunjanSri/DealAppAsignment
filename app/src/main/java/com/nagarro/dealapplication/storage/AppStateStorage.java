package com.nagarro.dealapplication.storage;

import android.content.Context;

public class AppStateStorage extends Storage{

    private static final String KEY_STATE = "key_state";
    private static final String EMAIL_ID = "email_id";

    public enum State {
        UNREGISTERED(0),
        REGISTERED(1);

        private final int value;

        State(int value) {
            this.value = value;
        }

        public static State fromInt(int value) {
            switch (value) {
                case 1:
                    return REGISTERED;
                default:
                    return UNREGISTERED;
            }
        }
    }

    public AppStateStorage(Context context) {
        super(context);
    }

    @Override
    public String getStorageId() {
        return "app_state";
    }

    public State getState() {
        return State.fromInt(getInt(KEY_STATE, State.UNREGISTERED.value));
    }

    public void setState(State state) {
        putInt(KEY_STATE, state.value);
    }

    public String getEmailId() {
        return getString(EMAIL_ID );
    }

    public void setEmaild(String email) {
        putString(EMAIL_ID, email);
    }


}
