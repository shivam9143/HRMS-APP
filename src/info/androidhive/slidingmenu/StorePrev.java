package info.androidhive.slidingmenu;

import android.util.Log;

public class StorePrev {
	private  String leave;
	private String leavetype;
	private String appno;
	private  String fromd;
	private  String tod;	
	private  String appsts;

	public StorePrev(String le, String let,String apno,String fmd,String td,String appst) {
		
		this.leave = le;
		Log.e("LEAVE ",leave);

		this.leavetype=let;
		Log.e("LEAVE type", leavetype);

		this.appno=apno;
		Log.e("App no", appno);

		this.fromd=fmd;
		Log.e("DATE", fromd);

		this.tod=td;
		Log.e("HFTYPE", tod);

		this.appsts=appst;
		Log.e("Status", appst);


	}
	
	
//	public void StoreProject(String ty,String Name) 
//	{
//		this.task=ty;
//		this.assign_by=Name;
//		
//	}


	public String getLeave() {
		return this.leave;
	}



	public String getLeavetype() {
		return this.leavetype;
	}


	public String getAppno() {
		return this.appno;
	}



	public String getFromd() {
		return this.fromd;
	}

	public String getTod() {
		return this.tod;
	}

	public String getAppsts() {
		return this.appsts;
	}



}
