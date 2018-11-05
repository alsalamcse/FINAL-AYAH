package aslan.ayahtaskmanager;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class AddTaskActivity extends AppCompatActivity {

    private EditText title, text, duedate;
    private SeekBar important, necessary;
    private Button save, datepicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        title = (EditText)findViewById(R.id.title);
        text = (EditText)findViewById(R.id.text);
        duedate = (EditText)findViewById(R.id.duedate);
        important = (SeekBar)findViewById(R.id.important);
        necessary = (SeekBar)findViewById(R.id.necessary);

        save = (Button)findViewById(R.id.save);
        datepicker = (Button)findViewById(R.id.datepicker);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        private void dataHandler()
        {
            boolean isOk = true;
            String title1 = title.getText().toString();
            String text1 = text.getText().toString();
            String duedate1 = duedate.getText().toString();
            int important1 = important.getProgress();
            int necessary1 = necessary.getProgress();

            if (title1.length()<4) {
                title.setError(" title Have to be at least 4 letters");
                isOk = false;
            }
            if (text1.length()<4) {
                text.setError(" text Have to be at least 4 letters");
                isOk = false;
            }
            if (duedate1.length() <4) {
                duedate.setError(" Have to be at least 4 letters");
                isOk = false;
            }
//            if (isOk)

        }

//        datepicker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

    }
}
