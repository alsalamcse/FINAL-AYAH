package aslan.ayahtaskmanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

import aslan.ayahtaskmanager.data.MyTask;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class AddTaskActivity extends AppCompatActivity {

    private EditText title, text, duedate;
    private SeekBar important, necessary;
    private Button save, datepicker;

//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference myRef = database.getReference("message");

    private int year;
    private int month;
    private int day;

    static final int DATE_DIALOG_ID = 999;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        title = (EditText) findViewById(R.id.title);
        text = (EditText) findViewById(R.id.text);
        duedate = (EditText) findViewById(R.id.duedate);
        important = (SeekBar) findViewById(R.id.important);
        necessary = (SeekBar) findViewById(R.id.necessary);

        save = (Button) findViewById(R.id.save);
        datepicker = (Button) findViewById(R.id.datepicker);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataHandler();
            }
        });
    }

        private void dataHandler() {
        boolean isOk = true;
        String title1 = title.getText().toString();
        String text1 = text.getText().toString();
        String duedate1 = duedate.getText().toString();
        int important1 = important.getProgress();
        int necessary1 = necessary.getProgress();

        if (title1.length() < 4) {
            title.setError(" title Have to be at least 4 letters");
            isOk = false;
        }
        if (text1.length() < 4) {
            text.setError(" text Have to be at least 4 letters");
            isOk = false;
        }
        if (duedate1.length() < 4) {
            duedate.setError(" Have to be at least 4 letters");
            isOk = false;
        }
        if (isOk) {
            MyTask task = new MyTask();
            task.setCreatedate(new Date());
//               task.setDuedate(new Date(Date));
            task.setText(text1);
            task.setTitle(title1);
            task.setImportant(important1);
            task.setNecessary(necessary1);

            FirebaseAuth auth = FirebaseAuth.getInstance();
            task.setOwner(auth.getCurrentUser().getEmail());

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            // foo2 add bl firebase
            //  ho mo2asher llproect(jzr) a3ml abn smeh wdefo - bt3ml key autumatically  (3mlyat el adafa t7t:)+ nt2kd eza endaf)

            String key = reference.child("MyTasks").push().getKey();
            task.setKey(key);

            reference.child("MyTask").child(key).setValue(task).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful())
                    {
                        Toast.makeText(AddTaskActivity.this, "Add Succesful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainTabsActivity.class);
                        startActivity(i);
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Add Failed",Toast.LENGTH_SHORT);
                }
            });
        }
    }
}



