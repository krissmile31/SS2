package com.example.myui.mynotes.notes.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myui.R;
import com.example.myui.mynotes.notes.db.NoteManager;
import com.example.myui.mynotes.notes.models.Note;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private List<Note> noteList;
    private OnEditClickListener listener;

    public NoteAdapter(List<Note> noteList) {
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View itemView = layoutInflater.inflate(R.layout.item_note, parent, false);
        return new NoteHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        holder.bind(noteList.get(position));
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class NoteHolder extends RecyclerView.ViewHolder {
        private Context context;
        private TextView tvNote;

        public NoteHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            tvNote = itemView.findViewById(R.id.note);
        }

        public void bind(Note note) {
            tvNote.setText(note.getText());
            tvNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                        listener.onEditClick(noteList.get(position));
                    NoteManager noteManager = NoteManager.getInstance(context);
                    noteManager.delete(note.getId());
                    notifyDataSetChanged();
                }
            });


            tvNote.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    new AlertDialog.Builder(context)
                            .setIcon(R.drawable.checked)
                            .setTitle("Are you sure to remove this note >.<?")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    noteList.remove(note);
                                    NoteManager noteManager = NoteManager.getInstance(context);
                                    noteManager.delete(note.getId());
                                    notifyDataSetChanged();
                                }})
                            .show();
                    return true;

                }
            });
        }

    }
    public interface OnEditClickListener {
        void onEditClick(Note note);
    }

    public void setOnEditClickListener(OnEditClickListener listener){
        this.listener = listener;
    }

}

