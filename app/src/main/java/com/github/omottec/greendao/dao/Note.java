package com.github.omottec.greendao.dao;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by qinbingbing on 07/04/2017.
 */
@Entity
public class Note {
    @Id
    private Long id;

    @NotNull
    private String text;
    private String comment;

    @Convert(converter = NoteTypeConverter.class, columnType = String.class)
    private NoteType type;

    @Generated(hash = 1272611929)
    public Note() {
    }

    @Generated(hash = 1668423019)
    public Note(Long id, @NotNull String text, String comment, NoteType type) {
        this.id = id;
        this.text = text;
        this.comment = comment;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(@NotNull String text) {
        this.text = text;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public NoteType getType() {
        return this.type;
    }

    public void setType(NoteType type) {
        this.type = type;
    }
}
