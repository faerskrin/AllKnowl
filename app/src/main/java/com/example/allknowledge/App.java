package com.example.allknowledge;

import android.app.Application;

public class App extends Application {

    public static ApiHelper apiHelper;
    public  static  Datamanager dm;

    @Override
    public void onCreate() {
        super.onCreate();
        apiHelper = ApiHelper.Creator.newApi();
        dm = new Datamanager(getApplicationContext());
    }
}
