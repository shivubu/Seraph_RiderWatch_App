package com.example.kamen_app_01;import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.Log;
import java.io.IOException;

public class PerfectLoopMediaPlayer {
    private static final String TAG = PerfectLoopMediaPlayer.class.getName();
    private Context mContext = null;private int mResId = 0;
    private String mPath = null;
    private MediaPlayer mCurrentPlayer = null;
    private MediaPlayer mNextPlayer = null;

    public static PerfectLoopMediaPlayer create(Context context, int resId) {
        return new PerfectLoopMediaPlayer(context, resId);}

    public static PerfectLoopMediaPlayer create(Context context, String path) {
        return new PerfectLoopMediaPlayer(context, path);
    }

    private PerfectLoopMediaPlayer(Context context, int resId) {mContext = context;
        mResId = resId;
        try {
            AssetFileDescriptor afd = context.getResources().openRawResourceFd(mResId);
            mCurrentPlayer = new MediaPlayer();
            mCurrentPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mCurrentPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override public void onPrepared(MediaPlayer mediaPlayer) {
                    mCurrentPlayer.start();
                }
            });mCurrentPlayer.prepareAsync();
            createNextMediaPlayerRaw();
        } catch (IOException e){
            Log.e(TAG, "Error", e);
        }
    }

    private PerfectLoopMediaPlayer(Context context, String path) {
        mContext = context;
        mPath = path;try {
            mCurrentPlayer = new MediaPlayer();
            mCurrentPlayer.setDataSource(mPath);
            mCurrentPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override public void onPrepared(MediaPlayer mediaPlayer) {
                    mCurrentPlayer.start();
                }
            });mCurrentPlayer.prepareAsync();
            createNextMediaPlayerPath();
        } catch (IOException e){
            Log.e(TAG, "Error", e);
        }
    }

    private void createNextMediaPlayerRaw() {
        if (mCurrentPlayer == null) return;
        try {AssetFileDescriptor afd = mContext.getResources().openRawResourceFd(mResId);
            mNextPlayer = new MediaPlayer();
            mNextPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mNextPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
                @Override
                public void onPrepared(MediaPlayer mp) {
                    if (mCurrentPlayer !=null) {
                        mNextPlayer.seekTo(0);
                        mCurrentPlayer.setNextMediaPlayer(mNextPlayer);
                        mCurrentPlayer.setOnCompletionListener(onCompletionListener);
                    }
                }});
            mNextPlayer.prepareAsync();
        } catch (IOException e) {
            Log.e(TAG, "Error", e);
        }
    }

    private void createNextMediaPlayerPath() {
        if (mCurrentPlayer == null) return;
        try {
            mNextPlayer =new MediaPlayer();
            mNextPlayer.setDataSource(mPath);
            mNextPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {if (mCurrentPlayer != null) {
                    mNextPlayer.seekTo(0);mCurrentPlayer.setNextMediaPlayer(mNextPlayer);
                    mCurrentPlayer.setOnCompletionListener(onCompletionListener);
                }
                }
            });
            mNextPlayer.prepareAsync();
        } catch(IOException e) {
            Log.e(TAG, "Error", e);
        }
    }private final MediaPlayer.OnCompletionListener onCompletionListener =
            new MediaPlayer.OnCompletionListener() {@Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (mCurrentPlayer != null){
                    mCurrentPlayer = mNextPlayer;
                    if (mPath != null) {
                        createNextMediaPlayerPath();
                    } else {
                        createNextMediaPlayerRaw();
                    }
                }mediaPlayer.release();
            }
            };

    public boolean isPlaying() {
        return mCurrentPlayer != null && mCurrentPlayer.isPlaying();
    }

    public void start() {
        if (mCurrentPlayer != null) {
            mCurrentPlayer.start();
        }
    }

    public void stop() {
        if (mCurrentPlayer != null && mCurrentPlayer.isPlaying()) {
            mCurrentPlayer.stop();
        }
    }

    public void pause() {
        if (mCurrentPlayer != null && mCurrentPlayer.isPlaying()) {
            mCurrentPlayer.pause();
        }}

    public void setVolume(float leftVolume, float rightVolume) {
        if (mCurrentPlayer!= null) {
            mCurrentPlayer.setVolume(leftVolume, rightVolume);
        }}

    public void setWakeMode(Context c, int mode) {
        if (mCurrentPlayer !=null) {
            mCurrentPlayer.setWakeMode(c, mode);
        }
    }public void setAudioStreamType(int audioStreamType) {
        if (mCurrentPlayer != null){
            mCurrentPlayer.setAudioStreamType(audioStreamType);
        }
    }public void reset() {
        if (mCurrentPlayer != null) {
            mCurrentPlayer.reset();}
    }

    public void release() {
        if (mCurrentPlayer != null) {mCurrentPlayer.setOnCompletionListener(null);
            mCurrentPlayer.setNextMediaPlayer(null);try {
                if (mCurrentPlayer.isPlaying()) mCurrentPlayer.stop();
            } catch(Exception ignored) {}
            mCurrentPlayer.release();
            mCurrentPlayer = null;
        }if (mNextPlayer != null) {
            try {
                if (mNextPlayer.isPlaying()) mNextPlayer.stop();
            } catch (Exception ignored) {}
            mNextPlayer.release();mNextPlayer = null;
        }
    }
}