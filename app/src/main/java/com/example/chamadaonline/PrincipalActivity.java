package com.example.chamadaonline;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chamadaonline.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class PrincipalActivity extends AppCompatActivity {

    private EditText etPIN;
    private Button btnEnviarPIN;
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private String codigoPin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        etPIN = findViewById(R.id.etPIN);
        btnEnviarPIN = findViewById(R.id.btnEnviarPIN);

        btnEnviarPIN.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String pinUsuario = etPIN.getText().toString();
                String pinServidor = "";

                if (!pinUsuario.isEmpty()){

                    pinServidor = GetPinServidor();

                    if (pinUsuario == pinServidor){

                        //usuario = new Usuarios();
                        //usuario.setEmail(email);
                        //usuario.setSenha(senha);
                        //validarLogin();

                    }else
                        Toast.makeText(PrincipalActivity.this, "Código PIN Inválido!", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(PrincipalActivity.this, "Digite o Código PIN!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private String GetPinServidor()
    {
        String retorno = "";


        return retorno;
    }

}

