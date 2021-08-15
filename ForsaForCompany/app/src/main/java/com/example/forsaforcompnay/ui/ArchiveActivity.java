package com.example.forsaforcompnay.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forsaforcompnay.R;
import com.example.forsaforcompnay.adapters.ArchiveAdapter;
import com.example.forsaforcompnay.api.Api;
import com.example.forsaforcompnay.model.archive.ArchiveCompany;
import com.example.forsaforcompnay.model.archive.ItemDataArchiveCompany;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArchiveActivity extends AppCompatActivity {
    private static final String LOG = "MyArchiveActivity";
    RecyclerView RV_archive;
    ArrayList<ItemDataArchiveCompany> itemDataArchiveCompanyArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        init();
        getArchiveCompany();
    }

    private void init() {
        RV_archive = findViewById(R.id.RV_archive);
        RelativeLayout toolbar = findViewById(R.id.headerArchive);
        TextView title = toolbar.findViewById(R.id.titleActivity);
        TextView back = toolbar.findViewById(R.id.btnBack);
        title.setText(getString(R.string.archive));
    }

    private void getArchiveCompany() {
        itemDataArchiveCompanyArrayList = new ArrayList<>();
        Call<ArchiveCompany> archiveCompanyCall = Api.getINSTANCE(getApplicationContext()).getApiInterface().ARCHIVE_COMPANY_CALL();
        archiveCompanyCall.enqueue(new Callback<ArchiveCompany>() {
            @Override
            public void onResponse(Call<ArchiveCompany> call, Response<ArchiveCompany> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess() && response.body().getStatus() == 200) {
                        itemDataArchiveCompanyArrayList.addAll(response.body().getData().getItems());
                        ArchiveAdapter archiveAdapter = new ArchiveAdapter(getApplicationContext(), itemDataArchiveCompanyArrayList);
                        RV_archive.setAdapter(archiveAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArchiveCompany> call, Throwable t) {
                Log.d(LOG, "Error : " + t.getMessage());
            }
        });

    }
}