package com.example.manosyollas.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.manosyollas.clases.ForumItem;

import java.util.ArrayList;
import java.util.List;

public class BurrosVolanteSQLite extends SQLiteOpenHelper {
    private static final String nombreDB="BurrosVolante.db";
    private static final int versionDB=1;

    //Cadenitas DDL
    private static final String createTableUsuario="create table if not exists Usuarios(id integer, correo varchar(255), clave varchar(255));";
    private static final String dropTableUsuario="drop table if exists Usuarios;";


    // Nombre de la tabla
    public static final String TABLE_FOROS = "ForosLocales";


    // Nombres de las columnas
    public static final String COLUMN_ID = "idForo";
    public static final String COLUMN_TITLE = "titulo";
    public static final String COLUMN_DESCRIPTION = "descripcion";
    public static final String COLUMN_FEC_CREACION = "fecCreacion";
    public static final String COLUMN_ROL_LOCAL = "rolLocal";
    public static final String COLUMN_ICON_RES_ID = "iconResId";

    // Sentencia SQL para crear la tabla
    private static final String TABLE_CREATE_FOROS =
            "CREATE TABLE " + TABLE_FOROS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_TITLE + " TEXT NOT NULL, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_FEC_CREACION + " TEXT, " +
                    COLUMN_ROL_LOCAL + " TEXT, " +
                    COLUMN_ICON_RES_ID + " INTEGER" +
                    ");";

    private static final String createTableMensajesLocales="create table if not exists MensajesLocales(id INTEGER PRIMARY KEY, idForo INTEGER,idUsuario INTEGER,contenido TEXT not null,fecha_envio TEXT,FOREIGN KEY (idForo) REFERENCES ForosLocales(idForo));";
    private static final String dropTableMensajesLocales="drop table if exists MensajesLocales;";


    public BurrosVolanteSQLite(@Nullable Context context) {
        super(context,nombreDB,null,versionDB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //RECOMENDACIÓN CREAR EL ESQUEMA DE SQLITE
        //sqLiteDatabase.execSQL(createTableUsuario);
        sqLiteDatabase.execSQL(TABLE_CREATE_FOROS);
        //sqLiteDatabase.execSQL(createTableMensajesLocales);





    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FOROS); // Elimina la tabla si ya existe
        onCreate(sqLiteDatabase);

        //sqLiteDatabase.execSQL(dropTableMensajesLocales);
        //sqLiteDatabase.execSQL(createTableMensajesLocales);
    }

    // Insertar un foro
    public void insertForum(ForumItem forumItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, forumItem.getForoId());
        values.put(COLUMN_TITLE, forumItem.getTitle());
        values.put(COLUMN_DESCRIPTION, forumItem.getDescription());
        values.put(COLUMN_ICON_RES_ID, forumItem.getIconResId());
        values.put(COLUMN_FEC_CREACION, forumItem.getFecCracion());
        values.put(COLUMN_ROL_LOCAL, forumItem.getRol());

        db.insert(TABLE_FOROS, null, values);
        db.close();
    }

    // Método para actualizar un foro
    public int updateForum(ForumItem forumItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, forumItem.getForoId());
        values.put(COLUMN_TITLE, forumItem.getTitle());
        values.put(COLUMN_DESCRIPTION, forumItem.getDescription());
        values.put(COLUMN_ICON_RES_ID, forumItem.getIconResId());
        values.put(COLUMN_FEC_CREACION, forumItem.getFecCracion());
        values.put(COLUMN_ROL_LOCAL, forumItem.getRol());

        // Actualiza el foro donde idForo sea igual al id del ForumItem
        int rowsAffected = db.update(TABLE_FOROS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(forumItem.getForoId())});
        db.close();

        return rowsAffected; // Devuelve el número de filas afectadas (debería ser 1 si todo sale bien)
    }

    // Obtener todos los foros
    public List<ForumItem> getAllForos() {
        List<ForumItem> forumList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FOROS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Integer foroId = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION));
                String rol = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ROL_LOCAL));
                String fecCreacion = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FEC_CREACION));

                int iconResId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ICON_RES_ID));

                ForumItem forumItem = new ForumItem( title, description, iconResId);
                forumItem.setForoId(foroId);
                forumItem.setRol(rol);
                forumItem.setFecCracion(fecCreacion);

                forumList.add(forumItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return forumList;
    }

    // Eliminar todos los foros
    public void deleteAllForos() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FOROS, null, null);
        db.close();
    }
}