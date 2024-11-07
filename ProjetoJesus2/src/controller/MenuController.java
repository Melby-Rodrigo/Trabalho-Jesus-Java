package controller;

import dao.Conexao;
import dao.ProdutoDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;  // Importando JOptionPane
import view.MenuView;
import model.Produto;

/**
 * @author digom
 */
public class MenuController {
    private MenuView view;
    private final ProdutoDAO dao;

    public MenuController(MenuView view) throws SQLException {
        this.view = view;
        Connection conexao = new Conexao().getConnection();
        this.dao = new ProdutoDAO(conexao);
    }

    public Produto registrarProduto(String produto, String quantidade, String valor, String marca) {
        return this.dao.insert(produto, quantidade, valor, marca);
    }

    public ArrayList<Produto> listarProdutos() {
        return this.dao.getAll();
    }
    
    public ArrayList<Produto> buscarProdutos(String filtro) {
        return this.dao.getAllFilteredByName(filtro);
    }
    
    public Produto atualizarProduto(int id, String produto, String quantidade, String valor, String marca) {
        return this.dao.update(id, produto, quantidade, valor, marca);
    }

    public int excluirProduto(int id) {
        // Exibir a caixa de diálogo de confirmação
        int resposta = JOptionPane.showConfirmDialog(
                view, // A janela onde o diálogo será exibido
                "Tem certeza que deseja excluir este produto?", // Mensagem
                "Confirmar exclusão", // Título da janela
                JOptionPane.YES_NO_OPTION, // Opções de botões (Sim / Não)
                JOptionPane.WARNING_MESSAGE // Tipo de mensagem (Aviso)
        );

        // Se o usuário clicar em "Sim", executa a exclusão
        if (resposta == JOptionPane.YES_OPTION) {
            return this.dao.delete(id);
        }
        // Se o usuário clicar em "Não", retorna 0 (nenhuma exclusão feita)
        return 0;
    }
}