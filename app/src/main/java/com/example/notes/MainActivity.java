package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.notes.adapter.NotesAdapter;

import com.example.notes.viewmodels.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    NotesAdapter adapter;

FloatingActionButton btn;
NotesViewModel viewModel;

TextView Nofilter,LowtoHigh,HightoLow;
List<Notes> filternameList;
LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        btn=findViewById(R.id.newNotebtn);
        recyclerView=findViewById(R.id.noteRecycler);
        Nofilter=findViewById(R.id.textView3);
        LowtoHigh=findViewById(R.id.textView4);
        HightoLow=findViewById(R.id.textView5);
        Nofilter.setBackgroundResource(R.drawable.filter_selected_shape);


        Nofilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HightoLow.setBackgroundResource(R.drawable.filter_shape);
                LowtoHigh.setBackgroundResource(R.drawable.filter_shape);
                Nofilter.setBackgroundResource(R.drawable.filter_selected_shape);

                viewModel.getAllNotes.observe(MainActivity.this,notes -> {
                    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                    adapter=new NotesAdapter(MainActivity.this,notes);
                    recyclerView.setAdapter(adapter);
                    filternameList=notes;

                });
            }
        });

        LowtoHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HightoLow.setBackgroundResource(R.drawable.filter_shape);
                LowtoHigh.setBackgroundResource(R.drawable.filter_selected_shape);
                Nofilter.setBackgroundResource(R.drawable.filter_shape);

                viewModel.hightolow.observe(MainActivity.this,notes -> {
                    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                    adapter=new NotesAdapter(MainActivity.this,notes);
                    recyclerView.setAdapter(adapter);
                    filternameList=notes;

                });
            }
        });


        HightoLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HightoLow.setBackgroundResource(R.drawable.filter_selected_shape);
                LowtoHigh.setBackgroundResource(R.drawable.filter_shape);
                Nofilter.setBackgroundResource(R.drawable.filter_shape);

                viewModel.lowtohigh.observe(MainActivity.this,notes -> {
                    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                    adapter=new NotesAdapter(MainActivity.this,notes);
                    recyclerView.setAdapter(adapter);
                    filternameList=notes;

                });
            }
        });

        viewModel= ViewModelProviders.of(this).get(NotesViewModel.class);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),inserNoteActivity.class);
                startActivity(i);
            }
        });

        viewModel.getAllNotes.observe(MainActivity.this,notes -> {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
            adapter=new NotesAdapter(MainActivity.this,notes);
            recyclerView.setAdapter(adapter);

        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.delete(adapter.getnoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this,  "data deleted", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem menuItem=menu.findItem(R.id.app_bar_search);

        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.getQueryHint();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                NotesFilter(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }

    private void NotesFilter(String s) {

        ArrayList<Notes> filtername =new ArrayList<>();

        for (Notes notes:this.filternameList){
            if (notes.noteTitle.contains(s)|| notes.notesSubTitle.contains(s)){
                filtername.add(notes);
            }
        }


        this.adapter.serchnotes(filtername);
    }
}