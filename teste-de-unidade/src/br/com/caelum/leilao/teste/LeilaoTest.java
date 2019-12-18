package br.com.caelum.leilao.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class LeilaoTest {
	
	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new Leilao("Placa de video GTX a mais toop!");
		assertEquals(0,  leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Gabriela Guedes"), 2000));
		assertEquals(1, leilao.getLances().size());
		
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void deveReceberVariosLances() {
		Leilao leilao = new Leilao("Placa de video GTX a mais toop!");
		assertEquals(0,  leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Gabriela Guedes"), 2000));
		leilao.propoe(new Lance(new Usuario("Guedes"), 3000));
	
		assertEquals(2, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(3000.0, leilao.getLances().get(1).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("xinelo");
		Usuario gaby = new Usuario("Gabriela");
		
		leilao.propoe(new Lance(gaby, 4000.0));
		leilao.propoe(new Lance(gaby, 6000.0));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(4000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarCincoLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("xinelo");
		Usuario gaby = new Usuario("Gabriela");
		Usuario guedes = new Usuario("Guedes");
		
		leilao.propoe(new Lance(gaby, 4000.0));
		leilao.propoe(new Lance(guedes, 6000.0));
		
		leilao.propoe(new Lance(gaby, 1000.0));
		leilao.propoe(new Lance(guedes, 3000.0));
		
		leilao.propoe(new Lance(gaby, 2000.0));
		leilao.propoe(new Lance(guedes, 5000.0));
		
		leilao.propoe(new Lance(gaby, 7000.0));
		leilao.propoe(new Lance(guedes, 8000.0));
		
		leilao.propoe(new Lance(gaby, 9000.0));
		leilao.propoe(new Lance(guedes, 500.0));
		
		leilao.propoe(new Lance(gaby, 1200.0));
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(500.0, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.00001);
	}
}
