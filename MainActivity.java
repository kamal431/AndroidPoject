package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    public final static String MyPREFERENCES = "MyPrefs";

    public static Realm realm;
    public EditText username;
    public EditText password;
    Intent newuser;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Simple Toast", Toast.LENGTH_SHORT);
        Realm.init(this);
//        deleteDB();
        Realm.getDefaultConfiguration();
        realm = Realm.getDefaultInstance();
        sp = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
    }

    public void deleteDB(){
        try {
            Realm.deleteRealm(Realm.getDefaultConfiguration());
        } catch (Exception ex){
            throw ex;
        }
    }

//    public void CreateTable(View view){
//        realm.beginTransaction();
//
//        Contact_Model contact = realm.createObject(Contact_Model.class);
//        contact.setName("Shakeel Ahmed");
//        contact.setEmail("shakeel.cs17@gmail.com");
//        contact.setPassword("shakeel@123");
//        contact.setPhone(92939428);
//        contact.setAge(23);
//
//        realm.commitTransaction();
//        Toast.makeText(this, "Database Created.", Toast.LENGTH_LONG);
//    }

    public void Login(View view){
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        boolean flag = false;
        RealmResults<Contact_Model> results = realm.where(Contact_Model.class).findAll();
        for(Contact_Model i : results){
            if(username.getText().toString().equalsIgnoreCase(i.getEmail()) && password.getText().toString().equalsIgnoreCase(i.getPassword())){
                flag = true;
                editor = sp.edit();
                editor.putString("USERNAME", i.getName());
                editor.putString("EMAIL", i.getEmail());
                editor.putString("PHONE", String.valueOf(i.getPhone()));
                editor.putString("AGE", String.valueOf(i.getAge()));
                editor.commit();
                Intent intent = new Intent(this, Welcome.class);
                startActivity(intent);
            }
//            text.setText(i.getName()+"\n"+i.getEmail()+"\n"+i.getPassword()+"\n"+i.getPhone()+"\n"+i.getAge());
//            System.out.println(i.getName());
//            System.out.println(i.getEmail());
//            System.out.println(i.getPassword());
//            System.out.println(i.getPhone());
//            System.out.println(i.getAge());
        }
        if(flag == false){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("No Record Found");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                            dialog.cancel();
                        }
                    });
//                builder1.setNegativeButton(
//                        "No",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//                            }
//                        });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }

    public void SignUP(View view){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    public void getData(View view){
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        ((TextView) findViewById(R.id.login_check)).setText(username.getText().toString()+"\n"+password.getText().toString());

//        RealmResults<Contact_Model> results = realm.where(Contact_Model.class).findAll();
//        for(Contact_Model i : results){
//            ((TextView) findViewById(R.id.login_check)).setText(i.getName().toString()+"\n"+i.getPassword().toString());
//        }
    }

}
