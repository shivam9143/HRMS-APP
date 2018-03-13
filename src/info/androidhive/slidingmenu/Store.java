package info.androidhive.slidingmenu;

import android.util.Log;

public class Store {
	private  String leave;
	private String leavetype;
	private String appno;
	private  String fromd;
	private  String tod;	
	private  String days	;	
	private String repauth;
	private String levauth;
	private  String appsts;
	private  String appby;	
	private  String rsn	;	
	private String hftype;
	private String add;
	private String docsub;

	public Store(String le, String let,String apno,String fmd,String td,String dys,String rep,String leva,String appst, String apby,String rn,String hft,String ad,String docs) {
		
		this.leave = le;
		Log.e("LEAVE ",leave);

		this.leavetype=let;
		Log.e("LEAVE type", leavetype);

		this.appno=apno;
		Log.e("App no", appno);

		this.fromd=fmd;
		Log.e("fromd", fromd);

		this.tod=td;
		Log.e("tod", tod);

		this.days=dys;
		Log.e("days", days);

		this.repauth=rep;
		Log.e("repauth", repauth);

		this.levauth=leva;
		Log.e("levauth",levauth);

		Log.e("LEAVE AUTHHHHHHHHH", levauth);
		this.appsts=appst;
		Log.e("appsts",appsts);

		this.appby=apby;
		Log.e("appby", appby);

		this.rsn=rn;
		Log.e("rsn", rsn);

		this.hftype=hft;
		Log.e("hdt", hftype);

		this.add=ad;
		Log.e("addres", add);

		this.docsub=docs;	
		Log.e("doc", docsub);

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



	public String getDays() {
		return this.days;
	}

	public String getRepauth() {
		return this.repauth;
	}

	public String getLevauth() {
		return this.levauth;
	}

	public String getAppsts() {
		return this.appsts;
	}


	public String getAppby() {
		return appby;
	}


	public String getRsn() {
		return this.rsn;
	}


	public String getHftype() {
		return this.hftype;
	}


	public String getAdd() {
		return this.add;
	}

	public String getDocsub() {
		return this.docsub;
	}


}
