package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.dao.CategoriaDAO;
import br.edu.ifsp.pep.dao.ProdutoDAO;
import br.edu.ifsp.pep.model.Categoria;
import br.edu.ifsp.pep.model.Produto;
import br.edu.ifsp.pep.util.Util;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProdutoController implements Serializable {

    @EJB
    private ProdutoDAO produtoDAO;

    @EJB
    private CategoriaDAO categoriaDAO;

    private List<Produto> produtos;
    private Produto produto; // é utilizado no cadastro
    private Produto produtoSelecionado; // é utilizado na listagem
    private Categoria categoria;

    public ProdutoController() {
        System.out.println("Construtor.");
        this.produto = new Produto();

    }

    public String adicionar() {
        produtoDAO.inserir(produto);
        System.out.println("Produto adicionado.");
        this.produto = new Produto();
        //Para atualizar com os dados do banco de dados
        this.produtos = null;

        Util.info("Produto cadastrado.");

        return "/produto/List";
    }

    public String goToHome() {
        System.out.println("Ir para a página inicial.");
        return "/index";
    }

    public List<Produto> getProdutos() {
        //Reduzir o acesso ao banco de dados
        if (produtos == null) {
            produtos = produtoDAO.buscarTodos();
        }
        return produtos;
    }

    public void remover() {
        produtos.remove(produtoSelecionado);
    }

    public void exibir() {
        System.out.println(produtoSelecionado.getName());
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }

    private void inserirProdutos() {
        for (Produto produto : produtos) {
            produtoDAO.inserir(produto);
        }
    }

    public List<Categoria> findAllCategorias() {
        return categoriaDAO.findAll();
    }

    public Categoria findByCodigo(Integer id) {
        return categoriaDAO.findById(id);
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
