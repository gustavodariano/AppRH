package projetofinal.gustavodariano.apprhponto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private EditText etNome;
    private EditText etEtrada;
    private EditText etSaida;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private FirebaseAuth auth;
    private FirebaseUser usuario;
    private FirebaseAuth.AuthStateListener authStateListener;

    private Button btnSalvar;
    private Button btnShowList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        etNome = findViewById(R.id.etNome);
        etEtrada = findViewById(R.id.etEntrada);
        btnSalvar = findViewById(R.id.btnSarvar);
        btnShowList = findViewById(R.id.btnShowList);




        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
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

    private void salvar() {


        String name = etNome.getText().toString();
        String age = etEtrada.getText().toString();

        if( !name.isEmpty()  && !age.isEmpty() ) {
            UserProfile up = new UserProfile();
            up.userName = name;
            up.userAge = age;

            database = FirebaseDatabase.getInstance();
            reference = database.getReference();
            reference.child("UsuerProfile").push().setValue(up);
            finish();
        }

    }





}