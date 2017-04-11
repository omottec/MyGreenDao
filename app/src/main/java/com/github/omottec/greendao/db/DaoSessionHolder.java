package com.github.omottec.greendao.db;

import com.github.omottec.greendao.app.MyGreenDaoApp;
import com.github.omottec.greendao.dao.DaoMaster;
import com.github.omottec.greendao.dao.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by qinbingbing on 07/04/2017.
 */

public class DaoSessionHolder {
    private DaoSession myDaoSession;
    private DaoSession goodDaoSession;

    private DaoSessionHolder() {
        MyDbOpenHelper myDbOpenHelper = new MyDbOpenHelper(MyGreenDaoApp.getContext());
        Database myDb = myDbOpenHelper.getWritableDb();
        myDaoSession = new DaoMaster(myDb).newSession();

        GoodDbOpenHelper goodDbOpenHelper = new GoodDbOpenHelper(MyGreenDaoApp.getContext());
        Database goodDb = goodDbOpenHelper.getWritableDb();
        goodDaoSession = new DaoMaster(goodDb).newSession();
    }

    private static class InstanceHolder {
        private static final DaoSessionHolder INSTANCE = new DaoSessionHolder();
    }

    public static DaoSessionHolder getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public DaoSession getMyDaoSession() {
        return myDaoSession;
    }

    public DaoSession getGoodDaoSession() {
        return goodDaoSession;
    }
}
