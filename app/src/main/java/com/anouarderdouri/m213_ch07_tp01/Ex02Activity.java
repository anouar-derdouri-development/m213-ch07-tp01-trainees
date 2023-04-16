package com.anouarderdouri.m213_ch07_tp01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Ex02Activity extends AppCompatActivity {
    RadioButton rdPermanent, rdTemporary;
    ConstraintLayout cLayPermanent, cLayTemporary;
    CheckBox cbMarried;
    Spinner spinnerNumberOfChildren;
    TextView tvChildOrChildren;
    EditText etNumberOfHours;
    Button btnCalculate;
    TextView tvSalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex02);

        // ToDo: Restore state

        rdPermanent = findViewById(R.id.rdPermanent);
        rdTemporary = findViewById(R.id.rdTemporary);
        cLayPermanent = findViewById(R.id.cLayPermanent);
        cLayTemporary = findViewById(R.id.cLayTemporary);
        cbMarried = findViewById(R.id.cbMarried);
        spinnerNumberOfChildren = findViewById(R.id.spinnerNumberOfChildren);
        tvChildOrChildren = findViewById(R.id.tvChildOrChildren);
        etNumberOfHours = findViewById(R.id.etNumberOfHours);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvSalary = findViewById(R.id.tvSalary);

        //region Spinner

        ArrayList<Integer> numberOfChildren = new ArrayList<>();
        for (int i = 0; i <= 5; i++) numberOfChildren.add(i);

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(Ex02Activity.this, android.R.layout.simple_spinner_item, numberOfChildren);

        spinnerNumberOfChildren.setAdapter(adapter);

        spinnerNumberOfChildren.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int numberOfChildren = Integer.parseInt(spinnerNumberOfChildren.getSelectedItem().toString());

                if (numberOfChildren == 0 || numberOfChildren == 1)
                    tvChildOrChildren.setText(R.string.child);
                else
                    tvChildOrChildren.setText(R.string.children);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //endregion

        //region Radios

        rdPermanent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                enableOrDisableCLay(cLayPermanent, isChecked);
            }
        });

        rdTemporary.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                enableOrDisableCLay(cLayTemporary, isChecked);
            }
        });

        rdTemporary.performClick();
        rdPermanent.performClick();

        //endregion

        //region Married

        cbMarried.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                spinnerNumberOfChildren.setEnabled(isChecked);
            }
        });

        cbMarried.performClick();
        cbMarried.performClick();

        //endregion

        //region Calculate salary

        btnCalculate.setOnClickListener(v -> {
            try {
                double salary = 0;

                if (rdPermanent.isChecked()) {
                    salary = 7000;

                    if (cbMarried.isChecked())
                        salary = 7000 + 200 * Integer.parseInt(spinnerNumberOfChildren.getSelectedItem().toString());
                } else {
                    salary = 100 * Double.parseDouble(etNumberOfHours.getText().toString());
                }

                tvSalary.setText(String.valueOf(salary));
            } catch (NumberFormatException e) {
                tvSalary.setText(R.string.problem);
            }
        });

        //endregion
    }

    private void enableOrDisableCLay(ConstraintLayout cLay, boolean state) {
        for (int i = 0; i < cLay.getChildCount(); i++) {
            View child = cLay.getChildAt(i);
            child.setEnabled(state);
        }
    }
}