package net.middleland.mybluecar;

import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by Antonio on 02/04/2017.
 */
public class MyApplication extends Application {

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {

        super.onCreate();
    }

    @Override
    public void onLowMemory() {

        super.onLowMemory();
    }

    @Override
    public void onTerminate() {

        super.onTerminate();
    }

}
