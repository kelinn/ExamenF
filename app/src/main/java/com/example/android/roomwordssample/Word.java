package com.example.android.roomwordssample;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;
    @ColumnInfo(name = "medida")
    private String mMedida;
    @ColumnInfo(name = "fecha")
    private String mFecha;

    public Word(@NonNull String word,@NonNull String medida,@NonNull String fecha) {

        this.mWord = word;
        this.mMedida = medida;
        this.mFecha = fecha;
    }

    public void setmWord(@NonNull String mWord) {
        this.mWord = mWord;
    }

    @NonNull
    public String getWord() {
        return this.mWord;
    }

    public void setmMedida(@NonNull String mMedida) {
        this.mMedida= mMedida;
    }

    @NonNull
    public String getMedida() {
        return this.mMedida;
    }

    public void setmFecha(@NonNull String mFecha) {
        this.mFecha= mFecha;
    }

    @NonNull
    public String getFecha() { return this.mFecha; }
}
