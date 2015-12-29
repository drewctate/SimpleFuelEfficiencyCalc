package com.drewctate.fecalc;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;

public class FECalc extends Activity {

    /* renamed from: com.drewctate.fecalc.FECalc.1 */
    class C00001 implements OnClickListener {
        private final /* synthetic */ TextView val$fec_label;
        private final /* synthetic */ EditText val$fuel_field;
        private final /* synthetic */ RadioButton val$gallon_radio;
        private final /* synthetic */ RadioButton val$kilometer_radio;
        private final /* synthetic */ RadioButton val$liter_radio;
        private final /* synthetic */ RadioButton val$mile_radio;
        private final /* synthetic */ EditText val$odometer_field;

        C00001(EditText editText, EditText editText2, RadioButton radioButton, RadioButton radioButton2, RadioButton radioButton3, RadioButton radioButton4, TextView textView) {
            this.val$fuel_field = editText;
            this.val$odometer_field = editText2;
            this.val$mile_radio = radioButton;
            this.val$gallon_radio = radioButton2;
            this.val$liter_radio = radioButton3;
            this.val$kilometer_radio = radioButton4;
            this.val$fec_label = textView;
        }

        public void onClick(View v) {
            String fuelstr = "";
            String odometerstr = "";
            if (this.val$fuel_field.getText().toString().length() == 0 || this.val$odometer_field.getText().toString().length() == 0) {
                Toast.makeText(FECalc.this, "Please enter all required values.", 0).show();
                return;
            }
            fuelstr = this.val$fuel_field.getText().toString();
            odometerstr = this.val$odometer_field.getText().toString();
            double fuel = FECalc.this.stringToDouble(fuelstr);
            double odometer = FECalc.this.stringToDouble(odometerstr);
            if (fuel == 0.0d) {
                Toast.makeText(FECalc.this, "Amount of fuel cannot equal zero.", 0).show();
                return;
            }
            double fec = FECalc.this.roundThreeDecimals(FECalc.this.calculateFE(fuel, odometer));
            String unitlabel = "";
            if (this.val$mile_radio.isChecked() && this.val$gallon_radio.isChecked()) {
                unitlabel = "m/g";
            }
            if (this.val$mile_radio.isChecked() && this.val$liter_radio.isChecked()) {
                unitlabel = "m/l";
            }
            if (this.val$kilometer_radio.isChecked() && this.val$gallon_radio.isChecked()) {
                unitlabel = "km/g";
            }
            if (this.val$kilometer_radio.isChecked() && this.val$liter_radio.isChecked()) {
                unitlabel = "km/l";
            }
            String fe = new StringBuilder(String.valueOf(FECalc.this.doubleToString(fec))).append(" ").append(unitlabel).toString();
            this.val$fec_label.setText(fe);
            FECalc.this.save("CalculatedFE.dat", fe);
        }
    }

    /* renamed from: com.drewctate.fecalc.FECalc.2 */
    class C00012 implements OnKeyListener {
        private final /* synthetic */ TextView val$fec_label;
        private final /* synthetic */ EditText val$fuel_field;
        private final /* synthetic */ RadioButton val$gallon_radio;
        private final /* synthetic */ RadioButton val$kilometer_radio;
        private final /* synthetic */ RadioButton val$liter_radio;
        private final /* synthetic */ RadioButton val$mile_radio;
        private final /* synthetic */ EditText val$odometer_field;

        C00012(EditText editText, EditText editText2, RadioButton radioButton, RadioButton radioButton2, RadioButton radioButton3, RadioButton radioButton4, TextView textView) {
            this.val$fuel_field = editText;
            this.val$odometer_field = editText2;
            this.val$mile_radio = radioButton;
            this.val$gallon_radio = radioButton2;
            this.val$liter_radio = radioButton3;
            this.val$kilometer_radio = radioButton4;
            this.val$fec_label = textView;
        }

        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (event.getAction() != 0 || keyCode != 66) {
                return false;
            }
            String fuelstr = "";
            String odometerstr = "";
            if (this.val$fuel_field.getText().toString().length() == 0 || this.val$odometer_field.getText().toString().length() == 0) {
                Toast.makeText(FECalc.this, "Please enter all required values.", 0).show();
            } else {
                fuelstr = this.val$fuel_field.getText().toString();
                odometerstr = this.val$odometer_field.getText().toString();
                double fuel = FECalc.this.stringToDouble(fuelstr);
                double odometer = FECalc.this.stringToDouble(odometerstr);
                if (fuel == 0.0d) {
                    Toast.makeText(FECalc.this, "Amount of fuel cannot equal zero.", 0).show();
                } else {
                    double fec = FECalc.this.roundThreeDecimals(FECalc.this.calculateFE(fuel, odometer));
                    String unitlabel = "";
                    if (this.val$mile_radio.isChecked() && this.val$gallon_radio.isChecked()) {
                        unitlabel = "m/g";
                    }
                    if (this.val$mile_radio.isChecked() && this.val$liter_radio.isChecked()) {
                        unitlabel = "m/l";
                    }
                    if (this.val$kilometer_radio.isChecked() && this.val$gallon_radio.isChecked()) {
                        unitlabel = "km/g";
                    }
                    if (this.val$kilometer_radio.isChecked() && this.val$liter_radio.isChecked()) {
                        unitlabel = "km/l";
                    }
                    String fe = new StringBuilder(String.valueOf(FECalc.this.doubleToString(fec))).append(" ").append(unitlabel).toString();
                    this.val$fec_label.setText(fe);
                    FECalc.this.save("CalculatedFE.dat", fe);
                }
            }
            return true;
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0002R.layout.main);
        String FILENAME = "CalculatedFE.dat";
        TextView fec_label = (TextView) findViewById(C0002R.id.fec_label);
        RadioButton gallon_radio = (RadioButton) findViewById(C0002R.id.gallon_radio);
        RadioButton liter_radio = (RadioButton) findViewById(C0002R.id.liter_radio);
        RadioButton mile_radio = (RadioButton) findViewById(C0002R.id.mile_radio);
        RadioButton kilometer_radio = (RadioButton) findViewById(C0002R.id.kilometer_radio);
        EditText fuel_field = (EditText) findViewById(C0002R.id.fuel);
        EditText odometer_field = (EditText) findViewById(C0002R.id.odometer);
        Button updatebutton = (Button) findViewById(C0002R.id.update_button);
        fec_label.setText(restore("CalculatedFE.dat"));
        gallon_radio.setChecked(true);
        mile_radio.setChecked(true);
        updatebutton.setOnClickListener(new C00001(fuel_field, odometer_field, mile_radio, gallon_radio, liter_radio, kilometer_radio, fec_label));
        fuel_field.setOnKeyListener(new C00012(fuel_field, odometer_field, mile_radio, gallon_radio, liter_radio, kilometer_radio, fec_label));
    }

    public double calculateFE(double fuel, double odometer) {
        return odometer / fuel;
    }

    public double stringToDouble(String s) {
        return Double.valueOf(s.trim()).doubleValue();
    }

    public String doubleToString(double d) {
        return Double.toString(d);
    }

    double roundThreeDecimals(double d) {
        return Double.valueOf(new DecimalFormat("#.#").format(d)).doubleValue();
    }

    public boolean save(String FILENAME, String string) {
        try {
            String TESTSTRING = new String(string);
            OutputStreamWriter osw = new OutputStreamWriter(openFileOutput(FILENAME, 1));
            osw.write(TESTSTRING);
            osw.flush();
            osw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String restore(String FILENAME) {
        try {
            int i;
            char[] inputBuffer = new char[40];
            new InputStreamReader(openFileInput(FILENAME)).read(inputBuffer);
            int physicalSize = 0;
            for (char c : inputBuffer) {
                if (c != '\u0000') {
                    physicalSize++;
                }
            }
            char[] string = new char[physicalSize];
            for (i = 0; i < physicalSize; i++) {
                string[i] = inputBuffer[i];
            }
            return new String(string);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
