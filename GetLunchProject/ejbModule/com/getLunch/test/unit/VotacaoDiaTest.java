package com.getLunch.test.unit;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.getLunch.backend.message.Restaurante;
import com.getLunch.backend.utils.Votacao;
import com.getLunch.backend.utils.VotacaoDia;

public class VotacaoDiaTest {

	@Test
	public void testGetVotacoes() {
		Restaurante[] res = {new Restaurante("Test a", 1)};
		VotacaoDia vd = new VotacaoDia(res);
		Votacao[]  vts = new Votacao[res.length];
		
		for (int i = 0; i < res.length; i++) {
			vts[i] = new Votacao(res[i], new Date());
		}
		
		assertEquals(vts[0].getData(), vd.getVotacoes()[0].getData());
		assertEquals(vts[0].getRestaurante(), vd.getVotacoes()[0].getRestaurante());
		assertEquals(vts[0].getVotos(), vd.getVotacoes()[0].getVotos());
	}

	@Test
	public void testAddVotoRestaurante() {
	
		Restaurante[] res = {new Restaurante("Test a", 1)};
		VotacaoDia vd = new VotacaoDia(res);
		Votacao[]  vts;
		
		vd.addVoto(res[0]);
		
		vts = vd.getVotacoes();
		
		for (int i = 0; i < vts.length; i++) {
			if(vts[i].getRestaurante().equals(res[0])){
				assertEquals(1, vts[i].getVotos());
			}
		}
		
	}

	@Test
	public void testAddVotoVotacao() {
		Date d = new Date();
		Restaurante[] res = {new Restaurante("Test a", 1)};
		VotacaoDia vd = new VotacaoDia(res, d);
		Votacao  vt = new Votacao(res[0], d);
		Votacao[]  vts;
		
		vd.addVoto(vt);
		
		vts = vd.getVotacoes();
		
		for (int i = 0; i < vts.length; i++) {
			if(vts[i].getRestaurante().equals(res[0])){
				assertEquals(vt, vts[i]);
			}
		}
	}

	@Test
	public void testRemoveVoto() {
		Restaurante[] res = {new Restaurante("Test a", 1),new Restaurante("Test b", 2)};
		VotacaoDia vd = new VotacaoDia(res);
		Votacao[]  vts = new Votacao[res.length];
		
		for (int i = 0; i < res.length; i++) {
			vts[i] = new Votacao(res[i], new Date());
		}
		
		vd.removeVoto(res[1]);
		
		assertNotEquals(vts, vd.getVotacoes());
	}

	@Test
	public void testSelecionaRestaurante() {
		Restaurante[] res = {new Restaurante("Test a", 1),new Restaurante("Test b", 2)};
		VotacaoDia vd = new VotacaoDia(res);
		
		assertEquals(null, vd.getSelectedRestaurante());
		
		vd.addVoto(res[0]);
		
		assertEquals(res[0], vd.selecionaRestaurante());
	}

	@Test
	public void testGetTotalVotos() {
		
		Restaurante[] res = {new Restaurante("Test a", 1),new Restaurante("Test b", 2)};
		VotacaoDia vd = new VotacaoDia(res);
		
		vd.addVoto(res[0]);
		vd.addVoto(res[0]);
		vd.addVoto(res[1]);
		
		assertEquals(3, vd.getTotalVotos());
		
	}

	@Test
	public void testCheckHasVotos() {
		
		Restaurante[] res = {new Restaurante("Test a", 1),new Restaurante("Test b", 2)};
		VotacaoDia vd = new VotacaoDia(res);
		
		assertEquals(false,vd.checkHasVotos());
		
		vd.addVoto(res[0]);
		
		assertEquals(true,vd.checkHasVotos());
	}

	@Test
	public void testHasSelectedVotacao() {
		Restaurante[] res = {new Restaurante("Test a", 1),new Restaurante("Test b", 2)};
		VotacaoDia vd = new VotacaoDia(res);
		
		assertEquals(false,vd.hasSelectedVotacao());
		
		vd.addVoto(res[0]);
		vd.selecionaRestaurante();
		
		assertEquals(true,vd.hasSelectedVotacao());
	}

	@Test
	public void testGetSelectedRestaurante() {
		Restaurante[] res = {new Restaurante("Test a", 1),new Restaurante("Test b", 2)};
		VotacaoDia vd = new VotacaoDia(res);
		
		assertEquals(null, vd.getSelectedRestaurante());
		
		vd.addVoto(res[0]);
		vd.selecionaRestaurante();
		
		assertEquals(res[0], vd.getSelectedRestaurante());
	}

}
