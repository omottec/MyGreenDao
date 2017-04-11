package com.github.omottec.greendao.db;

import org.greenrobot.greendao.database.Database;

/**
 * Created by qinbingbing on 11/04/2017.
 */

public interface DbUpgrade {
    void onUpgrade(Database db);
}
