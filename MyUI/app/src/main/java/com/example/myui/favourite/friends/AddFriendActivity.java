package com.example.myui.favourite.friends;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myui.R;
import com.example.myui.models.Friend;

public class AddFriendActivity extends AppCompatActivity {
    private EditText newName, newNickname, newInsta, newFb, newEmail, newPhoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        newName = findViewById(R.id.newName);
        newNickname = findViewById(R.id.newNickname);
        newInsta = findViewById(R.id.newInsta);
        newFb = findViewById(R.id.newFb);
        newEmail = findViewById(R.id.newEmail);
        newPhoneNo = findViewById(R.id.newPhoneNo);

        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(AddFriendActivity.this)
                        .setIcon(R.drawable.info)
                        .setTitle("Will you cancel to add new friend?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Friend friend = new Friend(newName.getText().toString(), newNickname.getText().toString(), newInsta.getText().toString(), newFb.getText().toString(), newEmail.getText().toString(), newPhoneNo.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("ADD_FRIEND", friend);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}