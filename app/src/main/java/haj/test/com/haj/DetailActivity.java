package haj.test.com.haj;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import haj.test.com.haj.Data.TextRepository;
import haj.test.com.haj.Models.subject;
import haj.test.com.haj.Models.text;

public class DetailActivity extends AppCompatActivity {

    public Button btnSetting,btnSearch,btnMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        //setSupportActionBar(toolbar);


        Bundle b = getIntent().getExtras();
        int subject_id = b.getInt("subjectId", -1);
        TextRepository tr = new  TextRepository(this);
        final List<text> listTexts=tr.getBySubjectId(subject_id);
        TextListAdapter tla=new TextListAdapter(this,listTexts);
        ListView lv=(ListView)findViewById(R.id.mylist);
        lv.setAdapter(tla);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                text textClicked = (text) listTexts.get(pos);
               //Toast.makeText(getApplicationContext(), textClicked.Header, Toast.LENGTH_SHORT).show();

              Intent intent = new Intent(getApplicationContext(), ContextActivity.class);
                Bundle b = new Bundle();
                b.putString("header", textClicked.Header);
                b.putString("matn", textClicked.Matn);

                TextRepository tr = new  TextRepository(getApplicationContext());
                int countPage=tr.countPage(textClicked.Header);
                b.putInt("page",countPage);



                intent.putExtras(b);
                startActivity(intent);


            }
        });

        btnSetting = (Button)findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DetailActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        btnMenu = (Button)findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DetailActivity.this,MenuActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
