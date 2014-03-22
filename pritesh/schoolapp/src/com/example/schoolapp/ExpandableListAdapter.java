package com.example.schoolapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _h_quiz_type; // header titles
    private List<String> _h_quiz_no;
    private List<String> _h_quiz_subject;
    private List<String> _h_quiz_marks; 
    // child data in format of header title, child title
    private HashMap<String, List<String>> _c_chapter;
    private HashMap<String, List<String>> _c_rank;
    private HashMap<String, List<String>> _c_remark;
    
	public ExpandableListAdapter(Context context, List<String> h_quiz_type, List<String> _h_quiz_no , List<String> _h_quiz_subject, List<String> _h_quiz_marks, HashMap<String, List<String>> c_chapter,HashMap<String, List<String>> c_rank,HashMap<String, List<String>> c_remark) {
		// TODO Auto-generated constructor stub
		this._context = context;
        this._c_chapter = c_chapter;
        this._c_rank = c_rank;
        this._c_remark=c_remark;
        this._h_quiz_type=h_quiz_type;
        this._h_quiz_no=_h_quiz_no;
        this._h_quiz_subject=_h_quiz_subject;
        this._h_quiz_marks=_h_quiz_marks;
        System.out.println("check123");
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		 return this._h_quiz_type.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return this._c_rank.get(this._h_quiz_type.get(groupPosition))
                .size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
	//	 this._h_quiz_type=h_quiz_type;
	  //      this._h_quiz_no=_h_quiz_no;
	    //    this._h_quiz_subject=_h_quiz_subject;
	      //  this._h_quiz_marks=_h_quiz_marks;
		ArrayList<String> group_header =new ArrayList<String>();
		group_header.add(_h_quiz_type.get(groupPosition));
		group_header.add(_h_quiz_no.get(groupPosition));
		group_header.add(_h_quiz_subject.get(groupPosition));
		group_header.add(_h_quiz_marks.get(groupPosition));
		return group_header;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		// this._c_chapter = c_chapter;
	      //  this._c_rank = c_rank;
	       // this._c_remark=c_remark;
		ArrayList<String> group_child =new ArrayList<String>();
		
		group_child.add((this._c_chapter.get(this._h_quiz_type.get(groupPosition))).get(childPosition));
		group_child.add((this._c_rank.get(this._h_quiz_type.get(groupPosition))).get(childPosition));
		group_child.add((this._c_remark.get(this._h_quiz_type.get(groupPosition))).get(childPosition));
		
		return group_child;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		 ArrayList <String> headerTitle = (ArrayList<String>) getGroup(groupPosition);
	        if (convertView == null) {
	            LayoutInflater infalInflater = (LayoutInflater) this._context
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            System.out.println("check");
	            convertView = infalInflater.inflate(R.layout.report_card_header, null);
	            System.out.println("check 2");

	        }
	 
	        TextView quiz_type = (TextView) convertView
	                .findViewById(R.id.quiz_type_txt);
	        TextView quiz_no = (TextView) convertView
	                .findViewById(R.id.quiz_no_txt);
	        TextView subject = (TextView) convertView
	                .findViewById(R.id.subject_txt);
	        TextView marks = (TextView) convertView
	                .findViewById(R.id.marks_txt);
	       
	       quiz_type.setText(headerTitle.get(0));
	       quiz_no.setText(headerTitle.get(1));
	       subject.setText(headerTitle.get(2));
	       marks.setText(headerTitle.get(3));
	        return convertView;
		
	}


	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		final ArrayList<String> childText =  (ArrayList<String>) getChild(groupPosition, childPosition);
		  
	        if (convertView == null) {
	            LayoutInflater infalInflater = (LayoutInflater) this._context
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            convertView = infalInflater.inflate(R.layout.report_card_2, null);
	        }
	        TextView txtListChild = (TextView) convertView
	                .findViewById(R.id.chapter_txt);
	        TextView txtListChild2 = (TextView) convertView
	                .findViewById(R.id.rank_txt);
	        TextView txtListChild3 = (TextView) convertView
	                .findViewById(R.id.remarks_txt);
	        txtListChild.setText(childText.get(0));
	        txtListChild2.setText(childText.get(1));
	        txtListChild3.setText(childText.get(2));
	        return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}
