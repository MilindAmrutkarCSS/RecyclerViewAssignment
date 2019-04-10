package com.example.recyclerviewassignment;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends AppCompatActivity {

    ArrayList<String> namesList;

    @BindView(R.id.tvName)
    TextView tvName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        namesList = getIntent().getStringArrayListExtra("names");

        String namesOfEmployees = "";

        for (int i = 0; i < namesList.size(); i++) {
            namesOfEmployees += namesList.get(i) + " ";
        }
        tvName.setText(namesOfEmployees);
    }
}
