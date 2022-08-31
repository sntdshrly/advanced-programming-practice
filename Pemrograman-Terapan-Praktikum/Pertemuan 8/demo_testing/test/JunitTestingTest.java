import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JunitTestingTest {

    private JunitTesting junitTesting;

    @Before
    public void setUp() throws Exception {
        junitTesting = new JunitTesting();
    }

    @Test
    public void square() {
        int output = junitTesting.square(5);
        assertEquals(25,output);
    }

    @Test
    public void countA() {
        int output = junitTesting.countA("sherly santiadi");
        assertEquals(2,output);
    }
}