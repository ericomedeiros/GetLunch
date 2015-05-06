package com.getLunch.test.unit;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.getLunch.backend.message.Restaurante;
import com.getLunch.backend.utils.VotacaoSemanal;

public class VotacaoSemanalTest {

	@Test
	public void testGetToDayRestaurante() {
		Restaurante[] res = {new Restaurante("Test a", 1),new Restaurante("Test b", 2)};
		VotacaoSemanal vs = new VotacaoSemanal(res);
		
		vs.addVoto(res[0]);
		vs.addVoto(res[0]);
		vs.addVoto(res[1]);
		
		assertEquals(res[0], vs.getToDayRestaurante());
	}

	@Test
	public void testGetTotalVotosOfDate() {
		Date data = new Date();
		Restaurante[] res = {new Restaurante("Test a", 1),new Restaurante("Test b", 2)};
		VotacaoSemanal vs = new VotacaoSemanal(res,data);
		
		vs.addVoto(res[0]);
		vs.addVoto(res[0]);
		vs.addVoto(res[1]);
		vs.addVoto(res[1]);
		
		assertEquals(4, vs.getTotalVotosOfDate(data));
	
	}

	@Test
	public void testCheckHasVotosOfDate() {
		
		Date data = new Date();
		Restaurante[] res = {new Restaurante("Test a", 1),new Restaurante("Test b", 2)};
		VotacaoSemanal vs = new VotacaoSemanal(res,data);
		
		assertEquals(false, vs.checkHasVotosOfDate(data));
		
		vs.addVoto(res[0]);
		
		assertEquals(true, vs.checkHasVotosOfDate(data));
		
	}

}
