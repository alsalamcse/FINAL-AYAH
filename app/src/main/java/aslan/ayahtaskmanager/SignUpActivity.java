package aslan.ayahtaskmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private EditText first;
    private EditText last;
    private EditText phone;
    private EditText email2;
    private EditText password2;
    private Button save;

    FirebaseUser user;
    // alka2en elle ho bbne (user firebase) f2a jahzi
    FirebaseAuth auth;
    // alka2en bsa3dne a3ml sign up w sign in

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        first= (EditText)findViewById(R.id.first);
        last = (EditText)findViewById(R.id.last);
        phone = (EditText)findViewById(R.id.phone);
        email2 = (EditText) findViewById(R.id.email2);
        password2 = (EditText)findViewById(R.id.password2);
        save = (Button)findViewById(R.id.save);

        auth=FirebaseAuth.getInstance();
        user= auth.getCurrentUser();
        // btrj3 al user eza 3amil sign in(aluser al7ali)  aw brj3 null

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
