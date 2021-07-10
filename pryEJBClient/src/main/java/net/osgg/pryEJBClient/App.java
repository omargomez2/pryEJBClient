package net.osgg.pryEJBClient;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import net.osgg.ConverterBeanRemote;


public class App {
    public static void main( String[] args ) throws Exception {
    	ConverterBeanRemote converter = lookupHelloEJB();
		System.out.println("Dame un saludo: " + converter.saludar());
		System.out.println("Convierte 20 grados C a F: " + converter.convertToFahrenheit(20));
    }
    
    
	private static ConverterBeanRemote lookupHelloEJB() throws NamingException {
		final Hashtable<String, String> jndiProperties = new Hashtable<>();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        //use HTTP upgrade, an initial upgrade requests is sent to upgrade to the remoting protocol
        jndiProperties.put(Context.PROVIDER_URL,"remote+http://localhost:8080");
        final Context context = new InitialContext(jndiProperties);

		return (ConverterBeanRemote) context.lookup("ejb:/pryEJBConv/CoverterBean!net.osgg.ConverterBeanRemote");
	}
}
