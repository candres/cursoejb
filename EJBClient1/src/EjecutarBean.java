import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejbs.ContadorBean;
import ejbs.ContadorBeanRemote;


public class EjecutarBean implements Runnable {

	private static final String PKG_INTERFACES = "org.jboss.ejb.client.naming";
	private static Context initialContext;
	
	public static void main(String[] args) {
		EjecutarBean obj = new EjecutarBean();
		 for(int i = 0 ; i < 10; i++) {
            Thread t1 = new Thread(obj, "Hilo " + i);
            t1.start();
        }
	}
	
	public void run() { 
		ContadorBeanRemote bean = doLookup();
		try { // Added try
			bean.setName(Thread.currentThread().getName());
			System.out.println("Valor inicial hilo "+bean.getName()+" -> "+bean.getCount());
			bean.addCount();
			System.out.println("Valor final: "+bean.getName()+" -> "+bean.getCount());
		} finally { // Added finally
			if (bean != null) { // Added null check
				try {
					bean.remove(); // Call remove
					System.out.println("Bean removed for hilo "+Thread.currentThread().getName());
				} catch (Exception e) {
					// Log or handle exception during remove if necessary
					System.err.println("Error removing bean for hilo "+Thread.currentThread().getName()+": " + e.getMessage());
				}
			}
		}
	}

	 private static ContadorBeanRemote doLookup() {
        Context context = null;
        ContadorBeanRemote bean = null;
        try {
            context = getInitialContext(); 
            String lookupName = getLookupName();
            bean = (ContadorBeanRemote) context.lookup(lookupName);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return bean;
    }
	
	private static String getLookupName() {
		String appName = "";
		String moduleName = "EJBTest1";
		String distinctName = "";
		String beanName = ContadorBean.class.getSimpleName();
		final String interfaceName = ContadorBeanRemote.class.getName();
		String name = "ejb:" + appName + "/" + moduleName + "/" +
	            distinctName    + "/" + beanName + "!" + interfaceName;
		return name;
	}
	
	// Added synchronized
	public static synchronized Context getInitialContext() throws NamingException { 
        if (initialContext == null) {
            Properties properties = new Properties();
            properties.put(Context.URL_PKG_PREFIXES, PKG_INTERFACES);
            initialContext = new InitialContext(properties);
        }
        return initialContext;
    }
}
