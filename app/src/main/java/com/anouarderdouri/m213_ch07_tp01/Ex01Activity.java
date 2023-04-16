package com.anouarderdouri.m213_ch07_tp01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

public class Ex01Activity extends AppCompatActivity {
    ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    Button btnEx01CheckAll, btnEx01UncheckAll, btnEx01Reverse, btnEx01Display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex01);

        btnEx01CheckAll = findViewById(R.id.btnEx01CheckAll);
        btnEx01UncheckAll = findViewById(R.id.btnEx01UncheckAll);
        btnEx01Reverse = findViewById(R.id.btnEx01Reverse);
        btnEx01Display = findViewById(R.id.btnEx01Display);

        checkBoxes.add(findViewById(R.id.cbEx01C));
        checkBoxes.add(findViewById(R.id.cbEx01CPlusPlus));
        checkBoxes.add(findViewById(R.id.cbEx01CSharp));
        checkBoxes.add(findViewById(R.id.cbEx01VBDotNet));
        checkBoxes.add(findViewById(R.id.cbEx01Java));
        checkBoxes.add(findViewById(R.id.cbEx01HTML));
        checkBoxes.add(findViewById(R.id.cbEx01CSS));
        checkBoxes.add(findViewById(R.id.cbEx01Javascript));
        checkBoxes.add(findViewById(R.id.cbEx01SQL));
        checkBoxes.add(findViewById(R.id.cbEx01PHP));

        btnEx01CheckAll.setOnClickListener(v -> {
            for (CheckBox cb : checkBoxes) {
                cb.setChecked(true);
            }
        });

        btnEx01UncheckAll.setOnClickListener(v -> {
            for (CheckBox cb : checkBoxes) {
                cb.setChecked(false);
            }
        });

        btnEx01Reverse.setOnClickListener(v -> {
            for (CheckBox cb : checkBoxes) {
                cb.setChecked(! cb.isChecked());
            }
        });

        btnEx01Display.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(Ex01Activity.this);
            ArrayList<String> checkedlanguages = new ArrayList<>();

            builder.setTitle("Your preferred languages");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setCancelable(false);

            for (CheckBox cb : checkBoxes) {
                if(cb.isChecked())
                    checkedlanguages.add(cb.getText().toString());
            }

            if (checkedlanguages.isEmpty()) {
                builder.setMessage("None !!!");
                builder.setIcon(R.drawable.thumbs_down);
            } else {
                builder.setMessage(String.join("\n", checkedlanguages));
                builder.setIcon(R.drawable.thumbs_up);
            }

            builder.show();
        });
    }
}