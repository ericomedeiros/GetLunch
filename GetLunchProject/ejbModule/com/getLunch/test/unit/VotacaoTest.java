package com.getLunch.test.unit;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.getLunch.backend.message.Restaurante;
import com.getLunch.backend.utils.Votacao;

public class VotacaoTest {

	@Test
	public void testAddVoto() {
		
		Restaurante res = new Restaurante("Test a", 1);
		Votacao vt = new Votacao(res, new Date());
		
		vt.addVoto();
		
		assertEquals(1, vt.getVotos());
	}

	@Test
	public void testHasVotos() {
		
		Restaurante res = new Restaurante("Test a", 1);
		Votacao vt = new Votacao(res, new Date());
		
		vt.addVoto();
		
		assertTrue(vt.hasVotos());
	}

}
