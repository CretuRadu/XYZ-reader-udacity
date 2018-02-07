package com.example.xyzreader.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by xayru on 2/7/2018.
 */
@Entity(tableName = "articles")
public class Articles {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private String _ID;
    @ColumnInfo(name = "server_id")
    private String SERVER_ID;
    @ColumnInfo(name = "title")
    private String TITLE;
    @ColumnInfo(name = "author")
    private String AUTHOR;
    @ColumnInfo(name = "body")
    private String BODY;
    @ColumnInfo(name = "thumb_url")
    private String THUMB_URL;
    @ColumnInfo(name = "photo_url")
    private String PHOTO_URL;
    @ColumnInfo(name = "aspect_ratio")
    private String ASPECT_RATIO;
    @ColumnInfo(name = "published_date")
    private String PUBLISHED_DATE;
}
