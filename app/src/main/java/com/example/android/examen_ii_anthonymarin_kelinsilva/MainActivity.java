package com.example.android.examen_ii_anthonymarin_kelinsilva;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    private LoteViewModel mLoteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final LoteListAdapter adapter = new LoteListAdapter(this, mLoteViewModel);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mLoteViewModel = ViewModelProviders.of(this).get(LoteViewModel.class);

        mLoteViewModel.getAllWords().observe(this, new Observer<List<Lote>>() {
            @Override
            public void onChanged(@Nullable final List<Lote> lotes) {
                adapter.setWords(lotes);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewLoteActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            Bundle objetoRecibido = data.getExtras();

            Lote lote = (Lote) objetoRecibido.getSerializable("lote");

            mLoteViewModel.insert(lote);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
