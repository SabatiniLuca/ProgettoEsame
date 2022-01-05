package univpm.OpenWeather;


import java.net.MalformedURLException;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication//(scanBasePackages= {"univpm.OpenWeather.Service"})
public class Application extends SpringBootServletInitializer {
	
	
	public static void main(String[] args) throws MalformedURLException {
		SpringApplication.run(Application.class, args);
		
		
	}

}
