package com.mninetytechnology.mahamillateapp.acitivities.ui.user;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.google.gson.Gson;
import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.organisation.OrganisationMainActivity;
import com.mninetytechnology.mahamillateapp.adapters.PaginationAdapter;
import com.mninetytechnology.mahamillateapp.custom.PaginationScrollListener;
import com.mninetytechnology.mahamillateapp.databinding.ActivityOrganisationSearchBinding;
import com.mninetytechnology.mahamillateapp.models.contracts.OrganisationContract;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.OrganisationLoginObject;
import com.mninetytechnology.mahamillateapp.network.responsemodel.OrganisationListResponseModel;
import com.mninetytechnology.mahamillateapp.network.retrofit.RetrofitClient;
import com.mninetytechnology.mahamillateapp.presenter.OrganisationPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrganisationSearchActivity extends BaseActivity implements OrganisationContract.ViewModel {
    private ActivityOrganisationSearchBinding binding;
    private PaginationAdapter paginationAdapter;
    private boolean isLoading;
    private int currentPage = 1;
    private boolean isLastPage = false;
    private OrganisationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_organisation_search);
        presenter = new OrganisationPresenter(this,this);
        binding.edtOrganisation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                paginationAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        paginationAdapter = new PaginationAdapter(this, new PaginationAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(OrganisationLoginObject listModel, int position) {
                presenter.changeOrganisation(listModel);
            }
        });
        binding.rvOrganisation.setLayoutManager(linearLayoutManager);
        binding.rvOrganisation.setAdapter(paginationAdapter);
        binding.rvOrganisation.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;
                loadNextPage();
            }

            @Override
            public Boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public Boolean isLoading() {
                return isLoading;
            }
        });

        loadFirstPage();
    }

    private void loadNextPage() {
        startProgressDialog(this);
        RetrofitClient.getApiService().getOrganisationListByPage(String.valueOf(currentPage)).enqueue(new Callback<OrganisationListResponseModel>() {
            @Override
            public void onResponse(Call<OrganisationListResponseModel> call, Response<OrganisationListResponseModel> response) {
                Log.e("TAG", "onResponse: "+response);
                dismissProgressDialog();
                if (response.body() != null && response.body().getData() != null) {
                    isLoading = false;

                    OrganisationListResponseModel results = response.body();
                    paginationAdapter.addAll(results.getData());
                } else {
                    showErrorSnackBar(binding.getRoot(),getString(R.string.invalid_response));
                }
            }

            @Override
            public void onFailure(Call<OrganisationListResponseModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void loadFirstPage() {
        startProgressDialog(this);
        RetrofitClient.getApiService().getOrganisationListByPage(String.valueOf(currentPage)).enqueue(new Callback<OrganisationListResponseModel>() {
            @Override
            public void onResponse(Call<OrganisationListResponseModel> call, Response<OrganisationListResponseModel> response) {
                Log.e("TAG", "onResponse: "+response);
                dismissProgressDialog();
                if (response.body() != null && response.body().getData() != null) {
                    isLoading = false;

                    OrganisationListResponseModel results = response.body();
                    paginationAdapter.addAll(results.getData());

                } else {
                    showErrorSnackBar(binding.getRoot(),getString(R.string.invalid_response));
                }
            }

            @Override
            public void onFailure(Call<OrganisationListResponseModel> call, Throwable t) {
                t.printStackTrace();
                dismissProgressDialog();
            }
        });
    }

    @Override
    public void organisationChanged(OrganisationLoginObject organisation) {
        Gson gson = new Gson();
        String org = gson.toJson(organisation);
        getGlobalHelper().getSharedPreferencesHelper().setOrganisationId(org);
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("organizationId",organisation.get_id());
        startActivity(intent);
        finish();
    }

    @Override
    public void showOrganisationLoginObjectFailed(String error) {
        showErrorSnackBar(binding.getRoot(),error);
    }
}