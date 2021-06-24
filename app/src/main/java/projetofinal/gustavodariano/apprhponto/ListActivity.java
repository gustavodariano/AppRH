package projetofinal.gustavodariano.apprhponto;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
    private ListView lvList;
    private List<UserInfo> infoUser;
    private Button btnBackForm;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private ChildEventListener childEventListener;
    private Query query;
    private ArrayAdapter<UserInfo> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton btn = findViewById(R.id.btnBacktoForm);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, SecondActivity.class);
                startActivity( intent );
            }
        });

        lvList = findViewById( R.id.lvList );
        infoUser = new ArrayList<>();
        adapter = new ArrayAdapter<UserInfo>(
                ListActivity.this, android.R.layout.simple_list_item_1, infoUser);
        lvList.setAdapter( adapter );

        lvList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                remove( position );
                return true;
            }
        });

    }

    private void remove(final int posicao){

        final UserInfo userInfo = infoUser.get( posicao );

        AlertDialog.Builder alerta = new AlertDialog.Builder(ListActivity.this);
        alerta.setTitle("Remove employee");
        alerta.setIcon( android.R.drawable.ic_delete );
        alerta.setMessage("Confirm delete employee" + userInfo.Name + "?");
        alerta.setNeutralButton("Cancel", null);
        alerta.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reference.child("employeeinfo").child( userInfo.ID ).removeValue();

                infoUser.remove( posicao );
                adapter.notifyDataSetChanged();
            }
        });
        alerta.show();

    }



    @Override
    protected void onStart() {
        super.onStart();

        infoUser.clear();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        query = reference.child("employeeinfo").orderByChild("Name");

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                UserInfo ui = new UserInfo();
                ui.ID = dataSnapshot.getKey();
                ui.Name = dataSnapshot.child("Name").getValue(String.class);
                ui.Email = dataSnapshot.child("Email").getValue(String.class);
                ui.Age = dataSnapshot.child("Age").getValue(String.class);
                ui.Wage = dataSnapshot.child("Wage").getValue(Double.class);

                infoUser.add( ui );

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


