package com.example.lab19_room_mvvm_repository_viewmodel_livedata_et_recyclerview.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab19_room_mvvm_repository_viewmodel_livedata_et_recyclerview.R;
import com.example.lab19_room_mvvm_repository_viewmodel_livedata_et_recyclerview.data.db.JournalEntry;

import java.util.ArrayList;
import java.util.List;

public class JournalAdapter extends RecyclerView.Adapter<JournalAdapter.JournalViewHolder> {

    private List<JournalEntry> entryList = new ArrayList<>();
    private OnEntryClickListener clickListener;
    private OnEntryLongClickListener longClickListener;

    public interface OnEntryClickListener {
        void onEntryClick(JournalEntry entry);
    }

    public interface OnEntryLongClickListener {
        void onEntryLongClick(JournalEntry entry);
    }

    public void updateEntries(List<JournalEntry> entries) {
        this.entryList = entries;
        notifyDataSetChanged();
    }

    public void setOnEntryClickListener(OnEntryClickListener listener) {
        this.clickListener = listener;
    }

    public void setOnEntryLongClickListener(OnEntryLongClickListener listener) {
        this.longClickListener = listener;
    }

    @NonNull
    @Override
    public JournalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.journal_item, parent, false);
        return new JournalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JournalViewHolder holder, int position) {
        JournalEntry current = entryList.get(position);
        holder.txtHeader.setText(current.getHeader());
        holder.txtContent.setText(current.getContent());
    }

    @Override
    public int getItemCount() {
        return entryList.size();
    }

    class JournalViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtHeader;
        private final TextView txtContent;

        public JournalViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHeader = itemView.findViewById(R.id.textHeader);
            txtContent = itemView.findViewById(R.id.textContent);

            itemView.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (clickListener != null && pos != RecyclerView.NO_POSITION) {
                    clickListener.onEntryClick(entryList.get(pos));
                }
            });

            itemView.setOnLongClickListener(v -> {
                int pos = getAdapterPosition();
                if (longClickListener != null && pos != RecyclerView.NO_POSITION) {
                    longClickListener.onEntryLongClick(entryList.get(pos));
                    return true;
                }
                return false;
            });
        }
    }
}
