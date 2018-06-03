import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.os.Build;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "";
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    private SQLiteDatabase myDataBase;
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "BDcines35";

    private Context mContext=null;
    //Tables
    private static final String TablaPelícula = "Pelicula";

    private static final String TablaUsuario = "Usuario";

    private static final String TablaFavoritos = "Favoritos";

    private static final String TablaGenero = "Genero";

    private static final String TablaTipoUsuario = "TipoUsuario";

    private static final String TablaRate = "Rate";

    private static final String TablaComentario = "Comentario";

    //Column Names

    //Comúnes
    private static final String idPelicula = "idPelicula";
    private static final String idCliente = "idUsuario";
    private static final String nombre = "nombre";
    private static final String idTipo = "idTipo";
    private static final String descripcion = "descripcion";

    // Comentarios
    private static final String idComentario = "idComentario";
    private static final String idUsuario = "idUsuario";

    private static final String comment = "comment";

    //Favoritos
    private static final String idFavoritos = "idFavoritos";

    //Género
    private static final String idGenero = "idGenero";


    //Película
    private static final String año = "Año";
    private static final String keywords = "keywords";
    private static final String imagen = "imagen";
    private static final String Actores = "Actores";
    private static final String Directores = "Directores";

    //Rate
    private static final String idRate = "idRate";
    private static final String calificacion = "calificacion";

    //Usuario
    private static final String apellidos = "apellidos";
    private static final String nacionalidad = "nacionalidad";
    private static final String correo = "correo";
    private static final String nombreUsuario = "nombreUsuario";
    private static final String password = "password";

    //TipoUsuario
    //Ya están en los comunes

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        if (Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir+"/databases/";
        else
            DB_PATH = "/data/data/"+context.getPackageName()+"/databases/";

        mContext = context;
    }

    @Override
    public synchronized void close() {
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }

    private boolean checkDataBase() {
        SQLiteDatabase tempDB = null;
        try {
            String path = DB_PATH + DATABASE_NAME;
            tempDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        }
        catch (Exception ex) {}
        if (tempDB != null)
            tempDB.close();
        return tempDB != null ? true: false;
    }

    public void copyDataBase() {
        try {
            InputStream myInput = mContext.getAssets().open(DATABASE_NAME);
            String outputFileName = DB_PATH+DATABASE_NAME;
            OutputStream myOutput = new FileOutputStream(outputFileName);

            byte [] buffer = new byte[1024];
            int length;
            while((length = myInput.read(buffer))>0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openDataBase() {
        String path = DB_PATH+DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void createDatabase() {
        boolean isDBExist = checkDataBase();
        if (isDBExist) {

        }
        else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            }
            catch (Exception ex) {

            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables


        // create new tables
    }

    //public long createUsuario
}
