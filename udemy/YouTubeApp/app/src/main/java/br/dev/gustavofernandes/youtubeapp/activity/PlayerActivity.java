package br.dev.gustavofernandes.youtubeapp.activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import br.dev.gustavofernandes.youtubeapp.R;

public class PlayerActivity extends YouTubeBaseActivity
    implements YouTubePlayer.OnInitializedListener
{
    public final String CHAVE_API_GOOGLE = "AIzaSyAAtT5HuN9-aSjdqv_ZZjYBaAEBiIUoyOA";

    YouTubePlayer.PlayerStateChangeListener _PlayerStateChangeListener;
    YouTubePlayer.PlaybackEventListener _PlayBackEventListener;
    YouTubePlayerView player;
    String fIdVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle b = getIntent().getExtras();
        if (b != null)
        {
            String sString = b.getString("idVideo","");
            fIdVideo = sString;
        }
        player = findViewById(R.id.youtube_player);
        player.initialize(CHAVE_API_GOOGLE, this);

        _PlayerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
            @Override
            public void onLoading() {
                Toast.makeText(getApplicationContext(), "onLoading (Carragando)", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoaded(String s) {
                Toast.makeText(getApplicationContext(), "onLoaded (Carregado)", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdStarted() {
                Toast.makeText(getApplicationContext(), "onAdStarted (Propaganda)", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVideoStarted() {
                Toast.makeText(getApplicationContext(), "onVideoStarted (Vídeo iniciado)", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVideoEnded() {
                Toast.makeText(getApplicationContext(), "onVideoEnded (Finalizado)", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(YouTubePlayer.ErrorReason errorReason) {
                Toast.makeText(getApplicationContext(), "onError", Toast.LENGTH_SHORT).show();
            }
        };

        _PlayBackEventListener = new YouTubePlayer.PlaybackEventListener() {
            @Override
            public void onPlaying() {
                Toast.makeText(getApplicationContext(), "onPlaying", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPaused() {
                Toast.makeText(getApplicationContext(), "onPaused", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopped() {
                Toast.makeText(getApplicationContext(), "onStopped", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBuffering(boolean b) {
                Toast.makeText(getApplicationContext(), "onBuffering", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSeekTo(int i) {
                Toast.makeText(getApplicationContext(), "onSeekTo", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
       if(!b) {
           //youTubePlayer.cuePlaylist("PLWz5rJ2EKKc_ft4KzJnoWiihgFNvs_prV");

           //youTubePlayer.setShowFullscreenButton(false);
           youTubePlayer.cueVideo(fIdVideo);
           youTubePlayer.setFullscreen(true);
           youTubePlayer.setPlaybackEventListener( _PlayBackEventListener);
           youTubePlayer.setPlayerStateChangeListener(_PlayerStateChangeListener);
       }
        //youTubePlayer.cueVideo("wt-qTWriEi0");
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Falha no carregamento do player", Toast.LENGTH_SHORT).show();
    }


}