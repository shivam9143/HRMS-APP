package info.androidhive.slidingmenu;

public class Project 
{
	
	public  Integer Projid;	
	public String  ProjName;	
	public  Project(Integer id,String Name) 
	{
		this.Projid=id;
		this.ProjName=Name;
		
	}
	
	 public int getId () {
	        return Projid;
	    }

	    public String getValue () {
	        return ProjName;
	    }

	    @Override
	    public String toString () {
	        return ProjName;
	    }

}
