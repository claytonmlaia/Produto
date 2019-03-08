
package br.com.produtoapp.controle;

import br.com.produtoapp.modelo.Produto;
import dao.ProdutoDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean (name="mb")
public class ProdutoManagedBean {
    
    //declaração do objeto produto
    private Produto produto;
    private ProdutoDAO dao;
    private List<Produto> lista;
    
    
    //método construtor
    public ProdutoManagedBean(){
        //instancia do objeto produto
       produto = new Produto(); 
       dao = new ProdutoDAO();
       lista = dao.getListaProdutos();
       
    }
    
    //get e set

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    //BOTOES
    public void salvar(){
        dao.salvar(produto);
        lista.add(produto);
        limparTela();
        
    }
    public void pesquisar(){
        produto = dao.pesquisar(produto.getCodigo());
        
    }
    public void alterar(){
        dao.alterar(produto);
        lista = dao.getListaProdutos();
        limparTela();
    }
    public void excluir(){
        dao.excluir(produto);
        lista = dao.getListaProdutos();
        limparTela();
    }
    public void limparTela(){
        produto = new Produto();
    }

    public List<Produto> getLista() {
        return lista;
    }

    public void setLista(List<Produto> lista) {
        this.lista = lista;
    }
    
}
