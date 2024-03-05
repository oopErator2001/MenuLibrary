package lib.main;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import lib.listAdapter.User;
import lib.menu.InitCreate;
import lib.menu.InitMenu;
import lib.menu.Menu;
import lib.menu.MenuOnTouch;
import lib.userInterface.InitUI;
import lib.userInterface.UserInterface;
import oop.erator.libmenu.R;

public class MenuActivity {
    private final Context ctx;
    private ImageView imgMenu;
    private LinearLayout linearMenu;
    private TextView text1;
    private Switch[] arrSw;
    private Button btnMenu;
    private InitCreate[] arrMenu;
    private MenuStore menuStore;

    public MenuActivity(Context ctx) {
        this.ctx = ctx;
        new Menu(ctx).service()
                .sLayout(R.layout.activity_menu)
                .sDevice(InitMenu.sdkDefault)
                .sGravity(InitMenu.gravity_LT)
                .build(new InitMenu() {
                    @Override
                    public void create(View view, MenuOnTouch onTouch) {

                        //Thêm Switch vào menu
                        addSwitch(new Switch[]{
                                (Switch) findView(view, R.id.menu_sw_1),
                                (Switch) findView(view, R.id.menu_sw_2),
                        });

                        //Thêm Menu
                        addMenu(new InitCreate[]{
                                menuStore = new MenuStore(),
                                new MenuWeb()
                        });

                        createMenu(view, onTouch);
                    }
                });
    }

    private void addSwitch(Switch[] sw) {
        this.arrSw = sw;
    }

    private void addMenu(InitCreate[] menu) {
        this.arrMenu = menu;
    }

    private void createMenu(View view, MenuOnTouch onTouch) {
        getID(view);
        getTouch(view, onTouch);
        drawUI(new UserInterface(ctx));
        text1.setText("Menu Store");
        text1.setTextColor(InitUI.color_RED);
        getButton();
        for (int i = 0; i < arrSw.length & i < arrMenu.length; i++) {
            int finalI = i;
            arrSw[i].setText("Menu: User");
            if (i == arrMenu.length - 1) arrSw[i].setText("Menu: Web");
            arrSw[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        arrMenu[finalI].create(finalI, ctx, view, onTouch);
                        arrMenu[finalI].hideView(isChecked);
                    } else {
                        arrMenu[finalI].hideView(isChecked);
                    }
                }
            });
        }
    }

    private void getButton() {
        btnMenu.setText("Add User");
        btnMenu.setAllCaps(false);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tạo dialog
                AlertDialog dialog = dialogBuild().create();
                dialog.getWindow().setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
                dialog.show();
            }
        });

        btnMenu.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                menuStore.getDataList();
                return false;
            }
        });
    }

    private AlertDialog.Builder dialogBuild() {
        LinearLayout viewDialog = new LinearLayout(ctx);
        viewDialog.setOrientation(LinearLayout.VERTICAL);
        EditText edit1 = new EditText(ctx);
        edit1.setHint("Nhập tên");
        EditText edit2 = numEdit();
        edit2.setHint("Nhập tuổi");
        viewDialog.addView(edit1, -1, -2);
        viewDialog.addView(edit2, -1, -2);
        return new AlertDialog.Builder(ctx)
                .setView(viewDialog)
                .setCancelable(false)
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (TextUtils.isEmpty(edit1.getText().toString()) | TextUtils.isEmpty(edit2.getText().toString()))
                            return;
                        String name = edit1.getText().toString();
                        int age = Integer.parseInt(edit2.getText().toString());
                        menuStore.getDataList(new User(name, age));
                    }
                });
    }

    private EditText numEdit() {
        EditText editText = new EditText(ctx);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setKeyListener(DigitsKeyListener.getInstance("0123456789-., "));
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(10);
        editText.setFilters(FilterArray);
        return editText;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void getTouch(View view, MenuOnTouch onTouch) {
        MenuOnTouch img = new MenuOnTouch(onTouch.getW(), onTouch.getParam(), view).Linear(linearMenu);
        imgMenu.setOnTouchListener(img.Click());
        linearMenu.setOnTouchListener(onTouch.Click());
    }

    private void getID(View view) {
        imgMenu = (ImageView) findView(view, R.id.menu_icon);
        linearMenu = (LinearLayout) findView(view, R.id.menu_linear);
        text1 = (TextView) findView(view, R.id.menu_text_1);
        btnMenu = (Button) findView(view, R.id.menu_btn_1);
    }

    private void drawUI(UserInterface UI) {
        linearMenu.setVisibility(View.VISIBLE);
        linearMenu.setBackground(UI
                .gRadius(30)
                .gColorBG(InitUI.color_ZULE)
                .gStroke(2, InitUI.color_AZURE)
                .Draw());

        for (int i = 0, s = 1; i < arrSw.length; i++, s += 2) {
            linearMenu = UI.addLinear(linearMenu, InitUI.color_GRAY, s);
        }
    }

    private View findView(View view, int id) {
        return view.findViewById(id);
    }
}
