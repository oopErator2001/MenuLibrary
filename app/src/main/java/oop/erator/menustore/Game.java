package oop.erator.menustore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import lib.menu.Menu;
import lib.oop.R;

public class Game extends Menu implements Menu.InitCreate {
    private View view;
    private LinearLayout linear;

    public Game(Context ctx) {
        super(ctx);
    }

    @Override
    protected Menu GET() {
        return getActivity();
    }

    @Override
    protected int layout(int default_Layout_XML) {
        return R.layout.test;
    }

    @Override
    protected int gravity(int default_LEGT_TOP) {
        return gravity_CENTER;
    }

    @Override
    protected void findID(View view) {
        this.view = view;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void create(InitCreate init) {
        //LinearLayout ll = view.findViewById(lib.oop.R.id.ll_test);
        //ll.setOnTouchListener(onTouch(null));
        init.build(view);
    }

    @Override
    public void build(View view) {
        TextView tv = view.findViewById(lib.oop.R.id.txt);
        tv.setText("Game Class");
    }
}
