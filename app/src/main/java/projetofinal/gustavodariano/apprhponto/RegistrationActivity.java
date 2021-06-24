package projetofinal.gustavodariano.apprhponto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {
    private EditText etemail;
    private EditText etpassword;
    private Button regButton;
    private TextView userLogin;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etemail = findViewById(R.id.etUserEmail);
        etpassword = findViewById(R.id.etUserPassword);
        regButton = findViewById(R.id.btnRegister);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent );
            }
        });
    }

    private void register() {
        String email = etemail.getText().toString();
        String password = etpassword.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()) {
                    auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            user = auth.getCurrentUser();
                        }
                    });
        }
    }

}



