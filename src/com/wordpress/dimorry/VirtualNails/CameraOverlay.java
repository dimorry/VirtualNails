package com.wordpress.dimorry.VirtualNails;

import android.content.res.Configuration;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class CameraOverlay extends SurfaceView implements SurfaceHolder.Callback {
	private static final String TAG = "VirtualNails";

	SurfaceHolder mHolder;
	public Camera mCamera;	
	private Parameters mParameters;
	
	CameraOverlay(Context context) {
		super(context);
		// Cria um Surfaceholder.Callback para 
		// notificacao da criação, modificação e destruição da surface
		mHolder = getHolder();
		mHolder.addCallback(this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	public void surfaceCreated(SurfaceHolder holder) {
		// Surface criada. pega a camera e chama o preview
		mCamera = Camera.open();
		setPreviewDisplay(holder);
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// Surface vai ser encerrada, entao paramos o preview da camera
		// TODO: CORRIGIR: o surface destroyed eh chamado antes do surfaceChanged quando muda a rotação da tela,
		      // causando um blink na tela
		Log.v(TAG, "surfaceDestroyed");
		stopPreview();
		closeCamera();
		holder = null;
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		// usa o setDisplayOrientation para resolver o bug dos telefones motorola
		// que a imagem aparece virada em 90 graus -- so disponivel a partir da versao 2.2
		
		//TODO: Resolver esse problema para versões anteriores do android
 
		Log.v(TAG, "surfaceChanged");
		mParameters = mCamera.getParameters();
		
		Configuration config = getResources().getConfiguration();
	    if (config.orientation == Configuration.ORIENTATION_PORTRAIT)
	    {
	    	  mCamera.setDisplayOrientation(90); //disponivel apenas na versao 2.2 ou >
	    	  mParameters.setPreviewSize(h, w); //TODO: testar em um telefone que não seja motorola para ver se distorce
	    }
	    else
	    	mParameters.setPreviewSize(w, h);
	      
		mCamera.setParameters(mParameters);
		
		try {
			startPreview();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

    private void setPreviewDisplay(SurfaceHolder holder) {
        try {
            mCamera.setPreviewDisplay(holder);
        } catch (Throwable ex) {
            closeCamera();
            throw new RuntimeException("erro no setPreviewDisplay ", ex);
        }
    }
    
    private void closeCamera() { //liberar a camera é importante porque ela não pode ser compartilhada entre outros aplicativos
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }

    private void startPreview() throws CameraHardwareException {
        
        setPreviewDisplay(mHolder);
        
        try {
            Log.v(TAG, "startPreview");
            mCamera.startPreview();
        } catch (Throwable ex) {
            closeCamera();
            throw new RuntimeException("erro no startPreview", ex);
        }
    }

    private void stopPreview() {
        if (mCamera != null) {
            Log.v(TAG, "stopPreview");
            mCamera.stopPreview();
        }
    }
    
    public void restartPreview() {
    	try {
    		Log.v(TAG, "restartPreview");
			startPreview();
		} catch (CameraHardwareException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}