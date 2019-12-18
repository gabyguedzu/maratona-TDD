package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {

	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario eu;
	private Usuario zezaoDaTapioca;

	@Before
	public void criaAvaliador() {
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("João");
		this.eu = new Usuario("Gabriela");
		this.zezaoDaTapioca = new Usuario("Zezão da Tapioca");
	}

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		Leilao leilao = new Leilao("HB20 Novo!!!!");
		leilao.propoe(new Lance(joao, 300.00));
		leilao.propoe(new Lance(eu, 600.00));
		leilao.propoe(new Lance(zezaoDaTapioca, 900.00));

		leiloeiro.avalia(leilao);

		assertEquals(900.00, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(300.00, leiloeiro.getMenorLance(), 0.00001);
	}

	@Test
	public void deveEntenderLancesComApenasUmLance() {
		Leilao leilao = new Leilao("HB20 Novo!!!!");
		leilao.propoe(new Lance(joao, 1000.0));

		leiloeiro.avalia(leilao);

		assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
	}

	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Leilao leilao = new CriadorDeLeilao().para("HB20 Novo!!!!")
				.lance(joao, 1000.0)
				.lance(eu, 2000.0)
				.lance(joao, 3000.0)
				.lance(eu, 4000.0)
				.constroi();

		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();

		assertEquals(3, maiores.size());
		assertEquals(4000.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(3000.0, maiores.get(1).getValor(), 0.00001);
		assertEquals(2000.0, maiores.get(2).getValor(), 0.00001);
	}
}