/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.navgurukul019.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;
   // String orderSummary;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
        //Toast toast = new Toast()

    //Scanner sc= new Scanner(System.in);
    //String  a = sc.nextLine();
    //this methode calls the submitOrder button.
    public void submitOrder(View view){
       CheckBox wippedCheckBox =findViewById(R.id.wipped_checkbox);
       boolean hasWippedCheckBox=wippedCheckBox.isChecked();
       if(hasWippedCheckBox==true);
       Log.v("MainActivity","Has Wipped Cream:"+hasWippedCheckBox);


       CheckBox chocklateCheckBox =findViewById(R.id.chocklate_checkbox);
       boolean hasChocklateCheckBox=chocklateCheckBox.isChecked();
       Log.v("MainActivity","Has Chocklate:"+hasChocklateCheckBox);


       EditText userInput =findViewById(R.id.editText);
       String name =userInput.getText().toString();
       Log.v("MainActivity","Name: "+name);




        int price=calculatPrice(hasChocklateCheckBox,hasWippedCheckBox);
        String orderSummary=creatOrderSummary(price,hasWippedCheckBox,hasChocklateCheckBox,name);

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT,"just java app order summary"+name);
            intent.putExtra(Intent.EXTRA_TEXT,orderSummary);

        if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }


        //displayMessage(orderSummary);

    }
    //this called the increment button.
    public void increment(View view) {
        if(quantity==100){
            Toast.makeText(this,"You can order only 100 cups of coffee in one time",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity+1;

        display(quantity);
        }
        //this called the decrement button.
    public void decrement(View view) {
       if(quantity==1){
           Toast.makeText(this,"You can't order less than 1 cup of coffee",Toast.LENGTH_SHORT).show();
           return;
       }
        quantity=quantity-1;
       display(quantity);
   }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /*this method is for just caculate price of coffee regarding to its quantity.


    */

    private int calculatPrice(boolean Chocklat,boolean Cream){
        int basePrice = 5;
        if(Chocklat){
            basePrice+=2;
        }
        if (Cream){
            basePrice+=1;}
        return quantity*basePrice;

    }
    /*this method display the all contant about the summary which we want to display.
    */
    private String creatOrderSummary(int price,boolean add,boolean chocklate,String Name){
       String orderSummary = "Name ="+Name+"\n";
        orderSummary += "Price: $"+price+"\n";
        orderSummary += "quantity="+ quantity+"\n";
        orderSummary += "Wipped Cream Coffee: "+ add+"\n";
        orderSummary += "Chocklate: "+ chocklate;
        orderSummary += "\nThank You";
        return orderSummary;



    }


    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summry_text_view);
        orderSummaryTextView.setText(message);

    }
}


