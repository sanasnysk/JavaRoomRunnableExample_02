package com.sansan.javaroomrunnableexample_02;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private final LayoutInflater mInflater;
    private List<Word> mWords;

    WordListAdapter(Context context) { mInflater =LayoutInflater.from(context);}

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent,false);
        return new WordViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n,RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if (mWords != null){
            Word words = mWords.get(position);
            holder.word.setText(words.getWord());
        }else {
            holder.word.setText("No Word");
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    public void setmWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
            return (null != mWords ? mWords.size(): 0);
    }

    public static class WordViewHolder extends RecyclerView.ViewHolder {
        TextView word;
        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.textView);

        }
    }

}
