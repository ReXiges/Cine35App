package cine35app.esteb.cine35app;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "";
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    private SQLiteDatabase myDataBase;
    // Database Version
    private static final int DATABASE_VERSION = 2;

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

    public ArrayList<Cliente> getAllClients() {
        ArrayList<Cliente> temp = new ArrayList<Cliente>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        try {
            c = db.rawQuery("Select * From Usuario inner join TipoUsuario on Usuario.idTipo=TipoUsuario.idTipo where TipoUsuario.nombre = 'cliente'", null);
            if (c==null) return null;

            c.moveToFirst();
            do {
                Cliente cliente = new Cliente( c.getString(c.getColumnIndex("nombre")), c.getString(c.getColumnIndex("nacionalidad")),
                                               c.getString(c.getColumnIndex("password")), c.getString(c.getColumnIndex("correo")), c.getString(c.getColumnIndex("nombreUsuario")) );
                temp.add(cliente);
            }while(c.moveToNext());
            c.close();

        }
        catch(Exception e) {

        }
        db.close();
        return temp;

    }

    public ArrayList<Administrador> getAllAdmins() {
        ArrayList<Administrador> temp = new ArrayList<Administrador>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        try {
            c = db.rawQuery("Select * From Usuario inner join TipoUsuario on Usuario.idTipo=TipoUsuario.idTipo where TipoUsuario.nombre = 'admin'", null);
            if (c==null) return null;

            c.moveToFirst();
            do {
                Administrador admin = new Administrador( c.getString(c.getColumnIndex("nombre")),
                        c.getString(c.getColumnIndex("nacionalidad")), c.getString(c.getColumnIndex("nombreUsuario")),
                        c.getString(c.getColumnIndex("password")), c.getString(c.getColumnIndex("correo")) );
                temp.add(admin);
            }while(c.moveToNext());
            c.close();

        }
        catch(Exception e) {

        }
        db.close();
        return temp;

    }

    public ArrayList<Pelicula> getAllMovies() {
        ArrayList<Pelicula> temp = new ArrayList<Pelicula>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        try {
            c = db.rawQuery("Select * From Pelicula", null);
            if (c==null) return null;

            c.moveToFirst();
            do {
                Pelicula pelicula = new Pelicula( c.getString(c.getColumnIndex("nombre")), Integer.parseInt(c.getString(c.getColumnIndex("año"))),
                        c.getString(c.getColumnIndex("keywords")), c.getString(c.getColumnIndex("actores")),
                        c.getString(c.getColumnIndex("directores")), c.getString(c.getColumnIndex("imagen")),
                        c.getString(c.getColumnIndex("genero")) );
                temp.add(pelicula);
            }while(c.moveToNext());
            c.close();

        }
        catch(Exception e) {

        }
        db.close();
        return temp;

    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
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


    public long createUsuario(Cliente cliente) {
        ContentValues values = new ContentValues();
        values.put(nombre, cliente.getNombre());
        values.put(nacionalidad, cliente.getNacionalidad());
        values.put(correo, cliente.getCorreo());
        values.put(password, cliente.getPassword());
        values.put(idTipo, 2);
        values.put(nombreUsuario, cliente.getNombreUsuario());
        // insert row
        SQLiteDatabase db = this.getWritableDatabase();

        long todo_id = db.insert(TablaUsuario, null, values);

        db.close();
        return todo_id;
    }

    public Cliente getCliente(String userName) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TablaUsuario + " WHERE "
                + nombreUsuario + " = " + "'" +userName+"'";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Cliente td = new Cliente( c.getString(c.getColumnIndex("nombre")), c.getString(c.getColumnIndex("nacionalidad")),
                c.getString(c.getColumnIndex("password")),c.getString(c.getColumnIndex("correo")), c.getString(c.getColumnIndex("nombreUsuario")) );

        return td;
    }

}
