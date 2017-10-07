package ca.qc.cegep_heritage.presentandfuturevaluecalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Grab the various widgets from the activity.
        final Button btnCalculate = (Button) findViewById(R.id.btnCalculate);
        final EditText edtTxtValue = (EditText) findViewById(R.id.edtTxtValue);
        final RadioButton rdoMonthly = (RadioButton) findViewById(R.id.rdoMonthly);
        final RadioButton rdoQuarterly = (RadioButton) findViewById(R.id.rdoQuarterly);
        final RadioButton rdoSemi = (RadioButton) findViewById(R.id.rdoSemi);
        final RadioButton rdoAnnually = (RadioButton) findViewById(R.id.rdoAnnually);
        final RadioButton rdoPresent = (RadioButton) findViewById(R.id.rdoPresent);
        final RadioButton rdoFuture = (RadioButton) findViewById(R.id.rdoFuture);
        final SeekBar skBarInsuranceRate = (SeekBar) findViewById(R.id.skBarInsuranceRate);
        final SeekBar skBarYear = (SeekBar) findViewById(R.id.skBarYear);
        final TextView txtInsuranceRateValue = (TextView) findViewById(R.id.txtInsuranceRateValue);
        final TextView txtYearValue = (TextView) findViewById(R.id.txtYearValue);

        // Array containing all of the radio button widgets.
        final RadioButton[] rdoFrequencies = {rdoMonthly, rdoQuarterly, rdoSemi, rdoAnnually};
        final RadioButton[] rdoCalculations = {rdoPresent, rdoFuture};


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Checks the widgets for appropriate values, and returns an error if something has
                 gone wrong. */
                String errorMessage = "";


                if (TextUtils.isEmpty(edtTxtValue.getText())) {
                    errorMessage += getResources().getString(R.string.edtTxtValueError);
                }

                if (!isOneButtonSelected(rdoFrequencies)) {
                    errorMessage += getResources().getString(R.string.rdoFrequenciesError);
                }

                if (!isOneButtonSelected(rdoCalculations)) {
                    errorMessage += getResources().getString(R.string.rdoCalculationsError);
                }

                if (!errorMessage.equals("")) {
                    Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                } else {
                    String frequency = "";
                    Bundle extras = new Bundle();

                    for (RadioButton rdoFrequency : rdoFrequencies) {
                        if (rdoFrequency.isChecked()) {
                            frequency = rdoFrequency.getText().toString();
                        }
                    }

                    if (rdoPresent.isChecked()) {
                        Intent presentIntent = new Intent(getApplicationContext(),
                                PresentValue.class);
                        extras.putString("VALUE", edtTxtValue.getText().toString());
                        extras.putInt("INSURANCE_RATE", skBarInsuranceRate.getProgress());
                        extras.putInt("NUMBER_YEARS", skBarYear.getProgress());
                        extras.putString("FREQUENCY", frequency);
                        presentIntent.putExtras(extras);
                        startActivity(presentIntent);
                    } else {
                        Intent futureIntent = new Intent(getApplicationContext(),
                                FutureValue.class);
                        extras.putString("VALUE", edtTxtValue.getText().toString());
                        extras.putInt("INSURANCE_RATE", skBarInsuranceRate.getProgress());
                        extras.putInt("NUMBER_YEARS", skBarYear.getProgress());
                        extras.putString("FREQUENCY", frequency);
                        futureIntent.putExtras(extras);
                        startActivity(futureIntent);
                    }
                }

            }
        });

        skBarInsuranceRate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                double seekValue = skBarInsuranceRate.getProgress();
                txtInsuranceRateValue.setText(getResources().getString(R.string.percentage, seekValue / 10.0));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // ...
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // ...
            }
        });

        skBarYear.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //txtYearValue.setText(Integer.toString(skBarYear.getProgress()) + "yrs.");
                txtYearValue.setText(getResources().getString(R.string.years, skBarYear.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // ...
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // ...
            }
        });
    }

    protected boolean isOneButtonSelected(RadioButton[] radioButtons) {
        boolean isOneButtonSelected = false;
        for (RadioButton radioButton : radioButtons) {
            if (radioButton.isChecked()) {
                isOneButtonSelected = true;
            }
        }
        return isOneButtonSelected;
    }
}
