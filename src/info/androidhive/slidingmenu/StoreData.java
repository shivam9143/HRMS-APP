package info.androidhive.slidingmenu;
public class StoreData {
	
	private  String task;
	private String respp;
	private String assign_by;
	public  String act_date;
	private  String start_date;	
	public  String end_date;	
	public String work_hrs;
	public String comp_stats;
	
	
	

	public StoreData(String task1, String re,String assign,String date,String s,String e,String w,String c) {
		this.task = task1;
		this.respp = re;
		this.assign_by=assign;
		this.act_date=date;
		this.start_date=s;
		this.end_date=e;
		this.work_hrs=w;
		this.comp_stats=c;
		
	}
	
	
	public void StoreProject(String ty,String Name) 
	{
		this.task=ty;
		this.assign_by=Name;
		
	}
	
	public String gettask() 
	{
		return this.task;
	}
	public String getresp_p() {
		return this.respp;
	}

	public String getassign() {
		return this.assign_by;
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

}

