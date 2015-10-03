package haj.test.com.haj.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.sql.SQLException;

import haj.test.com.haj.Models.subject;
import haj.test.com.haj.Models.text;

/**
 * Created by zahra on 08/10/2015.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "haj.db";
    private static final int DATABASE_VERSION = 1;

    public Context mcontext;

    private Dao<subject, Integer> subjectsDao = null;
    private Dao<text, Integer> textDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mcontext=context;
        DatabaseInitializer initializer = new DatabaseInitializer(context);
        try {
            initializer.createDatabase();
            initializer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
//        try {
//            Log.i(DatabaseHelper.class.getName(), "onCreate");
//            TableUtils.createTable(connectionSource, text.class);
//            TableUtils.createTable(connectionSource, subject.class);
//
//        } catch (SQLException e) {
//            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
//            throw new RuntimeException(e);
//        }


        Log.i(DatabaseHelper.class.getName(), "created new entries in onCreate");
    }

    /**
     * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
     * the various data to match the new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, subject.class, true);
            TableUtils.dropTable(connectionSource, text.class, true);

            // after we drop the old databases, we create the new ones
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }


    public Dao<subject, Integer> getSubjectsDao() throws SQLException {
        if (subjectsDao == null) {
            subjectsDao = getDao(subject.class);
        }
        return subjectsDao;
    }

    public Dao<text, Integer> getTextDao() throws SQLException {
        if (textDao == null) {
            textDao = getDao(text.class);
        }
        return textDao;
    }


    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        subjectsDao = null;
        textDao = null;
    }
}
