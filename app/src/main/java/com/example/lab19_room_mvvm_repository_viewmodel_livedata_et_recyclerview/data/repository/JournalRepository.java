package com.example.lab19_room_mvvm_repository_viewmodel_livedata_et_recyclerview.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.lab19_room_mvvm_repository_viewmodel_livedata_et_recyclerview.data.db.AppDatabase;
import com.example.lab19_room_mvvm_repository_viewmodel_livedata_et_recyclerview.data.db.JournalEntry;
import com.example.lab19_room_mvvm_repository_viewmodel_livedata_et_recyclerview.data.db.JournalEntryDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JournalRepository {

    private final JournalEntryDao journalEntryDao;
    private final LiveData<List<JournalEntry>> allJournalEntries;
    private final ExecutorService diskIO;

    public JournalRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        journalEntryDao = db.entryDao();
        allJournalEntries = journalEntryDao.fetchAllEntries();
        diskIO = Executors.newSingleThreadExecutor();
    }

    public void add(JournalEntry entry) {
        diskIO.execute(() -> journalEntryDao.insertEntry(entry));
    }

    public void remove(JournalEntry entry) {
        diskIO.execute(() -> journalEntryDao.deleteEntry(entry));
    }

    public void wipeData() {
        diskIO.execute(journalEntryDao::clearAllEntries);
    }

    public LiveData<List<JournalEntry>> getAllJournalEntries() {
        return allJournalEntries;
    }
}
