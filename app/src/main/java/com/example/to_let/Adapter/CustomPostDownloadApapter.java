package com.example.to_let.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.to_let.Model.PostInformation;
import com.example.to_let.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CustomPostDownloadApapter extends ArrayAdapter<PostInformation> {
    private AppCompatActivity context;
    private List<PostInformation> postInformationList;

    public CustomPostDownloadApapter(AppCompatActivity context, List<PostInformation> postInformationList) {
        super(context, R.layout.sample_searchresult_layout, postInformationList);
        this.context=context;
        this.postInformationList=postInformationList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if(view==null){
        LayoutInflater layoutInflater=context.getLayoutInflater();
        view=layoutInflater.inflate(R.layout.sample_searchresult_layout,null,true);
        PostInformation postInformation=postInformationList.get(position);
        TextView tPhone=view.findViewById(R.id.phoneNumberPostSampleId);
        TextView t1=view.findViewById(R.id.areaSampleId);
        TextView t2=view.findViewById(R.id.bedRoomSampleId);
        ImageView i=(ImageView) view.findViewById(R.id.imageSampleId2);
        TextView t3=view.findViewById(R.id.latestSampleId);
        TextView t4=view.findViewById(R.id.priceSampleId);
        TextView t5=view.findViewById(R.id.sizeSampleId);
        tPhone.setText("Phone: "+postInformation.getPhoneNumber());
        t1.setText("Area: "+postInformation.getArea());
        t2.setText("BedRoom: "+postInformation.getBedroom());

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl(postInformation.getImage());//"gs://to-let-18884.appspot.com/images/49c5fbd6-ba88-4fba-a634-e1f462c23697"
        try {
            final File localFile = File.createTempFile("images", "jpg");
            storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    i.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                }
            });
        } catch (IOException e ) {}
//        try {
////                Picasso.get()
////                        .load(postInformation.getImage())
////                        .resize(120, 120)
////                        .centerCrop()
////                        .into(i);
////            Picasso.get().load(postInformation.getImage().toString()).into(i);
//
//            //String imageUri = postInformation.getImage();
//            //Picasso.get().load(imageUri).into(i);
//            FirebaseStorage storage = FirebaseStorage.getInstance();
//            StorageReference storageRef = storage.getReferenceFromUrl(postInformation.getImage());
//            //String urlImage = postInformation.getImage().toString();
////            Glide.with(context)
////                    .load(storageRef)
////                    .into(i);
//            Picasso.get().load(postInformation.getImage()).into(i);
//        }catch (Exception e){
//        }
//
        t3.setText("Submission of this post : "+postInformation.getLatest());
        t4.setText("Price: BDT "+postInformation.getPrice());
        t5.setText("Flat Size: "+postInformation.getSize()+"Sq.Ft.");

        }else{
            PostInformation postInformation=postInformationList.get(position);
            TextView tPhone=view.findViewById(R.id.phoneNumberPostSampleId);
            TextView t1=view.findViewById(R.id.areaSampleId);
            TextView t2=view.findViewById(R.id.bedRoomSampleId);
            ImageView i=(ImageView) view.findViewById(R.id.imageSampleId2);
            TextView t3=view.findViewById(R.id.latestSampleId);
            TextView t4=view.findViewById(R.id.priceSampleId);
            TextView t5=view.findViewById(R.id.sizeSampleId);
            tPhone.setText("Phone: "+postInformation.getPhoneNumber());
            t1.setText("Area: "+postInformation.getArea());
            t2.setText("BedRoom: "+postInformation.getBedroom());
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReferenceFromUrl(postInformation.getImage());//"gs://to-let-18884.appspot.com/images/49c5fbd6-ba88-4fba-a634-e1f462c23697"
            try {
                final File localFile = File.createTempFile("images", "jpg");
                storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        i.setImageBitmap(bitmap);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                    }
                });
            } catch (IOException e ) {}

//            try {
////                Picasso.get()
////                        .load(postInformation.getImage())
////                        .resize(120, 120)
////                        .centerCrop()
////                        .into(i);
////                Picasso.get().load(postInformation.getImage().toString()).into(i);
//                //String imageUri = postInformation.getImage();
//                //Picasso.get().load(imageUri).into(i);
//                FirebaseStorage storage = FirebaseStorage.getInstance();
//                StorageReference storageRef = storage.getReferenceFromUrl(postInformation.getImage());
//                //String urlImage = postInformation.getImage().toString();
////                Glide.with(context)
////                        .load(storageRef)
////                        .into(i);
//                Picasso.get().load(postInformation.getImage()).into(i);
//            }catch (Exception e){
//            }


            t3.setText("Submission of this post : "+postInformation.getLatest());
            t4.setText("Price: BDT "+postInformation.getPrice());
            t5.setText("Flat Size: "+postInformation.getSize()+"Sq.Ft.");
        }
        return view;
    }


}
