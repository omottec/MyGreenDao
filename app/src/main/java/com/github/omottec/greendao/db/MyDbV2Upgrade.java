package com.github.omottec.greendao.db;

import com.github.omottec.greendao.dao.UserDao;

import org.greenrobot.greendao.database.Database;

/**
 * Created by qinbingbing on 07/04/2017.
 */

public class MyDbV2Upgrade implements DbUpgrade {
    @Override
    public void onUpgrade(Database db) {
        UserDao.createTable(db, false);
    }
}
