package info.androidhive.slidingmenu;

public class Task 
{
	public  Integer Taskid;	
	public String  TaskName;	
	public  Task(Integer id,String Name) 
	{
		this.Taskid=id;
		this.TaskName=Name;
		
	}
	
	 public int getId () {
	        return Taskid;
	    }

	    public String getValue () {
	        return TaskName;
	    }

	    @Override
	    public String toString () {
	        return TaskName;
	    }
	    
}
