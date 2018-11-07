package aslan.ayahtaskmanager;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button signup;
    private Button signin;

    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        signin = (Button) findViewById(R.id.signin);
        signup = (Button) findViewById(R.id.signup);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i2);
            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dataHandler();
        }
    });
}
    private void dataHandler() {
        boolean isOk = true; // if all the fields filled well
        String email1 = email.getText().toString();
        String password1 = password.getText().toString();

        if (email1.length() < 4 || email1.indexOf('@') < 0 || email1.indexOf('.') < 0) {
            email.setError("Wrong Email");
            isOk = false;
        }

        if (password1.length() < 8) {
            password.setError("wrong password");
            isOk = false;
        }

        if (isOk)
            signIn(email1, password1);
    }

    private void signIn(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "signIn Successful.", Toast.LENGTH_SHORT).show();
                 Intent intent=new Intent(LoginActivity.this,MainTabsActivity.class);
                   startActivity(intent);
//                  finish();
                } else {
                    Toast.makeText(LoginActivity.this, "signIn failed." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });

    }
}





