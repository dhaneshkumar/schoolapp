package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

	private static String day;
	// Database Version
	private static final int DATABASE_VERSION = 2;

	// Database Name
	private static final String DATABASE_NAME = "schoolapp";
	public static int id;
	public static String table;

	public static String pid = "2";
	public static String currentSid = "1";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// list of tables having contents
	public final static String[] tableList = { "SchoolName", "Parent",
			"Student", "teacher", "TimeTable", "phoneList", "Class" };
	String[] tablesname;

	// final list of tables
	// public final static String[]
	// tableList={"SchoolName","Parent","Student","teacher","TimeTable","phoneList","Class","Event","Attendance","Medico","AcadHistory","Notifications","GradeAnalysis","TimeStampDetails"};

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		show("Creating db in sqlite---------------------");

		// Changes required if change occured in schemas : 1.-tableList 2>
		// no_of_tables 3> tablsnames

		// set no. of tables
		int no_of_tables = 15;

		tablesname = new String[no_of_tables];

		tablesname[0] = "CREATE TABLE  SchoolName  ( name  TEXT PRIMARY KEY)";
		tablesname[1] = "CREATE TABLE  Parent (  pid  INTEGER    NOT NULL ,  first_name  TEXT NOT NULL,  last_name  TEXT NOT NULL,   phone_no  TEXT ,  email_id  TEXT ,  profession  TEXT,  relation  TEXT, PRIMARY KEY( pid , relation ))";
		tablesname[2] = "CREATE TABLE  Login  ( username  TEXT PRIMARY KEY,  pid  INTEGER  NOT NULL,  password  TEXT NOT NULL,  type  CHAR(1) DEFAULT 'P',  no_child  INTEGER DEFAULT '1',  sec_password  TEXT,  conf_code  TEXT, FOREIGN KEY( pid ) REFERENCES  Parent ( pid )  )";
		tablesname[3] = "CREATE TABLE  Student ( sid  INTEGER  NOT NULL   PRIMARY KEY,  pid  INTEGER  NOT NULL, first_name  TEXT NOT NULL,  last_name  TEXT NOT NULL, roll  INTEGER NOT NULL,  class  TEXT NOT NULL,  section  TEXT NOT NULL,  dob  DATE,  phone_no  TEXT ,  email_id  TEXT,  address  TEXT,  achievements  TEXT,  INTEGERerests  TEXT, FOREIGN KEY( pid ) REFERENCES  Parent ( pid )   )";
		tablesname[4] = "CREATE TABLE  teacher  ( ID  INTEGER  NOT NULL  ,  NAME   TEXT NOT NULL,  SUBS  TEXT,  CLASSES  TEXT,  CONTACT  INTEGER,  EMAILID   TEXT, PRIMARY KEY (ID))";
		tablesname[5] = "CREATE TABLE  TimeTable (  class_no TEXT  NOT NULL,  section  TEXT NOT NULL,  day  CHAR(3) NOT NULL,  startTime  TEXT NOT NULL,  endTime  TEXT NOT NULL,   ID  INTEGER  ,  subject  TEXT , PRIMARY KEY( class_no ,  section ,  day ,  startTime ), FOREIGN KEY(ID) REFERENCES teacher (ID) )";
		tablesname[6] = "CREATE TABLE  phoneList  ( ID  INTEGER  NOT NULL  ,  NAME   TEXT NOT NULL,  POST  TEXT NOT NULL,  TAG  TEXT NOT NULL,  CON_PERSON  TEXT,  CONTACT  INTEGER,  EMAILID   TEXT, PRIMARY KEY (ID))";
		tablesname[7] = "Create TABLE  Class (  class_no TEXT  NOT NULL,  section  CHAR(2) NOT NULL,  subject  TEXT NOT NULL,  ID  INTEGER  ,  students  INTEGER  NOT NULL ,  classteacher  CHAR(1) DEFAULT 'N', PRIMARY KEY( class_no , section ,  subject ), FOREIGN KEY(ID) REFERENCES teacher (ID)  )";
		tablesname[8] = "CREATE TABLE  Event (  eid  INTEGER  NOT NULL   PRIMARY KEY,  title  TEXT NOT NULL,  description  TEXT,  start_time  DATETIME NOT NULL,  end_time  DATETIME ,  venue  TEXT,  special_guest  TEXT,  extra_details  TEXT)";
		tablesname[9] = "CREATE TABLE  Attendance (  sid  INTEGER  NOT NULL,  date  DATE NOT NULL,  status  CHAR(1) NOT NULL, PRIMARY KEY( sid , date ), FOREIGN KEY( sid ) REFERENCES  Student ( sid ))";
		tablesname[10] = "CREATE TABLE  Medico (  sid  INTEGER  NOT NULL,  blood_group  TEXT,  height  INTEGER ,  weight  INTEGER , eye_sight  TEXT,  pd  CHAR(3) DEFAULT 'No',  allergies  TEXT, injuries  TEXT, PRIMARY KEY( sid ), FOREIGN KEY( sid ) REFERENCES  Student ( sid ) )";
		tablesname[11] = "CREATE TABLE  AcadHistory  ( sid  INTEGER  NOT NULL,  class_no TEXT NOT NULL ,  subject  TEXT,  percentage  TEXT,  year  INTEGER,  school  TEXT NOT NULL,  board  TEXT, PRIMARY KEY(sid, class_no))";
		tablesname[12] = "CREATE TABLE  Notifications  ( nid  INTEGER  NOT NULL   PRIMARY KEY,  title  TEXT NOT NULL,  description  TEXT,  date  DATETIME NOT NULL)";
		tablesname[13] = "CREATE TABLE  GradeAnalysis  ( sid  INTEGER  NOT NULL PRIMARY KEY,  exam_type  TEXT,  subject  TEXT, marks TEXT,FOREIGN KEY( sid ) REFERENCES  Student ( sid ) )";
		tablesname[14] = "CREATE TABLE  TimeStampDetails (  table_name  TEXT,  time_stamp  TIMESTAMP,  flag  INTEGER, PRIMARY KEY( table_name ))";

		for (int i = 0; i < tablesname.length; i++) {
			db.execSQL(tablesname[i]);

		}

		show("table creation done in sqlite------------------------");
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed

		for (int i = 0; i < tableList.length; i++) {
			db.execSQL("DROP TABLE IF EXISTS " + tableList[i]);
		}

		/*
		 * db.execSQL("DROP TABLE IF EXISTS User");
		 * db.execSQL("DROP TABLE IF EXISTS MedicalRecord");
		 * db.execSQL("DROP TABLE IF EXISTS SubjectBookClass");
		 * db.execSQL("DROP TABLE IF EXISTS StudentParent");
		 * db.execSQL("DROP TABLE IF EXISTS StudentClass");
		 * db.execSQL("DROP TABLE IF EXISTS ClassTeacher");
		 * db.execSQL("DROP TABLE IF EXISTS Attendance");
		 * db.execSQL("DROP TABLE IF EXISTS Subject");
		 * db.execSQL("DROP TABLE IF EXISTS Event");
		 * db.execSQL("DROP TABLE IF EXISTS Book");
		 * db.execSQL("DROP TABLE IF EXISTS Class");
		 * db.execSQL("DROP TABLE IF EXISTS Teacher");
		 * db.execSQL("DROP TABLE IF EXISTS Parent");
		 * db.execSQL("DROP TABLE IF EXISTS Student");
		 */

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
		Log.e("DB", "Inserting Row in User");
		db.insert("User", null, values);
		db.close(); // Closing database connection

		this.id = id;
		this.table = table;
	}

	public void addStudent(int id, String first_name, String last_name,
			int age, String dob, String email_id, String phone_no) {
		Log.e("DB", "Inserting Row in Student");
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

	public void addParent(int id, String first_name, String last_name,
			String relation, String profession, String email_id,
			String phone_no, int no_of_children, String sec_password) {
		Log.e("DB", "Inserting Row in Parent");
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

	public void addTeacher(int id, String first_name, String last_name,
			String qualification, String email_id, String phone_no) {
		Log.e("DB", "Inserting Row in Teacher");
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

	// public HashMap<String, String> getSelfDetails(String relation){
	// String t = this.table;
	// int i = this.id;
	// if(t.equals("Student")){
	// return getStudentDetails(i);
	// }
	// else if(t.equals("Parent")){
	// return getParentDetails(i, relation);
	// }
	// else if(t.equals("Teacher")){
	// return getTeacherDetails(i);
	// }
	// else{
	// return null;
	// }
	// }

	public ArrayList<String> setParentTypes() {
		ArrayList<String> parent_types = new ArrayList<String>();
		String query = "SELECT relation FROM Parent";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);

		while (cursor.moveToNext()) {
			if (cursor.getCount() > 0)
				parent_types.add(cursor.getString(0));
		}
		cursor.close();
		db.close();
		return parent_types;
	}

	public String getStudentName(int i) {
		String name = null;
		String query = "SELECT first_name FROM Student WHERE id = " + i;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);

		while (cursor.moveToNext()) {
			if (cursor.getCount() > 0)
				name = cursor.getString(0);
		}
		cursor.close();
		db.close();
		return name;
	}

	public int getStudentId(String str) {
		String query = "SELECT id FROM Student WHERE first_name = '" + str
				+ "'";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		int id = 1;
		while (cursor.moveToNext()) {
			if (cursor.getCount() > 0) {
				id = cursor.getInt(0);
			}
		}
		cursor.close();
		db.close();
		return id;
	}

	public HashMap<String, String> getParentDetails(int id, String relation) {
		HashMap<String, String> user = new HashMap<String, String>();
		String selectQuery = "SELECT  * FROM Parent WHERE id = " + id
				+ " and relation = '" + relation + "'";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Move to first row
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
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

	public HashMap<String, String> getTeacherDetails(int id) {
		HashMap<String, String> user = new HashMap<String, String>();
		String selectQuery = "SELECT  * FROM Teacher WHERE id = " + id;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Move to first row
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
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
	 * Getting user login status return true if rows are there in table
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
	 * Re crate database Delete all tables and create them again
	 * */
	public void resetTables() {
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete("User", null, null);
		db.delete("Parent", null, null);
		db.delete("Teacher", null, null);
		db.delete("Student", null, null);
		db.close();
	}

/***********************************< STUDENT PROFILE >****************************************************/

	public HashMap<String, String> getStudentProfile() {
		HashMap<String, String> user = new HashMap<String, String>();
		
		show("current sid : " + currentSid);
		String selectQuery = "SELECT * FROM Student WHERE sid = " + currentSid;

		Cursor cursor = db.rawQuery(selectQuery, null);
		// Move to first row
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			user.put("class", cursor.getString(5));
			user.put("name", cursor.getString(2));
			user.put("last_name", cursor.getString(3));
			user.put("section", cursor.getString(6));
			user.put("address", cursor.getString(10));
		}
		cursor.close();

		//Retrieving school name
		selectQuery = "SELECT * FROM SchoolName ";
		cursor = db.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			user.put("schoolname", cursor.getString(0));
		}
		cursor.close();
		
		//Retrieving class-teacher & no. of students
		selectQuery = "SELECT * FROM class WHERE class_no =\""+ user.get("class")+"\" AND section =\""+ user.get("section")+"\" AND classteacher = \"Y\"";
		show(selectQuery);
		String teacherID="";
		cursor = db.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			user.put("noofstudents", cursor.getString(4));
			teacherID =cursor.getString(3);
		}
		cursor.close();
		show("class teacher id : --- " + teacherID);
		
		//retrieving teacher name
		selectQuery = "SELECT NAME FROM teacher WHERE ID ="+ teacherID;
		show(selectQuery);
		cursor = db.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			user.put("classteacher", cursor.getString(0));
			show(" classteacher name : --" + cursor.getString(0));
		}
		cursor.close();
		
		return user;
	}

	

	
/***********************************< PARENT PROFILE >****************************************************/

	public List<HashMap<String, String>> getParentProfile() {
		List<HashMap<String, String>> parentdetails = new ArrayList<HashMap<String, String>>();
		
		String selectQuery = "SELECT * FROM Parent WHERE pid = " + pid;
		
		Cursor cursor = db.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			HashMap<String, String> user = new HashMap<String, String>();
			//show("no of colulmns :  --  "+cursor.getColumnCount() +"   "+ cursor.getCount());
			if (cursor.getCount() > 0) {
				user.put("firstname", cursor.getString(1));
				user.put("lastname", cursor.getString(2));
				user.put("contactno", cursor.getString(3));
				user.put("emailid", cursor.getString(4));
				user.put("profession", cursor.getString(5));
				user.put("relation", cursor.getString(6));
			}

			cursor.moveToNext();
			parentdetails.add(user);
		}
		cursor.close();
		return parentdetails;
	}

	
	/***************************************************************************************/
	/* Timetable */
	/***************************************************************************************/

	UserFunctions uf = new UserFunctions();
	SQLiteDatabase db = this.getWritableDatabase();
	SQLiteDatabase db1 = this.getReadableDatabase();

	public void setTimeTable(String class_no, String section_no) {
		JSONObject json = uf.getTtFromServer("timetable", class_no, section_no);

		show("setting timetable sqlite database------------");

		String result1 = "";

		try {
			if (json.getString("success") != null) {
				int res = json.getInt("success");

				if (res == 1) {
					result1 = json.getString("response");

					System.out.println("got result from localhost:-- "
							+ result1);

					String[] store = result1.split("~");
					// System.out.println( "qqq" + "--- line----" );

					db.execSQL("DROP TABLE IF EXISTS timeTable");

					String TIMETABLE = "CREATE TABLE timeTable(class_no INTEGER, section TEXT, day TEXT, startTime  TEXT, endTime TEXT,  subject TEXT, teacher TEXT , PRIMARY KEY(class_no, section, day, startTime))";

					db.execSQL(TIMETABLE);

					// System.out.println( "www" + "--- line----" );
					for (int i = 0; i < store.length; i++) {
						// System.out.println( i + "--- line----" + store[i]);
						String[] input = store[i].split(",");

						ContentValues insertValues = new ContentValues();
						insertValues.put("class_no", input[0]);
						insertValues.put("section", input[1]);
						insertValues.put("day", input[2]);
						insertValues.put("startTime", input[3]);
						insertValues.put("endTime", input[4]);
						insertValues.put("subject", input[5]);
						insertValues.put("teacher", input[6]);
						db.insert("timeTable", null, insertValues);
					}
				}
			}
			System.out.println("data filled up in sqlite table------");
		} catch (JSONException e) {
			// Toast.makeText(getApplicationContext(),
			// "Error  null in retriving timetable ", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}

	}

	/************************************************************************************/

	// We need to store the id the student. From the id of student retrieve
	// class & section of student.
	public String getTimeTable(String day) {
		// HashMap<String,String> user = new HashMap<String,String>();
		// String selectQuery = "SELECT  * FROM Parent WHERE id = " + id +
		// " and relation = '" + relation + "'";
		String selectQuery = "SELECT * from timeTable where day = \"" + day
				+ "\"";

		show("retrieving data from timetable sqlite database----------------");
		// SQLiteDatabase db = this.getReadableDatabase();
		// show("cursor ------ check1");
		Cursor cursor = db1.rawQuery(selectQuery, null);
		// Move to first row
		String result = "";
		// show("cursor ------ check2");
		cursor.moveToFirst();

		// show("cursor ------ check3");

		while (!cursor.isAfterLast()) {
			// show("no of colulmns :  --  "+cursor.getColumnCount());

			if (cursor.getCount() > 0) {
				result += cursor.getString(3) + ",";
				result += cursor.getString(4) + ",";
				result += cursor.getString(5) + ",";
				result += cursor.getString(6);
				show(result);
			}

			cursor.moveToNext();
			result += "~";
		}

		show("data retrived");
		cursor.close();
		db.close();
		// return user
		return result;
	}

	/**************************************************************************************/
	/* TABLE-SETUP */
	/**************************************************************************************/

	/*
	 * fill contents in all sqlite data from server
	 */
	public void setup(String[] tablelist) {

		for (int i = 0; i < tablelist.length; i++) {
			show("table started : " + tablelist[i]);

			// deleting all tuples from table
			String deleteSQL = "DELETE FROM " + tablelist[i];
			db.execSQL(deleteSQL);
			// show("old table deleted : " + tablelist[i]);

			// loading new tuples from server
			JSONObject json = uf.getTables(tablelist[i]);
			show("setting timetable sqlite database------------");
			JSONArray result1;

			try {
				if (json.getString("success") != null) {
					int res = json.getInt("success");

					if (res == 1) {

						result1 = json.getJSONArray("response");
						// System.out.println("got result from localhost:-- " +
						// result1);

						for (int j = 0; j < result1.length(); j++) {
							JSONArray innerJsonArray = (JSONArray) result1
									.get(j);
							String temp = "INSERT INTO " + tablelist[i]
									+ " VALUES (";
							for (int k = 0; k < innerJsonArray.length() - 1; k++) {
								temp += innerJsonArray.get(k) + ",";
							}
							temp += innerJsonArray
									.get(innerJsonArray.length() - 1) + ")";

							db.execSQL(temp);
							// show("inserted");
						}
					}
				}
				System.out.println("data filled up in sqlite table--: "		+ tablelist[i] + "\n\n\n");
			} catch (JSONException e) {
				// Toast.makeText(getApplicationContext(),
				// "Error  null in retriving timetable ",
				// Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}
		}
		setDefaultID();
		show("setup done*****************************************************************");
	}

	//setting default student id & parent id
	public void setDefaultID()
	{
		String selectQuery = "SELECT * FROM Student";
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Move to first row
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			currentSid =cursor.getString(0);
			pid=cursor.getString(1);
		}
		cursor.close();
	}
	/***********************************< GET PHONELIST & TEACHER CONTACT >*************************************************/

	// We need to store the id the student. From the id of student retrieve
	// class & section of student.
	public String getPhoneList(String tag) {
		// HashMap<String,String> user = new HashMap<String,String>();
		// String selectQuery = "SELECT  * FROM Parent WHERE id = " + id +
		// " and relation = '" + relation + "'";
		String result = "";
		String selectQuery = "";
		if (tag.trim().equals("Teacher")) {
			selectQuery = "select teacher.ID,NAME, class.subject, SUBS, CLASSES, CONTACT, EMAILID  FROM class INNER JOIN teacher ON class.ID= teacher.ID";
			show(selectQuery);
		} else {

			selectQuery = "SELECT * from phoneList  where tag = \"" + tag
					+ "\"";

		}

		Cursor cursor = db1.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			if (cursor.getCount() > 0) {
				result += cursor.getString(0) + "~~";
				result += cursor.getString(1) + "~~";
				result += cursor.getString(2) + "~~";
				result += cursor.getString(3) + "~~";
				result += cursor.getString(4) + "~~";
				result += cursor.getString(5) + "~~";
				result += cursor.getString(6);
				show(result);
			}

			cursor.moveToNext();
			result += "###";
		}

		show("data retrived  ------:  " + result);
		cursor.close();

		db.close();
		return result;
	}

	void show(String st) {
		System.out.println(st);
	}

}