package com.example.to_let.Activity;

import android.app.ProgressDialog;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.to_let.Model.PostInformation;
import com.example.to_let.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.UUID;


public class PostActivity extends AppCompatActivity {
    private EditText area,price,bedroom,phoneNumber,size;
    private FirebaseAuth mAuth;
    private Button post,chooseButton;
    private TextView areaMap,latest,chooseTextView;
    DatabaseReference databaseReference;
    Calendar calendar;
    String currentDate;
    ImageView imageView;
    private Uri imageUri;
    private static final int IMAGE_REQUEST=1;

    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;
    FirebaseStorage storage;
    StorageReference storageReference;
    String imageUrl="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        mAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("PostInformation");

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        //  areaMap=findViewById(R.id.areaMapId);
        phoneNumber=findViewById(R.id.phoneNumberPostId);
        area=findViewById(R.id.areaId);
        price=findViewById(R.id.priceId);
        bedroom=findViewById(R.id.bedRoomId);
        latest=findViewById(R.id.latestId);
        size=findViewById(R.id.sizeId);


        post=findViewById(R.id.postId);
        chooseButton=findViewById(R.id.btnchooseId);
        chooseTextView=findViewById(R.id.txtchooseId);
        calendar=Calendar.getInstance();
        currentDate= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
      /*  imageView=findViewById(R.id.Propic);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                galleryIntent.setAction(galleryIntent.ACTION_GET_CONTENT);
                startActivityForResult(galleryIntent,IMAGE_REQUEST);



            }
        });*/
        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneNumber.getText().toString().trim();
                String a = area.getText().toString().trim();
                String b = bedroom.getText().toString().trim();
                String c=  imageUrl.trim();
                String p = price.getText().toString().trim();
                String s = size.getText().toString().trim();

                if(phone.isEmpty()){
                    phoneNumber.setError("Enter Phone Number");
                    phoneNumber.requestFocus();
                    return;
                }
                if(phone.length()<11 ){
                    phoneNumber.setError("PhoneNumber must be 11 digits ");
                    phoneNumber.requestFocus();
                    return;
                }

                if(a.isEmpty()){
                    area.setError("Enter location");
                    area.requestFocus();
                    return;
                }if(b.isEmpty()){
                    bedroom.setError("Enter Bed Room Number");
                    bedroom.requestFocus();
                    return;
                }
                if(p.isEmpty()){
                    price.setError("Enter Price");
                    price.requestFocus();
                    return;

                }
                if(s.isEmpty()){
                    size.setError("Enter Flat size in Sq.Ft");
                    size.requestFocus();
                    return;

                }
                uploadImage();
                postData();
            }
        });



    }


    private void uploadImage() {
        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            imageUrl=ref.toString();

            ref.putFile(filePath)

                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(PostActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(PostActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
//            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                @Override
//                public void onSuccess(Uri uri) {
//                    Log.e("Tuts+", "uri: " + uri.toString());
//                    //imageUrl=uri.toString();
//
//                    //Handle whatever you're going to do with the URL here
//                }
//            });
        }

    }

    public void postData() {
        String phoneS=phoneNumber.getText().toString().trim();
        String areaS=area.getText().toString().trim();
        String bedroomS=bedroom.getText().toString().trim();
        String image=imageUrl;
        String priceS=price.getText().toString().trim();
        String sizeS=size.getText().toString().trim();
        latest.setText(currentDate);
        String latestS=currentDate;
        String key=databaseReference.push().getKey();
        PostInformation postInformation=new PostInformation(phoneS,areaS,bedroomS,image,latestS,priceS,sizeS);
        databaseReference.child(key).setValue(postInformation);
        phoneNumber.setText("");
        area.setText("");
        bedroom.setText("");
        latest.setText("");
        price.setText("");
        size.setText("");
        Toast.makeText(PostActivity.this,"Submitted",Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       /* if(requestCode==IMAGE_REQUEST && resultCode==RESULT_OK && data.getData()!=null){
            imageUri=data.getData();
            imageView.setImageURI(imageUri);
        }*/
        if(requestCode==12){
            if(resultCode==RESULT_OK){
                String location=data.getStringExtra("result");
                area.setText(location);
            }
            if(resultCode==RESULT_CANCELED){
                Toast.makeText(PostActivity.this,"Cancelled",Toast.LENGTH_SHORT).show();
            }
        }

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//                imageView.setImageBitmap(bitmap);
//            }
//            catch (IOException e)
//            {
//                e.printStackTrace();
//            }
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Toast.makeText(PostActivity.this,"Login needed for creating Home Rent Post",Toast.LENGTH_SHORT).show();
            Intent intent2 = new Intent(this, LoginActivity.class);
            startActivity(intent2);

        }

        if (currentUser != null) {
            //loginUserAndUpdateInstanceId(currentUser.getUid());
            //finish();
        }

    }

}
