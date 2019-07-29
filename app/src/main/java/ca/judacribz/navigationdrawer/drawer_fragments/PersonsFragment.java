package ca.judacribz.navigationdrawer.drawer_fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import ca.judacribz.navigationdrawer.DisplayPersonsActivity;
import ca.judacribz.navigationdrawer.R;
import ca.judacribz.navigationdrawer.models.Person;


public class PersonsFragment extends Fragment {
    public static final String EXTRA_PERSONS = "ca.judacribz.multifunction.EXTRA_PERSONS";
    EditText etFirstName, etLastName, etAge;
    EditText[] form;
    Button btnClearInfo, btnDisplayPersons;
    Activity act;


    ArrayList<Person> persons;
    public PersonsFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        act = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_persons, container, false);


        btnDisplayPersons = view.findViewById(R.id.btnDisplayPersons);
        btnClearInfo = view.findViewById(R.id.btnClearInfo);
        btnClearInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInfo();
            }
        });

        etFirstName = view.findViewById(R.id.etFirstName);
        etLastName = view.findViewById(R.id.etLastName);
        etAge = view.findViewById(R.id.etAge);
        etFirstName.requestFocus();

        form = new EditText[3];
        form[0] = etFirstName;
        form[1] = etLastName;
        form[2] = etAge;

        persons = new ArrayList<>();
        return view;
    }

    public void clearInfo() {
        for (EditText et : form) {
            et.setText("");
        }

        etFirstName.requestFocus();
    }

    public boolean checkEmpty() {
        boolean allFilled = true;
        for (EditText et : form) {
            if (et.getText().toString().trim().isEmpty()) {
                et.setError(getResources().getString(R.string.required));
                allFilled = false;
            }
        }
        return allFilled;
    }

    public void enterInfo(View view) {
        if (checkEmpty()) {
            persons.add(new Person(
                    etFirstName.getText().toString().trim(),
                    etLastName.getText().toString().trim(),
                    Integer.valueOf(etAge.getText().toString().trim())
            ));

            btnDisplayPersons.setText(String.format(
                    getResources().getString(R.string.display_persons),
                    persons.size()
            ));

            if (btnDisplayPersons.getVisibility() == View.GONE) {
                btnDisplayPersons.setVisibility(View.VISIBLE);
            }

            clearInfo();
        }
    }

    public void displayPersons() {
        Intent intent = new Intent(act, DisplayPersonsActivity.class);
        intent.putParcelableArrayListExtra(EXTRA_PERSONS, persons);
        startActivity(intent);
    }

}
