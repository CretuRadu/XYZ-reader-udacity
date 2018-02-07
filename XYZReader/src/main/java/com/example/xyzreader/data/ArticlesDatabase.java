package com.example.xyzreader.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

/**
 * Created by xayru on 2/7/2018.
 */
@Database(entities = {Articles.class}, version = 2)
public abstract class ArticlesDatabase extends RoomDatabase {

    private static final String DB_NAME ="articlesData.db";
    private static ArticlesDatabase instance;
    public abstract ArticleDao articleDao();

    static synchronized ArticlesDatabase getInstance( Context context){
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }
    private static ArticlesDatabase create(final Context context){
        return Room
                .databaseBuilder(context,ArticlesDatabase.class,DB_NAME)
                .addMigrations(MIGRATION_1_2)
                .build();
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
// Since we didn't alter the table, there's nothing else to do here.
        }
    };

}

