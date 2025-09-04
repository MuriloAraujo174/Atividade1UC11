import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ProdutosDAO {
    
    String sql = "insert into produtos (nome, valor, status) values (?, ?, ?);";
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public ProdutosDAO() {
        this.conn = new conectaDAO().connectDB();
    }
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        try {
            PreparedStatement st = this.conn.prepareStatement(sql);
            
            st.setString(1, produto.getNome());
            st.setInt(2, produto.getValor());
            st.setString(3, produto.getStatus());
            
            st.executeUpdate();
            
        } catch (Exception e) {
        }
        
        
    }
    
    public List<ProdutosDTO> listarProdutos(){
         sql = "select * from produtos";
        
        try {
            PreparedStatement st = this.conn.prepareStatement(sql);
            
            ResultSet rs = st.executeQuery();
            
            List<ProdutosDTO> listagem = new ArrayList<>();
            
            while(rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                
                listagem.add(produto);
                
            }
            return listagem;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        }
        
    }
    
    public void venderProduto(int id) {
        sql = "update produtos set status = ? where id = ?";
        
        String status = "Vendido";
        
        try {
            PreparedStatement st = this.conn.prepareStatement(sql);
            
            st.setString(1, status);
            st.setInt(2, id);
            
            st.executeUpdate();
            
        } catch (Exception e) {
        }
    }
    
    public ProdutosDTO buscarID(int id) { 
        sql = "select * from produtos where id = ?";
        
        try {
            PreparedStatement st = this.conn.prepareStatement(sql);
            
            ProdutosDTO produto = null;
            
            st.setInt(1, id);
            
            ResultSet rs = st.executeQuery();
            
            while(rs.next()) {
                produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                
            }
            
            return produto;
            
        } catch (Exception e) {
            return null;
        }
        
    }
    
    public List<ProdutosDTO> listagemVendidos() {
        sql = "select * from produtos where status = 'Vendido'";
        
        try {
            PreparedStatement st = this.conn.prepareStatement(sql);
            
            ResultSet rs = st.executeQuery();
            
            List<ProdutosDTO> ListagemVendidos = new ArrayList<>();
            
            while(rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                
                ListagemVendidos.add(produto);
            }
            
            return ListagemVendidos;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        }
        
    }
    
}

