package com.example.notes;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes_Database")
public class Notes {


    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "notes_title")
   public String noteTitle;

    @ColumnInfo(name = "notes_subtitle")
   public String notesSubTitle;

    @ColumnInfo(name = "note_Date")
   public String notesDate;

    @ColumnInfo(name = "notes")
   public String notes;

    @ColumnInfo(name = "notes_priority")
   public String notes_priority;

}
