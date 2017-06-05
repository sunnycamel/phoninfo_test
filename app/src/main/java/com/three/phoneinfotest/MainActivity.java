package com.three.phoneinfotest;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class MainActivity extends AppCompatActivity {
    private  String str_info = "";
    private  TextView phoneinfo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phoneinfo = (TextView) findViewById(R.id.phoneinfo);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 0);
        } else {
            getPhoneInfo();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    getPhoneInfo();
                }
                break;

            default:
                break;
        }
    }

    // get deviceID, MAC address, SN, Android ID
    private void getPhoneInfo() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        str_info += "DeviceID: " + tm.getDeviceId() + "\n";
        str_info += "Build serial: " + Build.SERIAL + "\n";
        str_info += "Android ID: " + Settings.Secure.ANDROID_ID + "\n";
        str_info += "Mac address: " + getMac() + "\n";

        //test native hook
        NativeMethod nm = new NativeMethod();
        String temp = nm.readFile();
        str_info += temp;

        phoneinfo.setText(str_info);
    }

    private   String getMac() {
        String macSerial = null;
        String str = "";
        try {
            Process pp = Runtime.getRuntime().exec(
                    "cat /sys/class/net/wlan0/address ");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);


            for (; null != str;) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return macSerial;
    }

}
