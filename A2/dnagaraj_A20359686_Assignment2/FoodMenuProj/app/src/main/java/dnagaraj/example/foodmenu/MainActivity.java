package dnagaraj.example.foodmenu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import dnagaraj.example.foodmenu.adapters.FoodItemsAdapter;
import dnagaraj.example.foodmenu.models.FoodItemModel;

public class MainActivity extends AppCompatActivity implements NewItemDialog.OnNewItemAddeedListener,AdapterView.OnItemLongClickListener,View.OnClickListener,AdapterView.OnItemClickListener{

    private ListView lsFood;
    FoodItemsAdapter adapter;
    ArrayList<FoodItemModel> items = new ArrayList<>();
    FloatingActionButton fab ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        lsFood = (ListView) findViewById(R.id.listFood);
        fab.setOnClickListener(this);
        lsFood.setOnItemLongClickListener(this);
        lsFood.setOnItemClickListener(this);
        items.add(new FoodItemModel(R.drawable.celebration_bunde1, "Celebration of Bundle", "2 Chicken Prosperity, 2 French Fries (S), 3 Apple Pie, 2 Yuzu McFizz", 45));
        items.add(new FoodItemModel(R.drawable.grand_celeb,"Grand Celebration  Bundle","2 Chicken Prosperity, Chicken McNuggets (20pc), 5 Apple Pie, 2 French Fries (S), Sundae - Hot Fudge, 3 Coca-Cola Light (S)",65));
        items.add(new FoodItemModel(R.drawable.msd_bundle,"Grand Celebration  Bundle",
                "3 McChicken, 2 Cheeseburger, 1 Double Cheeseburger, 4 French Fries (S), 3 Sundae - Hot Fudge,Chicken McNuggets (20pc),3 Apple Pie, 3 Coca-Cola Light (S), 3 Iced MILO (S)",75));
        items.add(new FoodItemModel(R.drawable.bundle1,"Breakfast  Bundle 1","Breakfast Deluxe, Hotcakes, Egg McMuffin, Chicken Muffin, Hashbrowns, Cappuccino (M), Iced MILO,   ",17.95f));
        items.add(new FoodItemModel(R.drawable.bundle2,"Breakfast  Bundle 2","Big Breakfast, Hotcakes, Egg McMuffin, Chicken Muffin, 3 Hashbrowns, 2 Cappuccino (M),  2 Iced MILO",29.95f));
        items.add(new FoodItemModel(R.drawable.burger,"Egg Burger","Egg Burger, 4 French Fries (S), 1 Hashbrowns, 1 Cappuccino (M)", 8.55f));
        items.add(new FoodItemModel(R.drawable.turkey,"Sandwich","Turkey, Bread, 2 Hashbrowns, 1 Cappuccino (M), 1 Apple Pie",10.05f));

        adapter = new FoodItemsAdapter(items,this);
        lsFood.setAdapter(adapter);
    }

    @Override
    public void onNewItemAdded(String name, String descrption, float price, int drawableId) {
        items.add(new FoodItemModel(drawableId,name,descrption,price));
        adapter.notifyDataSetChanged();
        Snackbar.make(lsFood, "New Food Item Added!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(MainActivity.this,"Item "+items.get(position).getName()+" removed",Toast.LENGTH_SHORT).show();
        items.remove(position);
        adapter.notifyDataSetChanged();
        return false;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fab){
            NewItemDialog newItemDialog = new NewItemDialog();
            newItemDialog.setNewItemAddeedListener(MainActivity.this);
            newItemDialog.show(getSupportFragmentManager(),"new_item");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(MainActivity.this,"Items: "+items.get(position).getDescription(),Toast.LENGTH_SHORT).show();
    }
}
