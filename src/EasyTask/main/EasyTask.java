import EasyTask.main.Person;
import tools.jackson.databind.ObjectMapper;
import nl.jqno.equalsverifier.*;

void main() {
    Person person = new Person("Артем", "Мельник", 19);

    ObjectMapper mapper = new ObjectMapper();
    String jsonOutput = mapper.writeValueAsString(person);
    IO.println(jsonOutput);

    Person jsonPerson = mapper.readValue(jsonOutput, Person.class);
    IO.println(person.equals(jsonPerson));

}
