package net.osgg;

import javax.ejb.Remote;

@Remote
public interface ConverterBeanRemote {
	 public float convertToFahrenheit(int celsius);
	 public String saludar();
}