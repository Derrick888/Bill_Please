package sg.edu.rp.c346.id22011505.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView amtDisplay;

    EditText etamtInput;

    TextView paxDisplay;

    EditText etpaxInput;

    ToggleButton tbtnsvs;

    ToggleButton tbtngst;

    TextView discDisplay;

    EditText etDiscount;

    RadioGroup rgPayment;

    TextView totalDisplay;

    TextView eachDisplay;

    Button btnSplit;

    Button btnReset;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amtDisplay = findViewById(R.id.textAmount);
        etamtInput = findViewById(R.id.editInputAmount);
        paxDisplay = findViewById(R.id.textNumPax);
        etpaxInput = findViewById(R.id.editInputPax);
        tbtnsvs = findViewById(R.id.tbSvs);
        tbtngst = findViewById(R.id.tbGst);
        discDisplay= findViewById(R.id.viewDiscount);
        etDiscount = findViewById(R.id.editInputDiscount);
        rgPayment = findViewById(R.id.radioGroupPayment);
        totalDisplay = findViewById(R.id.viewTotalBill);
        eachDisplay = findViewById(R.id.viewEachPay);
        btnSplit = findViewById(R.id.buttonSplit);
        btnReset = findViewById(R.id.buttonReset);

        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String totalResponse;
                String eachResponse;
                int checkedRadioId= rgPayment.getCheckedRadioButtonId();
                int total = Integer.parseInt(etamtInput.getText().toString());
                int totalNoOfPax = Integer.parseInt(etpaxInput.getText().toString());
                int disc = Integer.parseInt(etDiscount.getText().toString());

                double AmountOfDisc = total * disc/100;
                double totalAmt = total - AmountOfDisc;
                double newAmt = 0;


                if (!tbtnsvs.isChecked() && !tbtngst.isChecked()) {
                    totalAmt= totalAmt + (totalAmt * 0.08 * 0.10);

                } else if (tbtnsvs.isChecked() && !tbtngst.isChecked()) {
                    totalAmt= totalAmt + (totalAmt * 0.10);
                    
                } else if (!tbtnsvs.isChecked() && tbtngst.isChecked()) {
                    totalAmt = totalAmt + (totalAmt * 0.08);

                }

                newAmt = Double.parseDouble(String.format("%.2f",totalAmt));
                double eachPay = Double.parseDouble(String.format("%.2f",(newAmt/totalNoOfPax)));

                if(checkedRadioId == R.id.radioButtonPaymentCash){
                    eachResponse = "Each pays: $" + eachPay + " in cash";
                    
                } else {
                    eachResponse = "Each pays: $" + eachPay + " via Paynow";

                }
                totalResponse = "Total Bill: $" + newAmt;

                totalDisplay.setText(totalResponse);
                eachDisplay.setText(eachResponse);





            }
        });




    }
}