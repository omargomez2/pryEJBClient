package net.osgg;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import net.osgg.ConverterBeanRemote;


public class App {
    public static void main( String[] args ) throws Exception {
    	ConverterBeanRemote converter = lookupHelloEJB();
    	int grados = 10;
		System.out.println("Dame un saludo: " + converter.saludar());
		System.out.println("Convierte "+grados+" grados C: "+converter.convertToFahrenheit(grados)+"F");
    }
    
    
	private static ConverterBeanRemote lookupHelloEJB() throws NamingException {
		final Hashtable<String, String> jndiProperties = new Hashtable<>();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        //use HTTP upgrade, an initial upgrade requests is sent to upgrade to the remoting protocol
        jndiProperties.put(Context.PROVIDER_URL,"remote+http://localhost:8080");
		//jndiProperties.put(Context.PROVIDER_URL,"remote+http://192.168.0.104:8080");
        final Context context = new InitialContext(jndiProperties);

		return (ConverterBeanRemote) context.lookup("ejb:/pryEJBConv/ConverterBean!net.osgg.ConverterBeanRemote");
	}
}
