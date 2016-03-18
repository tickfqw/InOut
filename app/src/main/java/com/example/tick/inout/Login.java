package com.example.tick.inout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by tick on 2016/3/17.
 */
public class Login extends Activity {

    private EditText txtlogin;
    private Button btnlogin;
    private Button btnclose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        txtlogin = (EditText) findViewById(R.id.txtLogin);
        btnlogin = (Button) findViewById(R.id.btnLogin);
        btnclose=(Button) findViewById(R.id.btnClose);

        btnclose.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                finish();
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                PwdDAO pwdDAO = new PwdDAO(Login.this);
                if ((pwdDAO.getCount() == 0 | pwdDAO.find().getPassword().isEmpty()) && txtlogin.getText().toString().isEmpty()) {
                    startActivity(intent);
                } else {
                    if (pwdDAO.find().getPassword().equals(txtlogin.getText().toString())) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "请输入正确的密码！", Toast.LENGTH_SHORT).show();
                    }
                }
                txtlogin.setText("");
            }
        });
    }

}
