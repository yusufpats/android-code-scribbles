package com.yusufpats.notesapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class AppDB extends RoomDatabase {

    public abstract NotesDao notesDao();
}
