package com.example.xyzreader.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by xayru on 2/7/2018.
 */
@Database(entities = {Article.class}, version = 1)
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


}

