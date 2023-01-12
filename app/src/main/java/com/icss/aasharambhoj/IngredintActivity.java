package com.icss.aasharambhoj;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class IngredintActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<IngredintDataModel> ingredintDataModels;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredints_activity);
        recyclerView=findViewById(R.id.recycler_1);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        ingredintDataModels=new ArrayList<>();
        ingredintDataModels.add(new IngredintDataModel(R.drawable.chana_dal,"chana dal","55","Add","1kg "));
        ingredintDataModels.add(new IngredintDataModel(R.drawable.tuar_daal,"tuar dal","40","Add","2kg"));
        ingredintDataModels.add(new IngredintDataModel(R.drawable.chana_dal,"chana dal","50","Add","3kg"));
        ingredintDataModels.add(new IngredintDataModel(R.drawable.chana_dal,"chana dal","70","Add","4kg"));
        IngredintAdapter ingredintAdapter=new IngredintAdapter(ingredintDataModels,IngredintActivity.this
        );
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(ingredintAdapter);
    }
}
