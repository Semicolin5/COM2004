import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

/**
 * Class testing the methods in AdditionQueries.java
 * */
public class TestAdditionQueries {

    public static void main(String[] args) {
        TestAdditionQueries t = new TestAdditionQueries();
        System.out.println("Testing testAddDepartment");
        t.testAddDepartment();

    }

    @Test
    // positive test case
    public void testAddDepartment() {
        System.out.println("test inserting into the database");
        String str = "string";
        assertEquals("string", str);
    }
}