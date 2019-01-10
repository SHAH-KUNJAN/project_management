package com.example.abc.project_management;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

public class TimepickerClass extends AppCompatActivity {

    String btn;
    TimePicker tp;
    int Hour,Minute;
    Boolean check=false;
    Button btntime;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timepicker);

        tp=(TimePicker)findViewById(R.id.timepick);
        btntime=(Button)findViewById(R.id.btntime);

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

                Hour=hourOfDay;
                Minute=minute;

            }
        });

        btntime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn=getIntent().getExtras().getString("btn");
                String timereply;
                switch(btn)
                {
                    case "time1":
                        timereply = Hour + ":" + Minute;
                      count=Hour;
                        if(Hour>6 && Hour<=22) {
                            Intent data = new Intent();
                            data.putExtra("time1", timereply);
                            data.putExtra("checknow", check);
                            setResult(RESULT_OK, data);
                            finish();
                        }
                        else{
                            check=true;
                            Intent data = new Intent();
                            data.putExtra("time1", timereply);
                            data.putExtra("checknow", check);
                            setResult(RESULT_OK, data);
                            finish();
                        }

                    case  "time2":
                        timereply=Hour+":"+Minute;
                        if(count<Hour && Hour>7 && Hour<=23) {
                            Intent data = new Intent();
                            data.putExtra("time2", timereply);
                            data.putExtra("checknow", check);
                            setResult(RESULT_OK, data);
                            finish();
                        }
                        else{
                            check=true;
                            Intent data1 = new Intent();
                            data1.putExtra("time2", timereply);
                            data1.putExtra("checknow", check);
                            setResult(RESULT_OK, data1);
                            finish();
                        }
                }



            }
        });


    }
}
