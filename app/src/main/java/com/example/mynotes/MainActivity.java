package com.example.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.recyclerview.widget.ItemTouchHelper;

import com.example.mynotes.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NoteViewModel noteViewModel;
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        RecyclerView recyclerView = binding.rv;

        noteAdapter = new NoteAdapter(new NoteAdapter.OnNoteClickListener() {
            @Override
            public void onNoteDeleteClick(int position) {
                // Handle the click event to delete the item
                noteViewModel.delete(noteAdapter.getNoteAt(position));

                Toast.makeText(MainActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
            }
        }, noteViewModel);
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        noteViewModel.getNoteList().observe(this, notes -> {
            noteAdapter.setNotes(notes);
        });

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // Get the position of the swiped item
//                int position = viewHolder.getAdapterPosition();
//
//                findViewById(R.color.red)
///              noteAdapter.onNoteDeleteClick(position); // Remove the item from the list

                final int position = viewHolder.getAdapterPosition();
                if (direction == ItemTouchHelper.LEFT || direction == ItemTouchHelper.RIGHT) {

                    recyclerView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            noteAdapter.onNoteDeleteClick(position);
                        }
                    }, 2000);
                }
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DataInsertActivity2.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String title = data.getStringExtra("title");
            String disp = data.getStringExtra("disp");
            note notes = new note(title, disp);
            noteViewModel.insert(notes);
            Toast.makeText(this, "Note added", Toast.LENGTH_SHORT).show();
        }
    }
}
