package com.vijendraniyer.projectdemo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;

    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
    private TextView t5;
    private TextView t6;
    private TextView t7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1= findViewById(R.id.T1);
        t2= findViewById(R.id.T2);
        t3= findViewById(R.id.T3);
        t4= findViewById(R.id.T4);
        t5= findViewById(R.id.T5);
        t6= findViewById(R.id.T6);
        t7= findViewById(R.id.T7);
        databaseReference=FirebaseDatabase.getInstance().getReference();

        GetDataAct();

    }

    public void GetDataAct() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String dummy_text1,dummy_error, text1, text2, text3, text4, text6, dummy_text6, dummy_status, status;
                        DataGetClass dataGetClass = dataSnapshot.getValue(DataGetClass.class);

                        if (dataGetClass != null) {
                            dummy_text1 = t1.getText().toString().trim();
                            dummy_error = dataGetClass.error;
                            dummy_text6 = t6.getText().toString().trim();
                            dummy_status = dataGetClass.status;
                            if(!Objects.equals(dummy_status, dummy_text6)) {
                                text6=dataGetClass.status;
                                switch (text6){
                                    case "51":
                                        status="Ready to start";
                                        t7.setText(R.string.hour_1_45);
                                        break;
                                    case "52":
                                        status="Weighing paper";
                                        t7.setText(R.string.hour_1_40);
                                        break;
                                    case "53":
                                        status="Done weighing. \nStart shredding.";
                                        t7.setText(R.string.hour_1_30);
                                        break;
                                    case "54":
                                        status="Shredding";
                                        t7.setText(R.string.hour_1_20);
                                        break;
                                    case "55":
                                        status="Done shredding"+"\ud83d\udc4d";
                                        t7.setText(R.string.hour_1_15);
                                        break;
                                    case "56":
                                        status="Water filling";
                                        t7.setText(R.string.hour_1_10);
                                        break;
                                    case "57":
                                        status="Water filled";
                                        t7.setText(R.string.hour_1);
                                        break;
                                    case "58":
                                        status="Chemical filling";
                                        t7.setText(R.string.hour_1);
                                        break;
                                    case "59":
                                        status="Chemical filled";
                                        t7.setText(R.string.hour_1);
                                        break;
                                    case "60":
                                        status="Motor on";
                                        t7.setText(R.string.min_55);
                                        break;
                                    case "61":
                                        status="Motor stopped";
                                        t7.setText(R.string.min_50);
                                        break;
                                    case "62":
                                        status="Soaking";
                                        t7.setText(R.string.min_45);
                                        break;
                                    case "63":
                                        status="Water Draining";
                                        t7.setText(R.string.min_15);
                                        break;
                                    case "64":
                                        status="Pulp Ready!"+"\ud83d\udc4d"+" Open outlet door";
                                        t7.setText(R.string.min_05);
                                        break;
                                    case "65":
                                        status="Chemical level is okay";
                                        t7.setText(R.string.hour_1);
                                        break;
                                    default:
                                        status="All Okay"+"\ud83d\udc4d";

                                        break;
                                }
                                t6.setText(status);
                            }
                                if (!Objects.equals(dummy_error, dummy_text1)) {
                                if(Objects.equals(dummy_text1, "No error")){
                                    text1 = dataGetClass.error;
                                    t1.setText(text1);
                                }
                                else {
                                    text4 = t4.getText().toString().trim();
                                    t5.setText(text4);
                                    text3 = t3.getText().toString().trim();
                                    t4.setText(text3);
                                    text2 = t2.getText().toString().trim();
                                    t3.setText(text2);
                                    text1 = t1.getText().toString().trim();
                                    t2.setText(text1);
                                    text1 = dataGetClass.error;
                                    t1.setText(text1);
                                }
                                }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        },0,100);
        }
}