package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Pais;

public class PaisDAO {
	
	public int criar(Pais pais) {
		
		String sqlInsert = "INSERT INTO(id, nome, populacao, area) VALUES (?, ?, ?, ?)";
		try(Connection conn = ConnectionFactory.obtemConexao();
			PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, pais.getId());
			stm.setString(2, pais.getNome());
			stm.setLong(3, pais.getPopulacao());
			stm.setDouble(4, pais.getArea());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2  = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();){
				if (rs.next()) {
					pais.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pais.getId();
	}
	
	public void atualizar(Pais pais) {
	
		String sqlUpdate = "UPADETE pais SET nome=?, populacao=?, area=? WHERE ID=?";
		
		try(Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, pais.getNome());
			stm.setLong(2, pais.getPopulacao());
			stm.setDouble(1, pais.getArea());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void excluir(int id) {
		
		String sqlDelete = "DELETE FROM pais WHERE id=?";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);){
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Pais carrega(int id) {
		
		Pais pais = new Pais();
		pais.setId(id);
		
		String sqlSelect = "SELECT nome, populacao, area FROM pais WHERE pais.id=?";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, pais.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if(rs.next()) {
					pais.setNome(rs.getString("Nome"));
					pais.setPopulacao(rs.getLong("Populacao"));
					pais.setArea(rs.getDouble("Area"));
				}else {
					pais.setId(-1);
					pais.setNome(null);
					pais.setPopulacao(0);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		return pais;
	}
	
	public double maiorPopulcao (double maior) {
		
		Pais pais = new Pais();
		
		double vetor[] = new double[(int) pais.getPopulacao()];
		for (int i = 0; i < vetor.length; i++) {
			if(maior > pais.getPopulacao()) {
				maior = (maior + pais.getPopulacao());
			}
		}
		return maior;
	}
	
	public double menorPopulcao (double menor) {
		
		Pais pais = new Pais();
		
		double vetor[] = new double[(int) pais.getPopulacao()];
		for (int i = 0; i < vetor.length; i++) {
			if(menor < pais.getArea()) {
				menor = (menor + pais.getArea());
			}
		}
		return menor;
	}
	
	public double[] carregarPais(double[] menor) {
	
		Pais pais = new Pais();
		
		double vetor[] = new double[(int) pais.getPopulacao()];
				
		for (int i = 0; i < vetor.length; i++) {
			if(i < 3) {
				menor = vetor;
			}
		}
		return menor;
	
	}
	
}
