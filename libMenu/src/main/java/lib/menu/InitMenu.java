package lib.menu;

import android.annotation.SuppressLint;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

/**
 * @author <b>oopErator</b>
 * <li><b>Date:</b> 17/02/2024
 * <li><b>Interface:<b> InitMenu </b>
 * <li> Stores menu parameters
 */
public interface InitMenu {
    String PACKAGE = "package:";
    int sdkANDROID_13 = 13;
    int sdkANDROID_7 = 7;
    String sdkRelease = Build.VERSION.RELEASE;

    //<b>Note:</b> Convert variable from string to number
    int sdkDefault = (sdkRelease.length() == 2) ? Integer.parseInt(sdkRelease) : Integer.parseInt(String.valueOf(sdkRelease.charAt(0)));

    //<b>Params Default</b
    int xPos = 0;
    int yPos = 0;
    int W = -2;
    int H = -2;
    @SuppressLint("RtlHardcoded")
    int gravity_LT = Gravity.LEFT | Gravity.TOP;
    int gravity_CENTER = Gravity.CENTER;
    int ver_M = Build.VERSION_CODES.M;
    int verSDK_INT = Build.VERSION.SDK_INT;
    @SuppressLint("InlinedApi")
    int TYPE_APPLICATION_OVERLAY = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
    int TYPE_PHONE = WindowManager.LayoutParams.TYPE_PHONE;

    /**
     * <li><b>TYPE_APPLICATION</b> is used when using <b>MainActivity</b>
     */
    int TYPE_APPLICATION = WindowManager.LayoutParams.TYPE_APPLICATION;
    int FLAG_NOT_TOUCHABLE = WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
    int FLAG_NOT_FOCUSABLE = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
    int FLAG_LAYOUT_NO_LIMITS = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
    int TRANSPARENT = PixelFormat.TRANSPARENT;
    int GONE = View.GONE;

    /**
     * <b>Method:</b> create
     */
    void create(View view, MenuOnTouch onTouch);
}
