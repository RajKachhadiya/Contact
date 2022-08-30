package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    EditText name, number;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detail);

        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
        save = findViewById(R.id.save);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name.getText().toString().trim().equalsIgnoreCase("")) {
                    name.setError("Name Nakho Pela");
                } else if (number.getText().toString().trim().equalsIgnoreCase("")) {
                    number.setError("Number Nakho Pela");
                } else {

                    String Name = name.getText().toString();
                    String Number = number.getText().toString();

                    Dbhelper dbhelper = new Dbhelper(DetailActivity.this);

                    dbhelper.insertdata(Name, Number);

                    Toast.makeText(DetailActivity.this, "Number Save", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(DetailActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

        });

        name.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                name.setError(null);

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (name.getText().toString().equals("")) {
                    name.setError("Name Nakho Pela");
                } else {
                    number.setError(null);
                }
            }
        });
        number.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                number.setError(null);

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (number.getText().toString().equals("")) {
                    number.setError("Number Nakho Pela");
                } else {
                    name.setError(null);
                }
            }
        });

    }
}