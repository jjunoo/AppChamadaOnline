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
                if (!isStringEmpty(etMatricula) && !isStringEmpty(etSenha) && !isStringEmpty(etSenhaConfirma)){

                    // Alerta de passwords diferentes
                    if ( etSenha.equals(etSenhaConfirma) ){//preciso utilizar o metodo equals()

                        //SharedPreferences sharedPrefs = getSharedPreferences("userdata", MODE_PRIVATE);
                        //SharedPreferences.Editor editor = sharedPrefs.edit();

                        //editor.putString("nome",etNome);
                        //editor.putString("email",etEmail);
                        //editor.putString("matricula",etMatricula);
                        //editor.putString("password",etSenha);

                        //editor.apply();


                        usuario = new Usuarios();

                        usuario.setNome(etNome);
                        usuario.setEmail(etEmail);
                        usuario.setMatricula(matricula);
                        usuario.setSenha(etSenha);

                        cadastrarUsuario();

                        Intent intent = new Intent(Cadastro.this, Login.class);
                        startActivity(intent);

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
                }else {
                    Toast.makeText(Cadastro.this, "Erro ao efetuar o seu cadastro!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
