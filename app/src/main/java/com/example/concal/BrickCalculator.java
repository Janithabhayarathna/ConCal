package com.example.concal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class BrickCalculator extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brick_calculator);

        EditText length = findViewById(R.id.len);
        EditText height = findViewById(R.id.units);

        EditText bLength = findViewById(R.id.tLength);
        EditText bWidth = findViewById(R.id.tWidth);
        EditText bHeight = findViewById(R.id.bHeight);

        List<String> thicknessList = Arrays.asList("10cm wall", "23cm wall");
        Spinner thicknessSpinner = findViewById(R.id.memberType);
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, thicknessList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        thicknessSpinner.setAdapter(adapter);

        List<String> ratioList = Arrays.asList("C.M 1:3", "C.M 1:4", "C.M 1:5", "C.M 1:6", "C.M 1:7", "C.M 1:8", "C.M 1:9");
        Spinner ratioSpinner = findViewById(R.id.ratioSpinner);
        ArrayAdapter adapter2 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, ratioList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ratioSpinner.setAdapter(adapter2);

        TextView out = findViewById(R.id.cementOut);
        TextView cementOut = findViewById(R.id.sandOut);
        TextView sandOut = findViewById(R.id.solarCount);

        Button submit = findViewById(R.id.submit);

        submit.setOnClickListener(v -> {

            if (length.getText().toString().equals("0.0") || height.getText().toString().equals("0.0") || bLength.getText().toString().equals("0.0") || bWidth.getText().toString().equals("0.0") || bHeight.getText().toString().equals("0.0")) {
                Toast toast = Toast.makeText(getApplicationContext(), "Please fill the empty fields", Toast.LENGTH_LONG);
                toast.show();
                out.setText("Error!");
                cementOut.setText("Error!");
                sandOut.setText("Error!");

            } else if (notANumInRange(length.getText().toString()) || notANumInRange(height.getText().toString()) || notANumInRange(bLength.getText().toString()) || notANumInRange(bWidth.getText().toString()) || notANumInRange(bHeight.getText().toString())) {
                Toast toast = Toast.makeText(getApplicationContext(), "All inputs should be positive", Toast.LENGTH_LONG);
                toast.show();
                out.setText("Error!");
                cementOut.setText("Error!");
                sandOut.setText("Error!");
            } else {
                //NUMBER OF Bricks counter
                double wallThickness;
                String tempLength = length.getText().toString();
                String tempWidth = height.getText().toString();
                double wallLength = Double.parseDouble(tempLength);
                double wallHeight = Double.parseDouble(tempWidth);
                String wallThicknessString = thicknessSpinner.getSelectedItem().toString();
                if (wallThicknessString.equals("10cm wall")) {
                    wallThickness = 10.0/100;
                } else {
                    wallThickness = 23.0/100;
                }

                String temp3 = bLength.getText().toString();
                String temp4 = bWidth.getText().toString();
                String temp5 = bHeight.getText().toString();
                double brickLength = Double.parseDouble(temp3);
                double brickWidth = Double.parseDouble(temp4);
                double brickHeight = Double.parseDouble(temp5);

                double brickMasonry = wallLength * wallHeight * wallThickness;
                double brickVolume = (brickLength + 0.01) * (brickWidth + 0.01) * (brickHeight + 0.01);

                int noOfBricks = (int) (brickMasonry / brickVolume);
                out.setText(String.valueOf(noOfBricks));

                //Amount of cement counter

                double cement;
                double sand;
                double tempSandQuantity;
                double sandQuantity;
                int cementBags;
                double bricksMortar = noOfBricks * (brickLength * brickWidth * brickHeight);
                double tempMortar = brickMasonry - bricksMortar;
                double tempMortar2 = tempMortar + (tempMortar * 0.15); // For the wastage
                double mortarQuantity = tempMortar2 + (tempMortar2 * 0.25); // For the dry volume
                String wallRatio = ratioSpinner.getSelectedItem().toString();
                switch (wallRatio) {
                    case "C.M 1:3":
                        cement = mortarQuantity / 4.0;
                        sand = mortarQuantity * (3 / 4.0);
                        cementBags = (int) (Math.ceil(cement / 0.035));
                        tempSandQuantity = Math.ceil((sand * 1500));
                        sandQuantity = Math.round(tempSandQuantity * 100.0) / 100.0;
                        cementOut.setText(cementBags + " bags");
                        sandOut.setText(sandQuantity + " kg");
                        break;
                    case "C.M 1:4":
                        cement = mortarQuantity / 5.0;
                        sand = mortarQuantity * (4 / 5.0);
                        cementBags = (int) (Math.ceil(cement / 0.035));
                        tempSandQuantity = Math.ceil((sand * 1500));
                        sandQuantity = Math.round(tempSandQuantity * 100.0) / 100.0;
                        cementOut.setText(cementBags + " bags");
                        sandOut.setText(sandQuantity + " kg");
                        break;
                    case "C.M 1:5":
                        cement = mortarQuantity / 6.0;
                        sand = mortarQuantity * (5 / 6.0);
                        cementBags = (int) (Math.ceil(cement / 0.035));
                        tempSandQuantity = Math.ceil((sand * 1500));
                        sandQuantity = Math.round(tempSandQuantity * 100.0) / 100.0;
                        cementOut.setText(cementBags + " bags");
                        sandOut.setText(sandQuantity + " kg");
                        break;
                    case "C.M 1:6":
                        cement = mortarQuantity / 7.0;
                        sand = mortarQuantity * (6 / 7.0);
                        cementBags = (int) (Math.ceil(cement / 0.035));
                        tempSandQuantity = Math.ceil((sand * 1500));
                        sandQuantity = Math.round(tempSandQuantity * 100.0) / 100.0;
                        cementOut.setText(cementBags + " bags");
                        sandOut.setText(sandQuantity + " kg");
                        break;
                    case "C.M 1:7":
                        cement = mortarQuantity / 8.0;
                        sand = mortarQuantity * (7 / 8.0);
                        cementBags = (int) (Math.ceil(cement / 0.035));
                        tempSandQuantity = Math.ceil((sand * 1500));
                        sandQuantity = Math.round(tempSandQuantity * 100.0) / 100.0;
                        cementOut.setText(cementBags + " bags");
                        sandOut.setText(sandQuantity + " kg");
                        break;
                    case "C.M 1:8":
                        cement = mortarQuantity / 9.0;
                        sand = mortarQuantity * (8 / 9.0);
                        cementBags = (int) (Math.ceil(cement / 0.035));
                        tempSandQuantity = Math.ceil((sand * 1500));
                        sandQuantity = Math.round(tempSandQuantity * 100.0) / 100.0;
                        cementOut.setText(cementBags + " bags");
                        sandOut.setText(sandQuantity + " kg");
                        break;
                    case "C.M 1:9":
                        cement = mortarQuantity / 10.0;
                        sand = mortarQuantity * (9 / 10.0);
                        cementBags = (int) (Math.ceil(cement / 0.035));
                        tempSandQuantity = Math.ceil((sand * 1500));
                        sandQuantity = Math.round(tempSandQuantity * 100.0) / 100.0;
                        cementOut.setText(cementBags + " bags");
                        sandOut.setText(sandQuantity + " kg");
                        break;
                    default:
                        //Invalid
                        cement = mortarQuantity;
                        sand = mortarQuantity;
                        cementBags = (int) (Math.ceil(cement / 0.035));
                        tempSandQuantity = Math.ceil((sand * 1500));
                        sandQuantity = Math.round(tempSandQuantity * 100.0) / 100.0;
                        cementOut.setText(cementBags + " bags");
                        sandOut.setText(sandQuantity + " kg");
                        break;
                }
            }
        });

        Button lMinus = findViewById(R.id.lengthMinus);
        Button lPlus = findViewById(R.id.lengthPlus);
        Button hMinus = findViewById(R.id.quantityMinus);
        Button hPlus = findViewById(R.id.quantityPlus);

        //Setting plus, minus buttons.
        lMinus.setOnClickListener(view -> decrementNum(length));
        lPlus.setOnClickListener(view -> incrementNum(length));

        hMinus.setOnClickListener(view -> decrementNum(height));
        hPlus.setOnClickListener(view -> incrementNum(height));
    }

    // validation
    private boolean notANumInRange(String strNum){
        if (strNum == null) {
            return true;//validate num is not null
        }
        try {
            double d = Double.parseDouble(strNum);//validate string is a num
            if(d<0){
                return true;//validate num cant be minus
            }
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }

    @SuppressLint("SetTextI18n")
    private void incrementNum(TextView textView){
        double newNum=Double.parseDouble(textView.getText().toString())+1.0;
        textView.setText(Double.toString(newNum));
    }
    @SuppressLint("SetTextI18n")
    private void decrementNum(TextView textView){
        double newNum=Double.parseDouble(textView.getText().toString())-1.0;
        textView.setText(Double.toString(newNum));
    }
}