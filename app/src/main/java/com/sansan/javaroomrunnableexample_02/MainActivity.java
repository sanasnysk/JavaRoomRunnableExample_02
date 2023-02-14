package com.sansan.javaroomrunnableexample_02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public static final int UPDATE_WORD_ACTIVITY_REQUEST_CODE = 2;
    private ViewModelProvider.AndroidViewModelFactory viewModelFactory;

    private WordViewModel mWordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final WordListAdapter adapter = new WordListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Model Provider
        //mWordViewModel= ViewModelProviders.of(this).get(WordViewModel.class);
        if (viewModelFactory == null){
            viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        }
        mWordViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) viewModelFactory)
                .get(WordViewModel.class);

        //observe : model의 LiveData를 관찰한다.
        mWordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable final List<Word> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setmWords(words);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewWordActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);

            }
        });

//        adapter.setOnItemClickListener(new WordListAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(Word model) {
//                Intent intent_update = new Intent(MainActivity.this, UpdateWordActivity.class);
//                intent_update.putExtra(UpdateWordActivity.EXTRA_ID, model.getId());
//                intent_update.putExtra(UpdateWordActivity.EXTRA_WORD,model.getWord());
//
//                startActivityForResult(intent_update, UPDATE_WORD_ACTIVITY_REQUEST_CODE);
//            }
//
//        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Word word = new Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY));
            mWordViewModel.insert(word);

        }else {
            Toast.makeText(
                    getApplicationContext(),
                    "저장되어 있는 단어가 없습니다.",
                    Toast.LENGTH_LONG).show();
        }

    }

}