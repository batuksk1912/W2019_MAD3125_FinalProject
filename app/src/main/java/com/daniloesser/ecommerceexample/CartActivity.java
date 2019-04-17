package com.daniloesser.ecommerceexample;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daniloesser.ecommerceexample.Model.Cart;
import com.daniloesser.ecommerceexample.Prevalent.Prevalent;
import com.daniloesser.ecommerceexample.ViewHolder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button NextProcessButton;
    private TextView txtTotalAmount, txtMsg1;

    private Float overTotalPrice = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.cart_list);
        recyclerView.hasFixedSize();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        NextProcessButton = findViewById(R.id.next_process_btn);
        txtTotalAmount = findViewById(R.id.total_price);
        txtMsg1 = findViewById(R.id.msg1);


        NextProcessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (overTotalPrice != 0.0) {
                    txtTotalAmount.setText("Total Price = $" + String.valueOf(overTotalPrice));
                    Intent intent = new Intent(CartActivity.this, ConfirmFinalOrderActivity.class);
                    intent.putExtra("Total Price", String.valueOf(overTotalPrice));
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(CartActivity.this, "Cart is empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        CheckOrderState();

        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");


        FirebaseRecyclerOptions<Cart> options = new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(cartListRef.child("User View")
                        .child(Prevalent.currentOnlineUser.getPhone()).child("Products"), Cart.class).build();

        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull Cart model) {
                holder.txtProductQuantity.setText("Quantity: " + model.getQuantity());
                holder.txtProductName.setText(model.getPname());
                holder.txtProductPrice.setText(model.getPrice());

                Float oneTypeProductPrice = (Float.valueOf(model.getPrice().substring(1))) * Float.valueOf(model.getQuantity());
                overTotalPrice = overTotalPrice + oneTypeProductPrice;

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CharSequence options[] = new CharSequence[]
                                {
                                        "Edit",
                                        "Delete"
                                };
                        AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                        builder.setTitle("Cart Items");
                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (i == 0) {
                                    Intent intent = new Intent(CartActivity.this, ProductDetailsActivity.class);
                                    intent.putExtra("pid", model.getPid());
                                    startActivity(intent);
                                }

                                if (i == 1) {
                                    cartListRef.child("Admin View")
                                            .child(Prevalent.currentOnlineUser.getPhone())
                                            .child("Products")
                                            .child(model.getPid())
                                            .removeValue();

                                    cartListRef.child("User View")
                                            .child(Prevalent.currentOnlineUser.getPhone())
                                            .child("Products")
                                            .child(model.getPid())
                                            .removeValue()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(CartActivity.this, "Item removed successfully.", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(CartActivity.this, HomeActivity.class);
                                                        startActivity(intent);
                                                    }
                                                }
                                            });


                                }

                            }
                        });
                        builder.show();
                    }
                });

            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_items_layout, viewGroup, false);
                CartViewHolder holder = new CartViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }


    private void CheckOrderState() {
        DatabaseReference ordersRef;
        ordersRef = FirebaseDatabase.getInstance().getReference()
                .child("Orders")
                .child(Prevalent.currentOnlineUser.getPhone());
        ordersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String shippingState = dataSnapshot.child("state").getValue().toString();
                    String userName = dataSnapshot.child("name").getValue().toString();

                    if (shippingState.equals("shipped")) {
                        txtTotalAmount.setText("Dear" + userName + "\n" + "your order has been shipped successfully.");
                        recyclerView.setVisibility(View.GONE);
                        txtMsg1.setVisibility(View.VISIBLE);
                        txtMsg1.setText("Your order has been shipped.");
                        NextProcessButton.setVisibility(View.GONE);
                        Toast.makeText(CartActivity.this, "After shipped your order, you can purchase more products.", Toast.LENGTH_SHORT).show();

                    } else if (shippingState.equals("not shipped")) {
                        txtTotalAmount.setText("Shipping State : Not Shipped");
                        recyclerView.setVisibility(View.GONE);
                        txtMsg1.setVisibility(View.VISIBLE);
                        NextProcessButton.setVisibility(View.GONE);
                        Toast.makeText(CartActivity.this, "After shipped your order, you can purchase more products.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
