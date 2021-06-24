package projetofinal.gustavodariano.apprhponto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    private EditText etuserName, etuserPassword, etuserEmail, etuserAge;
    private Button regButton;
    private TextView userLogin;

    //private ImageView userProfilePic;
    //private static final int PICK_IMAGE = 123;
    //Uri imagePath;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etuserName = (EditText) findViewById(R.id.etUserName);
        etuserEmail = (EditText) findViewById(R.id.etUserEmail);
        etuserPassword = (EditText) findViewById(R.id.etUserPassword);
        etuserAge = (EditText) findViewById(R.id.etAge);
        regButton = (Button) findViewById(R.id.btnRegister);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
                finish();
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void register() {
        String name = etuserName.getText().toString();
        String email = etuserEmail.getText().toString();
        String password = etuserPassword.getText().toString();
        String age = etuserAge.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()) {

            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                user = auth.getCurrentUser();
                                finish();
                                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                            } else {

                            }
                        }
                    });

        }


    }

}

