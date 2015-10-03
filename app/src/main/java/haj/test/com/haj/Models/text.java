package haj.test.com.haj.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by zahra on 08/10/2015.
 */
@DatabaseTable(tableName = "text")
public class text {
    public text()
    {
        //ormlite needs this
    }

    @DatabaseField(id=true)
    public int ID;

    @DatabaseField(foreign = true,columnName = "ID_p")
    public subject subject;

    @DatabaseField
    public String Header;
    @DatabaseField
    public String Matn;
    @DatabaseField
    public int Page;
    @DatabaseField
    public int Fav;
    @DatabaseField
    public String sound;
    @DatabaseField
    public  String pictures;
}
