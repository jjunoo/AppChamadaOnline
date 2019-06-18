package com.example.chamadaonline;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;

import com.example.chamadaonline.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PrincipalActivity extends AppCompatActivity {

    private EditText etPIN;
    private Button btnEnviarPIN;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        etPIN = findViewById(R.id.etPIN);
        btnEnviarPIN = findViewById(R.id.btnEnviarPIN);

        btnEnviarPIN.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference pinRef = ConfiguracaoFirebase.getFirebaseDatabase().child("id_chamada").child("rand");

                String rand = etPIN.getText().toString();

<<<<<<< HEAD
                    if (pinUsuario.equals(pinServidor)){
=======
                if (!rand.isEmpty()){
>>>>>>> 596dfe28af6db10ac66ec0a130a130a74768dbb6


                        if(!usuarioPresençaDia()) {
                        long x=new Date().getTime(); // como pegar data
                        int y=Integer.parseInt(etPIN.getText().toString()); // campo para mandar data via pin porém ciar uma campo de data 
                            //aqui tem que fazer todo o update do usuario para que seja realizada a chamada


                            etPIN.setText("");
                            Toast.makeText(PrincipalActivity.this, "Presença do dia confirmada!", Toast.LENGTH_SHORT).show();
                        }else
                            Toast.makeText(PrincipalActivity.this, "Usuário com presença no dia já!", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(PrincipalActivity.this, "Código PIN Inválido!", Toast.LENGTH_SHORT).show();

            }
        });
    }
   /* private String GetPinServidor(String rand)
    {
        String retorno = "";

        //Toast.makeText(PrincipalActivity.this, "Erro ao buscar o Código PIN" + e.getMessage() , Toast.LENGTH_SHORT).show();
        return retorno;
    }*/


    private boolean usuarioPresençaDia(){
        boolean retorno = false;

        //logica para verificar no banco se existir voltar true
        //se não voltar false

        return retorno;
    }}

