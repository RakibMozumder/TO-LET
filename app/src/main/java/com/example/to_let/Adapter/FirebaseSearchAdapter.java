package com.example.to_let.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.to_let.Activity.DetailsActivity;
import com.example.to_let.Model.FirebaseSearchModel;
import com.example.to_let.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FirebaseSearchAdapter extends RecyclerView.Adapter<FirebaseSearchAdapter.FirebaseViewHolder> {
    private Context mcontext;
    private ArrayList<FirebaseSearchModel> artistList;

    public FirebaseSearchAdapter(Context context,ArrayList<FirebaseSearchModel> artistLi) {
        mcontext=context;
        artistList = artistLi;
    }




    @Override
    public   FirebaseViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.sample_firebase_latest_search, parent, false);
        return new FirebaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder( FirebaseViewHolder holder, int position) {
        FirebaseSearchModel artist = artistList.get(position);
        holder.textViewName.setText("Area: "+ artist.getArea());
        holder.textViewGenre.setText("Bed Room: "+ artist.getBedroom());
        holder.textViewCountry.setText("Date: "+ artist.getLatest());
        holder.textViewAge.setText("Price: "+ artist.getPrice()+" BDT");
        holder.textViewSize.setText("Flat Size: "+ artist.getSize()+"Sq.Ft.");
        holder.textViewPhone.setText("Contact Number: "+ artist.getPhoneNumber());

    /*    holder.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent profileIntent=new Intent(v.getContext(), DetailsActivity.class);
                //profileIntent.putExtra("user_id",user_id);
             v.getContext().startActivity(profileIntent);
            }
        });*/
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
//            StorageReference storageRef = storage.getReferenceFromUrl("gs://to-let-18884.appspot.com/images/49c5fbd6-ba88-4fba-a634-e1f462c23697");
//            //String urlImage = postInformation.getImage().toString();
//            Glide.with(mcontext)
//                    .load("gs://to-let-18884.appspot.com/images/49c5fbd6-ba88-4fba-a634-e1f462c23697")
//                    .into(holder.imageView);
//            //Picasso.get().load(String.valueOf(storageRef)).into(i);
//        }catch (Exception e){
//
//        }
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl(artist.getImage());//"gs://to-let-18884.appspot.com/images/49c5fbd6-ba88-4fba-a634-e1f462c23697"
        try {
            final File localFile = File.createTempFile("images", "jpg");
            storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    holder.imageView.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                }
            });
        } catch (IOException e ) {}



    }

    @Override
    public int getItemCount() {
        return artistList.size();
    }

    public class FirebaseViewHolder extends RecyclerView.ViewHolder  {
      // View mView;
        LinearLayout parentLayout;
        TextView textViewName, textViewGenre, textViewAge, textViewCountry,textViewSize,textViewPhone;
        ImageView imageView;

        public FirebaseViewHolder( View itemView) {
            super(itemView);
         //   mView = itemView;
            textViewName = itemView.findViewById(R.id.areaId);
            textViewGenre = itemView.findViewById(R.id.bedRoomId);
            textViewCountry = itemView.findViewById(R.id.latestId);
            textViewAge = itemView.findViewById(R.id.priceId);
            textViewSize=itemView.findViewById(R.id.sizeId1);
            textViewPhone=itemView.findViewById(R.id.phoneId);
            imageView=itemView.findViewById(R.id.imageSampleId);
            parentLayout = itemView.findViewById(R.id.parentLayoutId);
        }

    }

}
