package com.tejas.crudapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends ListAdapter<Post, PostAdapter.PostHolder> {

    private List<Post> posts = new ArrayList<>();
    private OnItemClickListener listener;
    private PostViewModel postViewModel;

    protected PostAdapter(PostViewModel postViewModel) {
        super(CALLBACK);
        this.postViewModel = postViewModel;
    }

    private static final DiffUtil.ItemCallback<Post> CALLBACK = new DiffUtil.ItemCallback<Post>() {
        @Override
        public boolean areItemsTheSame(@NonNull Post oldItem, @NonNull Post newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Post oldItem, @NonNull Post newItem) {
            return oldItem.getTitle().equals(newItem.getTitle())
                    && oldItem.getDescription().equals(newItem.getDescription());
        }
    };

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item, parent, false);
        return new PostHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        Post currentPost = posts.get(position);
        holder.textViewTitle.setText(currentPost.getTitle());
        holder.textViewDescription.setText(currentPost.getDescription());
        holder.likeCount.setText(String.valueOf(currentPost.getLikectn()));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    public Post getPostAt(int position) {
        return posts.get(position);
    }

    class PostHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;

        private TextView textViewDescription;
        private Button readmorebtn;
        private CheckBox likeBtn;
        private CheckBox dislikeBtn;
        private TextView likeCount;
        private TextView dislikeCount;


        public PostHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            readmorebtn = itemView.findViewById(R.id.ReadMore);
            likeBtn = itemView.findViewById(R.id.likebtn);
            dislikeBtn = itemView.findViewById(R.id.dislikebtn);
            likeCount = itemView.findViewById(R.id.likecount);
            dislikeCount = itemView.findViewById(R.id.dislikecount);

            likeBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int position = getAdapterPosition();
                    Post currentPost = posts.get(position);
                    if (isChecked){
                        System.out.println("like checked");
                        int likecout = currentPost.getLikectn()+1;
                        currentPost.setLikectn(likecout);
                        postViewModel.update(currentPost);
                        System.out.println( currentPost.getLikectn());
                        dislikeBtn.setChecked(false);


                    }
                    else {
                        System.out.println("like uncheaked");
                        int likecout = currentPost.getLikectn()-1;
                        currentPost.setLikectn(likecout);
                        postViewModel.update(currentPost);
                        System.out.println( currentPost.getLikectn());
                    }
                }
            });


            dislikeBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        int count = Integer.parseInt(dislikeCount.getText().toString());
                        dislikeCount.setText(String.valueOf(count + 1));

                        likeBtn.setChecked(false);
                    } else {
                        int count = Integer.parseInt(dislikeCount.getText().toString());

                            dislikeCount.setText(String.valueOf(count - 1));

                    }
                }
            });
            readmorebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(posts.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Post post);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
