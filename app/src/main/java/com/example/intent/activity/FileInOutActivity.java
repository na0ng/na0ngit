package com.example.intent.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.intent.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class FileInOutActivity extends AppCompatActivity {

    EditText title, content;
    Button saveBtn, loadBtn;

    String content_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_in_out);

        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        saveBtn = findViewById(R.id.saveBtn);
        loadBtn = findViewById(R.id.loadBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String title_text = title.getText().toString().trim();
                content_text = content.getText().toString();

                String title_fin = title_text + ".txt";

                FileOutputStream fileOutputStream = null;

                Log.d("kny_dir", String.valueOf(getFilesDir()));

                try {
                    fileOutputStream = openFileOutput(title_fin, Context.MODE_PRIVATE);
                    byte[] data = content_text.getBytes("UTF-8");
                    fileOutputStream.write(data);

                    Toast.makeText(FileInOutActivity.this, title_fin+ "파일저장", Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        loadBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("kny_load", "loadBtn Click");

                String title_text = title.getText().toString().trim();
                String title_fin = title_text + ".txt";

                Log.d("kny_loadTitle", title_text);

                try {
                    FileInputStream fileInputStream = openFileInput(title_fin);

                    if(fileInputStream == null) {
                        Toast.makeText(FileInOutActivity.this, title_fin+"파일이 존재하지 않습니다.", Toast.LENGTH_LONG).show();
                    }
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

                    String line = bufferedReader.readLine();

                    Toast.makeText(FileInOutActivity.this, line, Toast.LENGTH_LONG).show();
                    Log.d("kny_fileRead", line);

                    bufferedReader.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

    }
}
