package haj.test.com.haj.Models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by zahra on 08/09/2015.
 */
@DatabaseTable(tableName = "subject")
public class subject {
    public subject()
    {
        //ormlite needs this
    }


    @DatabaseField(id=true)
    public int ID;

    @DatabaseField
    public String Name;

    @DatabaseField(foreign = true,columnName = "Parent_ID",foreignAutoRefresh =true )
    public subject parent;

    @ForeignCollectionField
    public ForeignCollection<subject> children;

    @ForeignCollectionField
    public ForeignCollection<text> texts;
}
