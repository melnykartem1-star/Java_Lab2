package EasyTask.test;

import EasyTask.main.Person;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class PersonTest {

    @Test
    public void simpleEqualsContract() {
        EqualsVerifier.simple().forClass(Person.class).verify();
    }
}
