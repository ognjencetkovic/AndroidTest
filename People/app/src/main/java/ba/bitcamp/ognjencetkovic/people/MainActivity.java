package ba.bitcamp.ognjencetkovic.people;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private EditText mFirstNameInput;
    private EditText mLastNameInput;
    private Button mAddBtn;
    private RadioButton mFirstNameSortButton;
    private RadioButton mLastNameSortButton;
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
                if (mFirstNameInput.getText().toString().equals("") || mLastNameInput.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, R.string.incorrect_form, Toast.LENGTH_SHORT).show();
                    return;
                }
                Person person = new Person(mFirstNameInput.getText().toString(), mLastNameInput.getText().toString());
                People.get().add(person);
                mFirstNameInput.setText("");
                mLastNameInput.setText("");
                mAdapter.notifyDataSetChanged();
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.person_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        updateUI();

        mFirstNameSortButton = (RadioButton) findViewById(R.id.first_name_sort);
        mLastNameSortButton = (RadioButton) findViewById(R.id.last_name_sort);

        mFirstNameSortButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mLastNameSortButton.setChecked(false);
                    Collections.sort(People.get().getPersons(), new Comparator<Person>() {
                        @Override
                        public int compare(Person p1, Person p2) {
                            return p1.getFirstName().compareTo(p2.getFirstName());
                        }
                    });
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

        mLastNameSortButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mFirstNameSortButton.setChecked(false);
                    Collections.sort(People.get().getPersons(), new Comparator<Person>() {
                        @Override
                        public int compare(Person p1, Person p2) {
                            return p1.getLastName().compareTo(p2.getLastName());
                        }
                    });
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

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
            holder.bindPerson(person);
        }

        @Override
        public int getItemCount() {
            return mPersons.size();
        }
    }

    private class PersonHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Person mPerson;

        private TextView mNameTextView;
        private TextView mDateTextView;
        private Button mButtonDelete;

        public PersonHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mNameTextView = (TextView) itemView.findViewById(R.id.name_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.created_text_view);
            mButtonDelete = (Button) itemView.findViewById(R.id.button_delete);

        }

        public void bindPerson(final Person person) {
            mPerson = person;
            mNameTextView.setText(mPerson.getFirstName() + " " + mPerson.getLastName());
            mDateTextView.setText(mPerson.getCreated().getTime().toString());
            mButtonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    People.get().getPersons().remove(person);
                    mAdapter.notifyDataSetChanged();
                }
            });
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), EditPersonActivity.class);
            intent.putExtra("person_id", mPerson.getId());
            startActivity(intent);
        }
    }


}
