package projetofinal.gustavodariano.apprhponto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnUserRegister;

    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnUserRegister = (Button) findViewById(R.id.btnUserRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        auth = FirebaseAuth.getInstance();



        btnUserRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity( intent );
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logar();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity( intent );
            }
        });

    }

    private void logar(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if( !email.isEmpty() && !password.isEmpty() ){
            auth.signInWithEmailAndPassword(email, password).
                    addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if( ! task.isSuccessful() ){
                                Toast.makeText( MainActivity.this, "Login ok", Toast.LENGTH_LONG).show();
                                etPassword.setBackgroundColor(Color.argb(127, 255, 0, 0 ));
                            }
                        }
                    });
        }
    }

}





