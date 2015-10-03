package haj.test.com.haj;

import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.InputStream;

import haj.test.com.haj.Data.TextRepository;
import haj.test.com.haj.Models.text;

public class ContextActivity extends AppCompatActivity {

    private int MyPage=1;
    private text text;
    public String soundFileName="";
    public Button btnSound;
    public ImageView imPagePic;
    public ToggleButton tgbSound;
   final MediaPlayer mediaPlayer=new MediaPlayer();
    public TextView context;
    ///////
    ScaleGestureDetector scaleGestureDetector;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context);



        Bundle b = getIntent().getExtras();
        final String Header = b.getString("header");
        String matn = b.getString("matn");
        final int page = b.getInt("page", -1);

        imPagePic=(ImageView)findViewById(R.id.imPagePic);
        final TextRepository tr = new  TextRepository(this);
        text=tr.getTextByPage(Header,MyPage);
       btnSound = (Button) findViewById(R.id.btnSound);
        final int[] status = {0};//GLOBAL VARIABLE : the status of the Button ( 0 or 1 )
        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (status[0] == 0) {

                    if(MainActivity.mode.matches("day")) {

                        btnSound.setBackgroundResource(R.drawable.speaker_on);
                    }
                    else {
                        btnSound.setBackgroundResource(R.drawable.speaker_on2);
                    }
                try
                {
                    AssetFileDescriptor descriptor = getApplicationContext().getAssets().openFd(soundFileName + ".mp3");
                    long start = descriptor.getStartOffset();
                    long end = descriptor.getLength();
                    mediaPlayer.setDataSource(descriptor.getFileDescriptor(), start, end);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }
                catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                    ex.printStackTrace();
                }

                    status[0] =1 ; // change the status to 1 so the at the second clic , the else will be executed

                }

                else {
                    if(MainActivity.mode.matches("day")) {

                        btnSound.setBackgroundResource(R.drawable.speaker_off);
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        status[0] =0;//change the status to 0 so the at the second clic , the if will be executed

                    }
                    else {
                        btnSound.setBackgroundResource(R.drawable.speaker_off2);
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        status[0] =0;//change the status to 0 so the at the second clic , the if will be executed

                    }

                }


            }


        });
        soundFileName=text.sound;

        setSoundFromAssetToSoundFileName(soundFileName);
        setImageFromAssetToImageView(text.pictures);

       TextView head = (TextView) findViewById(R.id.main_titr);
        head.setText(Header);
        head.setTypeface(MainActivity.font);






        context = (TextView) findViewById(R.id.main_context);
        context.setText(text.Matn);

        context.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
        return scaleGestureDetector.onTouchEvent(motionEvent);
    }
});
//////////////////////////////

        scaleGestureDetector = new ScaleGestureDetector(this, new simpleOnScaleGestureListener());



        context.setTypeface(MainActivity.font);
        context.setTextSize(MainActivity.size);
        context.setLineSpacing(MainActivity.space, 1);

       final TextView cpage = (TextView) findViewById(R.id.page);
        cpage.setText("صفحة" + MyPage + "من" + page);


        LinearLayout rl=(LinearLayout) findViewById(R.id.main_matn_layout);

        if(MainActivity.mode.matches("day")){

            Resources res = getResources();
            Drawable dr = res.getDrawable(R.drawable.back_lighte);
            rl.setBackgroundDrawable(dr);


            rl.setBackgroundColor(Color.WHITE);
            context.setTextColor(Color.BLACK);
            head.setTextColor(Color.BLACK);
            cpage.setTextColor(Color.BLACK);

        }else{
            Resources res = getResources();
            Drawable dr = res.getDrawable(R.drawable.back_darck);
            Drawable drb = res.getDrawable(R.drawable.speaker_off2);
            rl.setBackgroundDrawable(dr);
            btnSound.setBackgroundDrawable(drb);

            rl.setBackgroundColor(Color.BLACK);
            context.setTextColor(Color.WHITE);
            head.setTextColor(Color.WHITE);
            cpage.setTextColor(Color.WHITE);

        }

        Button next = (Button) findViewById(R.id.next_page);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (page == MyPage) {

                    Toast.makeText(getApplicationContext(), " الصفحة الأخيرة", Toast.LENGTH_SHORT).show();

                } else {

                    MyPage++;
                    TextRepository tr = new TextRepository(getApplicationContext());
                    text = tr.getTextByPage(Header, MyPage);
                    setSoundFromAssetToSoundFileName(soundFileName);

                    setImageFromAssetToImageView(text.pictures);

                    context = (TextView) findViewById(R.id.main_context);
                    context.setText(text.Matn);
                    context.setTypeface(MainActivity.font);
                    context.setTextSize(MainActivity.size);
                    context.setLineSpacing(MainActivity.space, 1);

                    final TextView cpage = (TextView) findViewById(R.id.page);
                    cpage.setText("صفحة" + MyPage + "من" + page);

                }
            }
        });

        Button previous = (Button) findViewById(R.id.pre_page);

        previous.setOnClickListener(new View.OnClickListener(){


            public void onClick(View arg0){

                if(MyPage==1){

                    Toast.makeText(getApplicationContext(), " الصفحة الأولي", Toast.LENGTH_SHORT).show();

                }else{
                    MyPage--;
                    TextRepository tr = new  TextRepository(getApplicationContext());
                    text=tr.getTextByPage(Header,MyPage);
                    setSoundFromAssetToSoundFileName(soundFileName);

                    setImageFromAssetToImageView(text.pictures);
                    context = (TextView) findViewById(R.id.main_context);
                    context.setText(text.Matn);
                    context.setTypeface(MainActivity.font);
                    context.setTextSize(MainActivity.size);
                    context.setLineSpacing(MainActivity.space, 1);

                    final TextView cpage = (TextView) findViewById(R.id.page);
                    cpage.setText("صفحة"+MyPage +"من"+page);
                }

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }

    public void setSoundFromAssetToSoundFileName(String soundfile) {

        if(soundfile==null ||soundfile.length()==0)
        {
            btnSound.setVisibility(View.GONE);
            return;
        }
        else {
            btnSound.setVisibility(View.VISIBLE);
        }
    }
    public void setImageFromAssetToImageView(String filename) {

        if(filename==null||filename.length()==0)
        {
            imPagePic.setVisibility(View.GONE);
            return;
        }

        try {
            imPagePic.setVisibility(View.VISIBLE);
            InputStream ims = getAssets().open(filename + ".jpg");
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            imPagePic.setImageDrawable(d);

        } catch (Exception e) {

            Toast.makeText(getApplicationContext(), "errore", Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_context, menu);
        return true;
    }


    ////////////////////////////////////////////


//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        // TODO Auto-generated method stub
//        scaleGestureDetector.onTouchEvent(event);
//        return true;
//
//    }

    public class simpleOnScaleGestureListener extends
            ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            // TODO Auto-generated method stub
            float size = context.getTextSize();
            Log.d("TextSizeStart", String.valueOf(size));

            float factor = detector.getScaleFactor();
            Log.d("Factor", String.valueOf(factor));


            float product = size*factor;
            Log.d("TextSize", String.valueOf(product));
            context.setTextSize(TypedValue.COMPLEX_UNIT_PX, product);

            size = context.getTextSize();

            context.setText(context.getText().toString().trim());
            Log.d("TextSizeEnd", String.valueOf(size));
            return true;
        }
    }




    ////////////////////////////////////////////////

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
