package oop.erator.menustore;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import lib.menu.Menu;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);


//        Menu menu = new Game(this);
//        Menu menu1 = new Game(this);

//        menu.create(new Menu.InitCreate() {
//            View viewMenu1;
//            @Override
//            public void build(View view) {
//                viewMenu1 = view;
//                LinearLayout ll = view.findViewById(lib.oop.R.id.ll_test);
//                TextView textView = view.findViewById(lib.oop.R.id.txt);
//                textView.setText("Menu 0");
//            }
//        });
//
//        menu1.create(new Menu.InitCreate() {
//            @Override
//            public void build(View view) {
//                LinearLayout ll = view.findViewById(lib.oop.R.id.ll_test);
//                ll.setOnTouchListener(menu1.onTouch(null));
//                TextView textView = view.findViewById(lib.oop.R.id.txt);
//                textView.setText("Menu 1");
//
//                LinearLayout llx = viewMenu1.findViewById(lib.oop.R.id.ll_test);
//                llx.setOnTouchListener(menu.onTouch(textView));
//            }
//        });


        Menu[] menus = {new Game(this), new Game(this), new Game(this)};
        TextView[] textViews = new TextView[menus.length];
        for (int i = 0; i < menus.length; i++) {
            int finalI = i;
            menus[i].create(new Menu.InitCreate() {
                @Override
                public void build(View view) {
                    textViews[finalI] = view.findViewById(lib.oop.R.id.txt);
                    textViews[finalI].setText("Menu: " + finalI);
                }
            });
        }

        for (int i = 0; i < menus.length; i++) {
            int finalI = i;
            menus[i].create(new Menu.InitCreate() {
                @Override
                public void build(View view) {
                    LinearLayout ll = view.findViewById(lib.oop.R.id.ll_test);
                    //ll.setOnTouchListener(menus[finalI].onTouch());
                }
            });
        }
    }
}