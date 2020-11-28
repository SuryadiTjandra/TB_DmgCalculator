package spring;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableHypermediaSupport(type = {HypermediaType.HAL})
public class TerraBattleDamageCalculator {
	public static void main(String[] args) {
		SpringApplication.run(TerraBattleDamageCalculator.class);
	}
}
