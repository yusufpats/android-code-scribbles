package com.yusufpats.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateNoteActivity extends AppCompatActivity {

    public static String TAG = "CreateNoteActivity";

    EditText titleEditText;
    EditText bodyEditText;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        initViews();
    }

    private void initViews(){
        titleEditText = this.findViewById(R.id.title_edit_text);
        bodyEditText = this.findViewById(R.id.body_edit_text);
        saveButton = this.findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO: Take title, and body from edittext
                String title = titleEditText.getText().toString();
                String body = bodyEditText.getText().toString();

                createNewNote(title, body);
            }
        });
    }

    private void createNewNote(String title, String body){

        // TODO: Create a note object using the fields above
        Note note = new Note();
        note.title = title;
        note.body = body;
        note.createdAtTimestamp = System.currentTimeMillis();

        // TODO: save Note object to DB
        Log.d(TAG, note.title + " - " + note.body);

        AppDB appDB = Room.databaseBuilder(getApplicationContext(), AppDB.class, "app-database").build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                appDB.notesDao().insertNote(note);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(CreateNoteActivity.this, "Note added successfully", Toast.LENGTH_SHORT).show();

                        // TODO: go back to previous activity
                        CreateNoteActivity.this.finish();
                    }
                });
            }
        }).start();
    }
}