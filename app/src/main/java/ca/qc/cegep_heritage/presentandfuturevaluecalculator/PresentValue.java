package ca.qc.cegep_heritage.presentandfuturevaluecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

public class PresentValue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present_value);
        final TextView txtView = (TextView) findViewById(R.id.txtView);
        Bundle extras = getIntent().getExtras();

        double futureValue = Double.parseDouble(extras.getString("VALUE"));
        int insuranceRate = extras.getInt("INSURANCE_RATE");
        int numberOfYears = extras.getInt("NUMBER_YEARS");
        char frequencyChar = extras.getString("FREQUENCY").charAt(0);
        double presentValue = 0.0;

        switch (frequencyChar) {
            case 'M':
                presentValue = calculateFutureValue(futureValue, insuranceRate, numberOfYears, 12);
                break;
            case 'Q':
                presentValue = calculateFutureValue(futureValue, insuranceRate, numberOfYears, 4);
                break;
            case 'S':
                presentValue = calculateFutureValue(futureValue, insuranceRate, numberOfYears, 2);
                break;
            case 'A':
                presentValue = calculateFutureValue(futureValue, insuranceRate, numberOfYears, 1);
                break;
            default:
                presentValue = -999999999;
                break;
        }
        txtView.setText(String.format(Locale.CANADA, "The present day value of $%.2f is $%.2f", futureValue,
                presentValue));
    }

    private double calculateFutureValue(double value, int rate, int years, int frequency) {
        double interestRatePerTimePeriod = 1 + (rate / 100.0);
        int interestPeriods = years * frequency;
        double irtpPow = Math.pow(interestRatePerTimePeriod, interestPeriods);
        return value / irtpPow;
    }
}
