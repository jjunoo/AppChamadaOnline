package com.example.chamadaonline;

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
                DatabaseReference data = ConfiguracaoFirebase.getFirebaseDatabase().child("Chamada").child("Data");
                String rand = etPIN.getText().toString();

                if (!rand.isEmpty()){



                    if(!usuarioPresencaDia()) {
                        //long x=new Date().getTime(); // como pegar data
                        Date date = Calendar.getInstance().getTime();
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                        String strDate = dateFormat.format(date);
                        String strdate =date.toString();

                        //System.out.println("Converted String: " + strDate);
                        //int y=Integer.parseInt(data.getText().toString()); // campo para mandar data via pin porém ciar uma campo de data
                        //aqui tem que fazer todo o update do usuario para que seja realizada a chamada
                        salvarPresenca();



                    // DATA e PIN estão sendo pegos obteção de dados OK testado
                        etPIN.setText("");
                       Toast.makeText(PrincipalActivity.this, "Presença do dia confirmada!", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(PrincipalActivity.this, "Usuário com presença no dia já!", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(PrincipalActivity.this, "Código PIN Inválido!", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private String GetPinServidor(String rand)
    {
        String retorno = "";

        //Toast.makeText(PrincipalActivity.this, "Erro ao buscar o Código PIN" + e.getMessage() , Toast.LENGTH_SHORT).show();
        return retorno;
    }


    private boolean usuarioPresencaDia(){
        boolean retorno = false;

        //logica para verificar no banco se existir voltar true
        //se não voltar false

        return retorno;
    }

    public void salvarPresenca(){

        String idUsuario = ConfiguracaoFirebase.getIdUsuario();
        DatabaseReference alunoRef = ConfiguracaoFirebase.getFirebaseDatabase()
                .child("Chamada").child("Data").child("Presenca");

        alunoRef.child(idUsuario)
                .child("Chamada")
                .child("Data")
                .child("Presenca")
                .setValue(this);
        return;
    }}