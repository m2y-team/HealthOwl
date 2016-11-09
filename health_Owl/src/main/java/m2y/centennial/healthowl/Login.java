package m2y.centennial.healthowl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
/*M2Y*/
public class Login extends AppCompatActivity {

    private EditText mLogin;
    private EditText mPassword;
    private Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLogin = (EditText) findViewById(R.id.login_editText);
        mPassword = (EditText) findViewById(R.id.password_editText);
        mLoginButton = (Button) findViewById(R.id.signInButton);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = mLogin.getText().toString();
                String pwd = mPassword.getText().toString();
                startLogin(login, pwd);
            }
        });
    }

    private void startLogin(String login, String pwd){
        Intent intent = new Intent(this, menu.class);
        intent.putExtra("login", login);
        intent.putExtra("pwd", pwd);
        startActivity(intent);
    }


    }

