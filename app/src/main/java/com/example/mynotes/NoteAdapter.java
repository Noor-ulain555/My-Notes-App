package com.example.mynotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<note> noteList = new ArrayList<>();
    private OnNoteClickListener onNoteClickListener;
    private NoteViewModel noteViewModel; // Add a reference to your ViewModel

    public NoteAdapter(OnNoteClickListener listener, NoteViewModel viewModel) {
        this.onNoteClickListener = listener;
        this.noteViewModel = viewModel; // Initialize your ViewModel
    }

    public void setNotes(List<note> notes) {
        this.noteList = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_rv, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        note note = noteList.get(position);
        holder.bind(note);
        holder.setDeleteIcon(R.drawable.baseline_delete_24); // Use your custom delete icon here
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public note getNoteAt(int position) {
        return noteList.get(position);
    }

    public void removeItem(int position) {
        noteList.remove(position);
        notifyItemRemoved(position);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView descriptionTextView;
        private ImageView deleteIcon;

        NoteViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titlerv);
            descriptionTextView = itemView.findViewById(R.id.discription);
            deleteIcon = itemView.findViewById(R.id.deleteIcon);
            deleteIcon.setVisibility(View.GONE); // Initially hide the delete icon

            deleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onNoteClickListener.onNoteDeleteClick(position);
                    }
                }
            });
        }

        void bind(note note) {
            titleTextView.setText(note.getTitle());
            descriptionTextView.setText(note.getDisp());
        }

        void setDeleteIcon(int iconResId) {
            deleteIcon.setImageResource(iconResId);
        }

    }
    public void showDeleteIcon(RecyclerView.ViewHolder viewHolder) {
        int position = viewHolder.getAdapterPosition();
        notifyItemChanged(position);
    }

    public void onNoteDeleteClick(int position) {
        note note = getNoteAt(position);
        noteViewModel.delete(note);
        removeItem(position);
    }

    public interface OnNoteClickListener {
        void onNoteDeleteClick(int position);
    }
}







