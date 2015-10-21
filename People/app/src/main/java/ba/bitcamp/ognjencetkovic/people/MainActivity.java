package ba.bitcamp.ognjencetkovic.people;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends ActionBarActivity {

    private EditText mFirstNameInput;
    private EditText mLastNameInput;
    private Button mAddBtn;
    private RecyclerView mRecyclerView;
    private PersonAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirstNameInput = (EditText) findViewById(R.id.first_name_input);
        mLastNameInput = (EditText) findViewById(R.id.last_name_input);
        mAddBtn = (Button) findViewById(R.id.add_button);

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person = new Person(mFirstNameInput.getText().toString(), mLastNameInput.getText().toString());
                People.get().add(person);
                mAdapter.notifyDataSetChanged();
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.person_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        updateUI();

    }

    public void updateUI() {
        People people = People.get();
        List<Person> persons = people.getPersons();

        mAdapter = new PersonAdapter(persons);
        mRecyclerView.setAdapter(mAdapter);
    }


    private class PersonAdapter extends RecyclerView.Adapter<PersonHolder> {

        private List<Person> mPersons;

        public PersonAdapter(List<Person> persons) {
            mPersons = persons;
        }

        @Override
        public PersonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
            View view = layoutInflater.inflate(R.layout.list_item_person, parent, false);
            return new PersonHolder(view);
        }

        @Override
        public void onBindViewHolder(PersonHolder holder, int position) {
            Person person = mPersons.get(position);
            holder.bindCrime(person);
        }

        @Override
        public int getItemCount() {
            return mPersons.size();
        }
    }

    private class PersonHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Person mPerson;

        private TextView mNameTextView;
        private TextView mDateTextView;

        public PersonHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            mNameTextView = (TextView) itemView.findViewById(R.id.name_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.created_text_view);

        }

        public void bindCrime(Person person) {
            mPerson = person;
            mNameTextView.setText(mPerson.getFirstName() + " " + mPerson.getLastName());
            mDateTextView.setText(mPerson.getCreated().getTime().toString());
        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(getActivity(), mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
        }
    }


}
