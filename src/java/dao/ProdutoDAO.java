
package dao;

import br.com.produtoapp.modelo.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.Conexao;

public class ProdutoDAO {
    
    //CLASSE CONEXÃO
    Connection con;
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    
    //FUNÇÃO SALVAR
    public boolean salvar (Produto produto){
        con = Conexao.abrirConexao();
        sql = "insert into produto (codigo, nome, quantidade, preco) values(?,?,?,?)";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, produto.getCodigo());
            ps.setString(2, produto.getNome());
            ps.setInt(3, produto.getQuantidade());
            ps.setDouble(4, produto.getPreco());
            ps.execute();
            Conexao.fecharConexao();
            con.close();
            return true;
            
        }catch (SQLException ex){
            System.out.println(ex);
            return false;
        }
    }
    //FUNÇÃO PESQUISAR
    
    public Produto pesquisar(String codigo) {
        con = Conexao.abrirConexao();
        sql = "select * from produto where codigo = ?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            Produto produto = null;
            if(rs.next()){
                produto = new Produto();
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setCodigo(codigo);
            }
            Conexao.fecharConexao();
            con.close();
            return produto;
            
        }catch (SQLException ex){
            System.out.println(ex);    
            return null;
        
        }
    }

    public boolean alterar(Produto produto) {
        con = Conexao.abrirConexao();
        sql = "update produto set nome = ?, quantidade = ?, preco = ?" +
                "where codigo = ?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, produto.getNome());
            ps.setInt(2, produto.getQuantidade());
            ps.setDouble(3, produto.getPreco());
            ps.setString(4,produto.getCodigo());
            ps.execute();
            Conexao.fecharConexao();
            con.close();
            return true;
            
        }catch (SQLException ex){
            System.out.println(ex);    
            return false;
            }
        }

    public boolean excluir(Produto produto) {
        con = Conexao.abrirConexao();
        sql = "delete from produto where codigo = ?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, produto.getCodigo());
            ps.execute();
            Conexao.fecharConexao();
            con.close();
            return true;
            
        }catch (SQLException ex){
            System.out.println(ex);
            return false;
            
        }
    }
    
    public ArrayList<Produto> getListaProdutos(){
        ArrayList<Produto> lista = new ArrayList<Produto>();
        
        con = Conexao.abrirConexao();
        sql = "select * from produto ";
        
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            Produto produto = null;
            while(rs.next()){
                produto = new Produto();
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setCodigo(rs.getString("codigo"));
                lista.add(produto);
            }
            Conexao.fecharConexao();
            con.close();
            
        }catch (SQLException ex){
            System.out.println(ex);    
        }
        return lista;
    }
}
