package com.cobranza;

import android.app.Application;

import com.cobranza.model.DaoMaster;
import com.cobranza.model.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by agutierrs on 22/05/17.
 */

public class CobranzaApp extends Application
{
    protected static final String DB_NAME = "cobranza-db";
    protected static final String DB_NAME_ENCRYPTED = "cobranza-db-encrypted";

    /** A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher. */
    protected static final boolean ENCRYPTED = false;

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, getDbName());
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
        daoSession.getDatabase().close();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public String getDbName()
    {
        return ENCRYPTED ? DB_NAME_ENCRYPTED : DB_NAME;
    }

}
