package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText uname, unumber;
    Button update;
    String nam,num;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        uname = findViewById(R.id.nam);
        unumber = findViewById(R.id.num);
        update = findViewById(R.id.update);

        nam=getIntent().getStringExtra("nam");
        num=getIntent().getStringExtra("num");
        id=getIntent().getIntExtra("id",0);

        uname.setText(nam);
        unumber.setText(num);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String namee=uname.getText().toString();
                String numm=unumber.getText().toString();

                Dbhelper dbhelper = new Dbhelper(UpdateActivity.this);
                dbhelper.onUpgrade1(id,namee,numm);

                Toast.makeText(UpdateActivity.this, "Number Updated", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}