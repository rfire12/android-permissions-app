package com.example.android_permissions_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import com.google.android.material.snackbar.Snackbar;

public class ActionActivity extends AppCompatActivity {

    Button storageBtn;
    Button locationBtn;
    Button cameraBtn;
    Button phoneBtn;
    Button contactsBtn;
    ConstraintLayout actionLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        actionLayout = findViewById(R.id.actionLayout);

        // Header back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initialize();
        buttonsEvents();

    }

    public void initialize(){
        storageBtn = findViewById(R.id.storageBtn);
        locationBtn = findViewById(R.id.locationBtn);
        cameraBtn = findViewById(R.id.cameraBtn);
        phoneBtn = findViewById(R.id.phoneBtn);
        contactsBtn = findViewById(R.id.contactsBtn);
    }

    public void buttonsEvents() {
        storageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPermissionGranted(MainActivity.READ_EXTERNAL_STORAGE))
                    showSnackbar("Permission already granted.", MainActivity.READ_EXTERNAL_STORAGE );
                else
                    showSnackbar("Please request permission.", "");
            }
        });
        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPermissionGranted(MainActivity.ACCESS_FINE_LOCATION))
                    showSnackbar("Permission already granted.", MainActivity.ACCESS_FINE_LOCATION );
                else
                    showSnackbar("Please request permission.", "");
            }
        });
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPermissionGranted(MainActivity.CAMERA))
                    showSnackbar("Permission already granted.", MainActivity.CAMERA );
                else
                    showSnackbar("Please request permission.", "");
            }
        });
        phoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPermissionGranted(MainActivity.CALL_PHONE))
                    showSnackbar("Permission already granted.", MainActivity.CALL_PHONE );
                else
                    showSnackbar("Please request permission.", "");
            }
        });
        contactsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPermissionGranted(MainActivity.READ_CONTACTS))
                    showSnackbar("Permission already granted.", MainActivity.READ_CONTACTS );
                else
                    showSnackbar("Please request permission.", "");
            }
        });
    }

    public Boolean isPermissionGranted(String permission) {
        return ContextCompat.checkSelfPermission(ActionActivity.this, permission) == PackageManager.PERMISSION_GRANTED;
    }


    public void showSnackbar(String text, String permission) {
        Snackbar snackbar = Snackbar.make(actionLayout, text, Snackbar.LENGTH_SHORT);

        switch(permission) {
            case MainActivity.READ_EXTERNAL_STORAGE:
                snackbar.setAction("OPEN", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        intent.setType("image/*");
                        startActivity(intent);
                    }
                });
                break;
            case MainActivity.ACCESS_FINE_LOCATION:
                snackbar.setAction("OPEN", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                Uri.parse("google.navigation:q=google"));
                        startActivity(intent);
                    }
                });
                break;
            case MainActivity.CAMERA:
                snackbar.setAction("OPEN", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivity(intent);
                    }
                });
                break;
            case MainActivity.CALL_PHONE:
                snackbar.setAction("OPEN", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:18005554567"));
                        startActivity(intent);
                    }
                });
                break;
            case MainActivity.READ_CONTACTS:
                snackbar.setAction("OPEN", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);
                        startActivity(intent);
                    }
                });
                break;
        }

        snackbar.show();
    }
}