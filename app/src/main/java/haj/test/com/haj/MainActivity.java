package haj.test.com.haj;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import haj.test.com.haj.Data.SubjectRepository;
import haj.test.com.haj.Models.subject;

public class MainActivity extends AppCompatActivity {

    public Handler handler;
    public static Typeface font;
    public static int size;
    public static int space;
    public static String mode;
    private SharedPreferences sp;
    Boolean flag =false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //just for example
        load();

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.labbaik);

        mp.start();


        handler = new Handler();
        if (flag=false){


        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
                startActivity(new Intent(MainActivity.this, PrimaryActivity.class));

            }
        }, 17000);
        }


        RelativeLayout rl = (RelativeLayout)findViewById(R.id.main);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                finish();
                mp.stop();
                startActivity(new Intent(MainActivity.this, PrimaryActivity.class));
                flag=true;

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void load(){
        sp=getApplicationContext().getSharedPreferences("setting", 0);
        String h = sp.getString("font", "Abo-thar");
        font=Typeface.createFromAsset(getAssets(), "font/"+h+".ttf");

        size=sp.getInt("size", 18);
        space=sp.getInt("space", 1);

        mode=sp.getString("mode", "day");


    }
}
