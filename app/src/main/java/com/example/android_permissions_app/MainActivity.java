package com.example.android_permissions_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.content.pm.PackageManager;
import android.os.Bundle;
import android.Manifest;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.android.material.snackbar.Snackbar;

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
    private Button cancelBtn;
    private Button continueBtn;
    private ConstraintLayout settingsLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        checkGrantedPermissions();
        onClickSwitchButton();

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

    public void initialize() {
        // Switches
        storageSw = findViewById(R.id.storageSw);
        locationSw = findViewById(R.id.locationSw);
        cameraSw = findViewById(R.id.cameraSw);
        phoneSw = findViewById(R.id.phoneSw);
        contactsSw = findViewById(R.id.contactsSw);
        // Buttons
        cancelBtn = findViewById(R.id.cancelBtn);
        continueBtn = findViewById(R.id.continueBtn);
        // Layouts
        settingsLayout = findViewById(R.id.settingsLayout);
    }

    public void checkGrantedPermissions() {
        if (isPermissionGranted(READ_EXTERNAL_STORAGE))
            storageSw.setChecked(true);
        if (isPermissionGranted(ACCESS_FINE_LOCATION))
            locationSw.setChecked(true);
        if (isPermissionGranted(CAMERA))
            cameraSw.setChecked(true);
        if (isPermissionGranted(CALL_PHONE))
            phoneSw.setChecked(true);
        if (isPermissionGranted(READ_CONTACTS))
            contactsSw.setChecked(true);
    }

    public void onClickSwitchButton() {
        storageSw.setOnCheckedChangeListener(switchButtonListenerCallback(storageSw, READ_EXTERNAL_STORAGE));
        locationSw.setOnCheckedChangeListener(switchButtonListenerCallback(locationSw, ACCESS_FINE_LOCATION));
        cameraSw.setOnCheckedChangeListener(switchButtonListenerCallback(cameraSw, CAMERA));
        phoneSw.setOnCheckedChangeListener(switchButtonListenerCallback(phoneSw, CALL_PHONE));
        contactsSw.setOnCheckedChangeListener(switchButtonListenerCallback(contactsSw, READ_CONTACTS));
    }

    public CompoundButton.OnCheckedChangeListener switchButtonListenerCallback(final Switch switchBtn, final String permission) {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isPermissionGranted(permission)) {
                    switchBtn.setChecked(true);
                    showSnackbar("Permission Granted");
                }
            }
        };
    }

    public void showSnackbar(String text) {
        Snackbar snackbar = Snackbar.make(settingsLayout, text, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    public Boolean isPermissionGranted(String permission) {
        return ContextCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_GRANTED;
    }


}