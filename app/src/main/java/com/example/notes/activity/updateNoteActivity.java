package com.example.notes.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.notes.Notes;
import com.example.notes.R;
import com.example.notes.viewmodels.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;

public class updateNoteActivity extends AppCompatActivity {
    EditText Title,Sub,Not;
    ImageView green ,red,yellow;
    String title,substitle ,notes;
    NotesViewModel notesViewModel;
    FloatingActionButton btn;
    String priority="1";
    String stitle,ssubtitle,snotes,spriority;
    int sid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        
        Title=findViewById(R.id.updatetitle);
        Sub=findViewById(R.id.updatesubtitle);
        Not=findViewById(R.id.updatenote);
        btn=findViewById(R.id.updatebtn);
        green=findViewById(R.id.updategreen);
        red=findViewById(R.id.updatered);
        yellow=findViewById(R.id.updateyellow);


        sid=getIntent().getIntExtra("id",0);
        stitle=getIntent().getStringExtra("title");
        ssubtitle=getIntent().getStringExtra("subtitle");
        spriority=getIntent().getStringExtra("priority");
        snotes=getIntent().getStringExtra("note");

        Title.setText(stitle);
        Sub.setText(ssubtitle);
        Not.setText(snotes);





        notesViewModel= ViewModelProviders.of(this).get(NotesViewModel.class);


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

                UpdateNotes(title,substitle,notes);

            }
        });
    }

    private void UpdateNotes(String title, String substitle, String notes) {
        Date date=new Date();
        CharSequence sequence= DateFormat.format("MMMM d, yyyy",date.getTime());

        Notes notes1=new Notes();
        notes1.noteTitle=title;
        notes1.notesSubTitle=substitle;
        notes1.id=sid;
        notes1.notes_priority=priority;


        notes1.notes=notes;
        notes1.notesDate=sequence.toString();

        notesViewModel.update(notes1);
        Toast.makeText(this,"Notes updated successfully",Toast.LENGTH_SHORT).show();
        finish();
    }
}
