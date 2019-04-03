package com.unitbean.UnitBean.UI;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.unitbean.UnitBean.Adapters.CommentAdapter;
import com.unitbean.UnitBean.Adapters.RListAdapter;
import com.unitbean.UnitBean.R;
import java.io.IOException;
import java.net.URL;
import static com.unitbean.UnitBean.Utils.Auxiliary.stringAdjust;
import static com.unitbean.UnitBean.Utils.JsonUtils.getJsonEmailArray;
import static com.unitbean.UnitBean.Utils.JsonUtils.getJsonNameArray;
import static com.unitbean.UnitBean.Utils.JsonUtils.getJsonPostIdArray;
import static com.unitbean.UnitBean.Utils.NetworkUtils.generateURLcomments;
import static com.unitbean.UnitBean.Utils.NetworkUtils.getResponseFromURL;
import static com.unitbean.UnitBean.Utils.JsonUtils.getJsonContentArray;
import static com.unitbean.UnitBean.Utils.NetworkUtils.generateURLposts;
import static com.unitbean.UnitBean.Utils.JsonUtils.getJsonTitleArray;
import static com.unitbean.UnitBean.Utils.Auxiliary.firstLetterToUpperCase;

public class BuilderUI {

    private RecyclerView rList, rComment;
    private ProgressBar progressBar;
    private Context context;

    public void buildRList(View view, Context context){
        this.context = context;

        URL generatedURL = generateURLposts();
        new QueryForPosts().execute(generatedURL);

        progressBar = view.findViewById(R.id.pbLoading);
        rList = view.findViewById(R.id.rvList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        rList.setLayoutManager(layoutManager);
        rList.setHasFixedSize(true);

    }

    public void buildItem(View view, Bundle bundle){

        rComment = view.findViewById(R.id.rvComment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        rComment.setLayoutManager(layoutManager);
        rComment.setHasFixedSize(true);

        if (bundle != null) {
            String postId = bundle.getString("postId", "0");
            String title = bundle.getString("title", "");
            String content = bundle.getString("content", "");

            URL generatedURL = generateURLcomments(postId);
            new QueryForComments().execute(generatedURL);

            TextView tvTitle = view.findViewById(R.id.tvTitle);
            TextView tvContent = view.findViewById(R.id.tvContent);
            tvTitle.setText(firstLetterToUpperCase(title));

            content = stringAdjust(content);
            tvContent.setText(firstLetterToUpperCase(content));
        }


    }

    private class QueryForPosts extends QueryTask {

        @Override
        protected void onPostExecute(String response) {
            //super.onPostExecute(response);
            if(response != null && !response.equals("")){
                String[] postIdArray = getJsonPostIdArray(response);
                String[] titleArray = getJsonTitleArray(response);
                String[] contentArray = getJsonContentArray(response);

                RListAdapter rListAdapter = new RListAdapter(titleArray.length,
                        postIdArray, titleArray, contentArray, context);
                rList.setAdapter(rListAdapter);
            }
            else {
                Toast.makeText(context,"Connection error. Please, try again",Toast.LENGTH_LONG).show();
            }
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    private class QueryForComments extends QueryTask {

        @Override
        protected void onPostExecute(String response) {
            //super.onPostExecute(response);
            Log.d("MyLog", "Try");
            if(response != null && !response.equals("")){
                String[] userName = getJsonEmailArray(response);
                String[] userComment = getJsonNameArray(response);

                CommentAdapter commentAdapter = new CommentAdapter(userName.length,
                        userName, userComment, context);
                rComment.setAdapter(commentAdapter);
            }
            else {
                Log.d("MyLog", "Connection error");
            }

        }
    }

    private static class QueryTask extends AsyncTask<URL,Void,String> {

        @Override
        protected String doInBackground(URL... urls) {
            String response = null;

            try{
                response = getResponseFromURL(urls[0]);
            } catch (IOException e){
                e.printStackTrace();
            }
            return response;
        }

    }




}
