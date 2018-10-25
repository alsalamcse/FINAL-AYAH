package aslan.ayahtaskmanager;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private EditText first;
    private EditText last;
    private EditText phone;
    private EditText email2;
    private EditText password2;
    private EditText password3;
    private Button save;

    //1 - add auth to project
    //2
    FirebaseAuth auth;
    // alka2en bsa3dne a3ml sign up w sign in
    FirebaseUser user;
    // alka2en elle ho bbne (user firebase) f2a jahzi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        first = (EditText) findViewById(R.id.first);
        last = (EditText) findViewById(R.id.last);
        phone = (EditText) findViewById(R.id.phone);
        email2 = (EditText) findViewById(R.id.email2);
        password2 = (EditText) findViewById(R.id.password2);
        password3 = (EditText) findViewById(R.id.password3);

        save = (Button) findViewById(R.id.save);
        //3
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        // btrj3 al user eza 3amil sign in(aluser al7ali)  aw brj3 null

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataHandler();
            }
        });

    }

    /**
     *  get email and password from the field and try to create new user
     */

    private void dataHandler()
    {
        boolean isOk = true; // if all the fields filled well
     String email22 = email2.getText().toString();
     String password22 = password2.getText().toString();
     String password33 = password3.getText().toString();

     String first2 = first.getText().toString();
     String last2 = last.getText().toString();
     String phone2 = phone.getText().toString();

     if (email22.length()<4 || email22.indexOf('@')<0 || email22.indexOf('.')<0) {
         email2.setError("Wrong Email");
         isOk = false; }

     if (password22.length() < 8) {
     password2.setError("Have to be at least 8 char");
     isOk = false; }

     if (first2.length() < 3){
         first.setError("Set your first name");
         isOk=false;}

    if (last2.length() < 3){
            last.setError("Set your last name");
            isOk=false;}

    if (password22.length() != password33.length()) {
                password3.setError("Passwords haven't matched");
            }


     if (isOk)
         creatAcount(email22,password22);
    }

    /**
     *  create firebase user using email and pasword
     * @param email user email
     * @param passw user password
     */

    //4 - bna2 dala - create acount


    private void creatAcount(String email, String passw) {

        auth.createUserWithEmailAndPassword(email, passw).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Authentication Successful", Toast.LENGTH_SHORT).show();
                    //updateUserProfile(task.getResult().getUser());
                    finish();
                }
                else {
                    Toast.makeText(SignUpActivity.this, "Authentication failed." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }


}
