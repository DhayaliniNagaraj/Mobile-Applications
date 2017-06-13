package dnagaraj.example.foodmenu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import dnagaraj.example.foodmenu.R;
import dnagaraj.example.foodmenu.models.FoodItemModel;

/**
 * Created by dhayalini on 12-02-2016.
 */
public class FoodItemsAdapter  extends BaseAdapter{

    ArrayList<FoodItemModel> foodItems;
    Context context;
    private LayoutInflater inflater;
    public FoodItemsAdapter(ArrayList<FoodItemModel> foodItems,Context context){
        this.foodItems = foodItems;
        this.context = context;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return foodItems.size();
    }

    @Override
    public Object getItem(int position) {
        return foodItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FoodItemHolder holder = null;
       if(convertView == null){
           convertView = inflater.inflate(R.layout.item_food,parent,false);
           holder = new FoodItemHolder(convertView);
           convertView.setTag(holder);
       }else{
           holder = (FoodItemHolder) convertView.getTag();
       }
        holder.imgFood.setImageDrawable(context.getResources().getDrawable(foodItems.get(position).getDrawableId()));
        holder.tvName.setText(foodItems.get(position).getName());
        holder.tvPrice.setText("$"+foodItems.get(position).getPrice());



        return convertView;
    }

    static class FoodItemHolder{
        ImageView imgFood;
        TextView tvName;
        TextView tvPrice;

        FoodItemHolder(View view){
            imgFood = (ImageView) view.findViewById(R.id.imgFood);
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvPrice = (TextView) view.findViewById(R.id.tvPrice);
        }
    }
}
