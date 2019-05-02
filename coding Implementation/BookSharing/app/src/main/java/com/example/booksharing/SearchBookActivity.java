package com.example.booksharing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.booksharing.adapter.CustomAdapterSearchBook;
import com.example.booksharing.model.BookPropertyListVwCls;
import com.example.booksharing.model.DBhelperClsSearchBook;

import java.util.ArrayList;
import java.util.List;

public class SearchBookActivity extends AppCompatActivity {
    List<BookPropertyListVwCls>bookPropertyList;
    ListView listViewSearchBook;
    EditText editTextSearchBookName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book2);

        editTextSearchBookName = findViewById(R.id.edtVwSearch);
        listViewSearchBook = findViewById(R.id.listViewSearch);

       // CustomAdapterSearchBook adapterSearchBook = new CustomAdapterSearchBook(getApplicationContext(),bookPropertyList);
      // listViewSearchBook.setAdapter(adapterSearchBook);
    }

    public void searchMethod(View view) {
        String searchName = editTextSearchBookName.getText().toString();
        DBhelperClsSearchBook dBhelperClsSearchBook = new DBhelperClsSearchBook(getApplicationContext());
        List<BookPropertyListVwCls> bookPropertyList = dBhelperClsSearchBook.bookSearch(searchName);
        if (bookPropertyList != null) {
            listViewSearchBook.setAdapter(new CustomAdapterSearchBook(getApplicationContext(), bookPropertyList));
        }

    }
}
