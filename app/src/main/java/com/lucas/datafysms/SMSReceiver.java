package com.lucas.datafysms;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

// Referred to by AndroidManifest.xml
public class SMSReceiver extends BroadcastReceiver {

    public static final String pdu_type = "pdus";

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
        // Get the SMS message.

        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs;
        String strMessage = "";
        String format = bundle.getString("format");
        Object[] pdus = (Object[]) bundle.get(pdu_type);

        if (pdus != null) {
            // Check the Android version.
            boolean isVersionM = (Build.VERSION.SDK_INT >=
                    Build.VERSION_CODES.M);


            // Fill the msgs array.
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                // Check Android version and use appropriate createFromPdu.
                if (isVersionM) {
                    // If Android version M or newer:
                    msgs[i] =
                            SmsMessage.createFromPdu((byte[]) pdus[i], format);
                } else {
                    // If Android version L or older:
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }

                strMessage += "SMS from " + msgs[i].getOriginatingAddress();
                strMessage += " :" + msgs[i].getMessageBody() + "\n";

                // Log and display the SMS message.
                Log.d("SMSReceiver", "onReceive: " + strMessage);
                Toast.makeText(context, strMessage, Toast.LENGTH_LONG).show();
            }

            Log.d("SMSReceiver", "onReceive...");
        }
    }
}
