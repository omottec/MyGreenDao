package com.github.omottec.greendao.db;

import org.greenrobot.greendao.database.Database;

/**
 * Created by qinbingbing on 11/04/2017.
 */

public class MyDbV4Upgrade implements DbUpgrade {
    @Override
    public void onUpgrade(Database db) {
        db.execSQL("this is a wrong sql");
    }
}
