package com.unitbean.UnitBean;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import com.unitbean.UnitBean.Adapters.RListAdapter.recyclerViewItemClickeble;
import com.unitbean.UnitBean.Fragments.ItemFragment;
import com.unitbean.UnitBean.Fragments.RListFragment;

public class MainActivity extends AppCompatActivity implements recyclerViewItemClickeble {

    private RListFragment rListFragment;
    private ItemFragment itemFragment;
    private FragmentTransaction fTrans;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();

        rListFragment = new RListFragment();

        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.add(R.id.mainFrameLayout, rListFragment);
        fTrans.commit();

    }

    @Override
    public void onClickRecyclerViewItem(String postId, String title, String content) {

        //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        actionBar.setDisplayHomeAsUpEnabled(true);
        itemFragment = new ItemFragment();

        Bundle bundle = new Bundle();
        bundle.putString("postId", postId);
        bundle.putString("title", title);
        bundle.putString("content", content);
        itemFragment.setArguments(bundle);

        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.hide(rListFragment);
        fTrans.add(R.id.mainFrameLayout, itemFragment);
        fTrans.addToBackStack(null);
        fTrans.commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        actionBar.setDisplayHomeAsUpEnabled(false);
    }

}


