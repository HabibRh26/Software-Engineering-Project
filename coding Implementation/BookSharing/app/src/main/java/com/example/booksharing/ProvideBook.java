package com.example.booksharing;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.booksharing.adapter.CustomAdapterSearchBook;
import com.example.booksharing.model.BookPropertyListVwCls;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ProvideBook extends AppCompatActivity {

    DatabaseReference dbReference;
    List<BookPropertyListVwCls> bookPropertyList;

    EditText bkName,bKQuantity,setTxtBook;
    Spinner bkCategory;
    ListView listViewBook;
    Button updatebtnDialog,deleteBtnDialog;
    private EditText bookNameDialog,bookQuantityDialog;
    Spinner bookCategoryDialog;

    private static final String Channel_id ="WalletBookBNB";
    private static final String Channel_name ="WalletBookBNB";
    private static final String Channel_desc ="online book sharing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O)
        {
            NotificationChannel channel =new NotificationChannel(Channel_id, Channel_name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(Channel_desc);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        dbReference = FirebaseDatabase.getInstance().getReference("BookCollection");
        bkName = findViewById(R.id.editTxtName);
        bkCategory = findViewById(R.id.SpinnerBookCategory);
        bKQuantity = findViewById(R.id.editTxtQuantity);
        listViewBook = findViewById(R.id.listViewSearch);
        bookPropertyList = new ArrayList<>();

        listViewBook.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                final BookPropertyListVwCls bookObj = bookPropertyList.get(position);


                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ProvideBook.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_layout_update_book,null);
                dialogBuilder.setView(dialogView);

                bookNameDialog = dialogView.findViewById(R.id.edtTxtBook);
                //   final String bookName = bookNameDialog.getText().toString();
                bookCategoryDialog = dialogView.findViewById(R.id.SpinnerBookCat);
                //  final String category = bookCategoryDialog.getSelectedItem().toString();
                bookQuantityDialog = dialogView.findViewById(R.id.edtTxtQuantity);
                // final  String bookQuant2 = bookQuantityDialog.getText().toString();
                updatebtnDialog = dialogView.findViewById(R.id.updatebtnDialog);
                deleteBtnDialog = dialogView.findViewById(R.id.deleteBtnDialog);
                dialogBuilder.setTitle("Checking input info");
                final AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();

                updatebtnDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String bkId = bookObj.getId();
                        String bookName = bookNameDialog.getText().toString();
                        String category = bookCategoryDialog.getSelectedItem().toString();
                        String bookQuant2 = bookQuantityDialog.getText().toString();

                        updateMethod(bkId,bookName,category,bookQuant2);
                        alertDialog.dismiss();

                    }
                });

                deleteBtnDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //String bkId = bookObj.getId();
                        dbReference.child(bookObj.getId()).removeValue();
                        //DeleteMethod(bkId);
                        alertDialog.dismiss();

                    }
                });

                return false;
            }
        });


    }



    public void save(View view) {
        String nameBook = bkName.getText().toString();
        String categoryBook = bkCategory.getSelectedItem().toString();
        String quantityBook = bKQuantity.getText().toString();

        if(!TextUtils.isEmpty(nameBook)){

            String id = dbReference.push().getKey();

            BookPropertyListVwCls bookProperty = new BookPropertyListVwCls(id,nameBook,categoryBook,quantityBook);
            dbReference.child(id).setValue(bookProperty);
            Toast.makeText(this,"insertion done",Toast.LENGTH_LONG).show();
            displayNotification("new book is available here !!","from now you can borrow "+bkName.getText().toString());

        }
        else{
            Toast.makeText(this,"plz give a name of book",Toast.LENGTH_LONG).show();
        }


    }
    public void getData(View view) {
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bookPropertyList.clear();
                for(DataSnapshot bookSnapShot:dataSnapshot.getChildren()){
                    BookPropertyListVwCls bookPropertyObj1 = bookSnapShot.getValue(BookPropertyListVwCls.class);
                    bookPropertyList.add(bookPropertyObj1);
                    CustomAdapterSearchBook adapterSearchBook = new CustomAdapterSearchBook(ProvideBook.this,bookPropertyList);
                    if(bookPropertyList!=null){
                        listViewBook.setAdapter(adapterSearchBook);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


    }


    private void updateMethod(String bkid, String bookName, String category, String bookQuant2) {
        BookPropertyListVwCls bookPropertyObj = new BookPropertyListVwCls(bkid,bookName,category,bookQuant2);
        dbReference.child(bkid).setValue(bookPropertyObj);
        Toast.makeText(this,"update operation successful",Toast.LENGTH_LONG).show();


    }

    private void displayNotification(String title, String msg)
    {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, Channel_id)
                        .setContentTitle(title)
                        .setContentText(msg)
                        .setSmallIcon(R.drawable.ic_stat_notification)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat =
                NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1, mBuilder.build());

    }

}

    


