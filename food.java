
   ## food odering java code
/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int coffie=2;

    public void increment(View view) {
       if(coffie==100)
       {
           Toast.makeText(this,"you can not have more than 100 coffies", Toast.LENGTH_SHORT).show();
           return;
       }
        coffie=coffie+1;
        display(coffie);
    }

    public void decrement(View view) {
        if(coffie==1)
        {
            Toast.makeText(this,"you can not have less than 1 coffies", Toast.LENGTH_SHORT).show();
            return;
        }
        coffie=coffie-1;
        display(coffie);
    }

    public void submitOrder(View view) {

        EditText edit = (EditText) findViewById(R.id.name);
        //TextView tview = (TextView)findViewById(R.id.textview1);
        String result = edit.getText().toString();



        CheckBox whippedcreamcheckbox = (CheckBox) findViewById(R.id.check_for_cream);
        boolean hascheckbox = whippedcreamcheckbox.isChecked();


        CheckBox whippedcreamcheckbox1 = (CheckBox) findViewById(R.id.check_for_chocolate);
        boolean hascheckbox1 = whippedcreamcheckbox1.isChecked();

        int price =calculate_price(hascheckbox , hascheckbox1);

        String pricemessage = "Name:"+ result + "\nAdd whipped cream?" + hascheckbox + "\nAdd chocolate?" + hascheckbox1 +"\nQuantity:" + coffie + "\nTotal: $"+price + "\nThankyou";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(intent.EXTRA_SUBJECT, "just java order for" + result);
        intent.putExtra(intent.EXTRA_TEXT, pricemessage );
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        displaymessage(pricemessage);
    }

    private int calculate_price(boolean hascheckbox , boolean hascheckbox1)
    {
        int base_price=5;
        if(hascheckbox)
        {
            base_price=base_price+1;
        }
        if(hascheckbox1)
        {
            base_price=base_price+2;
        }
        return base_price*coffie;
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    private void displaymessage(String message)
    {
        TextView pricetextView = (TextView) findViewById(R.id.price_text_view);
        pricetextView.setText(message);
    }
}