package com.wordpress.dimorry.VirtualNails;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


public class TouchScreenView extends View {
	//TODO: criar efeito de brilho na cor para poder desenhar direto no canvas e 
	// passar o codigo da cor como parametro ao inves de usar um bitmap para cada cor
	private String TAG="TouchScreen";
	
	private int larguraTela;
	private int alturaTela;
	
	private boolean selecionadoPolegar;
	private Drawable imgPolegar;
	private int xPolegar = 380;
	private int yPolegar = 400;
	private int larguraImgPolegar;
	private int alturaImgPolegar;
	
	private boolean selecionadoIndicador;
	private Drawable imgIndicador;
	private int xIndicador = 300;
	private int yIndicador = 100;
	private int larguraImgIndicador;
	private int alturaImgIndicador;
	
	private boolean selecionadoMedio;
	private Drawable imgMedio;
	private int xMedio = 240;
	private int yMedio = 70;
	private int larguraImgMedio;
	private int alturaImgMedio;
		
	private boolean selecionadoAnular;
	private Drawable imgAnular;
	private int xAnular = 190;
	private int yAnular = 100;
	private int larguraImgAnular;
	private int alturaImgAnular;
	
	private boolean selecionadoMindinho;
	private Drawable imgMindinho;
	private int xMindinho = 160;
	private int yMindinho = 150;
	private int larguraImgMindinho;
	private int alturaImgMindinho;
	
		
	public TouchScreenView(Context context) {
		super(context);
		
		imgPolegar = context.getResources().getDrawable(R.drawable.polegar_apuro_violeta);		
		larguraImgPolegar = imgPolegar.getIntrinsicWidth();
		alturaImgPolegar = imgPolegar.getIntrinsicHeight();
		
		imgIndicador = context.getResources().getDrawable(R.drawable.indicador_apuro_violeta);		
		larguraImgIndicador = imgIndicador.getIntrinsicWidth();
		alturaImgIndicador = imgIndicador.getIntrinsicHeight();
		
		imgMedio = context.getResources().getDrawable(R.drawable.medio_apuro_violeta);		
		larguraImgMedio = imgMedio.getIntrinsicWidth();
		alturaImgMedio = imgMedio.getIntrinsicHeight();
		
		imgAnular = context.getResources().getDrawable(R.drawable.anular_apuro_violeta);		
		larguraImgAnular = imgAnular.getIntrinsicWidth();
		alturaImgAnular = imgAnular.getIntrinsicHeight();
		
		imgMindinho = context.getResources().getDrawable(R.drawable.mindinho_apuro_violeta);		
		larguraImgMindinho = imgMindinho.getIntrinsicWidth();
		alturaImgMindinho = imgMindinho.getIntrinsicHeight();		
		
		
		setFocusable(true);
		
		// TODO Auto-generated constructor stub
	}
	
	public void MudaCor(String cor){
		Context context = getContext();
		
		if (cor=="violeta"){
			imgPolegar = context.getResources().getDrawable(R.drawable.polegar_apuro_violeta);		
			imgIndicador = context.getResources().getDrawable(R.drawable.indicador_apuro_violeta);		
			imgMedio = context.getResources().getDrawable(R.drawable.medio_apuro_violeta);		
			imgAnular = context.getResources().getDrawable(R.drawable.anular_apuro_violeta);
			imgMindinho = context.getResources().getDrawable(R.drawable.mindinho_apuro_violeta);
		}
		else if (cor == "pink"){
			imgPolegar = context.getResources().getDrawable(R.drawable.polegar_apuro_pink);
			imgIndicador = context.getResources().getDrawable(R.drawable.indicador_apuro_pink);		
			imgMedio = context.getResources().getDrawable(R.drawable.medio_apuro_pink);		
			imgAnular = context.getResources().getDrawable(R.drawable.anular_apuro_pink);
			imgMindinho = context.getResources().getDrawable(R.drawable.mindinho_apuro_pink);
		}		
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);		
		
		Paint pincel = new Paint();
		pincel.setColor(Color.TRANSPARENT);                                                                                                       
		canvas.drawRect(0, 0, larguraTela, alturaTela, pincel);
		
				
		imgPolegar.setBounds(xPolegar, yPolegar, (xPolegar + larguraImgPolegar), (yPolegar + larguraImgPolegar));
		imgPolegar.draw(canvas);
		
		imgIndicador.setBounds(xIndicador, yIndicador, xIndicador + larguraImgIndicador, yIndicador + larguraImgIndicador);		
		imgIndicador.draw(canvas);
		
		imgMedio.setBounds(xMedio, yMedio, xMedio + larguraImgMedio, yMedio + larguraImgMedio);		
		imgMedio.draw(canvas);
		
		imgAnular.setBounds(xAnular, yAnular, xAnular + larguraImgAnular, yAnular + larguraImgAnular);		
		imgAnular.draw(canvas);
		
		imgMindinho.setBounds(xMindinho, yMindinho, xMindinho + larguraImgMindinho, yMindinho + larguraImgMindinho);		
		imgMindinho.draw(canvas);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		
		this.larguraTela = w;
		this.alturaTela = h;
		
		Log.i(TAG, "onSizeChanged x/y: " + xPolegar + "/" + yPolegar);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
		float x = event.getX();
		float y = event.getY();
		
		Log.i(TAG, "onTouchEvent X/Y: " + x + "/" + y);
		
		switch (event.getAction()){
			case MotionEvent.ACTION_DOWN:
				selecionadoPolegar = imgPolegar.copyBounds().contains((int) x, (int) y);
				selecionadoIndicador = imgIndicador.copyBounds().contains((int) x, (int) y);
				selecionadoMedio = imgMedio.copyBounds().contains((int) x, (int) y);
				selecionadoAnular = imgAnular.copyBounds().contains((int) x, (int) y);
				selecionadoMindinho = imgMindinho.copyBounds().contains((int) x, (int) y);
				break;
			case MotionEvent.ACTION_MOVE:
				if (selecionadoPolegar){
					this.xPolegar = (int)x - (larguraImgPolegar/2);
					this.yPolegar = (int)y - (alturaImgPolegar/2);
				}
				else if (selecionadoIndicador){
					this.xIndicador = (int)x - (larguraImgIndicador/2);
					this.yIndicador = (int)y - (alturaImgIndicador/2);
				}
				else if (selecionadoMedio){
					this.xMedio = (int)x - (larguraImgMedio/2);
					this.yMedio = (int)y - (alturaImgMedio/2);
				}
				else if (selecionadoAnular){
					this.xAnular = (int)x - (larguraImgAnular/2);
					this.yAnular = (int)y - (alturaImgAnular/2);
				}
				else if (selecionadoMindinho){
					this.xMindinho = (int)x - (larguraImgMindinho/2);
					this.yMindinho = (int)y - (alturaImgMindinho/2);
				}
				break;
			case MotionEvent.ACTION_UP:
				selecionadoPolegar = 
				selecionadoIndicador =
				selecionadoMedio =
				selecionadoAnular =
				selecionadoMindinho = false;
				break;
		}
		invalidate();
		
		return true;
		
	}

}
