package com.electro.todolistnodb;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView todoListView;
    EditText todoListItem;
    Button addToListBtn;

    ArrayList<String> listItems;
    ArrayAdapter<String> listItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        todoListView = (ListView) findViewById(R.id.todoListView);
        todoListItem = (EditText) findViewById(R.id.todoTextInput);
        addToListBtn = (Button) findViewById(R.id.addItemButton);

        listItems = new ArrayList<String>();
        listItemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);

        todoListView.setAdapter(listItemsAdapter);

        ArrayList<String> exampleItems = new ArrayList<>(Arrays.asList("First Item", "Second Item"));

        listItems.addAll(exampleItems);

        initListViewEventListener();
    }

    public void addItemToLisView(View view) {
        String inputText = todoListItem.getText().toString();

        System.out.println(inputText);

        if (!inputText.isEmpty()){
            listItemsAdapter.add(inputText);

            todoListItem.getText().clear();
        } else {
            Toast.makeText(this, "Please input item before trying to add it to the list", Toast.LENGTH_SHORT).show();
        }
    }


    public void initListViewEventListener(){
        todoListView.setOnItemLongClickListener(
                (adapter, item, pos, id) -> {
                    listItems.remove(pos);

                    listItemsAdapter.notifyDataSetChanged();

                    Toast.makeText(this, "Item Removed Successfully", Toast.LENGTH_SHORT).show();

                    return true;
                }
        );
    }
}