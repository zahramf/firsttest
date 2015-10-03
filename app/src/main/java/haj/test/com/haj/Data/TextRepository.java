package haj.test.com.haj.Data;

import android.accounts.Account;
import android.content.Context;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import org.w3c.dom.Text;

import java.sql.SQLException;
import java.util.List;

import haj.test.com.haj.Models.subject;
import haj.test.com.haj.Models.text;

/**
 * Created by zahra on 08/10/2015.
 */
public class TextRepository {
    private DatabaseHelper db;
    public Dao<text, Integer> textDao;
    public  Context mcontext;


    public TextRepository(Context ctx)
    {
        try {
            mcontext=ctx;
            DatabaseManager dbManager = new DatabaseManager();
            db = dbManager.getHelper(ctx);
            textDao = db.getTextDao();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
    }

    public int create(text Adv)
    {
        try {
            return textDao.create(Adv);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int update(text Adv)
    {
        try {
            return textDao.update(Adv);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int delete(text Adv)
    {
        try {
            return textDao.delete(Adv);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    public List<text> getAll()
    {
        try {
            return textDao.queryForAll();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }


    public text getByID(int id)
    {
        try {
            return textDao.queryForId(id);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

    public List<text> getBySubjectId(int subject_id)
    {
        try {
            QueryBuilder<text, Integer> textQb =textDao.queryBuilder();
            return textQb.groupBy("Header").where().eq("ID_p",subject_id).query();


        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

    public int countPage(String header)
    {
        try {
            QueryBuilder<text,Integer> textQB = textDao.queryBuilder();
            return (int)textQB.where().eq("Header",header).countOf();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    public text getTextByPage(String header, int pageNumber)
    {
        try {

            QueryBuilder<text, Integer> qb = textDao.queryBuilder();
            Where where = qb.where();
            // the name field must be equal to "foo"
            where.eq("Header", header);
            // and
            where.and();
            // the password field must be equal to "_secret"
            where.eq("Page", pageNumber);
            return qb.queryForFirst();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

    public List<text> getListBySearch(String word )
    {
        try {
            QueryBuilder<text, Integer> qb = textDao.queryBuilder();

             return qb.groupBy("Header").where().like("Header", "%"+word+"%").query();
        }catch (Exception ex) {
            // TODO: Exception Handling
            ex.printStackTrace();
            Toast.makeText(mcontext,"errore",Toast.LENGTH_SHORT).show();
        }
        return  null;
    }
}
