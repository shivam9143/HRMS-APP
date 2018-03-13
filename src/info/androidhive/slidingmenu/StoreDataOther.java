package info.androidhive.slidingmenu;


import android.R.string;

public class StoreDataOther {
	
	private  String task;
	private String projname;
	private String act_type;
	public  String act_date;
	private  String start_date;	
	public  String end_date;	
	public String work_hrs;
	public String comp_stats;
	public String respp;
	
	
	

	public StoreDataOther(String task1, String proj,String type,String date,String s,String e,String w,String c,String re) {
		this.task = task1;
		this.projname = proj;
		this.act_type=type;
		this.act_date=date;
		this.start_date=s;
		this.end_date=e;
		this.work_hrs=w;
		this.comp_stats=c;
		this.respp=re;
		
	}
		
	
	public void StoreProject(String ty,String Name) 
	{
		this.task=ty;
		this.projname=Name;
		
	}
	
	public String gettask() 
	{
		return this.task;
	}
	public String getprojname() {
		return this.projname;
	}

	public String getacttype() {
		return this.act_type;
	}

	public String getActDate() {
		return this.act_date;
	}
	
	public String getStarDate() {
		return this.start_date;
	}
	
	public String getEndDate() {
		return this.end_date;
	}
	
	public String getWrkHrs() {
		return this.work_hrs;
	}
	
	public String getStatus() {
		return this.comp_stats;
	}
	
	public String getRespp() {
		return this.respp;
	}
	

}

