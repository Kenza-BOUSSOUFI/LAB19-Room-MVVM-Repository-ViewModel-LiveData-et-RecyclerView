package com.example.lab19_room_mvvm_repository_viewmodel_livedata_et_recyclerview;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab19_room_mvvm_repository_viewmodel_livedata_et_recyclerview.data.db.JournalEntry;
import com.example.lab19_room_mvvm_repository_viewmodel_livedata_et_recyclerview.ui.adapter.JournalAdapter;
import com.example.lab19_room_mvvm_repository_viewmodel_livedata_et_recyclerview.ui.viewmodel.JournalViewModel;

public class MainActivity extends AppCompatActivity {

    private JournalViewModel viewModel;
    private EditText inputHeader;
    private EditText inputContent;
    private Button btnSave;
    private Button btnClear;
    private JournalAdapter journalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupRecyclerView();
        setupViewModel();
        setupListeners();
    }

    private void initViews() {
        inputHeader = findViewById(R.id.editHeader);
        inputContent = findViewById(R.id.editContent);
        btnSave = findViewById(R.id.buttonSave);
        btnClear = findViewById(R.id.buttonClear);
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerEntries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        journalAdapter = new JournalAdapter();
        recyclerView.setAdapter(journalAdapter);
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(JournalViewModel.class);
        viewModel.getEntries().observe(this, list -> {
            journalAdapter.updateEntries(list);
        });
    }

    private void setupListeners() {
        btnSave.setOnClickListener(v -> saveEntry());

        btnClear.setOnClickListener(v -> {
            viewModel.clearAll();
            Toast.makeText(this, "Journal vidé", Toast.LENGTH_SHORT).show();
        });

        journalAdapter.setOnEntryLongClickListener(entry -> {
            viewModel.delete(entry);
            Toast.makeText(this, "Entrée supprimée", Toast.LENGTH_SHORT).show();
        });

        journalAdapter.setOnEntryClickListener(entry -> {
            Toast.makeText(this, "Aperçu : " + entry.getHeader(), Toast.LENGTH_SHORT).show();
        });
    }

    private void saveEntry() {
        String headerStr = inputHeader.getText().toString().trim();
        String contentStr = inputContent.getText().toString().trim();

        if (headerStr.isEmpty() || contentStr.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        JournalEntry newEntry = new JournalEntry(headerStr, contentStr);
        viewModel.insert(newEntry);

        inputHeader.setText("");
        inputContent.setText("");
        inputHeader.clearFocus();

        Toast.makeText(this, "Entrée sauvegardée", Toast.LENGTH_SHORT).show();
    }
}
