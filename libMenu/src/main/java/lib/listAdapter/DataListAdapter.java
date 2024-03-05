package lib.listAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import oop.erator.libmenu.R;

public class DataListAdapter extends BaseAdapter {
    private final ArrayList<User> list;

    public DataListAdapter(ArrayList<User> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //convertView là View của phần tử ListView, nếu convertView != null nghĩa là
        //View này được sử dụng lại, chỉ việc cập nhật nội dung mới
        //Nếu null cần tạo mới

        View viewProduct;
        if (convertView == null) {
            viewProduct = View.inflate(parent.getContext(), R.layout.list_item_menustore, null);
        } else {
            viewProduct = convertView;
        }

        //Bind sữ liệu phần tử vào View
        User user = (User) getItem(position);
        TextView textView = viewProduct.findViewById(R.id.list_item_menu_store_tv_name);
        textView.setText(String.format("Name: %-10s Age: %s", user.getUserName(), user.getUserAge()));

        return viewProduct;
    }
}
