package com.prachi.topic7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class AddActivity extends AppCompatActivity {
    EditText etWord;
    EditText etMeaning;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etWord=findViewById(R.id.etWord);
        etMeaning=findViewById(R.id.etMeaning);
        btnSave=findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
            }
        });
    }

    private void Save()
    {
        try
        {
            PrintStream printStream=new PrintStream(openFileOutput("words.txt",MODE_PRIVATE | MODE_APPEND));
            printStream.println(etWord.getText().toString()+"->"+etMeaning.getText().toString());
            Toast.makeText(this,"Saved to" + getFilesDir(),Toast.LENGTH_SHORT).show();

        }
        catch (IOException e)
        {
            Log.d("Meaning","Error : " + e.toString());
            e.printStackTrace();
        }

    }


}
