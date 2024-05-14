
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class codeSuite {

// Internal method to test
public String categorizeUser(char userType) {
    if (userType == '1') {
        return "community";
    } else if (userType == '2') {
        return "encost-unverified";
    } else {
        return "invalid";
    }
}

@Test
public void testCommunityUser() {
    assertEquals("community", categorizeUser('1'));
}

@Test
public void testEncostUser() {
    assertEquals("encost-unverified", categorizeUser('2'));
}

@Test
public void testInvalidInput() {
    assertEquals("invalid", categorizeUser(' ')); // Empty char
    assertEquals("invalid", categorizeUser('9')); // Invalid char '9'
    assertEquals("invalid", categorizeUser('a')); // Invalid char 'a'
}


    
}



