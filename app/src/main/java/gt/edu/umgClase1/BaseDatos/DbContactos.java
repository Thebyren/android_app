package gt.edu.umgClase1.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbContactos extends DbHelper {
    Context context;

    public DbContactos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    //este metodo inserta un contacto en la tabla t_contactos

    public long insertaContacto(String nombre, String telefono, String email) {
        try{
            if (nombre.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
                return -1;
            }
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("telefono", telefono);
            values.put("email", email);

            long id = db.insert(DbHelper.TABLE_CONTACTOS, null, values);
            return id;
        } catch (Exception e) {
            return -1;
        }
    }

    public ArrayList<Contactos> MostrarContactos(){
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase db = helper.getReadableDatabase();
            ArrayList<Contactos> listaContactos = new ArrayList<>();
            Contactos contacto = null;
            Cursor cursorContactos = db.rawQuery("select * from " + DbHelper.TABLE_CONTACTOS, null);

            if (cursorContactos.moveToFirst()) {
                do {
                    contacto = new Contactos();
                    
                    // Suponiendo que las columnas en la base de datos son: Id, Nombre, Telefono, Email
                    contacto.setId(cursorContactos.getInt(cursorContactos.getColumnIndex("Id")));
                    contacto.setNombre(cursorContactos.getString(cursorContactos.getColumnIndex("Nombre")));
                    contacto.setTelefono(cursorContactos.getString(cursorContactos.getColumnIndex("Telefono")));
                    contacto.setEmail(cursorContactos.getString(cursorContactos.getColumnIndex("Email")));

                    listaContactos.add(contacto);
                } while (cursorContactos.moveToNext());
            }
            cursorContactos.close(); // No olvides cerrar el cursor
            db.close(); // También es buena práctica cerrar la base de datos
        } catch (Exception e) {
            e.printStackTrace(); // Imprimir la excepción para depuración
        }

    }
}

