package ba.bitcamp.ognjencetkovic.people;

import java.util.Calendar;
import java.util.UUID;

/**
 * Created by ognjen.cetkovic on 21/10/15.
 */
public class Person {

    private UUID mId;
    private String mFirstName;
    private String mLastName;
    private Calendar mCreated;

    public Person(String firstName, String lastName) {
        mId = UUID.randomUUID();
        mFirstName = firstName;
        mLastName = lastName;
        mCreated = Calendar.getInstance();
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public UUID getId() {
        return mId;
    }

    public Calendar getCreated() {
        return mCreated;
    }
}
