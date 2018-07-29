package com.example.shehrooz.foodish.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shehrooz.foodish.R;
import com.example.shehrooz.foodish.activity.MainActivity;
import com.example.shehrooz.foodish.activity.MenuActivity;
import com.example.shehrooz.foodish.model.GeneralFood;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.media.CamcorderProfile.get;
import static com.example.shehrooz.foodish.activity.CartActivity.grandTotal;
import static com.example.shehrooz.foodish.activity.CartActivity.priceAdjust;
import static com.example.shehrooz.foodish.activity.MainActivity.cartFoods;
import static com.example.shehrooz.foodish.activity.MainActivity.cartUpdate;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private CartAdapter mCartAdapter;


    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder holder, final int position) {
        holder.cartTitle.setText(cartFoods.get(position).getTitle());
        holder.cartPrice.setText((Double.toString((cartFoods.get(position).getPrice()))) + " Taka");
        Picasso.get().load(cartFoods.get(position).getImage()).fit().into(holder.cartPicture);


        holder.cartDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralFood item = cartFoods.get(position);
                cartFoods.remove(item);

                notifyItemRemoved(position);
                notifyItemRangeChanged(position, cartFoods.size());

                grandTotal(cartFoods);
                priceAdjust();

                cartUpdate();


            }});


    }

    @Override
    public int getItemCount() {
        return cartFoods.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView cartPicture;
        TextView cartTitle;
        TextView cartPrice;
        CardView cartParentLayout;
        ImageButton cartDelete;

        public CartViewHolder(View itemView) {
            super(itemView);

            cartPicture = itemView.findViewById(R.id.cart_food_pic);
            cartTitle = itemView.findViewById(R.id.cart_food_title);
            cartPrice = itemView.findViewById(R.id.cart_food_price);
            cartParentLayout = itemView.findViewById(R.id.cart_parent_layout);
            cartDelete = itemView.findViewById(R.id.cart_food_delete);

        }
    }

    public CartAdapter(List<GeneralFood> cartFoods, int recyclerview_cart, Context context){
        this.context = context;
        MainActivity.cartFoods = cartFoods;

    }


}
