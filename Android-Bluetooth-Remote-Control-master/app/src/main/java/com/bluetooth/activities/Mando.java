package com.bluetooth.activities;


import com.bluetooth.BluetoothActivity;
import com.bluetooth.BluetoothRemoteControlApp;
import com.bluetooth.R;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.animation.TimeInterpolator;

/**
 * This activity lets the user send absolutely any data to the robot and display
 * whatever the robot response, basically it's an echo.
 */
public class Mando extends BluetoothActivity {

    private final static String TAG = "mando";

    ImageButton buttonExit;
    ImageButton buttonOnInterior;
    ImageButton buttonOffInterior;
    ImageButton buttonOnExterior;
    ImageButton buttonOffExterior;
    ImageButton modoExterior;
    ImageButton modoVentilador;
    ImageButton imgMode0;
    ImageButton imgMode1;
    ImageButton imgModeventilador;
    ImageButton buttonOnVentilador;
    ImageButton buttonOffVentilador;
    ImageView imgFan0;
    ImageView imgFan1;
    ImageView imgFan2;
    TextView txtTemp;
    TextView txtFanAuto;
    TextView interiorTop;
    TextView exteriorTop;
    TextView ventiladorTop;
    RotateAnimation animation;
    Vibrator vibrator;

    int modVen = 0;
    int modLuz =0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.mando);

        vibrator =(Vibrator) getSystemService(VIBRATOR_SERVICE);
        buttonExit = (ImageButton) findViewById(R.id.buttonExit);
        modoExterior = (ImageButton) findViewById(R.id.modoExterior);
        modoVentilador = (ImageButton) findViewById(R.id.modoVentilador);
        buttonOnInterior = (ImageButton) findViewById(R.id.buttonOnInterior);
        buttonOffInterior = (ImageButton) findViewById(R.id.buttonOffInterior);
        buttonOnExterior = (ImageButton) findViewById(R.id.buttonOnExterior);
        buttonOffExterior = (ImageButton) findViewById(R.id.buttonOffExterior);
        imgMode0 = (ImageButton) findViewById(R.id.imgMode0);
        imgMode1 = (ImageButton) findViewById(R.id.imgMode1);
        imgModeventilador = (ImageButton) findViewById(R.id.imgModeventilador);
        buttonOnVentilador = (ImageButton) findViewById(R.id.buttonOnVentilador);
        buttonOffVentilador = (ImageButton) findViewById(R.id.buttonOffVentilador);
        imgFan0 = (ImageView) findViewById(R.id.imgFan0);
        imgFan1 = (ImageView) findViewById(R.id.imgFan1);
        imgFan2 = (ImageView) findViewById(R.id.imgFan2);

        txtTemp = (TextView) findViewById(R.id.txtTemp);
        txtFanAuto = (TextView) findViewById(R.id.txtFanAuto);
        interiorTop = (TextView) findViewById(R.id.interiorTop);
        exteriorTop = (TextView) findViewById(R.id.exteriorTop);
        ventiladorTop = (TextView) findViewById(R.id.ventiladorTop);

        buttonExit.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                finish();
            }
        });



        buttonOnInterior.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                write("3\n");
                imgMode0.setBackgroundResource(R.drawable.air_mode_auto_sel);
                interiorTop.setTextColor(0xFFC1C0C0);

            }
        });

        buttonOffInterior.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                write("4\n");
                imgMode0.setBackgroundResource(R.drawable.air_mode_auto);
                interiorTop.setTextColor(0Xff8c8c8c);

            }
        });

        buttonOnExterior.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                write("1\n");
                imgMode1.setBackgroundResource(R.drawable.air_mode_auto_sel);
                exteriorTop.setTextColor(0xFFC1C0C0);

            }
        });

        buttonOffExterior.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                vibrator.vibrate(30);
                write("2\n");
                imgMode1.setBackgroundResource(R.drawable.air_mode_auto);
                exteriorTop.setTextColor(0Xff8c8c8c);

            }
        });

        modoVentilador.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                if (modVen == 0) {
                    imgModeventilador.setBackgroundResource(R.drawable.air_mode_cool_sel);
                    ventiladorTop.setTextColor(0xFFC1C0C0);
                    buttonOnVentilador.setEnabled(false);
                    buttonOffVentilador.setEnabled(false);
                    modVen = 1;
                } else {
                    imgModeventilador.setBackgroundResource(R.drawable.air_mode_cool);
                    ventiladorTop.setTextColor(0Xff8c8c8c);
                    buttonOnVentilador.setEnabled(true);
                    buttonOffVentilador.setEnabled(true);
                    modVen = 0;
                }
            }
        });
        modoExterior.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                if (modLuz == 0) {
                    buttonOnExterior.setEnabled(false);
                    buttonOffExterior.setEnabled(false);
                    modLuz = 1;
                } else {
                    buttonOnExterior.setEnabled(true);
                    buttonOffExterior.setEnabled(true);
                    modLuz = 0;

                }
            }
        });
        buttonOnVentilador.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                animation = new RotateAnimation(0.0F, 360.0F, imgFan0.getMeasuredWidth() / 2.0F, imgFan0.getMeasuredHeight() / 2.0F);
                animation.setInterpolator(new LinearInterpolator());
                animation.setRepeatCount(-1);
                animation.setDuration(1000L);
                imgFan0.startAnimation(animation);
                imgFan1.startAnimation(animation);
                imgFan2.startAnimation(animation);
            }
            }

            );

        buttonOffVentilador.setOnClickListener(new

            OnClickListener() {

                public void onClick (View v){
                    imgFan0.clearAnimation();
                    imgFan1.clearAnimation();
                   imgFan2.clearAnimation();
                }
            }
            );
        }

        public boolean handleMessage (Message msg)
        {
            super.handleMessage(msg);

            switch (msg.what) {
                case BluetoothRemoteControlApp.MSG_READ:



                    if(msg.obj.toString().contains("T:")){
                        int fin = msg.obj.toString().length();
                        String temp = msg.obj.toString().substring(2,fin);
                        txtTemp.setText(temp);
                    }else
                    if(msg.obj.toString().equals("1")){
                        buttonOnExterior.performClick();
                    }else
                    if(msg.obj.toString().equals("2")){
                        buttonOffExterior.performClick();
                    }else
                    if(msg.obj.toString().equals("3")){
                        buttonOnInterior.performClick();
                    }else
                    if(msg.obj.toString().equals("4")){
                        buttonOffInterior.performClick();
                    }else
                    if(msg.obj.toString().equals("5")){
                        buttonOnVentilador.performClick();
                        txtFanAuto.setVisibility(View.VISIBLE);
                    }else
                    if(msg.obj.toString().equals("6")){
                        buttonOffVentilador.performClick();
                        txtFanAuto.setVisibility(View.INVISIBLE);

                    }else
                    break;
                case BluetoothRemoteControlApp.MSG_WRITE:

            }
            return false;
        }

    }