package com.myandroid.sharedpreferencestest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText uNameEdit;
    private EditText uPassEdit;
    private Button loginButton;
    private CheckBox rememberCheckBox;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uNameEdit = (EditText) findViewById(R.id.uname);
        uPassEdit = (EditText) findViewById(R.id.upass);
        loginButton = (Button) findViewById(R.id.login);
        rememberCheckBox = (CheckBox) findViewById(R.id.remember_pass);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember = pref.getBoolean("isRemember",false);
        if (isRemember){
            String uName = pref.getString("uname","");
            String uPass = pref.getString("upass","");
            uNameEdit.setText(uName);
            uPassEdit.setText(uPass);
            rememberCheckBox.setChecked(true);
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uName = uNameEdit.getText().toString();
                String uPass = uPassEdit.getText().toString();
                if (uName.equals("admin") && uPass.equals("123456")){
                    /*
                    Intent intent = new Intent(this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    */
                    editor = pref.edit();
                    if (rememberCheckBox.isChecked()){
                        editor.putString("uname",uName);
                        editor.putString("upass",uPass);
                        editor.putBoolean("isRemember",true);
                    }else{
                        editor.clear();
                    }
                    editor.apply();
                    Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"用户名或密码错误",Toast.LENGTH_LONG).show();
                }

                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("name","tome");
                editor.putInt("age",18);
                editor.putBoolean("married",false);
                editor.apply();
            }
        });
        /*
        buttonGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
                //SharedPreferences pref = getSharedPreferences("data",MODE_PRIVATE);

                String name = preferences.getString("name","");
                int age = preferences.getInt("age",0);
                boolean married = preferences.getBoolean("married",false);

                Log.d("name:",name);
                //Log.d("age:",age);
                //Log.d("marred:",married);

            }
        });
        */
    }

}
