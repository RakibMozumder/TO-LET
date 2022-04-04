package com.example.to_let.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.to_let.R;
import com.example.to_let.Model.UserInformation;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<UserInformation> {

    private Context context;
    private List<UserInformation> userInformationList;

    public CustomAdapter(Context context,List<UserInformation> userInformationL) {
        super(context,0,userInformationL);
        this.context=context;
        userInformationList=userInformationL;
    }





    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        View row=convertView;
        if(row==null){
          //  LayoutInflater inflater=context.getLayoutInflater();
            row=LayoutInflater.from(context).inflate(R.layout.sample_profile_layout,parent,false);//specific main work
            UserInformation userInformation=userInformationList.get(position);
            TextView textView1=(TextView) row.findViewById(R.id.listviewRowTextId1);
            TextView textView2=(TextView)row.findViewById(R.id.listviewRowTextId2);
            TextView textView3=(TextView) row.findViewById(R.id.listviewRowTextId3);
            TextView textView4=(TextView)row.findViewById(R.id.listviewRowTextId4);
            TextView textView5=(TextView) row.findViewById(R.id.listviewRowTextId5);
            TextView textView6=(TextView)row.findViewById(R.id.listviewRowTextId6);
            textView1.setText("First Name: "+userInformation.getUserFirstName());
            textView2.setText("Last Name: "+userInformation.getUserLastName());
            textView3.setText("Email: "+userInformation.getUserEmail());
            textView4.setText("Password: "+userInformation.getUserPassword());
            textView5.setText("Phone Number: "+userInformation.getUserPhone());
            textView6.setText("City: "+userInformation.getUserCity());


        }else{
            UserInformation userInformation=userInformationList.get(position);
            TextView textView1=(TextView) row.findViewById(R.id.listviewRowTextId1);
            TextView textView2=(TextView)row.findViewById(R.id.listviewRowTextId2);
        }
        return row;

    }


}
