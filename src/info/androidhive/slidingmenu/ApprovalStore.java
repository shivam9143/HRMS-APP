package info.androidhive.slidingmenu;

import android.util.Log;

public class ApprovalStore {
	private String empname;
	private  String leave;
	private String leavetype;
	private String appno;
	private  String fromd;
	private  String tod;	
	private  String days;
	
	private  String appby;	
	private String hftype;
	private String appdate;
private  String empid;
	
	private  String empcode;	
	private String Type;
	private String LeaveHead;
	private Boolean status;
	private String approve;
	private String reason;
	public ApprovalStore(String name,String le, String let,String apno,String fmd,String td,String dys, String apby,String hft,String appdate,String eid,String ecode,String typ,String LevHead,Boolean status,String appvd,String rsn) {
		
		this.empname=name;
		Log.e("Name ",empname);

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

	

		this.appby=apby;
		Log.e("appby", appby);


		this.hftype=hft;
		Log.e("hdt", hftype);
		
		this.appdate=appdate;
		Log.e("App DAte ",this.appdate);
		
		this.empid=eid;
		Log.e("EmpID",this.empid);

		this.empcode=ecode;
		Log.e("EmpCode",this.empcode);

		this.Type=typ;
		Log.e("Type",this.Type);

		this.LeaveHead=LevHead;
		Log.e("LeaveHead ",this.LeaveHead);

		this.status=status;
		Log.e("Status ",this.status.toString());
		
		this.approve=appvd;
		Log.e("ApproveStatus", approve);
		
		this.reason=rsn;
		Log.e("Reason", reason);

	}	
	
	



	public String getEmpname() {
		return this.empname;
	}

	public String getAppdate() {
		return this.appdate;
	}

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


	public String getAppby() {
		return this.appby;
	}

	public String getHftype() {
		return this.hftype;
	}


	public String getEmpid() {
		return this.empid;
	}


	public String getEmpcode() {
		return this.empcode;
	}


	public String getType() {
		return this.Type;
	}



	public String getLeaveHead() {
		return this.LeaveHead;
	}





	public Boolean getStatus() {
		return status;
	}





	public void setStatus(Boolean status) {
		this.status = status;
	}





	public String getApprove() {
		return approve;
	}





	public void setApprove(String approve) {
		this.approve = approve;
	}





	public String getReason() {
		return reason;
	}





	public void setReason(String reason) {
		this.reason = reason;
	}









}
