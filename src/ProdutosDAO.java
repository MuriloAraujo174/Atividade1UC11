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
    
    String sql = "insert into produtos (nome, valor) values (?, ?);";
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        
        conn = new conectaDAO().connectDB();
        
        try {
            PreparedStatement st = this.conn.prepareStatement(sql);
            
            st.setString(1, produto.getNome());
            st.setInt(2, produto.getValor());
            
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
                
            }
            return listagem;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        }
        
    }
    
    
    
        
}

