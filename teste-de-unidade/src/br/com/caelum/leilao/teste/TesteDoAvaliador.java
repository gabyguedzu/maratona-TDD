package br.com.caelum.leilao.teste;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;


public class TesteDoAvaliador {
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		Usuario joao = new Usuario("João");
		Usuario eu = new Usuario("Gabriela");
		Usuario zezaoDaTapioca = new Usuario("Zezão da Tapioca");
		
		Leilao leilao = new Leilao("HB20 Novo!!!!");
		leilao.propoe(new Lance(joao, 300.00));
		leilao.propoe(new Lance(eu, 600.00));
		leilao.propoe(new Lance(zezaoDaTapioca, 900.00));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		double maiorEsperado = 900;
		double menorEsperado = 300;
		
		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
	}
}
	