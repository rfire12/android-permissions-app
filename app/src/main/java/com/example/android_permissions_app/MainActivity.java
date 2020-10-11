package com.example.android_permissions_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.Manifest;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    private static final String ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String CAMERA = Manifest.permission.CAMERA;
    private static final String CALL_PHONE = Manifest.permission.CALL_PHONE;
    private static final String READ_CONTACTS = Manifest.permission.READ_CONTACTS;
    private static final int PERMISSION_CODE = 1;
    private Switch storageSw;
    private Switch locationSw;
    private Switch cameraSw;
    private Switch phoneSw;
    private Switch contactsSw;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storageSw = findViewById(R.id.storageSw);
        locationSw = findViewById(R.id.locationSw);
        cameraSw = findViewById(R.id.cameraSw);
        phoneSw = findViewById(R.id.phoneSw);
        contactsSw = findViewById(R.id.contactsSw);

        Button cancelBtn = findViewById(R.id.cancelBtn);
        Button continueBtn = findViewById(R.id.continueBtn);

        // Close APP
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });

        // Check Permissions
        continueBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                List<String> permissionsList = new ArrayList<>();

                if (storageSw.isChecked() && !isPermissionGranted(READ_EXTERNAL_STORAGE))
                    permissionsList.add(READ_EXTERNAL_STORAGE);
                if (locationSw.isChecked() && !isPermissionGranted(ACCESS_FINE_LOCATION))
                    permissionsList.add(ACCESS_FINE_LOCATION);
                if (cameraSw.isChecked() && !isPermissionGranted(CAMERA))
                    permissionsList.add(CAMERA);
                if (phoneSw.isChecked() && !isPermissionGranted(CALL_PHONE))
                    permissionsList.add(CALL_PHONE);
                if (contactsSw.isChecked() && !isPermissionGranted(READ_CONTACTS))
                    permissionsList.add(READ_CONTACTS);

                // If there are permissions to request.
                if( permissionsList.size() != 0 ) {
                    ActivityCompat.requestPermissions(MainActivity.this, permissionsList.toArray(new String[0]), PERMISSION_CODE);
                }
            }

        });
    }

    public Boolean isPermissionGranted(String permission) {
        return ContextCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_GRANTED;
    }


}