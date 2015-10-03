package haj.test.com.haj.Data;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import haj.test.com.haj.Models.subject;

/**
 * Created by zahra on 08/10/2015.
 */
public class SubjectRepository {
    private DatabaseHelper db;
    public Dao<subject, Integer> subjectsDao;

    public SubjectRepository(Context ctx)
    {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            db = dbManager.getHelper(ctx);
            subjectsDao = db.getSubjectsDao();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
    }

    public int create(subject Adv)
    {
        try {
            return subjectsDao.create(Adv);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int update(subject Adv)
    {
        try {
            return subjectsDao.update(Adv);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int delete(subject Adv)
    {
        try {
            return subjectsDao.delete(Adv);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    public List<subject> getAll()
    {
        try {
            return subjectsDao.queryForAll();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }


    public subject getByID(int id)
    {
        try {
            return subjectsDao.queryForId(id);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

    public List<subject> getByParentID(int parent_id)
    {
        try {
            return subjectsDao.queryForEq("Parent_ID",parent_id);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }
}
