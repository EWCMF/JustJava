/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        displayMessage(createOrderSummary(price));

        // Don't want to configure email.
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:"));
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + getName());
//        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(price));
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
    }

    public void increment(View view) {
        if (quantity == 100)
            return;
        quantity++;
        display(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1)
            return;
        quantity--;
        display(quantity);
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * Calculates the price of the order.
     */
    private int calculatePrice() {
        int price = quantity * 5;
        if (hasChocolate())
            price += 2;
        if (hasWhippedCream())
            price += 1;
        return price;
    }

    private boolean hasWhippedCream() {
        CheckBox checkBox = findViewById(R.id.with_whipped_cream_check_box);
        return checkBox.isChecked();
    }

    private boolean hasChocolate() {
        CheckBox checkBox = findViewById(R.id.with_chocolate_check_box);
        return checkBox.isChecked();
    }

    private String getName() {
        EditText editText = findViewById(R.id.name_edit_text);
        return editText.getText().toString();
    }

    private String createOrderSummary(int price) {
        return getString(R.string.summaryName, getName()) +
                "\n" + getString(R.string.addWhippedCream) + " " + hasWhippedCream() +
                "\n" + getString(R.string.addChocolate) + " " + hasChocolate() +
                "\n" + getString(R.string.summaryQuantity) + quantity +
                "\n" + getString(R.string.summaryTotal, "" + price) +
                "\n" + getString(R.string.summaryThankYou);
    }
}