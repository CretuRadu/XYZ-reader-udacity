package com.example.xyzreader.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by xayru on 2/7/2018.
 */
@Database(entities = {Article.class}, version = 4)
public abstract class ArticleRoomDatabase extends RoomDatabase {

    private static final String DB_NAME ="articles_database";
    private static ArticleRoomDatabase INSTANCE;
    public abstract ArticleDao articleDao();

    static synchronized ArticleRoomDatabase getInstance(final Context context){
    if (INSTANCE == null) {
            synchronized (ArticleRoomDatabase.class){
                if(INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),ArticleRoomDatabase.class,DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static ArticleRoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<List<Article>,Void,Void>{
        private  final ArticleDao mDao;

        PopulateDbAsync(ArticleRoomDatabase database){
            mDao = database.articleDao();
        }

        @Override
        protected Void doInBackground(List<Article> []articles) {
            mDao.deleteAll();
            mDao.insertArticles(articles[0]);
            return null;
        }
    }


}

