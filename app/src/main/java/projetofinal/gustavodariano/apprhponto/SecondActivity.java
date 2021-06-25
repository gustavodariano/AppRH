package projetofinal.gustavodariano.apprhponto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SecondActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etEmail;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private Button btnSave;
    private Button btnShowList;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        //etAge = findViewById(R.id.etAge);
        //etWage = findViewById(R.id.etWage);

        btnSave = findViewById(R.id.btnSave);
        btnShowList = findViewById(R.id.btnShowList);
        btnLogout = findViewById(R.id.btnLogout);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveinfo();
            }

        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void saveinfo() {

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        //String age = etAge.getText().toString();
        //String wage = etWage.getText().toString();

        if (!name.isEmpty() && !email.isEmpty()) {
            Employee employee = new Employee();
            employee.Name = name;
            employee.Email = email;

            database = FirebaseDatabase.getInstance();
            reference = database.getReference();
            reference.child("employeeinfo").push().setValue(employee);
        }else{
            Toast.makeText(SecondActivity.this, "Error", Toast.LENGTH_LONG).show();
        }

    }


}