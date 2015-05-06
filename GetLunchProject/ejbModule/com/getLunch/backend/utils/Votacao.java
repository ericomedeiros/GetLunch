package com.getLunch.backend.utils;

import java.io.Serializable;
import java.util.Date;

import com.getLunch.backend.message.Restaurante;

public class Votacao implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Restaurante 	  restaurante;
	private int 			  votos;
	private Date 			  data;
	
	public Votacao(Restaurante restaurante, Date data) {
		this.restaurante = restaurante;
		this.votos 		 = 0;
		this.data 		 = data;
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
	
	public boolean hasVotos(){
		return votos > 0;
	}
}
