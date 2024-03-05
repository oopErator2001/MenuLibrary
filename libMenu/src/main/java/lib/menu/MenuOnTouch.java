package lib.menu;

import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * @author <b>oopErator</b>
 * <li><b>Date:</b> 17/02/2024
 * <li><b>Class:</b><b> MenuOnTouch </b>
 * Use this class to move the view
 */
public class MenuOnTouch {
    private final WindowManager mWindowManager;
    private final WindowManager.LayoutParams mParams;
    private final View mView;
    private View linear;
    private boolean check = false;

    public MenuOnTouch(WindowManager mWindowManager, WindowManager.LayoutParams mParams, View mView) {
        this.mWindowManager = mWindowManager;
        this.mParams = mParams;
        this.mView = mView;
    }

    public WindowManager getW() {
        return mWindowManager;
    }

    public WindowManager.LayoutParams getParam() {
        return mParams;
    }

    /**
     * <li><b>Method:</b> <b>Linear</b>
     * Use when you want to show or hide
     *
     * @param linear Linear should be used
     * @return this
     */
    public MenuOnTouch Linear(View linear) {
        this.linear = linear;
        this.check = true;
        return this;
    }

    /**
     * <li><b>Method:</b> <b>Click</b>
     * Use to move that Linear when calling the OnTouchListener method
     */
    public View.OnTouchListener Click() {
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
                        if (check) {
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
}
