package ca.qc.cegep_heritage.presentandfuturevaluecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class FutureValue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_value);
        Bundle extras = getIntent().getExtras();

        double presentValue = Double.parseDouble(extras.getString("VALUE"));
        int insuranceRate = extras.getInt("INSURANCE_RATE");
        int numberOfYears = extras.getInt("NUMBER_YEARS");
        char  frequencyChar = extras.getString("FREQUENCY").charAt(0);

        switch (frequencyChar) {
            case 'M':
                displayFutureValues(presentValue, insuranceRate, numberOfYears, 12);
                break;
            case 'Q':
                displayFutureValues(presentValue, insuranceRate, numberOfYears, 4);
                break;
            case 'S':
                displayFutureValues(presentValue, insuranceRate, numberOfYears, 2);
                break;
            case 'A':
                displayFutureValues(presentValue, insuranceRate, numberOfYears, 1);
                break;
            default:
                break;
        }
    }

    private void displayFutureValues(double pv, int rate, int years, int frequency) {
        for (int i = 1; i <= years - 1; i++) {
            int fontSizeSp = 15;
            int interestPeriods = i * frequency;
            double interestRatePerTimePeriod = 1 + (rate / 100.0);
            double irtpPow = Math.pow(interestRatePerTimePeriod, interestPeriods);
            double fv = pv * irtpPow;
            TableLayout tblFutureValues = (TableLayout) findViewById(R.id.tblFutureValues);
            TableRow tblRow = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            TextView txtYear = new TextView(this);
            TextView txtValue = new TextView(this);
            TextView txtInterest = new TextView(this);

            txtYear.setText(getResources().getString(R.string.years, i));
            txtYear.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSizeSp);
            txtValue.setText(getResources().getString(R.string.currency, fv));
            txtValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSizeSp);
            txtInterest.setText(getResources().getString(R.string.currency, fv * (rate / 100.0)));
            txtInterest.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSizeSp);
            tblRow.setLayoutParams(lp);
            tblRow.addView(txtYear);
            tblRow.addView(txtInterest);
            tblRow.addView(txtValue);
            tblFutureValues.addView(tblRow);
        }
    }
}
