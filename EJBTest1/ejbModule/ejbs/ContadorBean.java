package ejbs;

import javax.ejb.Stateless;

@Stateless
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
}
