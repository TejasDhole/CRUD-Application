package com.tejas.crudapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.tejas.crudapp.databinding.ActivityViewPostBinding;

import java.util.List;

public class ViewPostActivity extends AppCompatActivity {
    ActivityViewPostBinding binding;
    private String title;
    private String description;
    private String author;
    private PostViewModel postViewModel;
    int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        PostAdapter adapter = new PostAdapter();
//binding.toolbar.setTitleMarginStart(400);
        binding.toolbar.setTitle("VIEW POST");
        postViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(PostViewModel.class);
        postViewModel.getAllPost().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(@Nullable List<Post> posts) {
                adapter.setPosts(posts);
            }
        });

        Intent intent = getIntent();
        ID = intent.getIntExtra("id", -1);
        title = intent.getStringExtra("title");
        description = intent.getStringExtra("description");
        author = intent.getStringExtra("author");
        binding.textTitle.setText(title);
        binding.textDescription.setText(description);
        binding.Author.setText(author);

        binding.editbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ViewPostActivity.this, AddPostActivity.class);
                intent1.putExtra("title", title);
                intent1.putExtra("description", description);
                intent1.putExtra("id", ID);
                intent1.putExtra("author",author);
                startActivityForResult(intent1, 3);
            }
        });

        Post post = new Post(title, description, author);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        binding.deleteBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.setTitle("Are You Sure?")
                                .setMessage("Are you sure you want to delete the post")
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                post.setId(ID);
                                                postViewModel.delete(post);
                                                Intent intent1 = new Intent(ViewPostActivity.this, MainActivity.class);
                                                startActivity(intent1);
                                                finish();
                                            }
                                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();

            }
        });

        binding.likebtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    int count = Integer.parseInt(binding.likecount.getText().toString());
                    binding.likecount.setText(String.valueOf(count + 1));
                    binding.dislikebtn.setChecked(false);
                } else {
                    int count = Integer.parseInt(binding.likecount.getText().toString());

                    binding.likecount.setText(String.valueOf(count - 1));

                }
            }
        });

        binding.dislikebtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    int count = Integer.parseInt(binding.dislikecount.getText().toString());
                    binding.dislikecount.setText(String.valueOf(count + 1));
                    binding.likebtn.setChecked(false);
                } else {
                    int count = Integer.parseInt(binding.dislikecount.getText().toString());

                    binding.dislikecount.setText(String.valueOf(count - 1));

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home_menu,menu);
        return true;
    }

    //    public boolean onCreateOptionMenu(Menu menu){
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.home_menu,menu);
//        return true;
//    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.home_btn){

            startActivity( new Intent(this,MainActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3 && resultCode == RESULT_OK) {
            int id = data.getIntExtra("id", -1);

            if (id == -1) {
                Toast.makeText(this, "Post can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String title = data.getStringExtra("title");
            String description = data.getStringExtra("description");
            String author = data.getStringExtra("author");
binding.textTitle.setText(title);
binding.textDescription.setText(description);
binding.Author.setText(author);
            Post post = new Post(title, description, author);
            post.setId(id);
            postViewModel.update(post);

            Toast.makeText(this, "Post updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Post not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
