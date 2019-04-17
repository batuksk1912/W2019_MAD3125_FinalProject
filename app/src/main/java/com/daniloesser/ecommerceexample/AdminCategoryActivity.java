package com.daniloesser.ecommerceexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class AdminCategoryActivity extends AppCompatActivity {

    private ImageView tShirts, sportsTShirts, femaleDresses, sweathers, glasses, hatsCaps, walletsBagsPurses, shoes, headphonesHandFree, laptops, watches, mobilePhones;
    private Button logoutButton, checkOrdersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        logoutButton = findViewById(R.id.admin_logout_btn);
        checkOrdersButton = findViewById(R.id.check_orders_btn);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        checkOrdersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewOrdersActivity.class);
                startActivity(intent);
            }
        });


        tShirts = findViewById(R.id.t_shirts);
        sportsTShirts = findViewById(R.id.sports_t_shirts);
        femaleDresses = findViewById(R.id.female_dresses);
        sweathers = findViewById(R.id.sweathers);
        glasses = findViewById(R.id.glasses);
        hatsCaps = findViewById(R.id.hats);
        walletsBagsPurses = findViewById(R.id.purses_bags_wallets);
        shoes = findViewById(R.id.shoes);
        headphonesHandFree = findViewById(R.id.headphones);
        laptops = findViewById(R.id.laptop_pc);
        watches = findViewById(R.id.watches);
        mobilePhones = findViewById(R.id.mobiles);

        tShirts.setOnClickListener((v -> {
            Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
            intent.putExtra("category", "TShirts");
            //getResources().getResourceEntryName(v.getId()
            startActivity(intent);
        }));
        sportsTShirts.setOnClickListener((v -> {
            Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
            intent.putExtra("category", "Sports tShirts");
            //getResources().getResourceEntryName(v.getId()
            startActivity(intent);
        }));
        femaleDresses.setOnClickListener((v -> {
            Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
            intent.putExtra("category", "Female Dresses");
            //getResources().getResourceEntryName(v.getId()
            startActivity(intent);
        }));
        sweathers.setOnClickListener((v -> {
            Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
            intent.putExtra("category", "Sweathers");
            //getResources().getResourceEntryName(v.getId()
            startActivity(intent);
        }));
        headphonesHandFree.setOnClickListener((v -> {
            Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
            intent.putExtra("category", "HeadPhones HandFree");
            //getResources().getResourceEntryName(v.getId()
            startActivity(intent);
        }));
        glasses.setOnClickListener((v -> {
            Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
            intent.putExtra("category", "Glasses");
            //getResources().getResourceEntryName(v.getId()
            startActivity(intent);
        }));
        hatsCaps.setOnClickListener((v -> {
            Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
            intent.putExtra("category", "Hats Caps");
            //getResources().getResourceEntryName(v.getId()
            startActivity(intent);
        }));
        walletsBagsPurses.setOnClickListener((v -> {
            Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
            intent.putExtra("category", "Wallets Bags Purses");
            //getResources().getResourceEntryName(v.getId()
            startActivity(intent);
        }));
        shoes.setOnClickListener((v -> {
            Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
            intent.putExtra("category", "Shoes");
            //getResources().getResourceEntryName(v.getId()
            startActivity(intent);
        }));
        laptops.setOnClickListener((v -> {
            Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
            intent.putExtra("category", "Laptops");
            //getResources().getResourceEntryName(v.getId()
            startActivity(intent);
        }));
        watches.setOnClickListener((v -> {
            Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
            intent.putExtra("category", "Watches");
            //getResources().getResourceEntryName(v.getId()
            startActivity(intent);
        }));
        mobilePhones.setOnClickListener((v -> {
            Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
            intent.putExtra("category", "Mobile Phones");
            //getResources().getResourceEntryName(v.getId()
            startActivity(intent);
        }));
    }
}
