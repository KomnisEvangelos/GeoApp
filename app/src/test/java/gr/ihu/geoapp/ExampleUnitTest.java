package gr.ihu.geoapp;

import org.junit.Test;

import static org.junit.Assert.*;

import gr.ihu.geoapp.managers.Repository;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testCreateUser() {
        Repository repository = new Repository();
        String testEmail = "test@example.com";
        String testPassword = "testpassword";

        System.out.println(repository.createUser(testEmail, testPassword).getResult());
        assertNotNull(repository.createUser(testEmail, testPassword).getResult());
    }

    @Test
    public void testCheckUser() {
        Repository repository = new Repository();
        String testEmail = "test@example.com";
        String testPassword = "testpassword";

        System.out.println(repository.checkUser(testEmail, testPassword).getResult());
        assertNotNull(repository.checkUser(testEmail, testPassword).getResult());
    }
}