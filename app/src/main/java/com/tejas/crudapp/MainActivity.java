package com.tejas.crudapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tejas.crudapp.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_POST_REQUEST = 1;
    public static final int EDIT_POST_REQUEST = 2;

    private PostViewModel postViewModel;
    private PostAdapter adapter;

ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.myToolbar.setTitle("All POST");
        binding.myToolbar.setTitleMarginStart(400);




        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddPostActivity.class);
                startActivityForResult(intent, ADD_POST_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);



        postViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(PostViewModel.class);

        PostAdapter adapter = new PostAdapter(postViewModel);
        recyclerView.setAdapter(adapter);
        postViewModel.getAllPost().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(@Nullable List<Post> posts) {
                adapter.setPosts(posts);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                postViewModel.delete(adapter.getPostAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Post deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new PostAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Post post) {
                Intent intent = new Intent(MainActivity.this, ViewPostActivity.class);
                intent.putExtra("id", post.getId());
                intent.putExtra("title", post.getTitle());
                intent.putExtra("description", post.getDescription());
                intent.putExtra("author",post.getAuthor());
                intent.putExtra("likectn",post.getLikectn());

                startActivityForResult(intent, EDIT_POST_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_POST_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra("title");
            String description = data.getStringExtra("description");
            String author = data.getStringExtra("author");
            int likeCtn = data.getIntExtra("likectn",-1);


            Post post = new Post(title, description, author,likeCtn);
            postViewModel.insert(post);

            Toast.makeText(this, "Post saved", Toast.LENGTH_SHORT).show();
        }
    }


}
