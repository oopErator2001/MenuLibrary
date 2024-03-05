package lib.menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import oop.erator.libmenu.R;

/**
 * @author <b>oopErator</b>
 * <li><b>Date:</b> 17/02/2024
 * <li><b>Class:<b> Menu </b>
 * <li>Use <b>{@link Menu new Menu(this)}</b> to initialize the menu as you like
 * @implNote <b>{@link InitMenu}</b>
 * <li> Stores menu parameters
 */
public class Menu implements InitMenu {
    private final Context ctx;
    private WindowManager.LayoutParams mParams;
    private WindowManager mWindowManager;
    private View mView;

    public Menu(Context ctx) {
        this.ctx = ctx;
        paramsMenu();
    }

    /**
     * <li><b>Method:</b> <b>Start</b>
     * Used to call up the Service Menu
     *
     * @param ctx          Add <b>this</b> here
     * @param serviceClass Add <b>Service Class</b> here
     */
    public static void Start(Context ctx, Class<?> serviceClass) {
        if (!Settings.canDrawOverlays(ctx)) {
            ctx.startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse(PACKAGE + ctx.getPackageName())));
            ((Activity) ctx).finish();
        } else {
            ctx.startService(new Intent(ctx, serviceClass));
        }
    }

    /**
     * <li><b>Method:</b> <b>Layout</b>
     *
     * @param layoutXML Add <b>{@link xml R.layout.xml}</b> here
     */
    public Menu sLayout(int layoutXML) {
        mView = LayoutInflater.from(ctx).inflate(layoutXML, null);
        return this;
    }

    /**
     * <li><b>Method:</b> <b>Gravity</b>
     *
     * @param gravity Add <b>{@link InitMenu gravity}</b> here
     */
    public Menu sGravity(int gravity) {
        mParams.gravity = gravity;
        return this;
    }

    /**
     * <li><b>Method:</b> <b>PosX</b>
     *
     * @param x Add <b>{@link menu number}</b> here
     */
    public Menu sPosX(int x) {
        mParams.x = x;
        return this;
    }

    /**
     * <li><b>Method:</b> <b>PosY</b>
     *
     * @param y Add <b>{@link menu number}</b> here
     */
    public Menu sPosY(int y) {
        mParams.y = y;
        return this;
    }

    /**
     * <li><b>Method:</b> <b>Flag</b>
     *
     * @param flag Add <b>{@link menu number}</b> here
     */
    public Menu sFlag(int flag) {
        mParams.flags = flag;
        return this;
    }

    /**
     * <li><b>Method:</b> <b>Flag</b>
     * Flag Default
     */
    public Menu sFlag() {
        mParams.flags = FLAG_NOT_TOUCHABLE;
        return this;
    }

    /**
     * <li><b>Method:</b> <b>Device</b>
     *
     * @param sdk Add <b>{@link menu Android Version}</b> here
     */
    public Menu sDevice(int sdk) {
        mParams.type = (sdk > sdkANDROID_7)
                ? TYPE_APPLICATION_OVERLAY : TYPE_PHONE;
        return this;
    }

    /**
     * <li><b>Method:</b> <b>paramsMenu</b>
     * Default initialization of parameters
     */
    private void paramsMenu() {
        mParams = new WindowManager.LayoutParams(
                W,
                H,
                xPos,
                yPos,
                (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) ? 2038 : 2002,
                FLAG_LAYOUT_NO_LIMITS |
                        FLAG_NOT_FOCUSABLE,
                TRANSPARENT
        );
        mParams.gravity = gravity_LT;
    }

    /**
     * <li><b>Method:</b> <b>activity</b>
     * Default get Main activity menu
     */
    public Menu activity() {
        mWindowManager = ((Activity) ctx).getWindowManager();
        mParams.type = TYPE_APPLICATION;
        return this;
    }

    /**
     * <li><b>Method:</b> <b>service</b>
     * Default get Main service menu
     */
    public Menu service() {
        mWindowManager = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        return this;
    }

    /**
     * <li><b>Method:</b> <b>build</b>
     *
     * @param menu Add <b>{@link InitMenu {new InitMenu} Call the constructor to write the menu}</b> here
     */
    public void build(InitMenu menu) {
        menu.create(mView, new MenuOnTouch(mWindowManager, mParams, mView));
        mWindowManager.addView(mView, mParams);
    }

    /**
     * <li><b>Method:</b> <b>buildDefault</b>
     * Default initialization
     */
    public void build() {
        sLayout(R.layout.text);
        sGravity(gravity_CENTER);
        //Check type is activity or service
        if (mParams.type != TYPE_APPLICATION) {
            sDevice(sdkDefault);
        }
        create(mView, new MenuOnTouch(mWindowManager, mParams, mView));
        mWindowManager.addView(mView, mParams);
    }

    /**
     * <li><b>Method:</b> <b>create</b>
     * Default initialization of parameters
     */
    @Override
    public void create(View view, MenuOnTouch onTouch) {
        Toast.makeText(ctx, "Hello Default", Toast.LENGTH_SHORT).show();
        LinearLayout lineTest = view.findViewById(R.id.ll_test);
        TextView txt = view.findViewById(R.id.txt);
        lineTest.setOnTouchListener(onTouch.Click());
        txt.setText(getSystemDetail());
    }

    /**
     * <li><b>Method:</b> <b>getSystemDetail</b>
     * Default initialization
     *
     * @return Phone information
     */
    private String getSystemDetail() {
        return "Brand: " + Build.BRAND + "\n" +
                "DeviceID: " + Settings.Global.getString(ctx.getContentResolver(), Settings.Global._ID) + "\n" +
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
    }
}
