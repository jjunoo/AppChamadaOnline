package com.example.chamadaonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.util.Calendar;


import com.example.chamadaonline.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PrincipalActivity extends AppCompatActivity {

    private EditText etPIN;
    private Button btnEnviarPIN;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private String pinDB, pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        etPIN = findViewById(R.id.etPIN);
        btnEnviarPIN = findViewById(R.id.btnEnviarPIN);

        btnEnviarPIN.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //final FirebaseDatabase database = FirebaseDatabase.getInstance();
                //DatabaseReference pinRef = ConfiguracaoFirebase.getFirebaseDatabase().child("id_chamada").child("rand");




                String pinAluno = etPIN.getText().toString();

                if (!pinAluno.isEmpty()){

                    if (pin.equals(pinAluno)) {

                        //if (!usuarioPresencaDia()) {




                            //aqui tem que fazer todo o update do usuario para que seja realizada a chamada
                            salvarPresenca();



                            Toast.makeText(PrincipalActivity.this, "Presença do dia confirmada!", Toast.LENGTH_SHORT).show();
                       // } else
                           // Toast.makeText(PrincipalActivity.this, "Usuário com presença já registrada", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(PrincipalActivity.this, "O pin digitado está incorreto!", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(PrincipalActivity.this, "Você precisa digitar um pin!" , Toast.LENGTH_SHORT).show();

            }
        });
    }


    /*private boolean usuarioPresencaDia(){
        boolean retorno = false;

        //logica para verificar no banco se existir voltar true
        //se não voltar false

        return retorno;
    }*/

    public void recuperarPin(){



        final DatabaseReference pinRef = reference.child("id_chamada").child("rand");
        pinRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot pin: dataSnapshot.getChildren();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void salvarPresenca(){

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String strDate = dateFormat.format(date);
        //String strdate = strDate.toString();

        String idUsuario = ConfiguracaoFirebase.getIdUsuario();
        DatabaseReference database = ConfiguracaoFirebase.getFirebaseDatabase()
                .child("Chamada").child(strDate).child(idUsuario).child("Presenca");

        database.child("Chamada")
                .child(strDate)
                .child(idUsuario)
                .child("Presente")
                .setValue(this);
        return;
    }}