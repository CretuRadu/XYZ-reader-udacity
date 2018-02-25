package com.example.xyzreader.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xyzreader.R;
import com.example.xyzreader.data.Article;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xayru on 2/15/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private static final String TAG = "Adapter class";
    public static final String ARTICLE_ID = "article_id";
    private List<Article> articles;
    private final LayoutInflater mInflater;
    private Context context;

    public Adapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public long getItemId(int position) {
        return articles.get(position).getId();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_article, parent, false);
        final ViewHolder vh = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ArticleDetailActivity.class);
                intent.setAction(Intent.ACTION_VIEW);
                intent.putExtra(ARTICLE_ID, vh.getAdapterPosition());
                context.startActivity(intent);
            }
        });
        return vh;
    }

    private Date parsePublishedDate(int position) {
        try {
            String date = articles.get(position).getPublishedDate();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss");
            return dateFormat.parse(date);
        } catch (ParseException ex) {
            Log.e(TAG, ex.getMessage());
            Log.i(TAG, "passing today's date");
            return new Date();
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (articles != null) {
            Article article = articles.get(position);
            holder.titleView.setText(article.getTitle());
            Date publishedDate = parsePublishedDate(position);
            holder.subtitleView.setText(Html.fromHtml(
                    DateUtils.getRelativeTimeSpanString(
                            publishedDate.getTime(),
                            System.currentTimeMillis(), DateUtils.HOUR_IN_MILLIS,
                            DateUtils.FORMAT_ABBREV_ALL).toString()
                            + "<br/>" + " by "
                            + article.getAuthor()));

            holder.thumbnailView.setImageUrl(
                    article.getThumbUrl(),
                    ImageLoaderHelper.getInstance(context).getImageLoader());

            holder.thumbnailView.setAspectRatio(Float.parseFloat(article.getAspectRatio()));
        } else {
            Log.d(TAG, "No items loaded in adapter ");
        }
    }

    @Override
    public int getItemCount() {
        if (articles != null) {
            return articles.size();
        } else return 0;
    }

    void setArticles(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.thumbnail)
        DynamicHeightNetworkImageView thumbnailView;
        @BindView(R.id.article_title)
        TextView titleView;
        @BindView(R.id.article_subtitle)
        TextView subtitleView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
