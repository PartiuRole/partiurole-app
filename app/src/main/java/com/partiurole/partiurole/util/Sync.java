package com.partiurole.partiurole.util;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.partiurole.partiurole.MainActivity;
import com.partiurole.partiurole.RetrofitClient;
import com.partiurole.partiurole.dao.EventDAO;
import com.partiurole.partiurole.model.Event;

import java.text.ParseException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// get events from api and save them in db
//    Call<List<Event>> call = RetrofitClient.getInstance().getMyApi().getEvents();
//
//        call.enqueue(new Callback<List<Event>>() {
//        @Override
//        public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
//            List<Event> events = response.body();
//            EventDAO eventDAO = new EventDAO();
//            for (Event event : events) {
//                Event event1 = eventDAO.getById(event.getUuid());
//                if (event.getImage() != null && event.getImage().length() > 0 && event1.getImageBase64().isEmpty()) {
//                    DownloadImageAndConvertToBase64 downloader = new DownloadImageAndConvertToBase64(new DownloadImageAndConvertToBase64.OnImageDownloadListener() {
//                        @Override
//                        public void onImageDownloaded(String base64Image) {
//                            event.setImageBase64(base64Image);
//                            eventDAO.updateImageBase64(event);
//                        }
//                    });
//                    downloader.execute(event.getImage());
//                }
//
//                try {
//                    if (event1.getUuid() != null) {
//                        if (DateParser.isOldest(event1.getUpdatedAt(), event.getUpdatedAt())) {
//                            eventDAO.update(event);
//                        }
//                    } else {
//                        eventDAO.insert(event);
//                    }
//                } catch (ParseException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//
//        @Override
//        public void onFailure(Call<List<Event>> call, Throwable t) {
//            Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
//            Log.e("ERROR", t.getMessage());
//        }
//    });

public class Sync extends AsyncTask<Void, Void, Void> {

    private Sync.OnSyncListener listener;

    public Sync(Sync.OnSyncListener listener) {
        this.listener = listener;
    }

    @Override
protected Void doInBackground(Void... voids) {
        EventDAO eventDAO = new EventDAO();
        Call<List<Event>> call = RetrofitClient.getInstance().getMyApi().getEvents();

        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                List<Event> events = response.body();
                for (Event event : events) {
                    Event event1 = eventDAO.getById(event.getUuid());
                    if (event.getImage() != null && event.getImage().length() > 0 && event1.getImageBase64().isEmpty()) {
                        DownloadImageAndConvertToBase64 downloader = new DownloadImageAndConvertToBase64(new DownloadImageAndConvertToBase64.OnImageDownloadListener() {
                            @Override
                            public void onImageDownloaded(String base64Image) {
                                event.setImageBase64(base64Image);
                                eventDAO.updateImageBase64(event);
                            }
                        });
                        downloader.execute(event.getImage());
                    }

                    try {
                        if (event1.getUuid() != null) {
                            if (DateParser.isOldest(event1.getUpdatedAt(), event.getUpdatedAt())) {
                                eventDAO.update(event);
                            }
                        } else {
                            eventDAO.insert(event);
                        }
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onSyncFinished();
    }

    public interface OnSyncListener {
        void onSyncFinished();
    }


}