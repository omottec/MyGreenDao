package com.github.omottec.greendao.dao;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.omottec.greendao.R;
import com.github.omottec.greendao.recycler.ClickRecyclerAdapter;
import com.github.omottec.greendao.recycler.ClickViewHolder;
import com.github.omottec.greendao.recycler.OnItemClickListener;
import com.github.omottec.greendao.recycler.OnItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends ClickRecyclerAdapter<NotesAdapter.NoteViewHolder> {

    private List<Note> dataset;

    static class NoteViewHolder extends ClickViewHolder {

        public TextView text;
        public TextView comment;

        public NoteViewHolder(View itemView, OnItemClickListener onItemClickListener, OnItemLongClickListener onItemLongClickListener) {
            super(itemView, onItemClickListener, onItemLongClickListener);
            text = (TextView) itemView.findViewById(R.id.textViewGoodText);
            comment = (TextView) itemView.findViewById(R.id.textViewGoodDesc);
        }
    }

    public NotesAdapter() {
        this.dataset = new ArrayList<>();
    }

    public void setNotes(@NonNull List<Note> notes) {
        dataset = notes;
        notifyDataSetChanged();
    }

    public Note getNote(int position) {
        return dataset.get(position);
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view, this, this);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Note note = dataset.get(position);
        holder.text.setText(note.getText());
        holder.comment.setText(note.getComment());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
