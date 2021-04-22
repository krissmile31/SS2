package com.example.myui.favourite.friends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myui.R;
import com.example.myui.favourite.friends.models.Friend;

public class EditFriendActivity extends AppCompatActivity {
    public static final String EXTRA_NAME = "com.a1_1801040015.myfriends.EXTRA_NAME";
    public static final String EXTRA_NICKAME = "com.a1_1801040015.myfriends.EXTRA_NICKAME";
    public static final String EXTRA_INSTA = "com.a1_1801040015.myfriends.EXTRA_INSTA";
    public static final String EXTRA_FB = "com.a1_1801040015.myfriends.EXTRA_FB";
    public static final String EXTRA_EMAIL = "com.a1_1801040015.myfriends.EXTRA_EMAIL";
    public static final String EXTRA_PHONENO = "com.a1_1801040015.myfriends.EXTRA_PHONENO";

    private EditText editName, editNickname, editInsta, editFb, editEmail, editPhoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_friend);

        editName = findViewById(R.id.editName);
        editNickname = findViewById(R.id.editNickname);
        editInsta = findViewById(R.id.editInsta);
        editFb = findViewById(R.id.editFb);
        editEmail = findViewById(R.id.editEmail);
        editPhoneNo = findViewById(R.id.editPhoneNo);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_NAME)){
            editName.setText(intent.getStringExtra(EXTRA_NAME));
            editNickname.setText(intent.getStringExtra(EXTRA_NICKAME));
            editInsta.setText(intent.getStringExtra(EXTRA_INSTA));
            editFb.setText(intent.getStringExtra(EXTRA_FB));
            editEmail.setText(intent.getStringExtra(EXTRA_EMAIL));
            editPhoneNo.setText(intent.getStringExtra(EXTRA_PHONENO));
        }

        findViewById(R.id.notok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Friend friend = new Friend(editName.getText().toString(), editNickname.getText().toString(), editInsta.getText().toString(), editFb.getText().toString(), editEmail.getText().toString(), editPhoneNo.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("EDIT_FRIEND", friend);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}