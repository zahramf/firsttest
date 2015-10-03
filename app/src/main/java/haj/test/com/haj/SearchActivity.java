package haj.test.com.haj;

import android.content.Intent;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import haj.test.com.haj.Data.TextRepository;
import haj.test.com.haj.Models.text;

public class SearchActivity extends AppCompatActivity {

    public List<text> sr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

       final EditText txtSearch = (EditText)findViewById(R.id.txtSearch);
        Button btnSearch = (Button) findViewById(R.id.btnSearch);

        txtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //zamanii ke too zarebiin mizanii code haye inja ejra mishe
                    TextRepository tr = new TextRepository(getApplicationContext());
                    // sr = tr.getListBySearch(txtSearch.getText().toString());
                    final List<text> listTexts = tr.getListBySearch(txtSearch.getText().toString());
                    TextListAdapter tla = new TextListAdapter(getApplicationContext(), listTexts);
                    ListView lv = (ListView) findViewById(R.id.mylist);
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

                            TextRepository tr = new TextRepository(getApplicationContext());
                            int countPage = tr.countPage(textClicked.Header);
                            b.putInt("page", countPage);


                            intent.putExtras(b);
                            startActivity(intent);


                        }
                    });
                    txtSearch.setSelection(txtSearch.getText().length());
                    return true;
                }
                return false;
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextRepository tr = new TextRepository(getApplicationContext());
               // sr = tr.getListBySearch(txtSearch.getText().toString());
                final List<text> listTexts = tr.getListBySearch(txtSearch.getText().toString());
                TextListAdapter tla = new TextListAdapter(getApplicationContext(), listTexts);
                ListView lv = (ListView) findViewById(R.id.mylist);
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

                        TextRepository tr = new TextRepository(getApplicationContext());
                        int countPage = tr.countPage(textClicked.Header);
                        b.putInt("page", countPage);


                        intent.putExtras(b);
                        startActivity(intent);

                        txtSearch.setSelection(txtSearch.getText().length());

                    }
                });
            }
        });


       /* TextListAdapter tla = new TextListAdapter(getApplicationContext(), sr);
        ListView lv = (ListView) findViewById(R.id.mylist);
         lv.setAdapter(tla);*/

       /* lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                text textClicked = (text) sr.get(pos);
                //Toast.makeText(getApplicationContext(), textClicked.Header, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), ContextActivity.class);
                Bundle b = new Bundle();
                b.putString("header", textClicked.Header);
                b.putString("matn", textClicked.Matn);

                TextRepository tr = new TextRepository(getApplicationContext());
                int countPage = tr.countPage(textClicked.Header);
                b.putInt("page", countPage);


                intent.putExtras(b);
                startActivity(intent);


            }
        });*/
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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
