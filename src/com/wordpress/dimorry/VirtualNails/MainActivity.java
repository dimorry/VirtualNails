package com.wordpress.dimorry.VirtualNails;

import android.app.Activity;
import android.os.Bundle;

import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends Activity {
	private static final String TAG = "VirtualNails";
	private CameraOverlay mCameraOverlay;
	private TouchScreenView mTs;
	
	private FrameLayout fl;
	
	private static final int VIOLETA = 0;
	private static final int PINK = 1;
	
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		montaLayout();

		Log.d(TAG, "onCreate activity");
	}
	
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
		// Adiciona as cores em um menu
		//TODO: mudar para um objeto que suporte mais itens para poder adicionar mais cores
		
		MenuItem item = menu.add(0, VIOLETA, 0, "Violeta");
		item.setIcon(R.drawable.indicador_apuro_violeta);
		item = menu.add(0, PINK, 0, "Pink");
		item.setIcon(R.drawable.indicador_apuro_pink);
		
		return true;
	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case VIOLETA:
			mTs.MudaCor("violeta");
			//Toast.makeText(this, "Violeta!", Toast.LENGTH_SHORT).show();
			return true;
		case PINK:
			mTs.MudaCor("pink");
			//Toast.makeText(this, "Pink!", Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
	}

	private void montaLayout() {
		// TODO Auto-generated method stub
		mCameraOverlay = new CameraOverlay(this);
		fl = (FrameLayout) findViewById(R.id.cameraLayout);
		fl.addView(mCameraOverlay);

		Button btnFoto = (Button) findViewById(R.id.btnFoto);
		btnFoto.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mCameraOverlay.mCamera.takePicture(shutterCallback, rawCallback,
						null);
			}
		});
		
		Button btnPreview = (Button) findViewById(R.id.btnPreview);
		btnPreview.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {				
				mCameraOverlay.restartPreview();
			}
		});
		
		mTs = new TouchScreenView(this);		
		fl.addView(mTs);
	}

	ShutterCallback shutterCallback = new ShutterCallback() {
		public void onShutter() {
			Log.d(TAG, "onShutter callback");
		}
	};

	/** Callback chamado quando tira a foto */
	PictureCallback rawCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			Log.d(TAG, "onPictureTaken - raw");
		}
	};

	
}