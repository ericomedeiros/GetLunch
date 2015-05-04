package com.getLunch.backend.utils;

import java.util.Calendar;
import java.util.Date;

import com.getLunch.backend.message.Restaurante;

public class VotacaoSemanal {
	
	private VotacaoDia[] votacaodias; 
	private int year;
	private int nrSemana;
	
	public int getYear() {
		return year;
	}
	public int getNrSemana() {
		return nrSemana;
	}
	
	@SuppressWarnings("deprecation")
	public VotacaoSemanal(Restaurante[] restaurantes){
		
		votacaodias = new VotacaoDia[7];
		Date data = new Date();
		for (int i = 0; i < 7; i++) {
			votacaodias[i] = new VotacaoDia(restaurantes);
		}
		
		year  = data.getYear();
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		nrSemana = cal.get(Calendar.WEEK_OF_YEAR);
		
	}
	
	public void addVoto(Restaurante re){
		Date data = new Date();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		
		int nrDia = cal.get(Calendar.DAY_OF_WEEK);
		
		nrDia = nrDia-2;
		
		if(nrDia < 0) 
			nrDia = 6;
		
		votacaodias[nrDia].addVoto(re);
	}
	public Restaurante getToDayRestaurante(){
		Date data = new Date();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		
		int nrDia = cal.get(Calendar.DAY_OF_WEEK);
		
		nrDia = nrDia-2;
		
		if(nrDia < 0) 
			nrDia = 6;
		
		Restaurante re = votacaodias[nrDia].selecionaRestaurante();
		for (int i = nrDia+1; i < 7; i++) {
			votacaodias[i].removeVoto(re);
		}
		
		return re;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + nrSemana;
		result = prime * result + year;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VotacaoSemanal other = (VotacaoSemanal) obj;
		if (nrSemana != other.nrSemana)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

}
