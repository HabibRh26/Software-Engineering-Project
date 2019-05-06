package com.example.booksharing;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.booksharing.adapter.CustomAdapterSearchBook;
import com.example.booksharing.model.BookPropertyListVwCls;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchBookActivity extends AppCompatActivity {
    List<BookPropertyListVwCls> bookSearchList;
    ListView listViewSearchBook;
    EditText editTextSearchBookName;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book2);



        editTextSearchBookName = findViewById(R.id.edtVwSearch);
        listViewSearchBook = findViewById(R.id.listViewSearch);
        bookSearchList = new ArrayList<>();


    }



    public void searchMethod(View view) {
        String searchName = editTextSearchBookName.getText().toString();
        Query querySearchBook = FirebaseDatabase.getInstance().getReference("BookCollection")
                .orderByChild("bookName")
                .equalTo(searchName);
        querySearchBook.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bookSearchList.clear();
                for(DataSnapshot bookSnapShot:dataSnapshot.getChildren()){
                    BookPropertyListVwCls bookPropertyObj1 = bookSnapShot.getValue(BookPropertyListVwCls.class);
                    bookSearchList.add(bookPropertyObj1);
                    CustomAdapterSearchBook adapterSearchBook = new CustomAdapterSearchBook(SearchBookActivity.this,bookSearchList);
                    if(bookSearchList!=null){
                        listViewSearchBook.setAdapter(adapterSearchBook);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
