package com.example.xyzreader.data;

import android.arch.lifecycle.LiveData;
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
public interface ArticleDao {

    @Query("SELECT * FROM articles_database ORDER BY title ASC")
    LiveData<List<Article>> getAricles();

    @Query("DELETE FROM articles")
    void deleteAll();

    @Query("SELECT * FROM articles_database WHERE _id = :id")
    Article loadArticleById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticles(List<Article> articles);

    @Update
    void updateUsers(Article... articles);

    @Delete
    void deleteUsers(Article... articles);

}



