package com.example.datastudent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.datastudent.DB.AppDatabase;
import com.example.datastudent.model.User;
import com.example.datastudent.util.AppExecutors;
import com.example.datastudent.R;

public class InsertDataActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);
        Button addButton = findViewById(R.id.update_button);
        final EditText nameEditText = findViewById(R.id.name_edit_text);
        final EditText lastNameEditText = findViewById(R.id.last_name_edit_text);
        final EditText addressEditText = findViewById(R.id.address_edit_text);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nameEditText.getText().toString();
                String lastname = lastNameEditText.getText().toString();
                String address = addressEditText.getText().toString();

                if(name.length() == 0 || lastname.length() == 0 || address.length() == 0){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(InsertDataActivity.this);
                    dialog.setMessage("กรอกข้อมูลให้ครบถ้วน");
                    dialog.setPositiveButton("Ok" , null);
                    dialog.show();
                }else{
                    String status = "ยังไม่จบการศึกษา";

                    final User user = new User(0, name , lastname , address ,status);

                    AppExecutors executors = new AppExecutors();
                    executors.diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            AppDatabase db = AppDatabase.getInstance(InsertDataActivity.this);
                            db.userDao().insertData(user);
                            finish();
                        }
                    });
                }
            }
        });
    }
}