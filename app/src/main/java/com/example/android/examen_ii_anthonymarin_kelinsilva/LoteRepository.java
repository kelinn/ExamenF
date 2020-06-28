package com.example.android.examen_ii_anthonymarin_kelinsilva;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

class LoteRepository {

    private LoteDao mWordDao;
    private LiveData<List<Lote>> mAllWords;

    LoteRepository(Application application) {
        LoteRoomDatabase db = LoteRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAlphabetizedWords();
    }


    LiveData<List<Lote>> getAllWords() {
        return mAllWords;
    }


    void insert(Lote lote) {
        new insertAsyncTask(mWordDao).execute(lote);
    }
    void update(Lote lote) {
        new updateAsyncTask(mWordDao).execute(lote);
    }
    void delete(Lote lote) {
        new deleteAsyncTask(mWordDao).execute(lote);
    }

    private static class insertAsyncTask extends AsyncTask<Lote, Void, Void> {

        private LoteDao mAsyncTaskDao;

        insertAsyncTask(LoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Lote... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Lote, Void, Void> {

        private LoteDao mAsyncTaskDao;

        updateAsyncTask(LoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Lote... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Lote, Void, Void> {

        private LoteDao mAsyncTaskDao;

        deleteAsyncTask(LoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Lote... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
