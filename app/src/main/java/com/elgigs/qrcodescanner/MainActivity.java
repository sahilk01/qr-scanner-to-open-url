package com.elgigs.qrcodescanner;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static TextView result;
        Button starQr;
        public static String url;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (url != null) {
            String urlString = url;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setPackage("com.android.chrome");

            try {
                getApplicationContext().startActivity(intent);
            } catch (ActivityNotFoundException ex) {
                // Chrome browser presumably not installed so allow user to choose instead
                intent.setPackage(null);
                getApplicationContext().startActivity(intent);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        starQr = findViewById(R.id.button);
        result = findViewById(R.id.result);
        requestPermissions(new String[]{Manifest.permission.CAMERA}, 10);
        requestPermissions(new String[]{Manifest.permission.INTERNET}, 20);


        starQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startScan = new Intent(getApplicationContext(), scan.class);
                startActivity(startScan);
            }
        });
    }
}
