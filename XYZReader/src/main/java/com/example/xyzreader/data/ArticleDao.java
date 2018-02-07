package com.example.xyzreader.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by xayru on 2/7/2018.
 */
@Dao
public class ArticleDao {

    public interface UserDao {

        @Query("SELECT * FROM articles")
        List<Article> getAricles();

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        public void insertUsers(Article... articles);

        @Update
        public void updateUsers(Article... articles);

        @Delete
        public void deleteUsers(Article... articles);
    }


}
