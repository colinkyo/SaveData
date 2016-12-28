package com.example.a7yan.savedata;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_save;
    private Button btr_restroe;
    private EditText user_name,user_age;
    private SharedPreferences sp;
    private Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        sp = getSharedPreferences("7Yan",MODE_PRIVATE);
    }

    private void initView()
    {
        user_name = (EditText) findViewById(R.id.edit_user);
        user_age = (EditText) findViewById(R.id.edit_age);
        btn_save =(Button) findViewById(R.id.btn_save);
        btn_next = (Button) findViewById(R.id.btn_next);
        btr_restroe = (Button) findViewById(R.id.btn_restroe);
        btn_save.setOnClickListener(this);
        btr_restroe.setOnClickListener(this);
        btn_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_save:
                String name = user_name.getText().toString().trim();
                String age = user_age.getText().toString().trim();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(age)){
                    return;
                }
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("name",name);
                editor.putString("age",age);
                boolean commit = editor.commit();
                if(commit){
                    user_name.setText("");
                    user_age.setText("");
                    Toast.makeText(this, "保存成功！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_restroe:
                String namev= sp.getString("name","");
                String agev= sp.getString("age","");
                user_name.setText(namev);
                user_age.setText(agev);
                break;
            case R.id.btn_next:
                Intent intent=new Intent(MainActivity.this,InternalStorage.class);
                startActivity(intent);
                break;
        }
    }
}
