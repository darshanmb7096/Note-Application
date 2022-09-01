package com.example.saveit;

import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class Activity_Add_Note extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        EditText titleInput = findViewById(R.id.titleinput);
        EditText descriptionInput = findViewById(R.id.descriptioninput);
        MaterialButton saveBtn = findViewById(R.id.savebtn);


        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleInput.getText().toString();
                String description = descriptionInput.getText().toString();
                long createdTime = System.currentTimeMillis();

                if (title.isEmpty() || description.isEmpty())
                {
                    Toast.makeText(Activity_Add_Note.this, "Please Enter Title and Description!!",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    realm.beginTransaction();
                    Note note = realm.createObject(Note.class);
                    note.setTitle(title);
                    note.setDescription(description);
                    note.setCreatedTime(createdTime);
                    realm.commitTransaction();
                    Toast.makeText(getApplicationContext(), "Note saved", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });


    }
}