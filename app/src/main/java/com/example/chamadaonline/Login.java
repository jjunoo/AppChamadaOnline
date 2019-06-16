package com.example.chamadaonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;



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
                btnEntrar.setVisibility(View.INVISIBLE);
                if (!senha.isEmpty()){
                    if (!email.isEmpty()){

                        usuario = new Usuarios();
                        usuario.setEmail(email);
                        usuario.setSenha(senha);
                        validarLogin();

                    }else
                    Toast.makeText(Login.this, "Digite o seu email!", Toast.LENGTH_SHORT).show();
                }else
                Toast.makeText(Login.this, "Digite a sua senha!", Toast.LENGTH_SHORT).show();

            }
        });
        btnEntrar.setVisibility(View.VISIBLE);
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

                   abrirTelaPrincipal();
                   finish();

                }else {
                    String excecao = "";
                    try {
                        throw task.getException();
                    }catch (FirebaseAuthInvalidUserException e){
                        excecao = "Usuário não cadastrado";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "Email e senha não correspondem a um usuário cadastrado";
                    }catch (Exception e){
                        excecao = "Erro ao logar usuário" + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(Login.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void abrirTelaPrincipal(){
        startActivity(new Intent(this, PrincipalActivity.class));
        finish();
    }
}
