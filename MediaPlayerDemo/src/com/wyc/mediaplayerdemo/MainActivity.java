package com.wyc.mediaplayerdemo;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnBufferingUpdateListener;
import io.vov.vitamio.MediaPlayer.OnInfoListener;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnInfoListener, OnBufferingUpdateListener {
	/**
	 * TODO: Set the path variable to a streaming video URL or a local media
	 * file path.
	 */
	private String path = "rtmp://218.28.177.199:1935/live/300k";
	private Uri uri;
	private VideoView mVideoView;
	private ProgressBar pb;
	private TextView downloadRateView, loadRateView;
	private View player_btn;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		// 初始化
		if (!LibsChecker.checkVitamioLibs(this))
			return;
		setContentView(R.layout.activity_main);
		mVideoView = (VideoView) findViewById(R.id.buffer);
		pb = (ProgressBar) findViewById(R.id.probar);
		player_btn = findViewById(R.id.player_btn);
		downloadRateView = (TextView) findViewById(R.id.download_rate);
		loadRateView = (TextView) findViewById(R.id.load_rate);
		if (path == "") {
			// Tell the user to provide a media file URL/path.
			Toast.makeText(MainActivity.this,
					"Please edit VideoBuffer Activity, and set path" + " variable to your media file URL/path",
					Toast.LENGTH_LONG).show();
			return;
		} else {
			/*
			 * Alternatively,for streaming media you can use
			 * mVideoView.setVideoURI(Uri.parse(URLstring));
			 */
			uri = Uri.parse(path);
			mVideoView.setVideoURI(uri);
			// mVideoView.setMediaController(new MediaController(this));

			mVideoView.requestFocus();
			mVideoView.setMediaBufferingIndicator(player_btn);
			mVideoView.setOnInfoListener(this);
			mVideoView.setOnBufferingUpdateListener(this);
			mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
				@Override
				public void onPrepared(MediaPlayer mediaPlayer) {
					// optional need Vitamio 4.0
					Log.e("tag", "---OnPreparedListener---");
					mediaPlayer.setPlaybackSpeed(1.0f);
					mediaPlayer.setAudioAmplify(1.0f);
				}
			});
		}
		player_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				mVideoView.start();
				player_btn.setVisibility(View.GONE);
			}
		});
		mVideoView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mVideoView.isPlaying()) {
					player_btn.setVisibility(View.VISIBLE);
					mVideoView.pause();
				} else {
					mVideoView.start();
				}
			}
		});
	}

	@Override
	public boolean onInfo(MediaPlayer mp, int what, int extra) {
		switch (what) {
		case MediaPlayer.MEDIA_INFO_BUFFERING_START:
			Log.e("tag", "---MEDIA_INFO_BUFFERING_START---");
			if (mVideoView.isPlaying()) {
				mVideoView.pause();
				pb.setVisibility(View.VISIBLE);
				downloadRateView.setText("");
				loadRateView.setText("");
				downloadRateView.setVisibility(View.VISIBLE);
				loadRateView.setVisibility(View.VISIBLE);

			}
			break;
		case MediaPlayer.MEDIA_INFO_BUFFERING_END:
			Log.e("tag", "---MEDIA_INFO_BUFFERING_END---");
			mVideoView.start();
			pb.setVisibility(View.GONE);
			downloadRateView.setVisibility(View.GONE);
			loadRateView.setVisibility(View.GONE);
			break;
		case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
			Log.e("tag", "---MEDIA_INFO_DOWNLOAD_RATE_CHANGED---");
			downloadRateView.setText("" + extra + "kb/s" + "  ");
			break;
		}
		return true;
	}

	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {
		Log.e("tag", "---onBufferingUpdate---");
		loadRateView.setText(percent + "%");
	}

	// 初始化
	public static final String FROM_ME = "fromVitamioInitActivity";

	public static final boolean checkVitamioLibs(Activity ctx) {
		if (!Vitamio.isInitialized(ctx) && !ctx.getIntent().getBooleanExtra(FROM_ME, false)) {
			Intent i = new Intent();
			i.setClassName(Vitamio.getVitamioPackage(), "io.vov.vitamio.activity.InitActivity");
			i.putExtras(ctx.getIntent());
			i.setData(ctx.getIntent().getData());
			i.putExtra("package", ctx.getPackageName());
			i.putExtra("className", ctx.getClass().getName());
			ctx.startActivity(i);
			ctx.finish();
			return false;
		}
		return true;
	}

}
