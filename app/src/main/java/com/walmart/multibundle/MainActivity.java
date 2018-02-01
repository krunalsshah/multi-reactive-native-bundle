package com.walmart.multibundle;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.walmart.multibundle.activities.CartActivity;
import com.walmart.multibundle.activities.ItemActivity;

public class MainActivity extends AppCompatActivity {

    private Button itemButton;
    private Button cartButton;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        itemButton = findViewById(R.id.btn_item);
        cartButton = findViewById(R.id.btn_cart);
        Toolbar myToolbar =findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setTitle(getString(R.string.app_name));
        registerListener();
    }

    private void registerListener() {
        itemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemActivity.launch(mContext);
            }
        });
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartActivity.launch(mContext);
            }
        });
    }
}
