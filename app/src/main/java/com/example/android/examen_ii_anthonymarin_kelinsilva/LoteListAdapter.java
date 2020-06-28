package com.example.android.examen_ii_anthonymarin_kelinsilva;

import android.app.AlertDialog;
import android.content.Context;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;


public class LoteListAdapter extends RecyclerView.Adapter<LoteListAdapter.WordViewHolder> {
    private LoteViewModel mLoteViewModel;
    private ViewGroup _parent;
    private LoteListAdapter adapter;
    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        public  Button updateBTN;
        public  Button deleteBTN;



        private WordViewHolder(View itemView) {
            super(itemView);

            wordItemView = itemView.findViewById(R.id.textView);
            updateBTN = (Button) itemView.findViewById(R.id.editBtn);
            deleteBTN = (Button) itemView.findViewById(R.id.deleteBtn);
            mLoteViewModel = ViewModelProviders.of((FragmentActivity) mInflater.getContext()).get(LoteViewModel.class);

        }
    }

    private final LayoutInflater mInflater;
    private List<Lote> mLotes = Collections.emptyList(); // Cached copy of words

    LoteListAdapter(Context context, LoteViewModel mWordViewM) {
        mLoteViewModel = mWordViewM;
        mInflater = LayoutInflater.from(context);
        adapter = this;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        _parent = parent;
        return new WordViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(final WordViewHolder holder, final int position) {

        final Lote current = mLotes.get(position);

        holder.wordItemView.setText(current.getNombre());

        holder.updateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View _inflater = mInflater.inflate(R.layout.edit_lote, _parent, false);

                final EditText editText;
                final EditText editFecha;
                final EditText editMts;
                final ImageView imagen;
                imagen = (ImageView) _inflater.findViewById(R.id.editImagen);

                Glide.with(_inflater)
                        .load("https://www.baccredomatic.com/sites/default/files/cr-hero-prestamo-lote-dic-2017-mobile_0.jpg")
                        .into(imagen);


                editText = (EditText) _inflater.findViewById(R.id.word_);
                editFecha = (EditText) _inflater.findViewById(R.id.fechaEdit);
                editMts = (EditText) _inflater.findViewById(R.id.mtsEdit);



                editText.setText(current.getNombre());
                editFecha.setText(current.getFecha());
                editMts.setText(""+current.getMts());


                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(mInflater.getContext());
                dialogo1.setTitle(current.getNombre());
                dialogo1.setMessage("¿ Desea Editar el item ?");
                dialogo1.setView(_inflater);
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        current.setNombre(editText.getText().toString());
                        current.setFecha(editFecha.getText().toString());
                        current.setMts(Double.parseDouble(editMts.getText().toString()));

                        mLoteViewModel.update(current);
                        adapter.notifyItemChanged(position);
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        dialogo1.dismiss();
                    }
                });
                dialogo1.show();

            }
        });

        holder.deleteBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(mInflater.getContext());
                dialogo1.setTitle(current.getNombre());
                dialogo1.setMessage("¿ Desea eliminar el item ?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        mLoteViewModel.delete(current);
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        dialogo1.dismiss();
                    }
                });
                dialogo1.show();
            }
        });
    }



    void setWords(List<Lote> lotes) {
        mLotes = lotes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mLotes.size();
    }
}


