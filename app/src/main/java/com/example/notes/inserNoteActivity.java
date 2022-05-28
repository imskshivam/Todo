package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import com.example.notes.viewmodels.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class inserNoteActivity extends AppCompatActivity {
    EditText Title,Sub,Not;
    ImageView green ,red,yellow;
    String title,substitle ,notes;
    NotesViewModel notesViewModel;
    FloatingActionButton btn;
    String priority="1";
    ImageButton calender;
    TextView calendertext;
    int year;
    int month;
    int day;
    String str;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_inser_note);
        Title=findViewById(R.id.title);
        Sub=findViewById(R.id.subtitle);
        Not=findViewById(R.id.Note);
        btn=findViewById(R.id.doneNotebtn);
        green=findViewById(R.id.green);
        red=findViewById(R.id.red);
        yellow=findViewById(R.id.yellow);
        calender=findViewById(R.id.calender);
        calendertext=findViewById(R.id.textView6);



notesViewModel= ViewModelProviders.of(this).get(NotesViewModel.class);

Calendar c=Calendar.getInstance();
calender.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        year=c.get(Calendar.YEAR);
        month=c.get(Calendar.MONTH);
        day=c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(inserNoteActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
str=SimpleDateFormat.getInstance().format(c.getTime());
calendertext.setVisibility(View.VISIBLE);
calendertext.setText(str);
            }
        },year,month,day);
        datePickerDialog.show();

    }
});


        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                green.setImageResource(R.drawable.ic_baseline_done_24);
                red.setImageResource(0);
                yellow.setImageResource(0);
                priority="1";

            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                green.setImageResource(0);
                red.setImageResource(R.drawable.ic_baseline_done_24);
                yellow.setImageResource(0);
                priority="3";
            }
        });

        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                green.setImageResource(0);
                red.setImageResource(0);
                yellow.setImageResource(R.drawable.ic_baseline_done_24);
                priority="2";

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title=Title.getText().toString();
                substitle=Sub.getText().toString();
                notes=Not.getText().toString();
                CreateNote(title,substitle,notes);
            }
        });
    }

    private void CreateNote(String title, String substitle, String notes) {

        Notes notes1=new Notes();
        notes1.noteTitle=title;
        notes1.notesSubTitle=substitle;

        notes1.notes_priority=priority;


        notes1.notes=notes;
        notes1.notesDate=str;

        notesViewModel.insert(notes1);
        Toast.makeText(this,"inserted",Toast.LENGTH_SHORT).show();
        finish();

    }
}