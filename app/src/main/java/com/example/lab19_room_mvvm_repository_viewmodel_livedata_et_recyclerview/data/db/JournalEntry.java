package com.example.lab19_room_mvvm_repository_viewmodel_livedata_et_recyclerview.data.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "journal_entries")
public class JournalEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String header;
    private String content;

    public JournalEntry(String header, String content) {
        this.header = header;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
