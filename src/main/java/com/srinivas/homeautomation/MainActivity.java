package com.srinivas.homeautomation;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.srinivas.homeautomation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final
        DatabaseReference smartAC = myRef.child("smartAC").child("status");
        final
        DatabaseReference smartBULB = myRef.child("smartBULB").child("status");
        final
        DatabaseReference smartTV = myRef.child("smartTV").child("status");



        TextView textView1, textView2, textView3, masterSwitch;
       Switch  s1, s2, s3, s4;
        s1 = (Switch) findViewById(R.id.button1);
        s2 = (Switch) findViewById(R.id.button2);
        s3 = (Switch) findViewById(R.id.button3);
        s4 = (Switch) findViewById(R.id.button4);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        masterSwitch = (TextView) findViewById(R.id.master_switch);

        smartAC.addValueEventListener(new ValueEventListener() {
            boolean firstTime=true;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
// This method is called once with the initial value and again
// whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                textView1.setText("smartAC " + value);
                if(firstTime) {
                    if (value.equals( "ON")) {
                        s4.setChecked(true);
                        s1.setChecked(true);
                        firstTime = false;
                    } else {
                        s1.setChecked(false);
                        firstTime = false;
                    }


                }


            }
            @Override
            public void onCancelled(DatabaseError error) {
// Failed to read value
                Log.w("file", "Failed to read value.", error.toException());
            }
        });


   smartBULB.addValueEventListener(new ValueEventListener() {
        boolean firstTime=true;
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
// This method is called once with the initial value and again
// whenever data at this location is updated.
            String value = dataSnapshot.getValue(String.class);
            textView2.setText("smartBULB " + value);
            if(firstTime) {
                if (value.equals( "ON")) {
                    s4.setChecked(true);
                    s2.setChecked(true);
                    firstTime = false;
                } else {
                    s2.setChecked(false);
                    firstTime = false;
                }
            }
        }
        @Override
        public void onCancelled(DatabaseError error) {
// Failed to read value
            Log.w("file", "Failed to read value.", error.toException());
        }
    });
smartTV.addValueEventListener(new ValueEventListener() {
        boolean firstTime=true;
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
// This method is called once with the initial value and again
// whenever data at this location is updated.
            String value = dataSnapshot.getValue(String.class);
            textView3.setText("smartTV " + value);
            if(firstTime) {
                if (value.equals( "ON")) {
                    s4.setChecked(true);
                    s3.setChecked(true);
                    firstTime = false;
                } else {
                    s3.setChecked(false);
                    firstTime = false;
                }
            }
        }
        @Override
        public void onCancelled(DatabaseError error) {
// Failed to read value
            Log.w("file", "Failed to read value.", error.toException());
        }
    });


        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (s4.isChecked())
                    if (s1.isChecked())
                        smartAC.setValue(("ON"));
                    else
                        smartAC.setValue("OFF");
                else
                if(s1.isChecked()){
                    s1.setChecked(false);
                    Toast.makeText(getApplicationContext(),"Enable master switch",Toast.LENGTH_SHORT).show();
                }
            }
        });
        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (s4.isChecked())
                    if (s2.isChecked())
                        smartBULB.setValue(("ON"));
                    else
                        smartBULB.setValue("OFF");
                else
                if(s2.isChecked()){
                    s2.setChecked(false);
                    Toast.makeText(getApplicationContext(),"Enable master switch",Toast.LENGTH_SHORT).show();
                }
            }
        });   s3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (s4.isChecked())
                    if (s3.isChecked())
                        smartTV.setValue(("ON"));
                    else
                        smartTV.setValue("OFF");
                else
                if(s3.isChecked()){
                    s3.setChecked(false);
                    Toast.makeText(getApplicationContext(),"Enable master switch",Toast.LENGTH_SHORT).show();
                }
            }
        });


        s4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (!s4.isChecked())
                {
                    s1.setChecked(false);
                    s2.setChecked(false);
                    s3.setChecked(false);
                    smartAC.setValue("OFF");
                    smartBULB.setValue("OFF");
                    smartTV.setValue("OFF");
                    masterSwitch.setText("Master Switch OFF");
                }
                else{ masterSwitch.setText("Master Switch ON");
                } } });
    }

}