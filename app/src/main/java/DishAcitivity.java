import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.icss.aasharambhoj.R;

import java.util.ArrayList;
import java.util.List;

public class DishAcitivity extends AppCompatActivity {
    List<DishDataModel>dishDataModels;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chef_dish_quntity);
        recyclerView=findViewById(R.id.recycler);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(this,1);
        dishDataModels=new ArrayList<>();
        dishDataModels.add(new DishDataModel("dal","1kg","10"));
        dishDataModels.add(new DishDataModel("dal","1kg","10"));
        dishDataModels.add(new DishDataModel("dal","1kg","10"));
        dishDataModels.add(new DishDataModel("dal","1kg","10"));
        dishDataModels.add(new DishDataModel("dal","1kg","10"));
        dishDataModels.add(new DishDataModel("dal","1kg","10"));
        DishAdapter dishAdapter= new DishAdapter(dishDataModels,DishAcitivity.this);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(dishAdapter);



    }

}
