package com.example.android.examen_ii_anthonymarin_kelinsilva;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;

public class NewLoteActivity extends AppCompatActivity {

    public NewLoteActivity(){

    }

    private String mParam1;
    private String mParam2;

    public static final String EXTRA_REPLY = "com.example.android.lotelistsql.REPLY";
    private static final String CARPETA_PRINCIPAL = "misImagenes/";
    private static final String CARPETA_IMAGEN = "imagenes/";
    private static final String DIRECTORIO_IMAGEN = CARPETA_PRINCIPAL+CARPETA_IMAGEN;
    private static  String path;
    private File fileImagen;
    private Bitmap bitmap;

    private  static final int COD_SELECCIONA = 10;
    private static final int COD_FOTO = 20;


    private EditText mEditWordView;
    private ImageView imageView;
    private EditText fecha;
    private EditText metros;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_lote);
        mEditWordView = findViewById(R.id.Nombre);
        metros = findViewById(R.id.mts);
        fecha = findViewById(R.id.fecha);
        imageView = findViewById(R.id.imageView);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                //abrirCamara();
                if (TextUtils.isEmpty(mEditWordView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String nombre = mEditWordView.getText().toString();
                    Double m = Double.parseDouble(metros.getText().toString());
                    String f = fecha.getText().toString();

                    String urlI = "https://www.baccredomatic.com/sites/default/files/cr-hero-prestamo-lote-dic-2017-mobile_0.jpg";
                    String urlV = "https://www.baccredomatic.com/sites/default/files/cr-hero-prestamo-lote-dic-2017-mobile_0.jpg";


                    Lote i = new Lote(nombre,m,f,urlV,urlI);

                    Bundle bundle= new Bundle();
                    bundle.putSerializable("lote",i);

                    replyIntent.putExtras(bundle);
                    setResult(RESULT_OK,replyIntent);
                    finish();

                }
                finish();
            }
        });
    }
    private void abrirCamara(){
        File miFile = new File(Environment.getExternalStorageDirectory(),DIRECTORIO_IMAGEN);
        boolean isCreada = miFile.exists();
        if(isCreada==false){
            isCreada = miFile.mkdirs();
        }
        if(isCreada == true){
            Long consecutivo = System.currentTimeMillis()/1000;
            String nombre = consecutivo.toString()+".jpg";

            path=Environment.getExternalStorageDirectory()+File.separator+DIRECTORIO_IMAGEN
                    +File.separator+nombre;
            fileImagen = new File(path);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileImagen));

        }
    }

}

