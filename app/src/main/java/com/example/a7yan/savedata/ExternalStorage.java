package com.example.a7yan.savedata;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ExternalStorage extends AppCompatActivity {

    private ImageView iv;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);
        iv = (ImageView) findViewById(R.id.iv);
        btn = (Button) findViewById(R.id.btn);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Bitmap bitmap= IOUtil.readImg("newTest.jpg");
               iv.setImageBitmap(bitmap);
            }
        });
    }
    public void saveImg(View view)
    {
       Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.test);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //将图片进行压缩
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        //回收图片
        bitmap.recycle();
        boolean save=IOUtil.saveImg("newTest.jpg",baos.toByteArray());
        if(save){
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        }
        try {
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
