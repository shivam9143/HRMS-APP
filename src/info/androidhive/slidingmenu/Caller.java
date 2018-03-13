package info.androidhive.slidingmenu;

import org.json.JSONObject;

import info.androidhive.slidingmenu.CallSoap;



public class Caller extends Thread{
	public CallSoap cs;
	  public  JSONObject json;
	 

	    public void run(){
	        try{
	            cs=new CallSoap();
	            String resp=cs.Call(json);
	            ApproveLeave.rslt=resp;
	        }catch(Exception ex)
	        {ApproveLeave.rslt=ex.toString();}    
	        
	    }
}
