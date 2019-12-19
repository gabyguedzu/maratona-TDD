package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.infra.dao.LeilaoDao;

public class EncerradorDeLeilaoTest {
	
	@Test
	public void deveEncerrarLeiloesQuecomecaramUmaSemanaAntes() {
		Calendar antiga = Calendar.getInstance();
		antiga.set(1999, 1, 20);
		
		Leilao leilao1 = new CriadorDeLeilao().para("TVzona").constroi();
		Leilao leilao2 = new CriadorDeLeilao().para("Cama").constroi();
		
		//parei aqui
		LeilaoDao dao = new LeilaoDao();
		dao.salva(leilao1);
		dao.salva(leilao2);
		
		EncerradorDeLeilao encerrador = new EncerradorDeLeilao();
		encerrador.encerra();
		
		assertTrue(leilao1.isEncerrado());
		assertTrue(leilao2.isEncerrado());
	}

}
