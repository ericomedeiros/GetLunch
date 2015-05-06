package com.getLunch.backend.utils;

import java.util.Date;

import com.getLunch.backend.message.Restaurante;

public class VotacaoDia {
	
	private Votacao[] votacoes;
	private Date dia;
	public Votacao[] getVotacoes() {
		return votacoes;
	}
	public Date getDia() {
		return dia;
	}
	public VotacaoDia(Restaurante[] restaurantes) {
		this.dia = new Date();
		this.votacoes = new Votacao[restaurantes.length];
		
		for (int i = 0; i < restaurantes.length; i++) {
			this.votacoes[i] = new Votacao(restaurantes[i], dia);
		}
	}
	public void addVoto(Restaurante restaurante) {
		
		for (int i = 0; i < votacoes.length; i++) {
			if(votacoes[i].getRestaurante().equals(restaurante)){
				votacoes[i].addVoto();
				return;
			}
		}
		
	}
	public void addVoto(Votacao vt) {
		
		for (int i = 0; i < votacoes.length; i++) {
			if(votacoes[i].getRestaurante().equals(vt.getRestaurante())){
				votacoes[i] = vt;
				return;
			}
		}
		
	}
	public void removeVoto(Restaurante restaurante) {
			
		for (int i = 0; i < votacoes.length; i++) {
			if(votacoes[i].getRestaurante() == restaurante){
				votacoes[i] = null;
				return;
			}
		}
			
	}
	public Restaurante selecionaRestaurante(){
		
		Votacao v1 = votacoes[0];
		Votacao v2 = votacoes[0];
		
		for (int i = 1; i < votacoes.length; i++) {
			v2 = votacoes[i];
			if(v2.getVotos() > v1.getVotos()){
				v1 = v2;
			}
		}
		
		return v1.getRestaurante();
		
	}
	
	public int getTotalVotos(){
		
		int total = 0;
		
		for (int i = 0; i < votacoes.length; i++) {
			total += votacoes[i].getVotos();
		}
		
		return total;
		
	}
	
	public boolean checkHasVotos(){
		for (int i = 0; i < votacoes.length; i++) {
			if(votacoes[i].hasVotos()){
				return true;
			}
		}
		return false;
	}
	

}
