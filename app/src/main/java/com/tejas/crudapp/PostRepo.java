package com.tejas.crudapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PostRepo {

        private PostDao postDao;
        private LiveData<List<Post>> allPost;

        public PostRepo(Application application) {
            PostDatabase database = PostDatabase.getInstance(application);
            postDao = database.postDao();
            allPost = postDao.getAllPost();
        }

        public void insert(Post post) {
            new InsertPostAsyncTask(postDao).execute(post);
        }

    public void update(Post post) {
        new UpdatePostAsyncTask(postDao).execute(post);
    }

    public void delete(Post post) {
        new DeletePostAsyncTask(postDao).execute(post);
    }



        public LiveData<List<Post>> getAllPost() {
            return allPost;
        }

        private static class InsertPostAsyncTask extends AsyncTask<Post, Void, Void> {
            private PostDao postDao;

            private InsertPostAsyncTask(PostDao postDao) {
                this.postDao = postDao;
            }

            @Override
            protected Void doInBackground(Post... posts) {
                postDao.insert(posts[0]);
                return null;
            }
        }

    private static class UpdatePostAsyncTask extends AsyncTask<Post, Void, Void> {
        private PostDao postDao;

        private UpdatePostAsyncTask(PostDao postDao) {
            this.postDao = postDao;
        }

        @Override
        protected Void doInBackground(Post... posts) {
            postDao.update(posts[0]);
            return null;
        }
    }

    private static class DeletePostAsyncTask extends AsyncTask<Post, Void, Void> {
        private PostDao postDao;

        private DeletePostAsyncTask(PostDao postDao) {
            this.postDao = postDao;
        }

        @Override
        protected Void doInBackground(Post... posts) {
            postDao.delete(posts[0]);
            return null;
        }
    }




}
