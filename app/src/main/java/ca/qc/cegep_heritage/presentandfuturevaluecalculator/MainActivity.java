package ca.qc.cegep_heritage.presentandfuturevaluecalculator;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        final SeekBar skBarInsuranceRate = (SeekBar) findViewById(R.id.skBarInsuranceRate);
        final SeekBar skBarYear = (SeekBar) findViewById(R.id.skBarYear);
        final TextView txtInsuranceRateValue = (TextView) findViewById(R.id.txtInsuranceRateValue);
        final TextView txtYearValue = (TextView) findViewById(R.id.txtYearValue);

        // Array containing all of the radio button widgets.
        final RadioButton[] rdoFrequencies = {rdoMonthly, rdoQuarterly, rdoSemi, rdoAnnually};

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Checks the widgets for apporpreate values, and returns an error if something has
                 gone wrong. */
                // TODO: Pass values to another activity based on the users selection

                String errorMessage = "";
                boolean rdoFound = false;
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Warning!");

                if (TextUtils.isEmpty(edtTxtValue.getText())) {
                    errorMessage += "Currency value is empty.\n";
                }

                for (RadioButton radioButton : rdoFrequencies) {
                    if (radioButton.isChecked()) {
                        rdoFound = true;
                    }
                }

                if (!rdoFound) {
                    errorMessage += "No frequency selected.\n";
                }

                if (!errorMessage.equals("")) {
                    alertDialog.setMessage(errorMessage);
                    alertDialog.show();
                }
            }
        });

        skBarInsuranceRate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                double seekValue = skBarInsuranceRate.getProgress();
                txtInsuranceRateValue.setText(Double.toString(seekValue / 10) + '%');
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
                txtYearValue.setText(Integer.toString(skBarYear.getProgress()) + "yrs.");
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
}
