package com.bluetooth.activities;

import android.os.Bundle;
import com.bluetooth.BluetoothActivity;
import com.bluetooth.R;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by diego on 29/1/15.
 */
public class LedRgb extends BluetoothActivity {

    private final static String TAG = "mando";
    SeekBar controlRed;
    SeekBar controlGreen;
    SeekBar controlBlue;

    int red=0;
    int green=0;
    int blue=0;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.led_rgb);



        controlRed = (SeekBar) findViewById(R.id.seekBar1);
        controlGreen = (SeekBar) findViewById(R.id.seekBar2);
        controlBlue = (SeekBar) findViewById(R.id.seekBar3);



        controlGreen.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                red = progress;
                write(red+","+green+","+blue+"\n");
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        controlGreen.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                green = progress;
                write(red+","+green+","+blue+"\n");
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        controlBlue.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                blue = progress;
                write(red+","+green+","+blue+"."+"\n");
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });


    }




        }





