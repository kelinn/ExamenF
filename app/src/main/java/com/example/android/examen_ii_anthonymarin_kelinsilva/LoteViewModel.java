package com.example.android.examen_ii_anthonymarin_kelinsilva;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LoteViewModel extends AndroidViewModel {

    private LoteRepository mRepository;
    private LiveData<List<Lote>> mAllWords;

    public LoteViewModel(Application application) {
        super(application);
        mRepository = new LoteRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    LiveData<List<Lote>> getAllWords() {
        return mAllWords;
    }

    void insert(Lote lote) {
        mRepository.insert(lote);
    }

    void update(Lote lote) {
        mRepository.update(lote);
    }

    void delete(Lote lote) {
        mRepository.delete(lote);
    }
}
