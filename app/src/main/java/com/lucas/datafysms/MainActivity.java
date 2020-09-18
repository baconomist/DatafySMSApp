package com.lucas.datafysms;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;

public class MainActivity extends AppCompatActivity {

    //    private static String serverPhoneNumberRaw = "+1 647-455-1820";
    private static String serverPhoneNumberRaw = "+1 647-960-4933";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissions();
        sendMessageToServer("Test Message To Server");
    }

    private void requestPermissions() {


        // The request code used in ActivityCompat.requestPermissions()
// and returned in the Activity's onRequestPermissionsResult()
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {
                Manifest.permission.SEND_SMS,
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.BROADCAST_SMS,
                Manifest.permission.READ_SMS
        };

//        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
//        }
    }

    private void sendMessageToServer(String msg) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(getUsableServerNumber(), null, msg, null, null);
    }

    private static String getUsableServerNumber() {
        return serverPhoneNumberRaw.replace(" ", "").replace("-", "");
    }
}
