package com.java.kingquiz.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "kingquiz_db";

    //Tabla de usuarios
    private static final String TABLE_NAME1 = "users";
    private static final String COL_FIRST_LAST_NAME = "first_last_name";
    private static final String COL_USERNAME = "username";
    private static final String COL_MAIL = "mail";
    private static final String COL_PASSWORD = "password";

    //Tabla de preguntas
    private static final String TABLE_NAME2 = "questions";
    private static final String COL_QUESTION = "question";
    private static final String COL_QUESTION_ID= "question_id";
    private static final String COL_QUESTION_SUBJECT= "question_subject";

    //Tabla de respuestas
    private static final String TABLE_NAME3 = "answers";
    private static final String COL_ANSWER = "answer";
    private static final String COL_ANSWER_ID = "answer_id ";
    private static final String COL_ANSWERS_SUBJECT = "answer_subject";
    private static final String COL_ISCORRECT = "isCorrect";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    //Método constructror que crea la base de datos
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME1 + " (" +
                COL_USERNAME + " TEXT PRIMARY KEY," +
                COL_FIRST_LAST_NAME + " TEXT ," +
                COL_MAIL + " TEXT," +
                COL_PASSWORD + " TEXT)";
        db.execSQL(createTableQuery);

        String createTableQuery2 = "CREATE TABLE " + TABLE_NAME2 + " (" +
                COL_QUESTION + " TEXT," +
                COL_QUESTION_ID + " TEXT PRIMARY KEY," +
                COL_QUESTION_SUBJECT + " TEXT)";
        db.execSQL(createTableQuery2);

        String createTableQuery3 = "CREATE TABLE " + TABLE_NAME3 + " (" +
                COL_ANSWER + " TEXT," +
                COL_ANSWER_ID + " TEXT," +
                COL_ANSWERS_SUBJECT + " TEXT," +
                COL_ISCORRECT + " TEXT,"+
                " FOREIGN KEY (answer_id) REFERENCES questions(question_id))";
        db.execSQL(createTableQuery3);
    }
    //Este método elimina la tabla existente y luego crea una nueva versión de la misma con los datos actualizados
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        onCreate(db);
    }
    //Este método registra los datos de nuevos usuarios en la base de datos y devuelve un booleano
    public boolean registrarUsuarios(String username, String nombre, String correo, String contrasena) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USERNAME, username);
        contentValues.put(COL_FIRST_LAST_NAME, nombre);
        contentValues.put(COL_MAIL, correo);
        contentValues.put(COL_PASSWORD, contrasena);
        long result = db.insert(TABLE_NAME1, null, contentValues);
        return result != -1;
    }

    public boolean registrarPreguntas(String questionid, String question, String subject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_QUESTION, question);
        contentValues.put(COL_QUESTION_ID, questionid);
        contentValues.put(COL_QUESTION_SUBJECT, subject);
        long result = db.insert(TABLE_NAME2, null, contentValues);
        return result != -1;
    }

    public boolean registrarRespuestas(String answer, String answer_id , String subject, String IsCorrect) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ANSWER, answer);
        contentValues.put(COL_ANSWER_ID, answer_id);
        contentValues.put(COL_ANSWERS_SUBJECT, subject);
        contentValues.put(COL_ISCORRECT, IsCorrect);
        long result = db.insert(TABLE_NAME3, null, contentValues);
        return result != -1;
    }

    //Este método se usa para validar los datos de lo usuarios en la base de datos y devuelve un booleano
    public boolean validar(String username, String contrasena) {
        SQLiteDatabase db = this.getReadableDatabase();             //Esto crea una una versión para sobreescribible de la base de datos para escribir información en ella
        String query = "SELECT * FROM " + TABLE_NAME1 + " WHERE " +
                COL_USERNAME + " = '" + username + "' AND " +
                COL_PASSWORD + " = '" + contrasena + "'";
        //Esta consulta busca en toda la base de datos la fila
        // donde los datos coincidan con la información con los que son enviados al método desde la clase que hace el llamado
        Cursor cursor = db.rawQuery(query, null); //Este es un método que ejecuta la consulta (el query) y almacena el resultado en la variable Cursor
        //este valor será un número entero que indicará la posición o número de la fila para que luego este pueda ser usado en el método "ObtenerNombre"
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }
    //Este método busca y devuelve el valor de la columna "Nombre" en la base de datos
    public String obtenerNombre(String pass) {
        SQLiteDatabase db = this.getReadableDatabase();                 //Esto crea una una versión de sólo lectura de la base de datos para buscar información en ella
        String query = "SELECT " + COL_USERNAME + " FROM " + TABLE_NAME1 + " WHERE " +
                COL_PASSWORD + " = '" + pass + "'";            //Esta consulta busca el nombre en una fila donde el valor de la cédula coincida
        // con el valor ingresado en el EditText de la clase que hace el llamado de este método
        Cursor cursor = db.rawQuery(query, null);
        String nombre = "";
        if (cursor.moveToFirst()) {
            nombre = cursor.getString(0);
        }
        cursor.close();
        return nombre;
    }
    //Este método es para actualizar los datos en la columna nombre y celular donde la cedula y la contraseña coincidan
    // con las ingresadas en el EditText de la clase que hace el llamado de este método
    public boolean ActualizarDatos(String cedula, String contrasena, String nombre, String celular) {
        SQLiteDatabase db = this.getWritableDatabase(); //Esto crea una una versión para sobreescribible de la base de datos para escribir información en ella
        ContentValues contentValues = new ContentValues(); //Crea un objeto para almacenar los datos que usaremos para actualizar
        contentValues.put(COL_FIRST_LAST_NAME, nombre); //carga el objeto con el dato recibido y asigna la columna en la que se ingresará
        contentValues.put(COL_MAIL, celular);
        String whereClause = COL_FIRST_LAST_NAME + " = ? AND " + COL_PASSWORD + " = ?"; //Arma la condición de "en qué posición hará la actualización?"
        String[] whereArgs = {cedula, contrasena}; //argumentos de la condición
        int result = db.update(TABLE_NAME1, contentValues, whereClause, whereArgs); //Devuelve un número entero con la cantidad de filas afectadas
        return result > 0;
    }
    //Este método es para borrar los datos en la fila donde la cedula y la contraseña coincidan con los ingresados
    public boolean BorrarDatos(String cedula, String contrasena) {
        SQLiteDatabase db = this.getWritableDatabase(); //Esto crea una una versión para sobreescribible de la base de datos para modificar información en ella
        String whereClause = COL_USERNAME + " = ? AND " + COL_PASSWORD + " = ?";  //Arma la condición de "en qué posición hará la actualización? En la que la cédula y la contraseña coincidan"
        String[] whereArgs = {cedula, contrasena};  //argumentos de la condición
        int result = db.delete(TABLE_NAME1, whereClause, whereArgs); //Devuelve un número entero con la cantidad de filas afectadas
        return result > 0;
    }

    public Cursor verDatosUsuarios() {
        SQLiteDatabase db = this.getReadableDatabase(); //Esto crea una una versión de sólo lectura de la base de datos para ver la información en ella
        String query = "SELECT * FROM " + TABLE_NAME1; //Consulta para ver toda la información ingresada en la tabla
        return db.rawQuery(query, null); //Ejecuta la consulta
    }

    public Cursor verPreguntas() {
        SQLiteDatabase db = this.getReadableDatabase(); //Esto crea una una versión de sólo lectura de la base de datos para ver la información en ella
        String query = "SELECT * FROM " + TABLE_NAME2; //Consulta para ver toda la información ingresada en la tabla
        return db.rawQuery(query, null); //Ejecuta la consulta
    }

    public Cursor verRespuestas() {
        SQLiteDatabase db = this.getReadableDatabase(); //Esto crea una una versión de sólo lectura de la base de datos para ver la información en ella
        String query = "SELECT * FROM " + TABLE_NAME3; //Consulta para ver toda la información ingresada en la tabla
        return db.rawQuery(query, null); //Ejecuta la consulta
    }

    public Cursor bringMeQuestion() {
        SQLiteDatabase db = this.getReadableDatabase(); //Esto crea una una versión de sólo lectura de la base de datos para ver la información en ella
        String query = "SELECT question, question_id FROM " + TABLE_NAME2 +" ORDER BY RANDOM() LIMIT 1; "; //Consulta para ver toda la información ingresada en la tabla
        return db.rawQuery(query, null); //Ejecuta la consulta
    }

    public Cursor bringMeCorrectAnswer(String id) {
        SQLiteDatabase db = this.getReadableDatabase(); //Esto crea una una versión de sólo lectura de la base de datos para ver la información en ella
        String query = "SELECT answer FROM " + TABLE_NAME3 +" WHERE answer_id = '"+id+"' AND isCorrect = 'true'; "; //Consulta para ver toda la información ingresada en la tabla
        //String query = "SELECT * FROM answers WHERE answer_id ='1' AND isCorrect = 'true'; "; //Consulta para ver toda la información ingresada en la tabla
        //String query = "SELECT isCorrect, answer FROM answers  INNER JOIN questions ON question_id = answer_id WHERE isCorrect = 'true'; "; //Consulta para ver toda la información ingresada en la tabla

        return db.rawQuery(query, null); //Ejecuta la consulta
    }

    public Cursor bringMeWrongAnswers(String id) {
        SQLiteDatabase db = this.getReadableDatabase(); //Esto crea una una versión de sólo lectura de la base de datos para ver la información en ella
        String query = "SELECT answer FROM " + TABLE_NAME3 +" WHERE isCorrect = 'false' AND answer_id = '"+id+"' ORDER BY RANDOM() LIMIT 3; "; //Consulta para ver toda la información ingresada en la tabla
        return db.rawQuery(query, null); //Ejecuta la consulta
    }

    public Cursor validateAnswer(String respuestaElegida, String id) {
        SQLiteDatabase db = this.getReadableDatabase(); //Esto crea una una versión de sólo lectura de la base de datos para ver la información en ella
        String query = "SELECT answer FROM answers INNER JOIN questions ON answer_id = question_id " +
                "WHERE isCorrect='true' AND answer='"+respuestaElegida+"'" +
                " UNION " +
                "SELECT question FROM questions INNER JOIN answers ON question_id = answer_id" +
                " WHERE answer_id = '"+id+"';";

        return db.rawQuery(query, null); //Ejecuta la consulta
    }


}

