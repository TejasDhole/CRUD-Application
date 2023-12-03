package com.tejas.crudapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PostViewModel extends AndroidViewModel {

    private PostRepo repository;
    private LiveData<List<Post>> allPost;

    public PostViewModel(@NonNull Application application) {
        super(application);
        repository = new PostRepo(application);
        allPost = repository.getAllPost();
    }

    public void insert(Post post) {
        repository.insert(post);
    }

    public void update(Post post) {
        repository.update(post);
    }

    public void delete(Post post) {
        repository.delete(post);
    }



    public LiveData<List<Post>> getAllPost() {
        return allPost;
    }
}
