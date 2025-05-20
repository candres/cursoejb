package ejbs;

import javax.ejb.Stateful;
import javax.ejb.Remove; // Added

@Stateful
public class ContadorBean implements ContadorBeanRemote {

	private int count;	
	private String name;
	
    public ContadorBean() {
        count = 0;
    }
    
	@Override
	public void addCount() { 
		count++;
	}
	
	@Override
	public int getCount() {
		return count;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Remove // Added
	public void remove() { // Added
		// This method can be empty. Its purpose is to signal the container
		// that this instance can be discarded.
	}
}
