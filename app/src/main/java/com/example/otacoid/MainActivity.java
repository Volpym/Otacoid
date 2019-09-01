package com.example.otacoid;





import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.otacoid.scraper.Scrapper;
import com.example.otacoid.urlManager.UrlManager;


public class MainActivity extends AppCompatActivity{

    Scrapper scrappy  = new Scrapper();
    UrlManager jarvis = new UrlManager();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_ui);

        Button bScan = findViewById(R.id.bScan);

        bScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Scan().execute();



            }
        });

    }

    private class Scan extends AsyncTask<Void, Void, Void>{
        final TextView tDescription = findViewById(R.id.description_text_view);
        final TextView tAsinCode = findViewById(R.id.product_code_text_view);
        final TextView tManufacturer = findViewById(R.id.manufacturer_text_view);
        final ImageView ivImage=findViewById(R.id.product_image_view);

        final EditText tMinPrice = findViewById(R.id.min_price_edit_text);
        final EditText tMinDelivery = findViewById(R.id.min_delivery_cost_edit_text);
        final EditText tMinSeller = findViewById(R.id.min_seller_edit_text);
        final EditText tBarcode = findViewById(R.id.barcode_edit_text);



        final ImageView productPic = findViewById(R.id.product_image_view);

        String asinCode;
        String minPrice;
        String minDeliveryCost;
        String minSeller;
        String description;
        String manufacturer;
        String resultLink;



        @Override
        protected Void doInBackground(Void... voids) {
            String searchUrl = jarvis.createBarcodeLink(tBarcode.getText().toString());
            org.jsoup.nodes.Document resultHtml = scrappy.connectToSite(searchUrl);
            resultLink = scrappy.scrapeLink(resultHtml);
            asinCode = scrappy.scrapeASINcode(resultLink);
            String listingUrl = jarvis.createListingLink(scrappy.scrapeASINcode(resultLink));


            int httpResponse = jarvis.getHTTPresponse(listingUrl);

            if(httpResponse == 200){
                resultHtml = scrappy.connectToSite(listingUrl);
                description = scrappy.scrapeDescription(resultHtml);
                manufacturer = scrappy.scrapeManufacturer(resultHtml);
                minPrice = scrappy.scrapeMinPrice(resultHtml);
                minDeliveryCost = scrappy.scrapeMinDeliveryCost(resultHtml);
                minSeller = scrappy.scrapeSellerName(resultHtml);
                System.out.println("pokemon: "+ minSeller);

            }


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            tAsinCode.setText(asinCode);
            tDescription.setText(description);
            tManufacturer.setText(manufacturer);
            tMinPrice.setText(minPrice);
            tMinDelivery.setText(minDeliveryCost);
            if(minSeller == " "){
                tMinSeller.setText("Amazon");
            }else{
                tMinSeller.setText(minSeller);
            }



        }
    }
}









