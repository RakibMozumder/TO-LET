package com.example.to_let;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.AutoCompleteTextView;

import com.example.to_let.Model.CountryItem;

import java.util.ArrayList;
import java.util.List;

public class AutoCompetionCustomSearch extends AppCompatActivity {
    private List<CountryItem> countryList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_auto_competion_custo_search);

        fillCountryList();

        //AutoCompleteTextView editText = findViewById(R.id);        //AutoCompleteCountryAdapter adapter = new AutoCompleteCountryAdapter(this,countryList);
       // editText.setAdapter(adapter);
    }

    private void fillCountryList() {
        countryList = new ArrayList<>();
        countryList.add(new CountryItem("Argentina", R.drawable.argentina));
        countryList.add(new CountryItem("brazil", R.drawable.brazil));
        countryList.add(new CountryItem("germany", R.drawable.germany));
        countryList.add(new CountryItem("united states", R.drawable.united_states));
    }
}