package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.ClientInfoStatus;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import model.Pais;
import service.PaisService;

class PaisTeste {
	
	Pais pais, copia;
	PaisService PaisService;
	static int id=0;
	
	
	@Before
	public void setUp() throws Exception{
		System.out.println("SETUP");
		pais = new Pais();
		pais.setId(id);
		pais.setNome("Alemanha");
		pais.setPopulacao(80619);
		pais.setArea(357.022);
		copia = new Pais();
		copia.setId(id);
		copia.setNome("Argentina");
		copia.setPopulacao(51461);
		copia.setArea(13.878);
		System.out.println(pais);
		System.out.println(copia);
		System.out.println(id);
	}
	

	@Test
	public void test00Carregar() {
		System.out.println("Carregar");
		Pais fixture = new Pais();
		fixture.setId(1);
		fixture.setNome("Albânia");
		fixture.setPopulacao(80619);
		fixture.setArea(357.022);
		PaisService novoService = new PaisService();
		Pais novo = novoService.carregar(1);
		assertEquals("Teste de Inclusao", novo, fixture);
	}
	
	@Test
	public void test01Criar() {
		System.out.println("Criar");
		id = PaisService.Criar(pais);
		System.out.println(id);
		copia.setId(id);
		assertEquals("Testar Criacao", pais, copia);
	}
	
	@Test
	public void test02Atualizar() {
		System.out.println("Atualizar");
		pais.setArea(357.022);
		copia.setArea(357.453);
		PaisService.atulizar(pais);
		pais = PaisService.carregar(pais.getId());
		assertEquals("Teste de Atualizacao", pais, copia);
		
	}
	
	@Test
	public void test03Excluir() {
		System.out.println("Excluir");
		copia.setId(-1);
		copia.setNome(null);
		PaisService.excluir(1);
		pais = PaisService.carregar(id);
		assertEquals("Teste de Exclusap", pais, copia);
	}
		
}
