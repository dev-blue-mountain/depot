import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloWorldJunit5Test {

    @Test
    void getHello() {
        String greetings = "Hello";
	System.out.println("message=" + greetings);
        assertEquals("Hello", greetings);
    }
}
