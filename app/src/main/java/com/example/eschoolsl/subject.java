package com.example.eschoolsl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class subject extends AppCompatActivity {


    String st,st0,st1,st2,st3,st8;
    TextView tv,tv0,tv1,tv2,tv3;


    ListView lv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);


        tv = findViewById(R.id.subjectv);
        tv1 = findViewById(R.id.ltext2);
        st = getIntent().getExtras().getString("value");
        st1 = getIntent().getExtras().getString("gid");
        tv.setText(st);
        tv1.setText(st1);

        lv = findViewById(R.id.llist);




        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {


                tv3 = view.findViewById(R.id.tvsid);
                st3 = tv3.getText().toString();

                tv2 = view.findViewById(R.id.tvsidname);
                st2 = tv2.getText().toString();

                tv0 = view.findViewById(R.id.tvsidpdf);
                st0 = tv0.getText().toString();

                getIntent().getExtras().getString("value");
                getIntent().getExtras().getString("gid");

                 st8 = "sg3";
                int leone = st8.length ();
                int letwo = st1.length ();

                if(leone == letwo){

                    Intent intent = new Intent(getApplicationContext(),lesson.class);

                    intent.putExtra("value",st);
                    intent.putExtra("gid",st1);
                    intent.putExtra("SIDNAME",st2);
                    intent.putExtra("SID",st3);
                    intent.putExtra("SIDPDF",st0);

                    startActivity(intent);

                }

                else {
                    Intent intent = new Intent(getApplicationContext(),plesson.class);

                    intent.putExtra("value",st);
                    intent.putExtra("gid",st1);
                    intent.putExtra("SIDNAME",st2);
                    intent.putExtra("SID",st3);
                    intent.putExtra("SIDPDF",st0);

                    startActivity(intent);

                }



            }
        });


    }

    public void back(View view){
        Intent intent = new Intent(this,menus.class);
        startActivity(intent);
        finish();
    }

    protected void onResume(){
        super.onResume();
        loadSession();
    }

    public void loadSession(){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://eschoolsl.000webhostapp.com/subject.php?gid="+st1+"";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        setSessions(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(subject.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
                        Log.d("VOLLEY", error.getMessage());


                    }
                });

        queue.add(request);
    }
    public void setSessions(JSONArray response){
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try{
            for(int i=0;i <response.length()  ;i++){
                JSONObject obj = response.getJSONObject(i);
                HashMap<String, String> map = new HashMap<>();

                map.put("gid",obj.getString("gid"));
                map.put("sid",obj.getString("sid"));
                map.put("sidname",obj.getString("sidname"));
                map.put("sidpdf",obj.getString("sidpdf"));

                list.add(map);
            }
            //1.layout file
            int layout= R.layout.sub;
            //2.views
            int[] views = {R.id.tvgid, R.id.tvsid,R.id.tvsidpdf, R.id.tvsidname};
            //3.Columms
            String[]  columns = {"gid","sid","sidpdf","sidname"};

            SimpleAdapter adapter = new SimpleAdapter(this, list, layout, columns, views);
            lv.setAdapter(adapter);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
