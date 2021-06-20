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
import android.widget.ListView;
import android.widget.Switch;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private ListView lvLista;
    private List<UserProfile> userList;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private ChildEventListener childEventListener;
    private Query query;
    private ArrayAdapter<UserProfile> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        FloatingActionButton btnback = findViewById(R.id.backtoForm);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, SecondActivity.class);
                startActivity( intent );
            }
        });

        lvLista = findViewById( R.id.lvLista );
        userList = new ArrayList<>();
        adapter = new ArrayAdapter<UserProfile>(
                ListActivity.this, android.R.layout.simple_list_item_1, userList);
        lvLista.setAdapter( adapter );

        lvLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                remove( position );
                return true;
            }
        });

    }

    private void remove(final int position) {
        final UserProfile selectUser = userList.get( position );

        AlertDialog.Builder alerta = new AlertDialog.Builder(ListActivity.this);
        alerta.setTitle("Excluir Ponto");
        alerta.setIcon( android.R.drawable.ic_delete );
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reference.child("userprofile").child( selectUser.userId ).removeValue();

                userList.remove( position );
                adapter.notifyDataSetChanged();
            }
        });
        alerta.show();

    }


    @Override
    protected void onStart() {
        super.onStart();

        //userList.clear();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        query = reference.child("userprofile").orderByChild("name");

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                UserProfile up = new UserProfile();
                up.userName = dataSnapshot.child("userName").getValue(String.class);
                up.userEmail = dataSnapshot.child("userEmail").getValue(String.class);


                userList.add( up );

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };



        query.addChildEventListener( childEventListener );
    }

    @Override
    protected void onStop() {
        super.onStop();

        query.removeEventListener( childEventListener );
    }

}


