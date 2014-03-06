package trumplab.teacherapp;

import nav_drawer.commonDrawer;
import android.annotation.SuppressLint;
import android.os.Bundle;

@SuppressLint("NewApi")
public class Home extends commonDrawer{
	
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
	}
}
