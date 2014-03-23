package library;

import com.example.schoolapp.*;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {
			return new Timetable(index);
			//----------------change it for timetable-----------------------------------
		//return new Fragment();
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 6;
	}

}
