package com.example.to_let.Activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.to_let.R;


public class SearchCriteria2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
    private Button areaButton,latestButton,priceButton,bedButton,ratingButton;

    private Button searchButton;
    private CheckBox radioButtonArea,radioButtonBed,radioButtonLatest,radioButtonPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_criteria2);
        searchButton=findViewById(R.id.searchButtonId2);
        radioButtonArea=findViewById(R.id.radioButtonAreaId);
        radioButtonBed=findViewById(R.id.radioButtonBedId);
        radioButtonLatest=findViewById(R.id.radioButtonLatestId);
        radioButtonPrice=findViewById(R.id.radioButtonPriceId);
        //NiceSpinner niceSpinner = (NiceSpinner) findViewById(R.id.spinnerMajor);
        // spinnerClasses = (NiceSpinner) findViewById(R.id.spinnerClass);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // int radioID=radioGroup.getCheckedRadioButtonId();
              //  radioButton=findViewById(radioID);

                 if(v.getId()==R.id.radioButtonBedId && v.getId()==R.id.radioButtonLatestId) {
                    Intent intent = new Intent(SearchCriteria2.this, FirebaseBedLatestSearchActivity.class);
                    startActivity(intent);
                }
                else if(v.getId()==R.id.radioButtonLatestId && v.getId()==R.id.radioButtonPriceId) {
                    Intent intent = new Intent(SearchCriteria2.this, FirebaseLatestPriceSearchActivity.class);
                    startActivity(intent);
                }
                else if(v.getId()==R.id.radioButtonPriceId && v.getId()==R.id.radioButtonAreaId) {
                    Intent intent = new Intent(SearchCriteria2.this, FirebaseAreaPriceSearchActivity.class);
                    startActivity(intent);
                }
                else if(v.getId()==R.id.radioButtonAreaId && v.getId()==R.id.radioButtonLatestId) {
                    Intent intent = new Intent(SearchCriteria2.this, FirebaseAreaLatestSearchActivity.class);
                    startActivity(intent);
                }
                else if(v.getId()==R.id.radioButtonBedId && v.getId()==R.id.radioButtonPriceId) {
                    Intent intent = new Intent(SearchCriteria2.this, FirebaseBedPriceSearchActivity.class);
                    startActivity(intent);
                }
            }
        });

       /* majors = new LinkedList<>(Arrays.asList("English", "Mathematics", "Higher Mathematics", "Physics", "Chemistry","Biology "));
        English = new LinkedList<>(Arrays.asList("Class1", "Class2", "Class3", "Class4", "Class5","Class6", "Class7", "Class8", "Class9", "Class10", "Class11", "Class12"));
        Math = new LinkedList<>(Arrays.asList("Class1", "Class2", "Class3", "Class4", "Class5","Class6", "Class7", "Class8", "Class9", "Class10", "Class11", "Class12"));
        HigherMath = new LinkedList<>(Arrays.asList("Class1", "Class2", "Class3", "Class4", "Class5","Class6", "Class7", "Class8", "Class9", "Class10", "Class11", "Class12"));
        Physics = new LinkedList<>(Arrays.asList( "Class9", "Class10", "Class11", "Class12"));
        Chemistry = new LinkedList<>(Arrays.asList( "Class9", "Class10", "Class11", "Class12"));
        Biology = new LinkedList<>(Arrays.asList("Class9", "Class10", "Class11", "Class12"));

        classes = new HashMap<String, List<String>>();
        spinnerMajor = (NiceSpinner) findViewById(R.id.spinnerMajor);
        spinnerClasses = (NiceSpinner) findViewById(R.id.spinnerClass);

        classes.put("English",English);
        classes.put("Mathematics",Math);
        classes.put("Higher Mathematics",HigherMath);
        classes.put("Physics",Physics);
        classes.put("Chemistry",Chemistry);
        classes.put("Biology",Biology);

        spinnerMajor.attachDataSource(majors);
        index=0;
        spinnerMajor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                index= i;
                spinnerClasses.attachDataSource(classes.get(majors.get(index)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                index=0;
                spinnerClasses.attachDataSource(classes.get(majors.get(index)));
            }
        });*/

        /*FButton fsearch= (FButton) findViewById(R.id.searchButtonId);
        fsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tosend =new int[]{spinnerMajor.getSelectedIndex(),spinnerClasses.getSelectedIndex()};
                Intent intent = new Intent(SearchCriteria.this, FirebaseAreaSearchActivity.class);
                //intent.putExtra("major and class",tosend);
                startActivity(intent);
            }
        });
*/
        //int index = 0;

        // spinnerClasses.attachDataSource(classes.get(majors.get(index)));

        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        if(text=="Two option"){
            Toast.makeText(parent.getContext(),"You are already in here", Toast.LENGTH_SHORT).show();
        }
        if(text=="One option"){
            Intent intent = new Intent(parent.getContext(), SearchCriteria.class);
            startActivity(intent);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}
/*

    </LinearLayout>
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentStart="true"
        android:layout_alignParentTop="true"
        android:layout_marginStart="80dp"
        android:id="@+id/selectMajor"
        android:layout_marginTop="40dp"
        android:text="Select Major"
        android:textColor="#ffffff"
        android:textSize="20dp"
        android:layout_alignParentLeft="true"
        android:layout_marginLeft="60dp" />

    <org.angmarch.views.NiceSpinner
        android:id="@+id/spinnerMajor"
        android:layout_width="30dp"
        android:layout_height="wrap_content"
        android:layout_below="@+id/selectMajor"
        android:layout_alignStart="@+id/selectMajor"
        android:layout_alignLeft="@+id/selectMajor"
        android:layout_marginLeft="160dp"
        app:textTint="@color/colorPrimaryDark" />

    <TextView
        android:id="@+id/SelectClass"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignStart="@+id/spinnerMajor"
        android:layout_below="@+id/spinnerMajor"
        android:layout_marginTop="34dp"
        android:textColor="#ffffff"
        android:text="Select Class"
        android:textSize="20dp"
        android:layout_marginLeft="80dp"/>

    <org.angmarch.views.NiceSpinner
        android:id="@+id/spinnerClass"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignStart="@+id/SelectClass"
        android:layout_below="@+id/SelectClass"
        app:textTint="@color/colorPrimaryDark"
        android:layout_marginLeft="180dp"/>



    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"

        android:orientation="vertical">


        <Button
            android:id="@+id/ratingId"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"

            android:background="#E0F1F1"
            android:text="RATINGS"
            android:textColor="#049C0D"
            android:textColorHighlight="#2196F3"
            android:textSize="15sp" />

        <Button
            android:id="@+id/latestId"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"

            android:background="#E0F1F1"
            android:text="LATEST"
            android:textColor="#049C0D"
            android:textSize="15sp" />

        <Button
            android:id="@+id/priceId"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"

            android:background="#E0F1F1"
            android:text="PRICE"
            android:textColor="#049C0D"
            android:textSize="15sp" />

        <Button
            android:id="@+id/bedId"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"

            android:background="#E0F1F1"
            android:text="BEDS"
            android:textColor="#049C0D"
            android:textSize="15sp" />

        <Button
            android:id="@+id/areaId"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"

            android:background="#E0F1F1"
            android:text="AREA"
            android:textColor="#049C0D"
            android:textSize="15sp" />

    </LinearLayout>
 */