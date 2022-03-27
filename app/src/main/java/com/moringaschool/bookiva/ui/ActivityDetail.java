package com.moringaschool.bookiva.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.bookiva.ActivityMoreDetail;
import com.moringaschool.bookiva.ActivitySearchBooks;
import com.moringaschool.bookiva.R;
import com.moringaschool.bookiva.adapters.Rv1_adapter;
import com.moringaschool.bookiva.adapters.Rv2_adapter;
import com.moringaschool.bookiva.client.BookApi;
import com.moringaschool.bookiva.client.EditorsBookClient;
import com.moringaschool.bookiva.client.RecommendedBookClient;
import com.moringaschool.bookiva.model.Items;
import com.moringaschool.bookiva.model.ResponseBooks;

import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivityDetail extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ActivityDetail.class.getSimpleName();
    Button title;
    CardView mFakeSearchView ;
    RecyclerView mRecyclerView;
    RecyclerView mRecyclerView2;


//    private TextView titles = (TextView)findViewById(R.id.titles);
    List<Items> items;
    private Rv1_adapter mAdapter;
    private Rv2_adapter mAdapter2;

    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_detail);
        mFakeSearchView = (CardView)findViewById(R.id.searchView);
        mRecyclerView = (RecyclerView)findViewById(R.id.rvRecommended);
        mRecyclerView2 =(RecyclerView)findViewById(R.id.rvRandom);
//        title = (Button)findViewById(R.id.titles);
        mFakeSearchView.setOnClickListener(this);
//        titles.setOnClickListener(this);

        BookApi client = RecommendedBookClient.getRecommendedBookClient();
        Call<ResponseBooks> call = client.getRecommendedBook("books");

        //first call of Recommended Books

        call.enqueue(new Callback<ResponseBooks>() {
            @Override
            public void onResponse(Call<ResponseBooks> call, Response<ResponseBooks> response) {
                if(response.isSuccessful()){
                    items = response.body().getItems();

                    mAdapter = new Rv1_adapter(ActivityDetail.this, items);

                    layoutManager = new LinearLayoutManager(ActivityDetail.this, LinearLayoutManager.HORIZONTAL, true);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setAdapter(mAdapter);

                    mAdapter.OnRecyclerViewClickListener(new Rv1_adapter.OnRecyclerViewClickListener() {
                        @Override
                        public void OnItemClick(int position) {

                            Toast.makeText(ActivityDetail.this, "clicked", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ActivityDetail.this, ActivityMoreDetail.class);
                            intent.putExtra("rate", items.get(position).getVolumeInfo().getRatingsCount());
                            intent.putExtra("pageCount", items.get(position).getVolumeInfo().getPageCount());
                            intent.putExtra("publishedDate", items.get(position).getVolumeInfo().getPublishedDate());
                            intent.putExtra("bookTitle", items.get(position).getVolumeInfo().getTitle());
                            intent.putExtra("ImageViewMoreDetail", items.get(position).getVolumeInfo().getImageLinks().getSmallThumbnail());
                            intent.putExtra("descriptionOfBook", items.get(position).getVolumeInfo().getDescription());


                            startActivity(intent);

                        }
                    });

                }
                else {
                    showFailureMessage();
                }
            }

            @Override
            public void onFailure(Call<ResponseBooks> call, Throwable t) {
                Log.d(TAG, "onFailure: Happened😭 on call1 " + t );
            }
        });

        //second call of editors
        BookApi client2 = EditorsBookClient.getEditorsBookClient();
        Call<ResponseBooks> call2 = client.geteditorsBook("books");

        call2.enqueue(new Callback<ResponseBooks>() {
            @Override
            public void onResponse(Call<ResponseBooks> call, Response<ResponseBooks> response) {
                if(response.isSuccessful()){
                    items = response.body().getItems();
                    mAdapter2 = new Rv2_adapter(ActivityDetail.this,items);
                    layoutManager = new LinearLayoutManager(ActivityDetail.this, LinearLayoutManager.HORIZONTAL, true);
                    mRecyclerView2.setLayoutManager(layoutManager);
                    mRecyclerView2.setAdapter(mAdapter2);
                }
                else {
                    showFailureMessage();
                }
            }

            @Override
            public void onFailure(Call<ResponseBooks> call, Throwable t) {
                Log.d(TAG, "onFailure: call2 " + t);
            }
        });
    }
    // open fragment that will show more detail of item clicked.
    private void OpenFragment() {


    }
    // show error message incase of no response
    private void showFailureMessage() {
    }


    @Override
    public void onClick(View v) {
        if(v == mFakeSearchView){
            Intent i = new Intent(ActivityDetail.this, ActivitySearchBooks.class);
            startActivity(i);
        }


    }
}