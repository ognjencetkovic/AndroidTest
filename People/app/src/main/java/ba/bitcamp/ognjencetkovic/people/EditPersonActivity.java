package ba.bitcamp.ognjencetkovic.people;

import android.app.Activity;
import android.os.Bundle;

import java.util.UUID;

/**
 * Created by ognje on 24-Oct-15.
 */
public class EditPersonActivity extends Activity {

    private Person mPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_person);

        UUID personId = (UUID) getIntent().getSerializableExtra("person_id");

        mPerson = People.get().getPerson(personId);

        


    }
}
