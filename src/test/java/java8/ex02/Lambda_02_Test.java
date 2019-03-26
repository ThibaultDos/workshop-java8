package java8.ex02;

import java8.data.Account;
import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 02 - Map
 */
public class Lambda_02_Test {

    // tag::PersonToAccountMapper[]
    interface PersonToAccountMapper {
        Account map(Person p);
    }
    // end::PersonToAccountMapper[]

    // tag::map[]
    private List<Account> map(List<Person> personList, PersonToAccountMapper mapper) {
        // TODO implémenter la méthode
    	List<Account> accounts = new ArrayList<>();
    	for(Person person : personList){
		accounts.add(mapper.map(person));
    	}
    	return accounts;
    }      
    
    // end::map[]
    // tag::test_map_person_to_account[]
    
    @Test
    public void test_map_person_to_account() throws Exception {

        List<Person> personList = Data.buildPersonList(100);

        // TODO transformer la liste de personnes en liste de comptes
        PersonToAccountMapper mapper = new PersonToAccountMapper() {
			
			@Override
			public Account map(Person p) {
				// TODO Auto-generated method stu
				Account aka = new Account();
				aka.setBalance(100);
				aka.setOwner(p);
				return aka;
			}
        };
		// TODO tous les objets comptes ont un solde à 100 par défaut
        List<Account> result = map(personList, mapper);
        
        	

        assertThat(result, hasSize(personList.size()));
        assertThat(result, everyItem(hasProperty("balance", is(100))));
        assertThat(result, everyItem(hasProperty("owner", notNullValue())));
    }
    // end::test_map_person_to_account[]

    // tag::test_map_person_to_firstname[]
    @Test
    public void test_map_person_to_firstname() throws Exception {

        List<Person> personList = Data.buildPersonList(100);

        // TODO transformer la liste de personnes en liste de prénoms
        List<String> result = personList.stream().map((p) -> p.getFirstname()).collect(Collectors.toList());

        assertThat(result, hasSize(personList.size()));
        assertThat(result, everyItem(instanceOf(String.class)));
        assertThat(result, everyItem(startsWith("first")));
    }
    // end::test_map_person_to_firstname[]
}
