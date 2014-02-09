package spinner;

public class StudentSpinner {
	 
    private String title;
    private int icon;
     
    public StudentSpinner(String title, int icon){
        this.title = title;
        this.icon = icon;
    }
     
    public String getTitle(){
        return this.title;     
    }
     
    public int getIcon(){
        return this.icon;
    }
}