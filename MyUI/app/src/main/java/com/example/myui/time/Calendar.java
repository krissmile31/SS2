package com.example.myui.time;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myui.R;

public class Calendar extends AppCompatActivity {
    private EditText topic;
    private EditText location;
    private EditText description;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        topic = findViewById(R.id.topic);
        location = findViewById(R.id.location);
        description = findViewById(R.id.description);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!topic.getText().toString().isEmpty() && !location.getText().toString().isEmpty() && !description.getText().toString().isEmpty()){
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, topic.getText().toString());
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, location.getText().toString());
                    intent.putExtra(CalendarContract.Events.DESCRIPTION, description.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, true);
                    intent.putExtra(Intent.EXTRA_EMAIL, "krissmile31@gmail.com");
                    startActivity(intent);

                    Toast.makeText(Calendar.this, "Error", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(Calendar.this, "None", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}