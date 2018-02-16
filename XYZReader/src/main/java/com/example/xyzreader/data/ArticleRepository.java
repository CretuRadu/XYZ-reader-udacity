package com.example.xyzreader.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by xayru on 2/16/2018.
 */

public class ArticleRepository {
    private ArticleDao articleDao;
    private LiveData<List<Article>> allArticles;

    ArticleRepository(Application application) {

        ArticleRoomDatabase database = ArticleRoomDatabase.getInstance(application);
        articleDao = database.articleDao();
        allArticles = articleDao.getAricles();
    }

    LiveData<List<Article>> getAllArticles() {
        return allArticles;
    }
    void deleteAll(){
        articleDao.deleteAll();
    }

    public void insert(List<Article> articles) {
        new insertAsyncTask(articleDao).execute(articles);

    }

    private static class insertAsyncTask extends AsyncTask<List<Article>, Void, Void> {
        private ArticleDao asyncArticleDao;

        insertAsyncTask(ArticleDao asyncArticleDao) {
            this.asyncArticleDao = asyncArticleDao;
        }

        @Override
        protected Void doInBackground(final List<Article>[] articles) {
            asyncArticleDao.insertArticles(articles[0]);
            return null;
        }
    }

}
