package info.androidhive.slidingmenu.model;





import info.androidhive.slidingmenu.HomeFragment;
import info.androidhive.slidingmenu.MainActivity;

public class Caller extends Thread
{

	public CallSoap cs;
  
  public String empid;
  public String sessnid;
  public String instid;
  public String userid;
  public String machineNo;
  public String SystemIP;
  public String networkip;
  public String hostname;
  public String latitute;
  public String longtitute;
  public String loc;
    public void run(){
        try{
            cs=new CallSoap();
           String resp=cs.Call(empid,sessnid,instid,userid,machineNo,SystemIP,networkip,hostname,latitute,longtitute,loc);
            HomeFragment.rslt=resp;
            MainActivity.rslt=resp;
        }
        catch(Exception ex)
        {
        	HomeFragment.rslt=ex.toString();
            MainActivity.rslt=ex.getMessage().toString();

        }    
    }
	
}
