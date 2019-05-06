package com.example.booksharing;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.booksharing.model.DbHelperClassUserPointTable;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class ViewProfile extends AppCompatActivity {

    private static final int CHOOSE_IMAGE =101 ;
    ImageView imgDisplay;
    EditText edtDisplayName;
    Uri uriProfileImage;
    ProgressBar progressBar;
    String profileImageUrl;
    FirebaseAuth mAuth;
    Button btnViewPoint;
    DbHelperClassUserPointTable db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth=FirebaseAuth.getInstance();
        edtDisplayName=findViewById(R.id.editTextDisplayName);
        imgDisplay=findViewById(R.id.imageViewDisplay);
        btnViewPoint=(Button)findViewById(R.id.buttonViewPoint);
        db=new DbHelperClassUserPointTable(this);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        loadUserInformation();

        imgDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageChooser();
            }
        });
        findViewById(R.id.buttonSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInformation();

            }
        });
        ViewData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()==null)
        {
            finish();
            startActivity(new Intent(this,LogIn.class));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menulayout,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menuProfileInfo:
                Toast.makeText(this, "Provide book function will work", Toast.LENGTH_LONG).show();
                break;
            case R.id.menuProvideBook:
                gotoProvideBook();
                break;
            case R.id.menuSearchBook:
                gotoSearchBook();
                break;
            case R.id.menuReturnBook:
                gotoReturnBook();

                break;
            case R.id.menuRatioPoint:
               gotoViewRatio();
                break;
            case R.id.menuSettings:
                Toast.makeText(this, "Settings function will work", Toast.LENGTH_LONG).show();
                break;
            case R.id.menuLogout:
                logout();
                break;

        }
        return  true;
    }

    private void gotoProvideBook() {
        Intent intent = new Intent(this,ProvideBook.class);
        startActivity(intent);
    }

    private void gotoSearchBook() {
        Intent intent = new Intent(this,SearchBookActivity.class);
        startActivity(intent);
    }

    private void gotoReturnBook() {
        Intent intent=new Intent(this,ReturnBook.class);
        startActivity(intent);
    }

    private void gotoViewRatio() {
        Intent intent=new Intent(this,viewPointAndRatio.class);
        startActivity(intent);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(this,MainActivity.class));
    }

    private void loadUserInformation() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            if (user.getPhotoUrl() != null) {
                Glide.with(this).load(user.getPhotoUrl().toString()).into(imgDisplay);
            }

            if(user.getDisplayName()!=null)
            {
                edtDisplayName.setText(user.getDisplayName());
            }

        }
    }

    private void saveUserInformation() {
        String displayName=edtDisplayName.getText().toString();
        if(displayName.isEmpty())
        {
            edtDisplayName.setError("Name Required");
            edtDisplayName.requestFocus();
            return;
        }
        FirebaseUser user=mAuth.getCurrentUser();
        if(user!=null && profileImageUrl!=null)
        {
            UserProfileChangeRequest profile=new UserProfileChangeRequest.Builder().setDisplayName(displayName).setPhotoUri(Uri.parse(profileImageUrl)).build();

            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(ViewProfile.this,"Profile Updated",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CHOOSE_IMAGE && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            uriProfileImage=data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uriProfileImage);
                imgDisplay.setImageBitmap(bitmap);
                uploadImageToFirebaseStorage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImageToFirebaseStorage() {
        final StorageReference profileImageRef= FirebaseStorage.getInstance().getReference("profilepics/"+System.currentTimeMillis()+".jpg");


        if(uriProfileImage!=null)
        {
            progressBar.setVisibility(View.VISIBLE);
            /*profileImageRef.putFile(uriProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressBar.setVisibility(View.GONE);

                               profileImageUrl=taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ViewProfile.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });*/

            profileImageRef.putFile(uriProfileImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressBar.setVisibility(View.GONE);


                            profileImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    profileImageUrl = uri.toString();
                                    Toast.makeText(getApplicationContext(), "Image Upload Successful", Toast.LENGTH_SHORT).show();

                                }
                            })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });


        }
    }

    private void showImageChooser()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        CharSequence charSequence = "Select profile image";
        startActivityForResult(Intent.createChooser(intent,charSequence),CHOOSE_IMAGE);
    }



    public void searchingBook(View view) {
        Intent intent = new Intent(this,SearchBookActivity.class);
        startActivity(intent);
    }


    public void ViewData(){
        btnViewPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = db.showData();

                if (data.getCount() == 0) {
                    display("Error", "No Data Found.");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (data.moveToNext()) {
                    buffer.append("ID: " + data.getString(0) + "\n");
                    buffer.append("EMAIL: " + data.getString(1) + "\n");
                    buffer.append("PROVIDE POINT: " + data.getString(2) + "\n");

                    buffer.append("BORROW POINT: " + data.getString(3) + "\n");
                    buffer.append("TOTAL POINT: " + data.getString(4) + "\n");


                    display("All Stored Data:", buffer.toString());
                }
            }
        });

    }
    public void display(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
