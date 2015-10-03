package haj.test.com.haj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import haj.test.com.haj.Data.SubjectRepository;
import haj.test.com.haj.Models.subject;

public class PrimaryActivity extends AppCompatActivity {

    public List<subject> sb;
    public Button btnSetting,btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);

        Bundle b = getIntent().getExtras();
        int myID=-9999;
        if(b!=null)
            myID= b.getInt("id",-9999);



        SubjectRepository sr=new SubjectRepository(this);

        if(myID==-9999)
            sb =sr.getByParentID(0);
        else
            sb =sr.getByParentID(myID);

        SubjectListAdapter sla=new SubjectListAdapter(this,sb);
        ListView lv=(ListView)findViewById(R.id.mylist);
        lv.setAdapter(sla);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                subject subjectClicked = (subject) sb.get(pos);
                //Toast.makeText(getApplicationContext(),subjectClicked.Name,Toast.LENGTH_SHORT).show();

                if (subjectClicked.children.size() > 0) {
                    Intent intent = new Intent(getApplicationContext(), PrimaryActivity.class);
                    Bundle b = new Bundle();
                    //b.putInt("id", subjectClicked.ID); //Your id
                    b.putInt("id", subjectClicked.ID);
                    intent.putExtras(b); //Put your id to your next Intent
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("subjectId", subjectClicked.ID);
                    intent.putExtras(b);
                    startActivity(intent);
                }

            }
        });

        btnSetting = (Button)findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PrimaryActivity.this,SettingActivity.class);
                startActivity(intent);
            }
        });

        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new  Intent(PrimaryActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_primary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
