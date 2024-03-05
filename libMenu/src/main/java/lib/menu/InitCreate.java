package lib.menu;

import android.content.Context;
import android.view.View;

public interface InitCreate {
    void create(int i, Context ctx, View view, MenuOnTouch onTouch);

    void hideView(boolean is);
}
