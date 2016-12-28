package com.example.a7yan.savedata;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;

import java.io.FileOutputStream;

/**
 * Created by 7Yan on 2016/12/28.
 */

public class IOUtil {
    public static final String STORE_PATH=Environment
            .getExternalStorageDirectory()
            +File.separator
            +"7Yan"
            +File.separator+"images";
    //判断SD卡是否挂载
    public static boolean isMounted(){
        String state= Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }
    //保存图片
    public  static  boolean  saveImg(String fileName,byte[] data){
        //Log.i("7Yan",STORE_PATH);
        if(!isMounted()){
            Log.i("7Yan","No sd card");
            return false;
        }
        File dir = new File(STORE_PATH);
        if(!dir.exists()){
            Log.i("7Yan","创建目录");
            //创建单层目录
            //dir.mkdir();
            //创建多层目录
            dir.mkdirs();
        }
        try {
            FileOutputStream fos = new FileOutputStream(new File(dir,fileName));
            fos.write(data);
            fos.close();
            return true;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    //读取图片
    public  static Bitmap readImg(String fileName){
        if(!isMounted()){
            return null;
        }
        File imgFile = new File(STORE_PATH,fileName);
        if(imgFile.exists()){
            return BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        }
        return null;
    }
}
