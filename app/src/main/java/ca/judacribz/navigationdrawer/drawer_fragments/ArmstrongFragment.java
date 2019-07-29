package ca.judacribz.navigationdrawer.drawer_fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import ca.judacribz.navigationdrawer.R;

import static ca.judacribz.navigationdrawer.Algorithms.isArmstrongNumber;

public class ArmstrongFragment extends Fragment {

    private String isArmstrong;
    private String not;

    private EditText etArmstrong;
    private TextView tvArmstrongOut;

    public ArmstrongFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_armstrong, container, false);

        isArmstrong = view.getResources().getString(R.string.armstrong_output);
        not = view.getResources().getString(R.string.not);

        etArmstrong = view.findViewById(R.id.etArmstrong);
        tvArmstrongOut = view.findViewById(R.id.tvArmstrongOut);

        return view;
    }

    public void checkArmstrong() {
        tvArmstrongOut.setText(String.format(
                isArmstrong,
                Integer.valueOf(etArmstrong.getText().toString()),
                isArmstrongNumber(Integer.valueOf(etArmstrong.getText().toString())) ?
                        "" :
                        not
        ));
    }
}
