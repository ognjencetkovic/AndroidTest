package ba.bitcamp.ognjencetkovic.people;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by ognje on 24-Oct-15.
 */
public class EditPersonActivity extends Activity {

    private Person mPerson;

    private EditText mFirstNameInput;
    private EditText mLastNameInput;
    private Button mEditPersonButton;

    private UUID mPersonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_person);

        mPersonId = (UUID) getIntent().getSerializableExtra("person_id");
        mPerson = People.get().getPerson(mPersonId);

        mFirstNameInput = (EditText) findViewById(R.id.first_name_input);
        mFirstNameInput.setText(mPerson.getFirstName());

        mLastNameInput = (EditText) findViewById(R.id.last_name_input);
        mLastNameInput.setText(mPerson.getLastName());

        mEditPersonButton = (Button) findViewById(R.id.edit_button);
        mEditPersonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                People.get().getPerson(mPersonId).setFirstName(mFirstNameInput.getText().toString());
                People.get().getPerson(mPersonId).setLastName(mLastNameInput.getText().toString());

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
