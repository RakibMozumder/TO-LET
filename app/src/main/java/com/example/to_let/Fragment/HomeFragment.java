package com.example.to_let.Fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.to_let.Activity.MainActivity;
import com.example.to_let.R;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Intent intent=new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        return inflater.inflate(R.layout.fragment_home,container,false);


    }
}
