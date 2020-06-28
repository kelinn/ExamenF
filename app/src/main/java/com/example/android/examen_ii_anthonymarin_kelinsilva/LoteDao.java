package com.example.android.examen_ii_anthonymarin_kelinsilva;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LoteDao {

    @Query("SELECT * from lote_db ORDER BY nombre ASC")
    LiveData<List<Lote>> getAlphabetizedWords();

    @Insert
    void insert(Lote lote);

    @Delete
    void delete(Lote lote);

    @Update
    void update(Lote lote);

    @Query("DELETE FROM lote_db")
    void deleteAll();
}
