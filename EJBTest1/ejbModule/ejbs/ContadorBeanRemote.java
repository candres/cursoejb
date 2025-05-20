package ejbs;

import javax.ejb.Remote;

@Remote
public interface ContadorBeanRemote {
	
	public int getCount();
	
	public void addCount();
	
	public void setName(String name);
	
	public String getName();
	public void remove(); // Added this line
}
