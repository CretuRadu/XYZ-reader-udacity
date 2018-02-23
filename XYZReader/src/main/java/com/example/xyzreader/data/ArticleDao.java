package com.example.xyzreader.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by xayru on 2/7/2018.
 */
@Dao
public interface ArticleDao {

    @Query("SELECT * FROM articles_database")
    LiveData<List<Article>> getArticles();

    @Query("DELETE FROM articles_database")
    void deleteAll();

    @Query("SELECT * FROM articles_database WHERE id = :id")
    Article getArticleById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticles(List<Article> articles);


}



