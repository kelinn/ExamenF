package com.example.android.examen_ii_anthonymarin_kelinsilva;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;


@Database(entities = {Lote.class}, version = 2)
public abstract class LoteRoomDatabase extends RoomDatabase {

    public abstract LoteDao wordDao();

    private static volatile LoteRoomDatabase INSTANCE;

    static LoteRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LoteRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LoteRoomDatabase.class, "examen")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final LoteDao mDao;

        PopulateDbAsync(LoteRoomDatabase db) {
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();

            Lote lote = new Lote("Lote1",5000d,"2020-06-28","/fsgdifsdf", "videosadjkas");
            mDao.insert(lote);
            lote = new Lote("Lote2",5000d,"2020-06-28","/fsgdifsdf", "videosadjkas");
            mDao.insert(lote);
            lote = new Lote("Lote3",5000d,"2020-06-28","/fsgdifsdf", "videosadjkas");
            mDao.insert(lote);
            lote = new Lote("Lote4",5000d,"2020-06-28","/fsgdifsdf", "videosadjkas");
            mDao.insert(lote);
            lote = new Lote("Lote5",5000d,"2020-06-28","/fsgdifsdf", "videosadjkas");
            mDao.insert(lote);
            return null;
        }
    }

}
