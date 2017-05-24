package com.cobranza;

import android.app.Application;

import com.cobranza.model.DaoMaster;
import com.cobranza.model.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by agutierrs on 22/05/17.
 */

public class App extends Application
{
    /** A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher. */
    public static final boolean ENCRYPTED = false;

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "cobranza-db-encrypted" : "cobranza-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
