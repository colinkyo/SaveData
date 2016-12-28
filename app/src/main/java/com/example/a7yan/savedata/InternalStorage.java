package com.example.a7yan.savedata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InterruptedIOException;

public class InternalStorage extends AppCompatActivity {

    private EditText file_name, et_content;
    Button btn_save, btn_open, btn_del, btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);
        file_name = (EditText) findViewById(R.id.file_name);
        et_content = (EditText) findViewById(R.id.et_content);

        btn_save = (Button) findViewById(R.id.btn_write);
        btn_open = (Button) findViewById(R.id.btn_read);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_next = (Button) findViewById(R.id.btn_next);
    }
    public void saveFile(View view){
        String filename =file_name.getText().toString().trim();
        String content=et_content.getText().toString().trim();
        if(TextUtils.isEmpty(filename) || TextUtils.isEmpty(content)){
            return;
        }
        try {
            FileOutputStream fos= openFileOutput(filename,MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
            file_name.setText("");
            et_content.setText("");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    public void openFile(View view){
        String filename =file_name.getText().toString().trim();
        if(TextUtils.isEmpty(filename)){
            return;
        }
        try {
            FileInputStream fis=openFileInput(filename);
            byte[] buffer=new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            et_content.setText(new String(buffer));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    public void delFile(View view){
        String filename =file_name.getText().toString().trim();
        if(TextUtils.isEmpty(filename)){
            return;
        }
        boolean delfile=deleteFile(filename);
        if(delfile){
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
            file_name.setText("");
            et_content.setText("");
        }
    }
    public  void next(View view){
        Intent intent = new Intent(InternalStorage.this,ExternalStorage.class);
        startActivity(intent);
    }
}
