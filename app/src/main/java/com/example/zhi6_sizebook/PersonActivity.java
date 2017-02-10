package com.example.zhi6_sizebook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.text.DecimalFormat;

/**
 * Created by Zhi Li on 2017/2/1.
 * This class was not used
 *
 */
public class PersonActivity extends MainActivity{

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nameText = (EditText) findViewById(R.id.name_edit);
        dateText = (EditText) findViewById(R.id.date_edit);
        neckText = (EditText) findViewById(R.id.neck_edit);
        bustText = (EditText) findViewById(R.id.bust_edit);
        chestText = (EditText) findViewById(R.id.chest_edit);
        waistText = (EditText) findViewById(R.id.waist_edit);
        hipText = (EditText) findViewById(R.id.hip_edit);
        inseamText = (EditText) findViewById(R.id.inseam_edit);
        commentText = (EditText) findViewById(R.id.comment_edit);

    }


}
