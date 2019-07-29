package ca.judacribz.navigationdrawer.drawer_fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

import ca.judacribz.navigationdrawer.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class EMIFragment extends Fragment {

    private static final int DEFAULT_LOAN_AMOUNT = 1000000;
    private static final int DEFAULT_INTEREST = 1;
    private static final int DEFAULT_LOAN_PERIOD = 12;

    SeekBar sbLoanAmount, sbInterestRate, sbLoanPeriod;
    TextView tvEMI, tvLoanAmount, tvInterestRate, tvLoanPeriod;


    public EMIFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emi, container, false);

        sbLoanAmount = view.findViewById(R.id.sbLoanAmount);
        sbInterestRate = view.findViewById(R.id.sbInterestRate);
        sbLoanPeriod = view.findViewById(R.id.sbLoanPeriod);

        tvEMI = view.findViewById(R.id.tvEMI);
        tvLoanAmount = view.findViewById(R.id.tvLoanAmount);
        tvInterestRate = view.findViewById(R.id.tvInterestRate);
        tvLoanPeriod = view.findViewById(R.id.tvLoanPeriod);

        sbLoanAmount.setOnSeekBarChangeListener(setOnSeekBarListener(tvLoanAmount, 0));
        sbInterestRate.setOnSeekBarChangeListener(setOnSeekBarListener(tvInterestRate, 8));
        sbLoanPeriod.setOnSeekBarChangeListener(setOnSeekBarListener(tvLoanPeriod, 1));

        setDefaults();

        return view;
    }

    private void setDefaults() {
        sbLoanAmount.setProgress(DEFAULT_LOAN_AMOUNT);
        sbInterestRate.setProgress(DEFAULT_INTEREST);
        sbLoanPeriod.setProgress(DEFAULT_LOAN_PERIOD);
    }

    private SeekBar.OnSeekBarChangeListener setOnSeekBarListener(final TextView tvDisplay,
                                                                 final int offset) {
        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvDisplay.setText(String.valueOf(progress + offset));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };

    }

    public void calculateEMI(View view) {
        double
                loanAmount = Double.valueOf(tvLoanAmount.getText().toString()),
                interestRate = Double.valueOf(tvInterestRate.getText().toString()),
                loanPeriod = Double.valueOf(tvLoanPeriod.getText().toString());

        double
                r = interestRate / 1200,
                r1 = Math.pow(r + 1, loanPeriod),
                monthlyPayment = (r + (r / (r1 - 1))) * loanAmount,
                totalPayment = monthlyPayment * loanPeriod;

        tvEMI.setText(String.format(
                Locale.ENGLISH,
                getResources().getString(R.string.emi_output),
                monthlyPayment,
                totalPayment
        ));
    }

}
