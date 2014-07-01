package webchat;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {

	@Test
	public void beforeTest() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-mvc.xml", "spring-context.xml");
		System.out.println(context.getBean("cbus"));
		
	}
}
