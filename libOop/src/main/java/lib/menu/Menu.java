package lib.menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import lib.oop.R;

public abstract class Menu {
    private final Context ctx;
    private View mView;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mParams;

    public Menu(Context ctx) {
        this.ctx = ctx;
        try {
            mParams = new WindowManager.LayoutParams(
                    -2,
                    -2,
                    0,
                    0,
                    getSdkDefault(),
                    512 | 8,
                    -2
            );
            mParams.gravity = gravity(51);
            GET();
            mView = LayoutInflater.from(ctx).inflate(layout(R.layout.test), null);
            findID(mView);
            mWindowManager.addView(mView, mParams);
        } catch (Exception e) {
            Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public static void Start(Context ctx, Class<?> serviceClass) {
        if (!Settings.canDrawOverlays(ctx)) {
            ctx.startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + ctx.getPackageName())));
            ((Activity) ctx).finish();
        } else {
            ctx.startService(new Intent(ctx, serviceClass));
        }
    }

    private int getSdkDefault() {
        //(Build.VERSION.SDK_INT >= 26) ? 2038 : 2002
        String sdkRelease = Build.VERSION.RELEASE;
        int sdk = (sdkRelease.length() == 2) ? Integer.parseInt(sdkRelease) : Integer.parseInt(String.valueOf(sdkRelease.charAt(0)));
        return (sdk > 7) ? 2038 : 2002;
    }

    protected Menu getActivity() {
        mWindowManager = ((Activity) ctx).getWindowManager();
        mParams.type = WindowManager.LayoutParams.TYPE_APPLICATION;
        return this;
    }

    protected Menu getService() {
        mWindowManager = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        return this;
    }

    public View.OnTouchListener onTouch(View linear) {
        return new View.OnTouchListener() {
            float initialTouchX, initialTouchY;
            int initialX, initialY;

            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = mParams.x;
                        initialY = mParams.y;
                        initialTouchX = motionEvent.getRawX();
                        initialTouchY = motionEvent.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        int rawX = (int) (motionEvent.getRawX() - initialTouchX);
                        int rawY = (int) (motionEvent.getRawY() - initialTouchY);
                        if (linear != null) {
                            if (rawX < 10 && rawY < 10 && linear.getVisibility() == View.GONE) {
                                linear.setVisibility(View.VISIBLE);
                            } else {
                                linear.setVisibility(View.GONE);
                            }
                        }
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        mParams.x = initialX + ((int) (motionEvent.getRawX() - initialTouchX));
                        mParams.y = initialY + ((int) (motionEvent.getRawY() - initialTouchY));
                        mWindowManager.updateViewLayout(mView, mParams);
                        return true;
                    default:
                        return false;
                }
            }
        };
    }

    protected abstract Menu GET();

    protected abstract int layout(int default_Layout_XML);

    protected abstract int gravity(int default_LEGT_TOP);

    protected abstract void findID(View view);

    public abstract void create(InitCreate init);

    public interface InitCreate {
        int gravity_CENTER = Gravity.CENTER;
        int gravity_LEFT = Gravity.LEFT;
        int gravity_TOP = Gravity.TOP;
        int gravity_LT = 51;
        String getSystemDetail = "DeviceID: " + Build.DEVICE + "\n" +
                "Model: " + Build.MODEL + "\n" +
                "ID: " + Build.ID + "\n" +
                "SDK: " + Build.VERSION.SDK_INT + "\n" +
                "Manufacture: " + Build.MANUFACTURER + "\n" +
                "Brand: " + Build.BRAND + "\n" +
                "User: " + Build.USER + "\n" +
                "Type: " + Build.TYPE + "\n" +
                "Base: " + Build.VERSION_CODES.BASE + "\n" +
                "Incremental: " + Build.VERSION.INCREMENTAL + "\n" +
                "Board: " + Build.BOARD + "\n" +
                "Host: " + Build.HOST + "\n" +
                "FingerPrint: " + Build.FINGERPRINT + "\n" +
                "Version Code: " + Build.VERSION.RELEASE;

        void build(View view);
    }
}
