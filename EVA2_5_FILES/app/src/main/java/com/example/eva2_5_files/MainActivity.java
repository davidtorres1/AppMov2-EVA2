package com.example.eva2_5_files;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText edtText;
    Button btnRead, btnSave;
    final String archivo="w.txt";
    final int PERMISO_ESCRITURA = 1000;
    String sRutaSD;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtText = findViewById(R.id.edtText);
        btnRead = findViewById(R.id.btnRead);
        btnSave = findViewById(R.id.btnSave);

        sRutaSD = getExternalFilesDir(null).getPath();
        Toast.makeText(this,sRutaSD,Toast.LENGTH_SHORT).show();
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISO_ESCRITURA);
        }

    }

    public void onRead(View v)  {
        try {
            File file = new File(getExternalFilesDir(null),archivo);
            FileInputStream fis = new FileInputStream(file);

            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String sCade;

            try {
                while ((sCade = br.readLine()) != null) {
                    edtText.append(sCade);

                }
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void onSave(View v){
        try {
            File file = new File(getExternalFilesDir(null),archivo);
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            try{
            bw.write(edtText.getText().toString());
            bw.close();
        }catch (FileNotFoundException e){
                e.printStackTrace();
            }} catch (Exception e) {
            e.printStackTrace();
        }
    }
}
