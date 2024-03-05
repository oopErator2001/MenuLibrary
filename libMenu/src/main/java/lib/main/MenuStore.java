package lib.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import lib.listAdapter.DataListAdapter;
import lib.listAdapter.DataRecyclerAdapter;
import lib.listAdapter.User;
import lib.menu.InitCreate;
import lib.menu.InitMenu;
import lib.menu.Menu;
import lib.menu.MenuOnTouch;
import lib.prefConfig.PrefConfig;
import lib.userInterface.InitUI;
import lib.userInterface.UserInterface;
import oop.erator.libmenu.R;

public class MenuStore implements InitCreate {
    private ArrayList<User> users;
    private DataListAdapter dataList;
    private DataRecyclerAdapter dataRecy;
    private Menu menu;
    private View hide;
    private LinearLayout linearMenu;
    private ListView listView;
    private RecyclerView recyclerView;
    private Context ctx;

    @Override
    public void create(int i, Context ctx, View view, MenuOnTouch onTouch) {
        this.ctx = ctx;
        if (menu == null) {
            menu = new Menu(ctx).service()
                    .sLayout(R.layout.menu_store)
                    .sDevice(InitMenu.sdkDefault)
                    .sGravity(InitMenu.gravity_CENTER);
            menu.build(new InitMenu() {
                @Override
                public void create(View view, MenuOnTouch onTouch) {
                    hide = view;
                    getID(view);
                    getTouch(onTouch);
                    drawUI(new UserInterface(ctx));
                    getLogic();
                }
            });
        }
    }

    private void getLogic() {
        users = PrefConfig.readListFromPref(ctx);
        if (users == null) users = new ArrayList<>();
        dataList = new DataListAdapter(users);
        dataRecy = new DataRecyclerAdapter(users);

        listView.setAdapter(dataList);

        recyclerView.setAdapter(dataRecy);
        recyclerView.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false));
    }

    public void getDataList(User user) {
        if (menu == null) return;
        users.add(user);
        PrefConfig.writeListInPref(ctx, users);

        dataList.notifyDataSetChanged();
        listView.smoothScrollToPosition(users.size() - 1);

        dataRecy.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(users.size() - 1);
    }

    public void getDataList() {
        if (menu == null) return;
        if (users.isEmpty()) return;
        Toast.makeText(ctx, "Clear Users:" + users.size(), Toast.LENGTH_SHORT).show();
        users.clear();
        PrefConfig.writeListInPref(ctx, users);

        dataList.notifyDataSetChanged();
        dataRecy.notifyDataSetChanged();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void getTouch(MenuOnTouch onTouch) {
        linearMenu.setOnTouchListener(onTouch.Click());
        linearMenu.setVisibility(View.VISIBLE);
    }

    private void getID(View view) {
        linearMenu = (LinearLayout) findView(view, R.id.menu_linear);
        listView = (ListView) findView(view, R.id.menu_list_view);
        recyclerView = (RecyclerView) findView(view, R.id.menu_recycler_view);
    }

    private void drawUI(UserInterface UI) {
        linearMenu.setBackground(UI
                .gRadius(30)
                .gColorBG(InitUI.color_ZULE)
                .gStroke(2, InitUI.color_AZURE)
                .Draw());
    }

    private View findView(View view, int id) {
        return view.findViewById(id);
    }

    @Override
    public void hideView(boolean is) {
        if (is) {
            hide.setVisibility(View.VISIBLE);
        } else {
            hide.setVisibility(View.GONE);
        }
    }
}
