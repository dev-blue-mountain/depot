
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloWorldJunit4Test {

    @Test
    public void getHello() {
        String greetings = "Hello From Junit 4";
	System.out.println("message =" + greetings);
        assertEquals("Hello From Junit 4", greetings);
    }
}
