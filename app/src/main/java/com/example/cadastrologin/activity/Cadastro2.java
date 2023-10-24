package com.example.cadastrologin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cadastrologin.R;
import com.example.cadastrologin.dao.UserDAO;
import com.example.cadastrologin.model.User;

public class Cadastro2 extends AppCompatActivity {
    Button btnSign;
    EditText txtmail, txtuser, txtpass, txtpassCon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro2);

        btnSign = findViewById(R.id.btnSign);
        txtuser = findViewById(R.id.edtUsername);
        txtmail = findViewById(R.id.edtEmail);
        txtpass = findViewById(R.id.edtPassword);
        txtpassCon = findViewById(R.id.edtPasswordConfirm);

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user, mail, pass, passCon;
                user = txtuser.getText().toString();
                mail = txtmail.getText().toString();
                pass = txtpass.getText().toString();
                passCon = txtpassCon.getText().toString();


                if (user.equals("") || mail.equals("") || pass.equals("") || passCon.equals("")){
                    //Testo campos vazios
                    Toast.makeText(Cadastro2.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                } else if(!pass.equals(passCon)){
                    //Testo senhas diferentes
                    Toast.makeText(Cadastro2.this, "As senhas estão diferentes!", Toast.LENGTH_SHORT).show();
                } else{
                    //Salvo os dados
                    UserDAO uDAO = new UserDAO(getApplicationContext(), new User(user, mail, pass));
                    if(uDAO.signUpVality()==true){
                        //Verifico se já possui cadastro com o email
                        Toast.makeText(Cadastro2.this, "Email já usado em outra conta!", Toast.LENGTH_SHORT).show();
                    } else{
                        //Se não houver eu salvo
                        uDAO.signUp();
                        Toast.makeText(Cadastro2.this, "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                    }

                    //Limpo os campos
                    txtuser.setText("");
                    txtmail.setText("");
                    txtpass.setText("");
                    txtpassCon.setText("");
                }
            }
        });
    }
}
