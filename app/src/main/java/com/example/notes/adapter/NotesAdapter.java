package com.example.notes.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.MainActivity;
import com.example.notes.Notes;
import com.example.notes.R;
import com.example.notes.activity.updateNoteActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.noteViewholder> {
    Context context;
    List<Notes> notes;
    List<Notes> allnotesitem;
    private AlertDialog.Builder dialoguebuilder;

    public NotesAdapter(Context context, List<Notes> notes) {
        this.context = context;
        this.notes = notes;
        allnotesitem=new ArrayList<>(notes);
    }

    public void serchnotes(List<Notes> filterNotes){
        this.notes=filterNotes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public noteViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_notes,parent,false);

        return new noteViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull noteViewholder holder, int position) {
        Notes note=notes.get(position);

        holder.Title.setText(note.noteTitle);
       holder.SubTitle.setText(note.notesSubTitle);
       holder.Notes.setText(note.notes);
       holder.Date.setText(note.notesDate);
        if (note.notes_priority.equals("1")){
            holder.notespriority.setBackgroundResource(R.drawable.green_shape);
        }else if (note.notes_priority.equals("2")){
            holder.notespriority.setBackgroundResource(R.drawable.yellow_shaoe);
        }else if (note.notes_priority.equals("3")){
            holder.notespriority.setBackgroundResource(R.drawable.red_shape);
        }


        holder.itemView.setOnClickListener(view -> {
           Intent intent=new Intent(context, updateNoteActivity.class);
           intent.putExtra("id",note.id);
           intent.putExtra("title",note.noteTitle);
           intent.putExtra("subtitle",note.notesSubTitle);
           intent.putExtra("priority",note.notes_priority);
           intent.putExtra("note",note.notes);

           context.startActivity(intent);
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getRootView().getContext());
                final View customLayout = LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.popuplayout, null);
                TextView poptitle, popsubtitle, popnote;
                popnote = customLayout.findViewById(R.id.popnote);
                popsubtitle = customLayout.findViewById(R.id.popsubtitle);
                poptitle = customLayout.findViewById(R.id.poptitle);
                poptitle.setText(note.noteTitle);
                popsubtitle.setText(note.notesSubTitle);
                popnote.setText(note.notes);
                alertDialog.setView(customLayout);
                alertDialog.setCancelable(true);
                alertDialog.show();
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public int getnoteAt(int position){
        return notes.get(position).id;
    }

    class noteViewholder extends RecyclerView.ViewHolder{
        TextView Title ,SubTitle, Notes,Date;
        View notespriority;
        CardView cardView;
        public noteViewholder(@NonNull View itemView) {
            super(itemView);

            Title=itemView.findViewById(R.id.title);
            SubTitle=itemView.findViewById(R.id.subtitle);
            cardView=itemView.findViewById(R.id.card);
            Notes=itemView.findViewById(R.id.Notes);
            notespriority=itemView.findViewById(R.id.prio);
            Date=itemView.findViewById(R.id.date);


        }
    }



}
