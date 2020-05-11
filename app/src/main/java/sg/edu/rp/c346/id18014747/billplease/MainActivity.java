package sg.edu.rp.c346.id18014747.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText etAmount;
    EditText etPax;
    ToggleButton tgSVS;
    ToggleButton tgGST;
    EditText etDiscount;
    TextView tvBill;
    TextView tvEaPay;
    Button btnSplit;
    Button btnReset;
    TextView txtError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmount = findViewById(R.id.editAmt);
        etPax = findViewById(R.id.editPax);
        tgSVS = findViewById(R.id.tgSVS);
        tgGST = findViewById(R.id.tgGST);
        etDiscount = findViewById(R.id.editDiscount);
        tvBill = findViewById(R.id.txtBill);
        tvEaPay = findViewById(R.id.txtEachPay);
        btnSplit = findViewById(R.id.btnSplit);
        btnReset = findViewById(R.id.btnReset);
        txtError = findViewById(R.id.txtError);

        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check empty
                if (etAmount.getText().toString().isEmpty() || etPax.getText().toString().isEmpty() || etDiscount.getText().toString().isEmpty()) {
                    txtError.setText("Error: Please fill in all fields!");
                    return;
                }

                // Calculations
                double totalBill = Double.parseDouble(etAmount.getText().toString());
                int numPax = Integer.parseInt(etPax.getText().toString());
                double totalExtra = 1.0; // 100% base
                double discount = Double.parseDouble(etDiscount.getText().toString());
                if (tgSVS.isChecked()) {
                    totalExtra += 0.1; //10%
                }
                if (tgGST.isChecked()) {
                    totalExtra += 0.07; //7%
                }
                totalBill *= (100-discount)/100;
                // Discount then GST & Service-Charge
                if (totalExtra > 0) {
                    totalBill *= totalExtra;
                }

                // Convert and Display
                tvBill.setText(String.format("%.2f",totalBill));
                tvEaPay.setText(String.format("%.2f",(totalBill/numPax)));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etAmount.setText("");
                etPax.setText("");
                tgSVS.setChecked(false);
                tgGST.setChecked(false);
                etDiscount.setText("");
                tvBill.setText("");
                tvEaPay.setText("");
                txtError.setText("");
            }
        });
    }
}
