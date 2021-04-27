package com.yusufpats.notesapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotesDao {

    @Insert
    void insertNote(Note note);

    @Query("SELECT * FROM notes_table")
    List<Note> getAllNotes();

}
