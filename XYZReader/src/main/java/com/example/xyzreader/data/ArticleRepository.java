package com.example.xyzreader.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

/**
 * Created by xayru on 2/16/2018.
 */

public class ArticleRepository {
    private Article mArticle;
    private ArticleDao articleDao;
    private LiveData<List<Article>> allArticles;

    public ArticleRepository(Application application) {

        ArticleRoomDatabase database = ArticleRoomDatabase.getInstance(application);
        articleDao = database.articleDao();
        allArticles = articleDao.getArticles();
    }

    LiveData<List<Article>> getAllArticles() {
        return allArticles;
    }
    void deleteAll(){
        articleDao.deleteAll();
    }

   public Article getArticle(int id) {
        new getArticleAsync(articleDao).execute( id);
        return mArticle;
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

    private class getArticleAsync extends AsyncTask<Integer, Void, Article> {
        private ArticleDao asyncArticleDao;
        private static final String TAG = "getArticleAsync";

        getArticleAsync(ArticleDao asyncArticleDao) {
            this.asyncArticleDao = asyncArticleDao;
        }

        @Override
        protected Article doInBackground(Integer... integers) {
            return asyncArticleDao.getArticleById(integers[0]);
        }

        @Override
        protected void onPostExecute(Article article) {
            super.onPostExecute(article);
            Log.d(TAG," On post execute return " + article.getTitle());
            mArticle = article;
        }
    }

}
