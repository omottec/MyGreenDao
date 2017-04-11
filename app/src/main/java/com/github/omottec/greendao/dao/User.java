package com.github.omottec.greendao.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by qinbingbing on 07/04/2017.
 */

@Entity
public class User {
    @Id
    private Long id;

    @NotNull
    private String name;

    private boolean female;

    @Generated(hash = 1105066515)
    public User(Long id, @NotNull String name, boolean female) {
        this.id = id;
        this.name = name;
        this.female = female;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFemale() {
        return female;
    }

    public void setFemale(boolean female) {
        this.female = female;
    }

    public boolean getFemale() {
        return this.female;
    }
}
