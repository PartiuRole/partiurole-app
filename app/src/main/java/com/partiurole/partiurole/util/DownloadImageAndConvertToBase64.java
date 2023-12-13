package com.partiurole.partiurole.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadImageAndConvertToBase64 extends AsyncTask<String, Void, String> {

    private OnImageDownloadListener listener;

    public DownloadImageAndConvertToBase64(OnImageDownloadListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... urls) {
        String urlDisplay = urls[0];
        Bitmap bitmap = null;
        String base64Image = "";

        try {
            URL url = new URL(urlDisplay);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);

            if (bitmap != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                base64Image = Base64.encodeToString(byteArray, Base64.DEFAULT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return base64Image;
    }

    @Override
    protected void onPostExecute(String base64Image) {
        if (listener != null) {
            listener.onImageDownloaded(base64Image);
        }
    }

    public interface OnImageDownloadListener {
        void onImageDownloaded(String base64Image);
    }
}

