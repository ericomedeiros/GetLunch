package com.getLunch.backend.utils;

import java.util.Date;

import com.getLunch.backend.message.Restaurante;

public class Votacao {
	
	private Restaurante restaurante;
	private int votos;
	private Date data;
	
	public Votacao(Restaurante restaurante, Date data) {
		this.restaurante = restaurante;
		this.votos = 0;
		this.data = data;
	}
	@Override
	public String toString() {
		return "Votacao [restaurante=" + restaurante + ", votos=" + votos
				+ ", data=" + data + "]";
	}
	public Restaurante getRestaurante() {
		return restaurante;
	}
	public int getVotos() {
		return votos;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public void addVoto(){
		this.votos++;
	}

}
