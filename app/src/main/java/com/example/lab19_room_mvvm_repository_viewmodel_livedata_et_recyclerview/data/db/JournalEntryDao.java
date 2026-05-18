package com.example.lab19_room_mvvm_repository_viewmodel_livedata_et_recyclerview.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface JournalEntryDao {

    @Insert
    void insertEntry(JournalEntry entry);

    @Delete
    void deleteEntry(JournalEntry entry);

    @Query("DELETE FROM journal_entries")
    void clearAllEntries();

    @Query("SELECT * FROM journal_entries ORDER BY id DESC")
    LiveData<List<JournalEntry>> fetchAllEntries();
}
