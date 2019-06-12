package com.example.chamadaonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chamadaonline.config.ConfiguracaoFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {


    private EditText etEmail, etSenha;
    private Button btnEntrar;
    private Usuarios usuario;
    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmailLogin);
        etSenha = findViewById(R.id.etSenhaLogin);
        btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();

                if (!senha.isEmpty()){
                    if (!email.isEmpty()){

                        usuario = new Usuarios();
                        usuario.setEmail(email);
                        usuario.setSenha(senha);



                    }
                    Toast.makeText(Login.this, "Digite o seu email!", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(Login.this, "Digite a sua senha!", Toast.LENGTH_SHORT).show();

            }
        });

    }
    public void validarLogin() {

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Login.this, "Sucesso ao fazer o login", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(Login.this, "Erro ao fazer login. Verifique seu email e senha", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
