package com.example.eschoolsl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class page extends AppCompatActivity {


    String st,st0,st1,st2,st3,st4,st5,st6,st16,st17,st18,st20;
    TextView tv,tv0,tv1,tv2,tv3,tv4,tv5,tv6,tv16,tv17,tv18,tv20;
    PDFView pdfView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);



        final ProgressBar progressBar = findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.VISIBLE);

        pdfView = findViewById(R.id.pdfview);

        tv = findViewById(R.id.ltext5);
        st = getIntent().getExtras().getString("value");
        tv.setText(st);
        tv1 = findViewById(R.id.ltext6);
        st1= getIntent().getExtras().getString("gid");
        tv1.setText(st1);
        tv2 = findViewById(R.id.ltext7);
        st2 = getIntent().getExtras().getString("SIDNAME");
        tv2.setText(st2);
        tv3 = findViewById(R.id.ltext8);
        st3 = getIntent().getExtras().getString("SID");
        tv3.setText(st3);
        tv4 = findViewById(R.id.ltext9);
        st4 = getIntent().getExtras().getString("CIDNAME");
        tv4.setText(st4);
        tv5 = findViewById(R.id.ltext10);
        st5 = getIntent().getExtras().getString("CID");
        tv5.setText(st5);
        tv6 = findViewById(R.id.ltext11);
        st6 = getIntent().getExtras().getString("CPDF");
        tv6.setText(st6);
        tv0 = findViewById(R.id.ltext12);
        st0 = getIntent().getExtras().getString("SIDPDF");
        tv0.setText(st0);
        tv16 = findViewById(R.id.ltext16);
        st16 = getIntent().getExtras().getString("qa");
        tv16.setText(st16);

        tv17 = findViewById(R.id.ltext17);
        st17 = getIntent().getExtras().getString("qb");
        tv17.setText(st17);

        tv18 = findViewById(R.id.ltext18);
        st18 = getIntent().getExtras().getString("qc");
        tv18.setText(st18);

        tv20 = findViewById(R.id.ltext20);
        st20 = getIntent().getExtras().getString("cvideo");
        tv20.setText(st20);



        new Retrivepddf().execute(st6);

    }
    class Retrivepddf extends AsyncTask<String,Void, InputStream> {


        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;

            try{
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                if(urlConnection.getResponseCode()==200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            }
            catch (IOException e){
                return null;

            }
            return inputStream;

        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream).load();
        }
    }

public void gamea(View view){
            Intent intent = new Intent (this, game.class);
    intent.putExtra("value",st);
    intent.putExtra("gid",st1);
    intent.putExtra("SID",st3);
    intent.putExtra("SIDNAME",st2);
    intent.putExtra("SIDPDF",st0);
    intent.putExtra("CIDNAME",st4);
    intent.putExtra("CID",st5);
    intent.putExtra("CPDF",st6);
    intent.putExtra("qa",st16);
    intent.putExtra("qb",st17);
    intent.putExtra("qc",st18);
    intent.putExtra("cvideo",st20);
            startActivity (intent);

}
    public void backpag(View view){

        Intent intent = new Intent(getApplicationContext(),lesson.class);
        intent.putExtra("value",st);
        intent.putExtra("gid",st1);
        intent.putExtra("SID",st3);
        intent.putExtra("SIDNAME",st2);
        intent.putExtra("SIDPDF",st0);
        startActivity(intent);
    }
}
