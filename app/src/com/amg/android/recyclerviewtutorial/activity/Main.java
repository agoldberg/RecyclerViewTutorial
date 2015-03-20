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

            final RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.list);
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

            final ListAdapter listAdapter = new ListAdapter();
            recyclerView.setAdapter(listAdapter);

            imgurClient = ImgurClient.getInstance(getActivity().getApplicationContext());
            imgurClient.getCatGallery(new ImgurClient.ImgurClientInterface() {
                @Override
                public void onPostExecute(boolean success) {
                    List<ImgurImage> images = imgurClient.getImgurImages();
                    if (success && images != null) {
                        listAdapter.setList(images);
                    }
                }
            });

            return rootView;
        }

        private class ListViewHolder extends RecyclerView.ViewHolder {

            private NetworkImageView imageView;
            private TextView titleView;

            public ListViewHolder(View view){
                super(view);
                imageView = (NetworkImageView)view.findViewById(R.id.image);
                titleView = (TextView)view.findViewById(R.id.title);
            }

            public void populateFrom(ImgurImage imgurImage){
                imageView.setImageUrl(imgurImage.getLink(),imgurClient.getImageLoader());
                titleView.setText(imgurImage.getTitle());
            }
        }


        private class ListAdapter extends RecyclerView.Adapter<ListViewHolder>{

            private List <ImgurImage> imgurImages;

            public ListAdapter(){ }

            public void setList(List<ImgurImage> images){
                imgurImages = images;
                notifyDataSetChanged();
            }

            @Override
            public int getItemCount() {
                return imgurImages == null?0:imgurImages.size();
            }

            @Override
            public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                final View view = LayoutInflater.from(getActivity()).inflate(R.layout.card_view, parent, false);
                return new ListViewHolder(view);

            }

            @Override
            public void onBindViewHolder(ListViewHolder holder, int position) {
                holder.populateFrom(imgurImages.get(position));
            }

        }
    }
}
