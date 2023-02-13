package com.sansan.javaroomrunnableexample_02;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private final LayoutInflater mInflater;
    private List<Word> mWords;
    private OnItemClickListener listener;

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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_update = new Intent(v.getContext(), UpdateWordActivity.class);
                intent_update.putExtra("id", mWords.get(position).getId());
                intent_update.putExtra("text", mWords.get(position).getWord());
                v.getContext().startActivity(intent_update);

                ((Activity) v.getContext()).finish();
            }
        });

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

    public class WordViewHolder extends RecyclerView.ViewHolder {
        TextView word;
        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(getItem(position));

                    }
                }

                private Word getItem(int position) {
                                return null;
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Word model);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
