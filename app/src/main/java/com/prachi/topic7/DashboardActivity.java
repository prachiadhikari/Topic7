package com.prachi.topic7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DashboardActivity extends AppCompatActivity {
    private ListView lstDictonary;
    Button btnAdd,btnDisplay;

    private Map<String, String> dictionary;
    private void readFromFile()
    {
        try {
            FileInputStream fos=openFileInput("word.txt");
            InputStreamReader isr=new InputStreamReader(fos);
            BufferedReader br=new BufferedReader(isr);
            String line ="";
            while((line=br.readLine())!=null)
            {
                String[] parts=line.split("->");
                dictionary.put(parts[0],parts[1]);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        lstDictonary=findViewById(R.id.lstDictionary);
        dictionary=new HashMap<>();
        btnAdd=findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Intent intent = new Intent(DashboardActivity.this,AddActivity.class);
             startActivity(intent);
            }
        });

        btnDisplay=findViewById(R.id.btnDisplay);


        readFromFile();

        ArrayAdapter adapter=new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(dictionary.keySet()));

        lstDictonary.setAdapter(adapter);
        lstDictonary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key =parent.getItemAtPosition(position).toString();
                String meaning=dictionary.get(key);
                // Toast.makeText(getApplicationContext(),capital.toString(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(DashboardActivity.this,DisplayActivity.class);
                intent.putExtra("meaning",meaning);
                startActivity(intent);

            }
        });
    }
}
