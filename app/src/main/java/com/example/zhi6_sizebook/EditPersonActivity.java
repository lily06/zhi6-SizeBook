package com.example.zhi6_sizebook;

        import android.app.DatePickerDialog;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.google.gson.Gson;

        import java.text.SimpleDateFormat;
        import java.util.Calendar;

/**
 * This class shows Edit page, start with loading a person's detail to all the textboxes.
 * It can delete or update the current person.
 * Update the peopleList when above actions are performed.
 * It also updates the Json file with the updated list.
 */
public class EditPersonActivity extends NewPersonActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * Show the edit page. Update and return when button clicked.
         */
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

        showRecordedValues();

        Button deleteButton = (Button) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                peopleList.remove(currentPosition);
                Toast.makeText(getApplicationContext(),
                        nameText.getText().toString() + " deleted!",
                        Toast.LENGTH_SHORT).show();
                saveInFile();
                finish();
            }
        });

        //Taken from http://stackoverflow.com/questions/32555490/android-edittext-setenabled-vs-setfocusable
        //2017-02-04 18:09
        //Do not allow user to use data edit text box.
        dateText.setFocusable(false);
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aDate = Calendar.getInstance();
                new DatePickerDialog(EditPersonActivity.this, onDateSetListener,
                        aDate.get(Calendar.YEAR), aDate.get(Calendar.MONTH),
                        aDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Button saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePerson();
            }
        });
    }

    /**
     * Load current person by it's position in the peopleList
     */
    private void showRecordedValues(){
        Gson gson = new Gson();
        //Taken from http://stackoverflow.com/questions/4233873/how-do-i-get-extra-data-from-intent-on-android
        //2017-02-05 13:36
        //To get person object passed in ( in string form).
        Bundle extras = getIntent().getExtras();
        String str;
        Person person1;
        if (extras != null) {
            str = extras.getString("update");
            person1 = gson.fromJson(str,Person.class);
            aDate = person1.getDateOfRecord();
            //Taken from http://stackoverflow.com/questions/7660940/how-to-apply-applypattern-for-simpledateformat-in-java
            // 2017-02-05 13:05
            //Set a format for date.
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            nameText.setText(String.valueOf(person1.getPersonName()));
            if(aDate!=null){
                dateText.setText(sdf.format(aDate.getTime()));}
            if (person1.getNeckCircumference()!=0){
                neckText.setText(String.valueOf(person1.getNeckCircumference()));}
            if (person1.getBustCircumference()!=0){
                bustText.setText(String.valueOf(person1.getBustCircumference()));}
            if (person1.getChestCircumference()!=0){
                chestText.setText(String.valueOf(person1.getChestCircumference()));}
            if (person1.getWaistCircumference()!=0){
                waistText.setText(String.valueOf(person1.getWaistCircumference()));}
            if (person1.getHipSizeCircumference()!=0){
                hipText.setText(String.valueOf(person1.getHipSizeCircumference()));}
            if (person1.getInseamLength()!=0){
                inseamText.setText(String.valueOf(person1.getInseamLength()));}
            commentText.setText(person1.getComment());
        }
    }

    /**
     * When Save is clicked, update current person with the new info.
     */
    private void updatePerson(){

        if (!nameText.getText().toString().isEmpty()) {
            Person person1 = new Person();
            person1.setPersonName(nameText.getText().toString());
            //Taken from http://stackoverflow.com/questions/2348657/android-use-a-datepicker-and-timepicker-from-within-a-dialog?rq=1
            // 2017-02-01 20:37
            if (!dateText.getText().toString().isEmpty()) {
                person1.setDateOfRecord(aDate);
            }
            if (!neckText.getText().toString().isEmpty()) {
                person1.setNeckCircumference(roundDecimal(neckText.getText().toString()));
            }
            if (!bustText.getText().toString().isEmpty()) {
                person1.setBustCircumference(roundDecimal(bustText.getText().toString()));
            }

            if (!chestText.getText().toString().isEmpty()) {
                person1.setChestCircumference(roundDecimal(chestText.getText().toString()));
            }

            if (!waistText.getText().toString().isEmpty()) {
                person1.setWaistCircumference(roundDecimal(waistText.getText().toString()));
            }

            if (!hipText.getText().toString().isEmpty()) {
                person1.setHipSizeCircumference(roundDecimal(hipText.getText().toString()));
            }

            if (!inseamText.getText().toString().isEmpty()) {
                person1.setInseamLength(roundDecimal(inseamText.getText().toString()));
            }

            person1.setComment(commentText.getText().toString());

            peopleList.set(currentPosition, person1);
            saveInFile();
            Toast.makeText(getApplicationContext(),
                    nameText.getText().toString() + " has been edited!",
                    Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(),
                    "Please enter name field before entering other data.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
