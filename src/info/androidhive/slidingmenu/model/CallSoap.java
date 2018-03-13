package info.androidhive.slidingmenu.model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope; 
import org.ksoap2.serialization.PropertyInfo; 
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.R.string;
import android.util.Log;


public class CallSoap 
{
	public final String SOAP_ACTION = "http://tempuri.org/insertAttendance";

	public  final String OPERATION_NAME = "insertAttendance"; 

	public  final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
	
	SoapPrimitive resultString;
	JSONArray obj ;

	public  final String SOAP_ADDRESS = "http://203.158.91.19:90/calender_details.asmx";
	public CallSoap() 
	{ 
	}
	public String Call(String empid,String sesnid,String instid,String userid,String machineNo,String systemid,String networkip,String hostname,String latitute,String longtitute,String loc)
	{
		Log.e("111111111111", "1111111111111111111");
	SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
	PropertyInfo pi=new PropertyInfo();
	        pi.setName("empid");
	        pi.setValue(empid);
	        pi.setType(String.class);
	        request.addProperty(pi);
	       
	        pi=new PropertyInfo();	           
	        pi.setName("sesnID");
	        pi.setValue(sesnid);
	        pi.setType(String.class);
	        request.addProperty(pi);
	        
	        pi=new PropertyInfo();
	        pi.setName("instID");
	        pi.setValue(instid);
	        pi.setType(Integer.class);
	        request.addProperty(pi);
	        
	        pi=new PropertyInfo();
	        pi.setName("Userid");
	        pi.setValue(userid);
	        pi.setType(String.class);
	        request.addProperty(pi);
	        
	        pi=new PropertyInfo();
	        pi.setName("MachineNo");
	        pi.setValue(machineNo);
	        pi.setType(String.class);
	        request.addProperty(pi);
	        
	        pi=new PropertyInfo();
	        pi.setName("SystemIp");
	        pi.setValue(systemid);
	        pi.setType(String.class);
	        request.addProperty(pi);
	        
	        pi=new PropertyInfo();
	        pi.setName("NetworkIp");
	        pi.setValue(networkip);
	        pi.setType(String.class);
	        request.addProperty(pi);
	        
	        pi=new PropertyInfo();
	        pi.setName("HostName");
	        pi.setValue(hostname);
	        pi.setType(String.class);
	        request.addProperty(pi);
	        
	        pi=new PropertyInfo();
	        pi.setName("Latitude");
	        pi.setValue(latitute);
	        pi.setType(String.class);
	        request.addProperty(pi);
	        
	        pi=new PropertyInfo();
	        pi.setName("Longitude");
	        pi.setValue(longtitute);
	        pi.setType(String.class);	        
	        request.addProperty(pi);
	        
	        pi=new PropertyInfo();
	        pi.setName("Location");
	        pi.setValue(loc);
	        pi.setType(String.class);	        
	        request.addProperty(pi);
	       
	       

	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
	SoapEnvelope.VER11);
	envelope.dotNet = true;
	
	envelope.setOutputSoapObject(request);

	HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
	httpTransport.debug = true;
	String responseJSON="";
	
	try
	{
		
    Log.e("111111111111", "4545466");
	httpTransport.call(SOAP_ACTION, envelope);
	Log.e("11111111111122222", "4545466");
	SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
	
	 responseJSON=response.toString();
	

     Log.e("response", "response=" + response.toString());
	
	}
	catch (Exception exception)
	{
		Log.e("CallSoapError", exception.getMessage().toString());
	}
	
	return responseJSON;
	}
}
