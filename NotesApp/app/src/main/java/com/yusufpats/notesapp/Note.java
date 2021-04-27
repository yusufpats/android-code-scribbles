package com.yusufpats.notesapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    int uid;

    @ColumnInfo(name = "title")
    String title;

    @ColumnInfo(name = "description")
    String body;

    @ColumnInfo(name = "createdAtTimestamp")
    long createdAtTimestamp;

}
