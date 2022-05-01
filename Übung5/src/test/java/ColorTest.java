import client.Client;
import static org.junit.Assert.*;
import org.junit.Test;


/**
 * Tests are not required for this exercise, we still decided to implement Tests. Why? - BeCaUsE tEsTInG yOUR sOfTwArE iS ImpORTanT!
 * Server needs to run, for tests to work
 */
public class ColorTest {


    @Test
    public void checkColorRed() {
        Client.main(new String[]{"src/main/resources/images/red.png"});
        // asserts if the diagnosis for the red picture is equal to the expected diagnosis for red
        assertEquals("Very bad", Client.getDiagnosis());
    }
    @Test
    public void checkColorGreen() {
        Client.main(new String[]{"src/main/resources/images/green.png"});
        // asserts if the diagnosis for the green picture is equal to the expected diagnosis for green
        assertEquals( "Concerning signs", Client.getDiagnosis());
    }
    @Test
    public void checkColorBlue() {
        Client.main(new String[]{"src/main/resources/images/blue.png"});
        // asserts if the diagnosis for the blue picture is equal to the expected diagnosis for blue
        assertEquals("Everything is fine", Client.getDiagnosis());
    }


}
