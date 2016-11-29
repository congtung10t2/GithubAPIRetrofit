package com.example.hoangcongtung.simpleandroidapplication.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by hoangcongtung on 11/26/16.
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    private ImageView bitmap;

    public DownloadImageTask(ImageView bitmap) {
        this.bitmap = bitmap;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap icon = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            icon = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return icon;
    }

    protected void onPostExecute(Bitmap result) {
        bitmap.setImageBitmap(result);
    }
}
