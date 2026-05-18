package com.example.lab19_room_mvvm_repository_viewmodel_livedata_et_recyclerview.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lab19_room_mvvm_repository_viewmodel_livedata_et_recyclerview.data.db.JournalEntry;
import com.example.lab19_room_mvvm_repository_viewmodel_livedata_et_recyclerview.data.repository.JournalRepository;

import java.util.List;

public class JournalViewModel extends AndroidViewModel {

    private final JournalRepository repository;
    private final LiveData<List<JournalEntry>> entries;

    public JournalViewModel(@NonNull Application application) {
        super(application);
        repository = new JournalRepository(application);
        entries = repository.getAllJournalEntries();
    }

    public void insert(JournalEntry entry) {
        repository.add(entry);
    }

    public void delete(JournalEntry entry) {
        repository.remove(entry);
    }

    public void clearAll() {
        repository.wipeData();
    }

    public LiveData<List<JournalEntry>> getEntries() {
        return entries;
    }
}
