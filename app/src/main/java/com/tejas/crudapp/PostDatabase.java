package com.tejas.crudapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Post.class},version = 2)
public abstract class PostDatabase extends RoomDatabase {

        private static PostDatabase instance;
        

        public abstract PostDao postDao();

        public static synchronized PostDatabase getInstance(Context context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                                PostDatabase.class, "post_database")
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build();
            }
            return instance;
        }

        private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                new PopulateDbAsyncTask(instance).execute();
            }
        };

private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
    private PostDao postDao;

    private PopulateDbAsyncTask(PostDatabase db) {
        postDao = db.postDao();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        postDao.insert(new Post("The SunSet",  "The sun dipped below the horizon, casting a warm orange glow across the sky, signaling the end of another day. As evening settled in, the city came alive with a symphony of sounds—cars honking in the distance, people chatting as they walked along the streets, and the occasional laughter echoing from nearby cafes. Amidst the bustling cityscape, a sense of tranquility lingered, embracing the beauty of the fading daylight and the promise of a new dawn.", "Tejas Dhole",5));
        postDao.insert(new Post("The SunSet",  "The sun dipped below the horizon, casting a warm orange glow across the sky, signaling the end of another day. As evening settled in, the city came alive with a symphony of sounds—cars honking in the distance, people chatting as they walked along the streets, and the occasional laughter echoing from nearby cafes. Amidst the bustling cityscape, a sense of tranquility lingered, embracing the beauty of the fading daylight and the promise of a new dawn.", "Tejas Dhole",7));
        postDao.insert(new Post("The SunSet",  "The sun dipped below the horizon, casting a warm orange glow across the sky, signaling the end of another day. As evening settled in, the city came alive with a symphony of sounds—cars honking in the distance, people chatting as they walked along the streets, and the occasional laughter echoing from nearby cafes. Amidst the bustling cityscape, a sense of tranquility lingered, embracing the beauty of the fading daylight and the promise of a new dawn.", "Tejas Dhole",8));
        return null;
    }
}
}
