package lib.userInterface;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.widget.LinearLayout;

public class UserInterface implements InitUI {
    private final GradientDrawable shape;
    private final Context ctx;

    public UserInterface(Context ctx) {
        this.ctx = ctx;
        shape = new GradientDrawable();
        shape.setShape(shape_RECTANGLE);
    }

    public LinearLayout addLinear(LinearLayout linear, int color, int size) {
        LinearLayout ll = new LinearLayout(ctx);
        ll.setBackgroundColor(color);
        ll.setLayoutParams(new LinearLayout.LayoutParams(-1, 1));
        linear.addView(ll, size);
        return linear;
    }

    public UserInterface gShape(int gShape) {
        shape.setShape(gShape);
        return this;
    }

    public UserInterface gRadius(float l, float r, float rb, float lb) {
        shape.setCornerRadii(new float[]{l, l, r, r, rb, rb, lb, lb});
        return this;
    }

    public UserInterface gRadius(float radius) {
        shape.setCornerRadius(radius);
        return this;
    }

    public UserInterface gStroke(int size, int color) {
        shape.setStroke(size, color);
        return this;
    }

    public UserInterface gColorBG(int colorBG) {
        shape.setColor(colorBG);
        return this;
    }

    public GradientDrawable Draw() {
        return shape;
    }
}
