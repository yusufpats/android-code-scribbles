package com.yusufpats.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupFABButton();

        loadNotesFromDB();
    }

    private void setupFABButton() {
        FloatingActionButton addButton = this.findViewById(R.id.add_note_fab_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. TODO: Open Add new Note activity
                Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadNotesFromDB(){
        // TODO: Fetch list of Notes from DB
        AppDB appDB = Room.databaseBuilder(getApplicationContext(), AppDB.class, "app-database").build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Note> notes = appDB.notesDao().getAllNotes();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO: setup My RecyclerView
                        setupNotesRecyclerView(notes);
                        Log.d("MainActivity", "Notes Count: " + notes.size());
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotesFromDB();
    }

    private void setupNotesRecyclerView(List<Note> notes) {
        // TODO: get reference to the recycler view from layout file
        RecyclerView notesRecyclerView = this.findViewById(R.id.notes_recycler_view);

        // TODO: Create and attach a layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL, false);
        notesRecyclerView.setLayoutManager(linearLayoutManager);

        // TODO: Create and attach an adapter
        NotesAdapter notesAdapter = new NotesAdapter(notes);
        notesRecyclerView.setAdapter(notesAdapter);
    }
}