package library;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
 
public class DatabaseHandler extends SQLiteOpenHelper {
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 3;
 
    // Database Name
    private static final String DATABASE_NAME = "StudentDatabase";
    
    public static int id;
    public static String table;
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String STUDENT = "CREATE TABLE Student(id INTEGER PRIMARY KEY,  first_name TEXT, last_name TEXT, age INTEGER, dob DATE, email_id TEXT, phone_no TEXT)";
        String PARENT = "CREATE TABLE Parent( id INTEGER, first_name TEXT , last_name TEXT , relation TEXT, profession TEXT, email_id TEXT, phone_no TEXT ,no_of_children INTEGER, sec_password VARCHAR, PRIMARY KEY(id, relation))";
        String TEACHER = "CREATE TABLE Teacher(id INTEGER PRIMARY KEY, first_name TEXT , last_name TEXT , qualification TEXT , email_id TEXT, phone_no TEXT)";
        String CLASS = "Create Table Class( class_no INTEGER  , section TEXT , strength INTEGER  , PRIMARY KEY(class_no,section))";
        String BOOK = "Create Table Book( id TEXT  PRIMARY KEY, title TEXT , author TEXT, description TEXT )";
        String EVENT = "CREATE Table Event( name TEXT , description TEXT , start_time DATETIME , end_time DATETIME , venue TEXT , PRIMARY KEY(start_time,end_time,venue))";
		String SUBJECT = "CREATE TABLE Subject( name TEXT  PRIMARY KEY)";
		String ATTENDANCE = "CREATE TABLE Attendance( student_id INTEGER  , date DATE , P_A TEXT , PRIMARY KEY(student_id,date), FOREIGN KEY(student_id) REFERENCES Student(id))";
		String CLASSTEACHER = "CREATE TABLE ClassTeacher( class_no INTEGER  , section TEXT , teacher_id INTEGER  , PRIMARY KEY(class_no,section), FOREIGN KEY(class_no,section) REFERENCES Class(class_no,section), FOREIGN KEY(teacher_id) REFERENCES Teacher(id))";
		String STUDENTCLASS = "CREATE TABLE StudentClass( student_id INTEGER  , class_no INTEGER  , section TEXT , PRIMARY KEY(student_id), FOREIGN KEY(student_id) REFERENCES Student(id), FOREIGN KEY(class_no,section) REFERENCES Class(class_no,section))";
		String STUDENTPARENT = "CREATE TABLE StudentParent( student_id INTEGER  , parent_id INTEGER  , PRIMARY KEY(student_id), FOREIGN KEY(student_id) REFERENCES Student(id), FOREIGN KEY(parent_id) REFERENCES Parent(id))";
		String SUBJECTBOOKCLASS= "CREATE TABLE SubjectBookClass( class_no INTEGER  , subject TEXT , book_id TEXT , PRIMARY KEY(class_no,book_id), FOREIGN KEY(class_no) REFERENCES Class(class_no), FOREIGN KEY(book_id) REFERENCES Book(id), FOREIGN KEY(subject) REFERENCES Subject(name))";
		String MEDICALRECORD = "CREATE TABLE MedicalRecord( student_id INTEGER  , blood_group TEXT , height INTEGER  , weight INTEGER  , allergies TEXT, PRIMARY KEY(student_id), FOREIGN KEY(student_id) REFERENCES Student(id))";
        String USER = "CREATE TABLE User( id INTEGER , table_name TEXT)";
        db.execSQL(STUDENT);
        db.execSQL(PARENT);
        db.execSQL(TEACHER);
        db.execSQL(CLASS);
        db.execSQL(BOOK);
        db.execSQL(EVENT);
        db.execSQL(SUBJECT);
        db.execSQL(ATTENDANCE);
        db.execSQL(CLASSTEACHER);
        db.execSQL(STUDENTCLASS);
        db.execSQL(STUDENTPARENT);
        db.execSQL(SUBJECTBOOKCLASS);
        db.execSQL(MEDICALRECORD);
        db.execSQL(USER);
    }
     
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS MedicalRecord");
        db.execSQL("DROP TABLE IF EXISTS SubjectBookClass");
        db.execSQL("DROP TABLE IF EXISTS StudentParent");
        db.execSQL("DROP TABLE IF EXISTS StudentClass");
        db.execSQL("DROP TABLE IF EXISTS ClassTeacher");
        db.execSQL("DROP TABLE IF EXISTS Attendance");
        db.execSQL("DROP TABLE IF EXISTS Subject");
        db.execSQL("DROP TABLE IF EXISTS Event");
        db.execSQL("DROP TABLE IF EXISTS Book");
        db.execSQL("DROP TABLE IF EXISTS Class");
        db.execSQL("DROP TABLE IF EXISTS Teacher");
        db.execSQL("DROP TABLE IF EXISTS Parent");
        db.execSQL("DROP TABLE IF EXISTS Student");
 
        // Create tables again
        onCreate(db);
    }
 
    /**
     * Storing user details in database
     * */
    public void addUser(int id, String table) {
    	SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("table_name", table);
        
        // Inserting Row
        Log.e("DB","Inserting Row in User");
        db.insert("User", null, values);
        db.close(); // Closing database connection
        
        this.id = id;
        this.table = table;
    }
    
    public void addStudent( int id, 
    						String first_name, 
				    		String last_name,
				    		int age,
				    		String dob,
				    		String email_id,
				    		String phone_no){
    	Log.e("DB","Inserting Row in Student");
    	SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("first_name", first_name);
        values.put("last_name", last_name);
        values.put("age", age);
        values.put("dob", dob);
        values.put("email_id", email_id);
        values.put("phone_no", phone_no);
        
        // Inserting Row
        db.insert("Student", null, values);
        db.close(); // Closing database connection
    }
    
    public void addParent( int id, 
							String first_name, 
				    		String last_name,
				    		String relation,
				    		String profession,
				    		String email_id,
				    		String phone_no,
				    		int no_of_children,
				    		String sec_password){
    	Log.e("DB","Inserting Row in Parent");
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("id", id);
		values.put("first_name", first_name);
		values.put("last_name", last_name);
		values.put("relation", relation);
		values.put("profession", profession);
		values.put("email_id", email_id);
		values.put("phone_no", phone_no);
		values.put("no_of_children", no_of_children);
		values.put("sec_password", sec_password);
		// Inserting Row
		db.insert("Parent", null, values);
		db.close(); // Closing database connection
	}
    
    public void addTeacher( int id, 
						String first_name, 
			    		String last_name,
			    		String qualification,
			    		String email_id,
			    		String phone_no){
    	Log.e("DB","Inserting Row in Teacher");
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("id", id);
		values.put("first_name", first_name);
		values.put("last_name", last_name);
		values.put("email_id", email_id);
		values.put("phone_no", phone_no);
		values.put("qualification", qualification);
		// Inserting Row
		db.insert("Teacher", null, values);
		db.close(); // Closing database connection
	}
    
    public HashMap<String, String> getSelfDetails(String relation){
    	String t = this.table;
    	int i = this.id;
    	if(t.equals("Student")){
    		return getStudentDetails(i);
    	}
    	else if(t.equals("Parent")){
    		return getParentDetails(i, relation);
    	}
    	else if(t.equals("Teacher")){
    		return getTeacherDetails(i);
    	}
    	else{
    		return null;
    	}
    }
    
    public HashMap<String, String> getStudentDetails(int id){
        HashMap<String,String> user = new HashMap<String,String>();
        String selectQuery = "SELECT * FROM Student WHERE id = " + id;
          
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
        	user.put("id", cursor.getString(0));
            user.put("first_name", cursor.getString(1));
            user.put("last_name", cursor.getString(2));
            user.put("age", cursor.getString(3));
            user.put("dob", cursor.getString(4));
            user.put("email_id", cursor.getString(5));
            user.put("phone_no", cursor.getString(6));
        }
        cursor.close();
        db.close();
        // return user
        return user;
    }
    
    public ArrayList<String> setParentTypes(){
    	ArrayList<String> parent_types = new ArrayList<String>();
    	String query = "SELECT relation FROM Parent";
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        
        while(cursor.moveToNext()){
        	if(cursor.getCount() > 0)
        		parent_types.add(cursor.getString(0));
        }
        cursor.close();
        db.close();
        return parent_types;
    }
    
    public ArrayList<String> getStudents(){
    	ArrayList<String> students = new ArrayList<String>();
    	String query = "SELECT first_name FROM Student";
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        
        while(cursor.moveToNext()){
        	if(cursor.getCount() > 0)
        		students.add(cursor.getString(0));
        }
        cursor.close();
        db.close();
        return students;
    }
    
    public String getStudentName(int i){
    	String name = null;
    	String query = "SELECT first_name FROM Student WHERE id = "+i;
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        
        while(cursor.moveToNext()){
        	if(cursor.getCount() > 0)
        		name = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return name;
    }
    
    public int getStudentId(String str){
    	String query = "SELECT id FROM Student WHERE first_name = '" + str + "'";
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int id = 1;
        while(cursor.moveToNext()){
        	if(cursor.getCount() > 0){
        		id = cursor.getInt(0);
        	}
        }
        cursor.close();
        db.close();
        return id;
    }
    
    public HashMap<String, String> getParentDetails(int id, String relation){
        HashMap<String,String> user = new HashMap<String,String>();
        String selectQuery = "SELECT  * FROM Parent WHERE id = " + id + " and relation = '" + relation + "'";
        
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
        	user.put("id", cursor.getString(0));
            user.put("first_name", cursor.getString(1));
            user.put("last_name", cursor.getString(2));
            user.put("relation", cursor.getString(3));
            user.put("profession", cursor.getString(4));
            user.put("email_id", cursor.getString(5));
            user.put("phone_no", cursor.getString(6));
            user.put("no_of_children", cursor.getString(7));
            user.put("sec_password", cursor.getString(8));
        }
        cursor.close();
        db.close();
        // return user
        return user;
    }
    
    public HashMap<String, String> getTeacherDetails(int id){
        HashMap<String,String> user = new HashMap<String,String>();
        String selectQuery = "SELECT  * FROM Teacher WHERE id = " + id;
          
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
        	user.put("id", cursor.getString(0));
            user.put("first_name", cursor.getString(1));
            user.put("last_name", cursor.getString(2));
            user.put("qualification", cursor.getString(3));
            user.put("email_id", cursor.getString(4));
            user.put("phone_no", cursor.getString(5));
        }
        cursor.close();
        db.close();
        // return user
        return user;
    }
 
    /**
     * Getting user login status
     * return true if rows are there in table
     * */
    public int getRowCount(String table_name) {
        String countQuery = "SELECT  * FROM " + table_name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();
         
        // return row count
        return rowCount;
    }
     
    /**
     * Re crate database
     * Delete all tables and create them again
     * */
    public void resetTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete("User", null, null);
        db.delete("Parent", null, null);
        db.delete("Teacher", null, null);
        db.delete("Student", null, null);
        db.close();
    }
 
}