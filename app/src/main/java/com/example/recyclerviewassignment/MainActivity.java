package com.example.recyclerviewassignment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IItemClick {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    Set<String> checkedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DataAdapter dataAdapter = new DataAdapter(this, generateData());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dataAdapter);
        checkedItem = new HashSet<>();
    }

    @Override
    public void onItemClick(String name) {
        Toast.makeText(this, "Selected: " + name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemCheck(String name) {
        checkedItem.add(name);
        Log.d(MainActivity.class.getSimpleName(), "onItemCheck: " + name);
    }

    @Override
    public void onItemUncheck(String name) {
        checkedItem.remove(name);
    }

    private List<Data> generateData() {
        GenerateData.setData();
        return GenerateData.getData();
    }

    @OnClick(R.id.btnSubmit)
    public void onViewClicked() {
        Intent intent = new Intent(this, SecondActivity.class);
        String[] names = new String[checkedItem.size()];
        checkedItem.toArray(names);
        ArrayList<String> stringList = new ArrayList<>(Arrays.asList(names));
        intent.putStringArrayListExtra("names", stringList);
        startActivity(intent);
    }
}
