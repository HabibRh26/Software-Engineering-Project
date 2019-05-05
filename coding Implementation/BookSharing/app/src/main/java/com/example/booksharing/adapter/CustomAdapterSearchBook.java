package com.example.booksharing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.booksharing.R;
import com.example.booksharing.model.BookPropertyListVwCls;

import java.util.List;

public class CustomAdapterSearchBook extends BaseAdapter {

    Context contxt;
    List<BookPropertyListVwCls> bookPropertyList;

    public CustomAdapterSearchBook(Context contxt, List<BookPropertyListVwCls> bookPropertyList) {
        this.contxt = contxt;
        this.bookPropertyList = bookPropertyList;
    }

    @Override
    public int getCount() {
        return bookPropertyList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) contxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.book_search_item_layout,null);

        TextView txtVwBookName = customView.findViewById(R.id.txtVwBook);
        TextView txtVwCategoryName = customView.findViewById(R.id.txtVwCategory);
        TextView txtVwQuantityNum = customView.findViewById(R.id.txtVwQuantity);

        txtVwBookName.setText(bookPropertyList.get(position).getBookName());
        txtVwCategoryName.setText(bookPropertyList.get(position).getBookCategory());
        txtVwQuantityNum.setText(bookPropertyList.get(position).getBookQuantity());

        return customView;
    }

}
