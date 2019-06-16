package com.example.chamadaonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
//import android.content.SharedPreferences;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class Cadastro extends AppCompatActivity {

    @BindView(R.id.etNome)
    EditText nome;

    @BindView(R.id.etEmail)
    EditText email;

    @BindView(R.id.etMatricula)
    EditText matricula;

    @BindView(R.id.etSenha)
    EditText senha;

    @BindView(R.id.etSenhaConfirma)
    EditText senhaConfirma;

    @BindView(R.id.btnSalvar)
    Button salvar;

    private FirebaseAuth autenticacao;
    private Usuarios usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        ButterKnife.bind(this);

        registerUser();
    }


    //Checa se os campos foram preenchidos
    private boolean isStringEmpty (String inputString){

        if (inputString.isEmpty() || inputString == null  ){
            return true;

        } else {

            return false;
        }
    }

    // Alerta de campo em branco
    private void registerUser (){

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Cadastro de Usuário

                String etNome = nome.getText().toString();
                String etEmail = email.getText().toString();
                String etMatricula = matricula.getText().toString();
                String etSenha = senha.getText().toString();
                String etSenhaConfirma = senhaConfirma.getText().toString();

                //converter String Matricula para Integer
                int matricula = Integer.parseInt(etMatricula);


                //Valida Campo
                if (!isStringEmpty(etNome) && !isStringEmpty(etEmail) && !isStringEmpty(etMatricula) && !isStringEmpty(etSenha) && !isStringEmpty(etSenhaConfirma)){

                    // Alerta de passwords diferentes
                    if ( etSenha.equals(etSenhaConfirma) ){


                        usuario = new Usuarios();

                        usuario.setNome(etNome);
                        usuario.setEmail(etEmail);
                        usuario.setMatricula(matricula);
                        usuario.setSenha(etSenha);

                        cadastrarUsuario();



                    } else {

                        Toast.makeText(Cadastro.this,"As senhas precisam ser iguais", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //Alerta de campo em branco
                    Toast.makeText(Cadastro.this, "Você não pode deixar nenhum campo em branco!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void cadastrarUsuario(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(),usuario.getSenha()).addOnCompleteListener(
                this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Cadastro.this, "Você foi cadastrado com sucesso!",Toast.LENGTH_SHORT).show();

                    abrirTelaLogin();
                    finish();

                }else {

                    String excecao = "";
                    try{
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e){
                        excecao = "Digite uma senha mais forte!";
                    } catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "Por favor, digite um e-mail válido!";
                    } catch (FirebaseAuthUserCollisionException e){
                        excecao = "Este E-mail já foi cadastrado";
                    } catch (Exception e){
                        excecao = "Erro ao cadastrar o usuário " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(Cadastro.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void abrirTelaLogin(){
        startActivity(new Intent(this, Login.class));
        finish();
    }

//teste 1 

}
