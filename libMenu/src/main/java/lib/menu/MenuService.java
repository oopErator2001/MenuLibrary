package lib.menu;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import lib.main.MenuActivity;

public class MenuService extends Service {
    public MenuService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        new MenuActivity(this);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.stopSelf();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}