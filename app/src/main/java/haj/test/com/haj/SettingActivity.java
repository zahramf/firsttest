package haj.test.com.haj;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    private RadioButton rbAbozar,rbQuran,rbZar,rbDay,rbNight;
    private TextView txtTest,txtFont,txtSize,txtSpace,txtMode;
    private SeekBar sbSize,sbSpace;
    private Button btnSave,btnCancel;
    private Typeface font;
    private String sfont;
    private String mode;

    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        set();
        load();

        rbAbozar.setTypeface(Typeface.createFromAsset(getAssets(), "font/Abo-thar.ttf"));
        rbQuran.setTypeface(Typeface.createFromAsset(getAssets(), "font/QuranTaha.ttf"));
        rbZar.setTypeface(Typeface.createFromAsset(getAssets(), "font/zar.ttf"));

        txtFont.setTypeface(Typeface.createFromAsset(getAssets(), "font/Abo-thar.ttf"));
        txtSize.setTypeface(Typeface.createFromAsset(getAssets(), "font/Abo-thar.ttf"));
        txtSpace.setTypeface(Typeface.createFromAsset(getAssets(), "font/Abo-thar.ttf"));
        txtMode.setTypeface(Typeface.createFromAsset(getAssets(), "font/Abo-thar.ttf"));

        rbDay.setTypeface(Typeface.createFromAsset(getAssets(), "font/Abo-thar.ttf"));
        rbNight.setTypeface(Typeface.createFromAsset(getAssets(), "font/Abo-thar.ttf"));

        sbSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int value,
                                          boolean fromUser) {
                txtTest.setTextSize(value);
            }
        });

        sbSpace.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int value,
                                          boolean fromUser) {
                txtTest.setLineSpacing(value, 1);
            }
        });

        rbAbozar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                font = Typeface.createFromAsset(getAssets(), "font/Abo-thar.ttf");
                txtTest.setTypeface(font);
                sfont = "Abo-thar";
            }

        });

        rbQuran.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                font=Typeface.createFromAsset(getAssets(), "font/QuranTaha.ttf");
                txtTest.setTypeface(font);
                sfont="QuranTaha";
            }
        });
        rbZar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                font=Typeface.createFromAsset(getAssets(), "font/zar.ttf");
                txtTest.setTypeface(font);
                sfont="zar";
            }
        });

        rbDay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                txtTest.setBackgroundColor(Color.WHITE);
                txtTest.setTextColor(Color.BLACK);
                mode="day";
            }
        });

        rbNight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                txtTest.setBackgroundColor(Color.BLACK);
                txtTest.setTextColor(Color.WHITE);
                mode ="night";
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                sp= getApplicationContext().getSharedPreferences("setting", 0);
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("font", sfont);
                if(sfont.matches("Abo-thar")){
                    MainActivity.font=Typeface.createFromAsset(getAssets(), "font/"+sfont+".ttf");
                }

                edit.putInt("size", sbSize.getProgress());
                MainActivity.size=sbSize.getProgress();

                edit.putInt("space", sbSpace.getProgress());
                MainActivity.space=sbSpace.getProgress();

                edit.putString("mode", mode);
                MainActivity.mode=mode;
                edit.commit();

                Toast.makeText(getApplicationContext(), "حفظ التغییرات", Toast.LENGTH_LONG).show();

                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    private void load(){

        sp=getApplicationContext().getSharedPreferences("setting", 0);


        //font
        String f = sp.getString("font", "Abo-thar");

        sfont=f;
        if(f.equals("Abo-zar")){
            font=Typeface.createFromAsset(getAssets(), "font/"+f+".ttf");
            txtTest.setTypeface(font);
            rbAbozar.setChecked(true);

        }
        if(f.equals("QuranTaha")){
            rbQuran.setChecked(true);
        }
        if(f.equals("zar")){
            rbZar.setChecked(true);
        }

        //size
        int s =sp.getInt("size", 18);
        txtTest.setTextSize(s);
        sbSize.setProgress(s);

        //space
        int p =sp.getInt("space", 2);
        txtTest.setLineSpacing(p, 1);
        sbSpace.setProgress(p);

        //mode
        String m =sp.getString("mode", "day");
        mode=m;
        if(m.equals("day")){
            txtTest.setBackgroundColor(Color.WHITE);
            txtTest.setTextColor(Color.BLACK);
            rbDay.setChecked(true);
            mode="day";

        }else{
            txtTest.setBackgroundColor(Color.BLACK);
            txtTest.setTextColor(Color.WHITE);
            rbNight.setChecked(true);
            mode="night";

        }
    }

    private void set(){
        rbAbozar=(RadioButton) findViewById(R.id.setting_rb_abozar);
        rbQuran=(RadioButton) findViewById(R.id.setting_rb_quran);
        rbZar=(RadioButton) findViewById(R.id.setting_zar);

        rbDay=(RadioButton) findViewById(R.id.setting_rb_day);
        rbNight=(RadioButton) findViewById(R.id.setting_rb_night);

        txtTest=(TextView) findViewById(R.id.setting_testtext);
        txtFont=(TextView) findViewById(R.id.setting1);
        txtSize=(TextView) findViewById(R.id.setting2);
        txtSpace=(TextView) findViewById(R.id.setting3);
        txtMode=(TextView) findViewById(R.id.setting4);

        sbSize=(SeekBar) findViewById(R.id.setting_sb_size);
        sbSpace=(SeekBar) findViewById(R.id.setting_sb_space);


        btnSave=(Button) findViewById(R.id.setting_save);
        btnCancel=(Button) findViewById(R.id.setting_cancel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting, menu);
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
