package com.example.zhi6_sizebook;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * This class shows an new person with all the details empty.
 * Person object can only be created when name is not null.
 * When delete button is clicked, no new person object wil be create.
 * When save button is clicked, a new person is appended to the people list.
 * The edit text boxes limits input to positive double number to those numeric records,
 * rounds the number to 1 decimal place.
 * Date is stored as an calendar object, shown in string form.
 * A dialogue will show when the date text box is clicked.
 * Comment and name are strings.
 * Created by Zhi Li on 2017/2/1.
 */
public class NewPersonActivity extends MainActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        nameText = (EditText) findViewById(R.id.name_edit);
        dateText = (EditText) findViewById(R.id.date_edit);
        neckText = (EditText) findViewById(R.id.neck_edit);
        bustText = (EditText) findViewById(R.id.bust_edit);
        chestText = (EditText) findViewById(R.id.chest_edit);
        waistText = (EditText) findViewById(R.id.waist_edit);
        hipText = (EditText) findViewById(R.id.hip_edit);
        inseamText = (EditText) findViewById(R.id.inseam_edit);
        commentText = (EditText) findViewById(R.id.comment_edit);

        Button deleteButton = (Button) findViewById(R.id.delete_button);
        Button saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewPerson();
            }
        });
        //Taken from http://stackoverflow.com/questions/2348657/android-use-a-datepicker-and-timepicker-from-within-a-dialog?rq=1
        // 2017-02-01 20:35

        dateText.setFocusable(false);
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aDate = Calendar.getInstance();
                new DatePickerDialog(NewPersonActivity.this, onDateSetListener,
                        aDate.get(Calendar.YEAR), aDate.get(Calendar.MONTH),
                        aDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(),
                                "No new record created!",
                                Toast.LENGTH_SHORT).show();
                                finish();
                    }
                });
        }

    /**
     * The On date set listener.
     */
    //Taken from http://stackoverflow.com/questions/2348657/android-use-a-datepicker-and-timepicker-from-within-a-dialog?rq=1
    // 2017-02-01 20:35
    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            aDate.set(Calendar.YEAR, year);
            aDate.set(Calendar.MONTH, month);
            aDate.set(Calendar.DATE, day);
            dateText.setText(year + "-" + (month + 1) + "-" + day);
        }
    };

    /**
     * This method is going to create a new person object with the information provided by user
     * The new person will be append to the list and save to the file.
     */
    protected void addNewPerson(){
        if (!nameText.getText().toString().isEmpty()) {
            Person person = new Person();

            person.setPersonName(nameText.getText().toString());
            //Taken from http://stackoverflow.com/questions/2348657/android-use-a-datepicker-and-timepicker-from-within-a-dialog?rq=1
            // 2017-02-01 20:35
            if (!dateText.getText().toString().isEmpty()){
                person.setDateOfRecord(aDate);
            }
            if (!neckText.getText().toString().isEmpty()){
                person.setNeckCircumference(roundDecimal(neckText.getText().toString()));}
            if (!bustText.getText().toString().isEmpty()){
            person.setBustCircumference(roundDecimal(bustText.getText().toString()));}

            if (!chestText.getText().toString().isEmpty()){
            person.setChestCircumference(roundDecimal(chestText.getText().toString()));}

            if (!waistText.getText().toString().isEmpty()){
            person.setWaistCircumference(roundDecimal(waistText.getText().toString()));}

            if (!hipText.getText().toString().isEmpty()){
            person.setHipSizeCircumference(roundDecimal(hipText.getText().toString()));}

            if (!inseamText.getText().toString().isEmpty()){
            person.setInseamLength(roundDecimal(inseamText.getText().toString()));}

            person.setComment(commentText.getText().toString());
            peopleList.add(person);
            saveInFile();
            Toast.makeText(getApplicationContext(),
                    nameText.getText().toString() + " has been created!",
                    Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(),
                    "Please enter name field before entering other data.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Returns rounded value to 1 d.p.
     * @param s the string form of a double needed to be converted.
     * @return the rounded double
     */
    protected double roundDecimal(String s){
        double n = Double.parseDouble(s);
            DecimalFormat df = new DecimalFormat("#.#");
            n = Double.valueOf(df.format(n));
        return n;
    }
}
