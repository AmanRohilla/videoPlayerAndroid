package com.example.videoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //All the entities in our application
    VideoView videoView;
    ListView listView;
    Button next;
    Button previous;
    ArrayList<playerObjects> urlList = new ArrayList<>();
    playerObjects obj1;
    playerObjects obj2;
    playerObjects currentPlayingObj;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //calling elements in our application
        videoView = findViewById(R.id.videoView);
        listView = findViewById(R.id.listView);
        next = findViewById(R.id.nextButton);
        previous = findViewById(R.id.previousButton);
        mediaController = new MediaController(this);


        //giving the objects their meaning
        obj1 = new playerObjects("https://www.radiantmediaplayer.com/media/bbb-360p.mp4",false);
        obj2 = new playerObjects("https://www.radiantmediaplayer.com/media/bbb-360p.mp4",false);

        //Filling the array
        urlList.add(obj1);
        urlList.add(obj2);

        //Adapter for the list
        final CustomAdapter customAdapter = new CustomAdapter(this,urlList);
        listView.setAdapter(customAdapter);

        //Applying onclickListner to the Listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(currentPlayingObj !=null){
                    changePlayStatus(currentPlayingObj);
                }
                currentPlayingObj = urlList.get(position);
                if(currentPlayingObj !=null){
                    changePlayStatus(currentPlayingObj);
                }
                int elementPosition = urlList.indexOf(currentPlayingObj);
                urlList.get(elementPosition).isPlaying = true;

                playVideo(currentPlayingObj.url);
                listView.invalidateViews();
            }
        });

    }

    //To change the play status
    public void changePlayStatus(playerObjects objects){
        int position = urlList.indexOf(objects);
        playerObjects playerObjects = urlList.get(position);
        if(playerObjects.isPlaying){
            urlList.get(position).isPlaying = false;
        }
        else{
            urlList.get(position).isPlaying = true;
        }
    }




    //Method to play video
    public void playVideo(String url){
        videoView.stopPlayback();
        videoView.setVideoURI(Uri.parse(url));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.setMediaController(mediaController);
                videoView.requestFocus();
                videoView.start();
            }
        });
    }
}
