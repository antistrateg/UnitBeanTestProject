package com.unitbean.UnitBean.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.unitbean.UnitBean.R;
import com.unitbean.UnitBean.UI.BuilderUI;

public class ItemFragment extends Fragment {

    private View viewItemFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewItemFragment = inflater.inflate(R.layout.fragment_item, container, false);

        return viewItemFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = this.getArguments();
        new BuilderUI().buildItem(viewItemFragment, bundle);



    }


}
