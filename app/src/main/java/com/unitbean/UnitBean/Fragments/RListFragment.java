package com.unitbean.UnitBean.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.unitbean.UnitBean.R;
import com.unitbean.UnitBean.UI.BuilderUI;

public class RListFragment extends Fragment {

    private View viewRListFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewRListFragment = inflater.inflate(R.layout.fragment_rlist, container, false);

        return viewRListFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Context context = getContext();
        new BuilderUI().buildRList(viewRListFragment, context);

    }

}
