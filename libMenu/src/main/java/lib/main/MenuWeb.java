package lib.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;

import lib.menu.InitCreate;
import lib.menu.InitMenu;
import lib.menu.Menu;
import lib.menu.MenuOnTouch;
import lib.userInterface.InitUI;
import lib.userInterface.UserInterface;
import oop.erator.libmenu.R;

public class MenuWeb implements InitCreate {
    private Context ctx;
    private Menu menu;
    private View hide;
    private RelativeLayout linearMenu;
    private WebView webView;
    private Button btnBack, btnGo;

    @Override
    public void create(int i, Context ctx, View view, MenuOnTouch onTouch) {
        this.ctx = ctx;
        if (menu == null) {
            menu = new Menu(ctx).service()
                    .sLayout(R.layout.menu_web)
                    .sDevice(InitMenu.sdkANDROID_7)
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
        webView.getSettings().setMinimumFontSize(18);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        String newUA = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12) AppleWebKit/602.1.50 (KHTML, like Gecko) Version/10.0 Safari/602.1.50";
        webView.getSettings().setUserAgentString(newUA);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.goBack();
            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.goForward();
            }
        });

        webView.loadUrl("https://www.youtube.com/");
    }

    @SuppressLint("ClickableViewAccessibility")
    private void getTouch(MenuOnTouch onTouch) {
        linearMenu.setOnTouchListener(onTouch.Click());
        linearMenu.setVisibility(View.VISIBLE);
    }

    private void getID(View view) {
        linearMenu = (RelativeLayout) findView(view, R.id.menuweb_linear);
        webView = (WebView) findView(view, R.id.menu_web);
        btnBack = (Button) findView(view, R.id.menuweb_btn_back);
        btnGo = (Button) findView(view, R.id.menuweb_btn_go);
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
