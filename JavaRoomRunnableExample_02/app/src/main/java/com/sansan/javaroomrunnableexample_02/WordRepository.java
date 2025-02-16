package com.sansan.javaroomrunnableexample_02;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class WordRepository {

    //Dao의 멤버변수와 word를 넣을 list변수를 만들어준다.
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        //RoomDatabase에 있는 Dao를 가져온다.
        mWordDao=db.wordDao();
        //Dao의 쿼리를 이용하여 저장되어있는 모든 word를 가져온다.
        mAllWords=mWordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

/*
    //word를 추가하는 함수
    public void insert(Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao=dao;
        }

        @Override
        protected Void doInBackground(final Word... words) {

            mAsyncTaskDao.insert(words[0]);
            return null;
        }
    }

 */

    public void insert(final Word word) {
        Runnable addRunnable= new Runnable() {
            @Override
            public void run() {
                mWordDao.insert(word);
            }
        };
        Executor diskIO= Executors.newSingleThreadExecutor();

        diskIO.execute(addRunnable);
    }

    public void update(final Word word){
        Runnable updateRunnable = new Runnable() {
            @Override
            public void run() {
                mWordDao.update(word);
            }
        };
        Executor diskIO = Executors.newSingleThreadExecutor();
        diskIO.execute(updateRunnable);
    }
}
