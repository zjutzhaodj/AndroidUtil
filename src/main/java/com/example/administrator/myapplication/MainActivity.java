package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    private Button show;
    private Button dis;
    private Button dialog;
    private Button cusdialog;
    PopWindowManager popWindowManager;
    DialogManager dialogManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        cusdialog= (Button) findViewById(R.id.cusdialog);
        cusdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogManager=new DialogManager(MainActivity.this);
                dialogManager.showDialog();
                dialogManager.setOnDialogClickListener(new DialogManager.onDialogClickListener() {
                    @Override
                    public void onPositiveClick() {
                        Toast.makeText(MainActivity.this,"Positive",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNegtiveClick() {
                        Toast.makeText(MainActivity.this,"Negtive",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        dialog= (Button) findViewById(R.id.dialog);
        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 dialogManager=new DialogManager(MainActivity.this,R.layout.dialog);
                dialogManager.showDialog();
            }
        });
        show= (Button) findViewById(R.id.show);
        dis= (Button) findViewById(R.id.dis);
        popWindowManager=new PopWindowManager(this,R.layout.popwindow);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindowManager.showPopWindow(getLayoutInflater().inflate(R.layout.activity_main,null));

            }
        });
        dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindowManager.dismisPopWindow();
            }
        });

    }



}
