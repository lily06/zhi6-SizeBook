package com.example.zhi6_sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * Reference: lonelytwitter https://github.com/joshua2ua/lonelyTwitter 2017-01-30
 * <p>
 * This class is the main view class of the project. <br> In this class, user interaction
 * and file manipulation is performed.
 * <p>
 * All files are in the form of "json" files that are stored in Emulator's accessible from Android Device Monitor
 * <pre>
 *     pre-formatted text: <br>
 *         File Explorer -> data -> data ->com.example.zhi6_sizebook -> files ->file.sav
 * </pre>
 */
public class MainActivity extends AppCompatActivity {
    /**
     * The file that all the records are saved here. The format of the file is JSON
     * @see #loadFromFile()
     * @see #saveInFile()
     *
     */
    //file name
    private static final String FILENAME = "file.sav";
    //Records exist already, show in list view
    private ListView previousPeopleView;
    /**
     * The Adapter.
     */
//list adapter
    protected ArrayAdapter<Person> adapter;
    /**
     * The People list.
     */
// Current version of records
    protected ArrayList<Person> peopleList;
    /**
     * The Total counts.
     */
//Indicates total number of records in one line.
    private TextView totalCounts;
    /**
     * The constant currentPosition.
     */
//private int initCounts;
    //which record
    protected static int currentPosition;

    /**
     * The Name text.
     */
    protected EditText nameText;
    /**
     * The Date text.
     */
    protected EditText dateText;
    /**
     * The Neck text.
     */
    protected EditText neckText;
    /**
     * The Bust text.
     */
    protected EditText bustText;
    /**
     * The Chest text.
     */
    protected EditText chestText;
    /**
     * The Waist text.
     */
    protected EditText waistText;
    /**
     * The Hip text.
     */
    protected EditText hipText;
    /**
     * The Inseam text.
     */
    protected EditText inseamText;
    /**
     * The Comment text.
     */
    protected EditText commentText;
    /**
     * The A date.
     */
    protected Calendar aDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * Initialize view, initialize clickables.
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previousPeopleView = (ListView) findViewById(R.id.oldPeopleList);
        totalCounts = (TextView) findViewById(R.id.totalCounts);

        Button newRecordButton = (Button) findViewById(R.id.New_record_button);
        /**
         * Initialize new record button.
         * Go to create new person page when clicked.
         */
        newRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewPersonActivity.class);
                startActivity(intent);
            }
        });

        /**
         *  Initialize click function on the list view.
         *  Enter edit page when individual record was clicked.
         */
        previousPeopleView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditPersonActivity.class);
                currentPosition = position;

                Gson gson = new Gson();
                String personStr = gson.toJson(peopleList.get(position));
                intent.putExtra("update",personStr);

                startActivity(intent);
                adapter.notifyDataSetChanged();

            }
        });

    }

    /**
     *  Load old record list if there is any.
     *  Initialize the start view.
     */
    @Override
    protected void onStart(){
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<>(this,
                R.layout.list_item, peopleList);
        previousPeopleView.setAdapter(adapter);

        totalCounts.setText(String.format("Total count: %s", peopleList.size()));
    }

    /**
     * Continue with updated peopleList.
     */
    @Override
    protected void onResume(){
        super.onResume();
        loadFromFile();

        totalCounts.setText(String.format("Total count: %s", peopleList.size()));
        adapter.notifyDataSetChanged();
    }

    /**
     * Load a file contains all the previous records; was saved in Json format.
     */
    private void loadFromFile(){

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //Take from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            //2017-01-24 18:19
            peopleList = gson.fromJson(in, new TypeToken<ArrayList<Person>>(){}.getType());
            fis.close();
        } catch (FileNotFoundException e) {
            peopleList = new ArrayList<Person>();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Save current version of the records in Json format.
     */
    protected void saveInFile(){

        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(peopleList, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
