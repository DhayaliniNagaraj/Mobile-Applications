package dnagaraj.example.foodmenu.models;

/**
 * Created by dhayalini on 12-02-2016.
 */
public class FoodItemModel {

    private int drawableId;
    private String name;
    private String description;
    private float price;
    private boolean isSpicy;
    private String materials;

    public FoodItemModel(int drawableId, String name, float price) {
        this.drawableId = drawableId;
        this.name = name;
        this.price = price;
    }

    public FoodItemModel(int drawableId, String name, String description,float price) {
        this.drawableId = drawableId;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public FoodItemModel(int drawableId, String name, String description, int price, boolean isSpicy, String materials) {
        this.drawableId = drawableId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isSpicy = isSpicy;
        this.materials = materials;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isSpicy() {
        return isSpicy;
    }

    public void setSpicy(boolean spicy) {
        isSpicy = spicy;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }
}
