package ca.judacribz.navigationdrawer.actionbar_activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import ca.judacribz.navigationdrawer.R;

public class CalculatorActivity extends AppCompatActivity {

    final private static char DIVIDE = '/';
    final private static char MULTIPLY = 'x';
    final private static char ADD = '+';
    final private static char SUBTRACT = '-';
    final private static char EQUALS = '=';
    final private static char DECIMAL = '.';

    TextView tvCalculations, tvAnswer;
    Button btnDelete;

    StringBuilder sb;

    boolean lastCharIsDigit = false;
    boolean containsOperator = false;

    char operator;
    double ans, num1, num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        tvCalculations = findViewById(R.id.tvCalculations);
        tvAnswer = findViewById(R.id.tvAnswer);
        btnDelete = findViewById(R.id.btnDelete);

        sb = new StringBuilder();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

         switch (item.getItemId()) {
             case android.R.id.home:
                 onBackPressed();
                 break;
         }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void setTvCalculations() {
        tvCalculations.setText(sb);
    }

    public void calculate() {

        if (containsOperator && lastCharIsDigit) {

            String[] nums = sb.toString().split(String.valueOf(operator));
            num1 = Double.valueOf(nums[0]);
            num2 = Double.valueOf(nums[1]);


            switch (operator) {
                case DIVIDE:
                    ans = num1 / num2;
                    break;
                case MULTIPLY:
                    ans = num1 * num2;
                    break;
                case ADD:
                    ans = num1 + num2;
                    break;
                case SUBTRACT:
                    ans = num1 - num2;
                    break;
            }

            tvAnswer.setText(String.valueOf(ans));

        } else {
            tvAnswer.setText("");
        }
    }

    public void appendToCalculation(String calcAppendStr) {
        if (!btnDelete.isEnabled()) {
            btnDelete.setEnabled(true);
        }

        sb.append(calcAppendStr);
        calculate();

        setTvCalculations();
    }

    public void deleteFromCalculation() {
        int strLen = sb.length();
        sb.deleteCharAt(strLen - 1);
        setTvCalculations();

        if (strLen == 1) {
            btnDelete.setEnabled(false);
        } else {
//            setBooleans();
            if (!sb.toString().contains(String.valueOf(operator))) {
                containsOperator = false;
            }

            if (sb.charAt(sb.length()-1) == operator) {
                lastCharIsDigit = false;
            }
        }

        calculate();
    }

    public void handleNumClick(View btnNum) {
        lastCharIsDigit = true;
        appendToCalculation(((Button) btnNum).getText().toString());
    }

    public void handleOpClick(View btnOperator) {
        if (!containsOperator) {


            if (!sb.toString().isEmpty() && lastCharIsDigit) {
                operator = ((Button) btnOperator).getText().toString().charAt(0);
                lastCharIsDigit = false;
                containsOperator = true;
                appendToCalculation(String.valueOf(operator));
            }
        }
    }
}
