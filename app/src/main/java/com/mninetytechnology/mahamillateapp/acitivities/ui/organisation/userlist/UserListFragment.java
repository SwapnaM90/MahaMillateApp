package com.mninetytechnology.mahamillateapp.acitivities.ui.organisation.userlist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mninetytechnology.mahamillateapp.acitivities.ui.organisation.OrganisationMainActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.user.MainActivity;
import com.mninetytechnology.mahamillateapp.adapters.LeaderboardListAdapter;
import com.mninetytechnology.mahamillateapp.adapters.OrganisationUserListAdapter;
import com.mninetytechnology.mahamillateapp.databinding.FragmentLeaderboardBinding;
import com.mninetytechnology.mahamillateapp.databinding.FragmentUserListBinding;
import com.mninetytechnology.mahamillateapp.databinding.SingleOrganisationUserBinding;
import com.mninetytechnology.mahamillateapp.models.contracts.LeaderboardContract;
import com.mninetytechnology.mahamillateapp.models.contracts.UserListContract;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Leaderboard;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.OrganisationLoginObject;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.UserLoginObject;
import com.mninetytechnology.mahamillateapp.presenter.LeaderboardPresenter;
import com.mninetytechnology.mahamillateapp.presenter.UserListPresenter;

import java.util.List;

public class UserListFragment extends Fragment implements UserListContract.ViewModel {
    private FragmentUserListBinding binding;
    private UserListPresenter presenter;
    private OrganisationUserListAdapter mAdapter;
    private OrganisationMainActivity mActivity;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUserListBinding.inflate(inflater, container, false);
        mActivity = (OrganisationMainActivity) getActivity();

        presenter = new UserListPresenter(mActivity, this);
        binding.setPresenter(presenter);
        presenter.getUserList();

        return binding.getRoot();
    }

    @Override
    public void setUpUserList(List<UserLoginObject> userList) {
        mAdapter = new OrganisationUserListAdapter(mActivity, userList, (listModel, position) -> {
            populateAlertDialog(listModel);
        });
        binding.rvLeaderboard.setLayoutManager(new LinearLayoutManager(mActivity));
        binding.rvLeaderboard.setAdapter(mAdapter);
    }

    private void populateAlertDialog(UserLoginObject listModel) {
        AlertDialog dialog = new AlertDialog.Builder(mActivity).create();
        SingleOrganisationUserBinding alertBinding = SingleOrganisationUserBinding.inflate(mActivity.getLayoutInflater());
        alertBinding.setUser(listModel);
        dialog.setView(alertBinding.getRoot());
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void showLeaderboardFailed(String error) {
        mActivity.showErrorSnackBar(binding.getRoot(), error);
    }

}
