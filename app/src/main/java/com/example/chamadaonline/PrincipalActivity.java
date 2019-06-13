package com.example.chamadaonline;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.chamadaonline.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class PrincipalActivity extends AppCompatActivity {


    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);



    }

    // Precisa finalizar os métodos abaixo para mostrar a turma e o usuário na tela
    public void recuperarMatricula{

        String idUsuario = autenticacao.getCurrentUser().getEmail();
        DatabaseReference usuarioRef = firebaseRef.child("Turma").;
    }


    public void recuperarTurma {
        DatabaseReference turmaRef = firebaseRef.child("Turma");
    }

}

