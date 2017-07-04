package com.example.mnpd.plantdetector;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;


public class MainActivity extends AppCompatActivity {

    //to keep the image...
    private ImageView imgPreview;

    //Buttons for specific purposes...
    private Button btnCapturePicture;
    private Button calculation;
    private Button toGalllery;
    private Button but1;

    //Declaring text views....
    private TextView red, green, yellow, textViewred, textViewgreen, textViewyellow;
    private Uri imageUri;

    //variables for calculation part...
    //used for keeping the number of pixels for following colors in the image..
    public static double lightRed = 0;
    public static double avgRed = 0;
    public static double drkRed = 0;

    public static double lightGrn = 0;
    public static double avgGrn = 0;
    public static double drkGrn = 0;

    public static double lightYlw = 0;
    public static double avgYlw = 0;
    public static double drkYlw = 0;


    //to keep the converted image bitmap..
    private Bitmap bmp;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing above declared variables...

        imgPreview = (ImageView) findViewById(R.id.imageView);
        red = (TextView) findViewById(R.id.redPercent);
        green = (TextView) findViewById(R.id.greenPercent);
        yellow = (TextView) findViewById(R.id.yellowPercent);

        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        btnCapturePicture = (Button) findViewById(R.id.captureButton);
        calculation = (Button) findViewById(R.id.cal);
        toGalllery = (Button) findViewById(R.id.toGallery);
        but1 = (Button) findViewById(R.id.Standards);
        textViewred = (TextView) findViewById(R.id.textView1);
        textViewgreen = (TextView) findViewById(R.id.textView2);
        textViewyellow = (TextView) findViewById(R.id.textView3);


        btnCapturePicture.setTypeface(font);

        //for invoking the inbuilt Camera...
        btnCapturePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });

        //for doing calculation...
        calculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorVariation();
            }
        });

        //to goto the gallery...
        toGalllery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selctImg();
            }
        });

        //to goto guidelines...
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Standards.class);
                startActivity(i);
            }

        });

        //to goto variations of red color..
        textViewred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, pop1.class));
            }

        });

        //to goto variations of green color..
        textViewgreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, pop2.class));
            }

        });

        //to goto variations of yellow color..
        textViewyellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, pop3.class));
            }

        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    //=============================================================================================================================
    //----------------------------------------Functions that are called onClickListner-------------------------------------------------
    //=============================================================================================================================


    //to invoke the inbuilt Camera app...
    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    //to goto the Gallery in the phone...
    private void selctImg() {
        Intent i = new Intent(
                Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, 1);
    }

    //to get the Color percentages..
    public void colorVariation() {

        BitmapDrawable abmp = (BitmapDrawable) imgPreview.getDrawable();//converting the image to bitmap...
        bmp = abmp.getBitmap();


        double rCount = 0;//to keep the whole red pixel count...
        double gCount = 0;//to keep the whole green pixel count...
        double yCount = 0;//to keep the whole yellow pixel count...


        double he, w, allPixel, backColor = 0;
        double height = bmp.getHeight();
        double width = bmp.getWidth();
        allPixel = height * width;

        //finding specific color of the pixel....
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                int pixel = bmp.getPixel(x, y);
                int redValue = Color.red(pixel);
                int blueValue = Color.blue(pixel);
                int greenValue = Color.green(pixel);

                double rN, gN, bN;
                double cMax = 0;
                double cMin = 1;
                double v, diff, h = 0;
                double s;

                rN = redValue / 255.0;
                gN = greenValue / 255.0;
                bN = blueValue / 255.0;


                if (cMax < rN) {
                    cMax = rN;
                }
                if (cMax < gN) {
                    cMax = gN;
                }
                if (cMax < bN) {
                    cMax = bN;
                }

                if (cMin > rN) {
                    cMin = rN;
                }
                if (cMin > gN) {
                    cMin = gN;
                }
                if (cMin > bN) {
                    cMin = bN;
                }

                diff = cMax - cMin;

                v = cMax * 100;
                if (cMax == 0) {
                    s = 0;
                } else {
                    s = (diff * 100) / cMax;
                }


                if (diff == 0) {
                    h = 0;
                } else if (cMax == rN) {

                    h = 60 * ((gN - bN) / diff);

                } else if (cMax == gN) {

                    h = 60 * (((bN - rN) / diff) + 2);

                } else if (cMax == bN) {

                    h = 60 * (((rN - gN) / diff) + 4);

                }

                if (h < 0) {
                    h = h + 360;
                }

                //Calculating background pixels to be removed...
				if(((h >= 0 && h <= 12) || (h >= 335 && h <= 360)) && (((s <= 100 && s >= 20) && (v <= 100 && v >= 80)) || ((s <= 100 && s >= 35) && (v <= 80 && v >= 50)))){
                	
                	backColor++;
                	continue;
               	}
           
                //for red main
                //(((s<=100 && s>=20 ) && (v<=100 && v>=80 )) || ((s<=100 && s>=35 ) && (v<=80 && v>=50 )))

                //red color variation..
                if (((h >= 0 && h <= 12) || (h >= 335 && h <= 360)) && (((s <= 100 && s >= 20) && (v <= 100 && v >= 80)) || ((s <= 100 && s >= 35) && (v <= 80 && v >= 50)))) {

                    rCount++;

                    if ((s <= 74 && s >= 20) && (v <= 100 && v >= 80)) {
                        lightRed++;
                    }
                    if ((s <= 100 && s >= 75) && (v <= 100 && v >= 80)) {
                        avgRed++;
                    }
                    if ((s <= 100 && s >= 35) && (v <= 79 && v >= 50)) {
                        drkRed++;
                    }

                }

                //green count
                // &&  ((s<=100 && s>=20) && (v<=100 && v>=30 ))
                if (h >= 61 && h <= 160) {
                    gCount++;


                    if (((s <= 100 && s >= 20) && (v <= 100 && v >= 70)) || ((s <= 45 && s >= 20) && (v <= 90 && v >= 60))) {
                        lightGrn++;
                    }
                    if ((s <= 100 && s >= 45) && (v <= 69 && v >= 60)) {
                        avgGrn++;
                    }
                    if ((s <= 100 && s >= 20) && (v <= 59 && v >= 30)) {
                        drkGrn++;
                    }

                }

                //yellow count..
                if (h >= 45 && h <= 60) {


                    if ((h >= 50 && h <= 60) && (s <= 69 && s >= 20) && (v <= 100 && v >= 97)) {
                        yCount++;
                        lightYlw++;
                    }
                    //consider about v 90...
                    if (((h >= 53 && h <= 55) && (s <= 100 && s >= 70) && (v <= 100 && v >= 85)) || ((h >= 56 && h <= 60) && (s <= 100 && s >= 70) && (v <= 100 && v >= 90))) {
                        yCount++;
                        avgYlw++;
                    }
                    if ((h >= 45 && h <= 52) && (s <= 100 && s >= 45) && (v <= 100 && v >= 60)) {
                        yCount++;
                        drkYlw++;
                    }

                    if (((h >= 57 && h <= 60) && (s <= 100 && s >= 40) && (v <= 89 && v >= 35))) {

                        gCount++;

                    }

                }


            }
        }

        //giving the output as percentages..
        red.setText(String.format("%.2f", (rCount / (allPixel- backColor)) * 100));
        green.setText(String.format("%.2f", (gCount / (allPixel- backColor)) * 100));
        yellow.setText(String.format("%.2f", (yCount / (allPixel- backColor)) * 100));

        lightRed = (lightRed / rCount) * 100;
        avgRed = (avgRed / rCount) * 100;
        drkRed = (drkRed / rCount) * 100;

        lightGrn = (lightGrn / gCount) * 100;
        avgGrn = (avgGrn / gCount) * 100;
        drkGrn = (drkGrn / gCount) * 100;

        lightYlw = (lightYlw / yCount) * 100;
        avgYlw = (avgYlw / yCount) * 100;
        drkYlw = (drkYlw / yCount) * 100;

        //for previewing that the work has been completed...
        Toast.makeText(getApplicationContext(), "Calculated Successfully! You can see more details by touching red,green and yellow Text Views!", Toast.LENGTH_LONG).show();

    }

    //=============================================================================================================================
    //=============================================================================================================================

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 0) {
            Bitmap bp = (Bitmap) data.getExtras().get("data");
            imgPreview.setImageBitmap(bp);
        }

        if (resultCode == RESULT_OK && requestCode == 1) {

            imageUri = data.getData();
            imgPreview.setImageURI(imageUri);

        }

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

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

}
