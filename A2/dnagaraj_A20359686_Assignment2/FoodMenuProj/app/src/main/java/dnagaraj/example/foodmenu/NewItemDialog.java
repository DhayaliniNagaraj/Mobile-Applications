package dnagaraj.example.foodmenu;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by dhayalini on 13-02-2016.
 */
public class NewItemDialog extends DialogFragment{

    OnNewItemAddeedListener newItemAddeedListener;
    Button btnAdd;
    EditText edtPrice,edtName,edtDesc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle("New Food Item");
        View rootView = inflater.inflate(R.layout.layout_new_item,container,false);
        btnAdd = (Button) rootView.findViewById(R.id.btnAdd);
        edtDesc = (EditText) rootView.findViewById(R.id.edtDesc);
        edtName = (EditText) rootView.findViewById(R.id.edtName);
        edtPrice = (EditText) rootView.findViewById(R.id.edtPrice);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newItemAddeedListener != null){
                    String name = edtName.getText().toString();
                    String desc = edtDesc.getText().toString();
                    String price = edtPrice.getText().toString();
                    if("".equals(name) || "".equals(desc) || "".equals(price)){
                        Toast.makeText(getActivity(),"Please enter all mandatory fields!", Toast.LENGTH_SHORT).show();
                    }else{
                        float foodPrice = 0;
                        try{
                            foodPrice = Float.valueOf(edtPrice.getText().toString());
                        }catch (NumberFormatException exception){

                        }
                        newItemAddeedListener.onNewItemAdded(edtName.getText().toString(),edtDesc.getText().toString(),foodPrice,R.drawable.default_icon);
                        dismiss();
                    }

                }
            }
        });
        return rootView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setTitle("New Food Item");
        return dialog;
    }

    public OnNewItemAddeedListener getNewItemAddeedListener() {
        return newItemAddeedListener;
    }

    public void setNewItemAddeedListener(OnNewItemAddeedListener newItemAddeedListener) {
        this.newItemAddeedListener = newItemAddeedListener;
    }

    public interface OnNewItemAddeedListener{
        void onNewItemAdded(String name,String descrption,float price, int drawableId);
    }
}
