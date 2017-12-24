package droidtute.greendao;

import android.app.Application;

import droidtute.greendao.database.DaoMaster;
import droidtute.greendao.database.DaoSession;

public class App extends Application {

    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mDaoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(this, "greendao_demo.db").getWritableDb()).newSession();

    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}