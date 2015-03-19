package com.amg.android.recyclerviewtutorial.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amg.android.recyclerviewtutorial.R;
import com.amg.android.recyclerviewtutorial.data.client.ImgurClient;
import com.amg.android.recyclerviewtutorial.data.model.ImgurImage;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class Main extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ImgurListFragment())
                    .commit();
        }
    }

    public static class ImgurListFragment extends Fragment {

        private ImgurClient imgurClient;

        public ImgurListFragment(){}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_list, container, false);

            /* Step 2 - Add RecyclerView */

            /* Step 6 - Add RecyclerView Adapter  */


            imgurClient = ImgurClient.getInstance(getActivity().getApplicationContext());
            imgurClient.getCatGallery(new ImgurClient.ImgurClientInterface() {
                @Override
                public void onPostExecute(boolean success) {
                    List<ImgurImage> images = imgurClient.getImgurImages();
                    if (success && images != null) {
                        /* Step 7 - Add list to RecyclerView Adapter */
                    }
                }
            });

            return rootView;
        }

        /* Step 4 - Create RecyclerView ViewHolder  */



        /* Step 5 - Create RecyclerView Adapter Class  */

    }
}
