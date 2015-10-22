package ba.bitcamp.ognjencetkovic.people;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ognjen.cetkovic on 21/10/15.
 */
public class People {

    private static People sPeople;
    private List<Person> mPersons;

    public static People get() {
        if (sPeople == null) {
            sPeople = new People();
        }
        return sPeople;
    }

    private People() {
        mPersons = new ArrayList<>();
    }

    public void add(Person person) {
        mPersons.add(person);
    }

    public List<Person> getPersons() {
        return mPersons;
    }

    public void delete(Person person) {
        mPersons.remove(person);
    }

}
