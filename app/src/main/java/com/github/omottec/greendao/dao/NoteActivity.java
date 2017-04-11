package com.github.omottec.greendao.dao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;


import com.github.omottec.greendao.R;
import com.github.omottec.greendao.db.DaoSessionHolder;
import com.github.omottec.greendao.recycler.OnItemClickListener;

import org.greenrobot.greendao.rx.RxDao;
import org.greenrobot.greendao.rx.RxQuery;

import java.text.DateFormat;
import java.util.Date;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by qinbingbing on 07/04/2017.
 */

public class NoteActivity extends AppCompatActivity {
    private EditText editText;
    private View addNoteButton;

    private RxDao noteDao;
    private RxQuery<Note> notesQuery;
    private NotesAdapter notesAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_note);
        setUpViews();

        // get the note DAO
        DaoSession daoSession = DaoSessionHolder.getInstance().getMyDaoSession();
        noteDao = daoSession.getNoteDao().rx();

        // query all notes, sorted a-z by their text
        notesQuery = daoSession.getNoteDao().queryBuilder().orderAsc(NoteDao.Properties.Text).rx();
        updateNotes();
    }


    private void updateNotes() {
        notesQuery
                .list()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(notes -> notesAdapter.setNotes(notes));
    }

    protected void setUpViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewUsers);
        //noinspection ConstantConditions
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        notesAdapter = new NotesAdapter();
        notesAdapter.setOnItemClickListener(noteClickListener);
        recyclerView.setAdapter(notesAdapter);

        addNoteButton = findViewById(R.id.buttonAdd);
        //noinspection ConstantConditions
        addNoteButton.setEnabled(false);

        editText = (EditText) findViewById(R.id.editTextGood);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    addNote();
                    return true;
                }
                return false;
            }
        });
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean enable = s.length() != 0;
                addNoteButton.setEnabled(enable);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void onAddButtonClick(View view) {
        addNote();
    }

    private void addNote() {
        String noteText = editText.getText().toString();
        editText.setText("");

        final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        String comment = "Added on " + df.format(new Date());

        Note note = new Note();
        note.setText(noteText);
        note.setComment(comment);
//        note.setDate(new Date());
        note.setType(NoteType.TEXT);
        noteDao.insert(note)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Note>() {
                    @Override
                    public void call(Note note) {
                        Log.d("DaoExample", "Inserted new note, ID: " + note.getId());
                        updateNotes();
                    }
                });
    }

    OnItemClickListener noteClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(RecyclerView.ViewHolder holder, View view) {
            Note note = notesAdapter.getNote(holder.getAdapterPosition());
            Long noteId = note.getId();

            noteDao.deleteByKey(noteId)
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(aVoid -> {
                        Log.d("DaoExample", "Deleted note, ID: " + noteId);
                        updateNotes();
                    });
        }
    };
}
