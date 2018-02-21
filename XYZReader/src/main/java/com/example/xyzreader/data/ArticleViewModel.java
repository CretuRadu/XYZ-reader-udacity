package com.example.xyzreader.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by xayru on 2/16/2018.
 */

public class ArticleViewModel extends AndroidViewModel {
    private ArticleRepository articleRepository;
    private LiveData<List<Article>> allArticles;
    private Article article;

    public ArticleViewModel(Application application) {
        super(application);
        articleRepository = new ArticleRepository(application);
        allArticles = articleRepository.getAllArticles();
    }


    public LiveData<List<Article>> getAllArticles() {
        return allArticles;
    }

    public Article getArticle(int id) {
        return articleRepository.getArticle(id);
    }

    public void insert(List<Article> articles) {
        articleRepository.insert(articles);
    }
}
