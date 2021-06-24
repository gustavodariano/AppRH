package projetofinal.gustavodariano.apprhponto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SecondActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etEmail;
    private EditText etAge;
    private EditText etWage;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private Button btnSave;
    private Button btnShowList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        etName = findViewById(R.id.etUserName);
        etEmail = findViewById(R.id.etEmail);
        btnSave = findViewById(R.id.btnSave);
        btnShowList = findViewById(R.id.btnShowList);
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.isEmpty()  && !email.isEmpty()){
                saveinfo();
                Intent intent = new Intent(SecondActivity.this, ListActivity.class);
                startActivity(intent );
                }
                Toast.makeText( SecondActivity.this, "Error Saving", Toast.LENGTH_LONG).show();
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, ListActivity.class);
                startActivity( intent );
            }
        });


    }

    private void saveinfo() {

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String age = etAge.getText().toString();
        String wage = etWage.getText().toString();

        if( !name.isEmpty() && !email.isEmpty()  && !age.isEmpty() ) {
            UserInfo userInfo = new UserInfo();

            wage = wage.replace(",", ".");
            double wageDouble = Double.valueOf( wage );

            userInfo.Name = name;
            userInfo.Email = email;
            userInfo.Age = age;
            userInfo.Wage = wageDouble;

            database = FirebaseDatabase.getInstance();
            reference = database.getReference();
            reference.child("employeeinfo").push().setValue(userInfo);
        }

    }





}