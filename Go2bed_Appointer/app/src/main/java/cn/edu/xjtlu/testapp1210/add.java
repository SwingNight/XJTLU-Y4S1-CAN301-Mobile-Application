package cn.edu.xjtlu.testapp1210;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class add extends AppCompatActivity {
    private TextView textView_1;
    private TextView textView_2;
    private Button button_1;
    private Button button_2;
    private String time_1;
    private String time_2;
    private EditText location;
    private int num1;
    private int num2;
    private String week;
    private Spinner sp;
    private String ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        textView_1 = findViewById(R.id.textView_start);
        button_1 = findViewById(R.id.button_s);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView_1.setText(time_1);
                Calendar calendar = Calendar.getInstance();
                TimePickerDialog dialog = new TimePickerDialog(add.this, AlertDialog.THEME_HOLO_LIGHT, new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        time_1 = String.format("%d:%d", i, i1);
                        num1 = i*60+i1;
                        timePickerResult(time_1,textView_1);
                    }
                },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true);
                dialog.show();
            }

        });

        textView_2 = findViewById(R.id.textView_close);
        button_2 = findViewById(R.id.button_c);
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView_2.setText(time_2);
                Calendar calendar = Calendar.getInstance();
                TimePickerDialog dialog = new TimePickerDialog(add.this, AlertDialog.THEME_HOLO_LIGHT, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        time_2 = String.format("%d:%d", i, i1);
                        num2 = i*60+i1;
                        timePickerResult(time_2,textView_2);
                    }
                },calendar.get(Calendar.HOUR_OF_DAY)+1,calendar.get(Calendar.MINUTE),true);
                dialog.show();
            }
        });

        sp = findViewById(R.id.spinner);
        sp.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int position, long id) {
                System.out.println(parent.getSelectedItem());
                week = sp.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button button4 = findViewById(R.id.button6);
        location = findViewById(R.id.location1);
        ac = LoginActivity.account;

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num2-num1>=60&&location.length()!=0){
                    Log.e("leo","start time :"+time_1);
                    Log.e("leo","close time :"+time_2);
                    Log.e("leo","week :"+week);
                    Log.e("leo","name:"+ac);
                    String l = location.getText().toString();
                    Log.e("leo","location :"+l);

                    Professor newItem = new Professor(ac,l,week,time_1,time_2);
                    Fragment2.search_stuList.add(newItem);

                    Intent intent = new Intent("android.intent.action.CART_BROADCAST");
                    intent.putExtra("data","refresh");
                    LocalBroadcastManager.getInstance(add.this).sendBroadcast(intent);
                    sendBroadcast(intent);

                    finish();//activity to Fragment
                }
                else if(num2-num1<60){
                    putToast();
                }
                else {
                    putToast2();
                }


            }
        });
    }

    public void putToast(){
        Toast.makeText(this,"please select correct time", LENGTH_SHORT).show();

    }
    public void putToast2(){
        Toast.makeText(this,"please input location", LENGTH_SHORT).show();

    }

    public void timePickerResult(String time, TextView textView) {
        textView.setText(time);
    }
}