package mx.com.app.data.dao.edificio;

import java.util.List;
import mx.com.app.data.model.edificio.Edificio;
import mx.com.app.data.sql.SqlConnectionFactory;
import org.apache.ibatis.session.SqlSession;

public class EdificioDAO {
    private SqlSession sqlSession;
    
    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
    public List<Edificio> todosEdificios() throws Exception{
        List<Edificio> edificios = null;
        try{
            sqlSession = SqlConnectionFactory.getSessionFactory().openSession(); //con false para transacción
            edificios = sqlSession.selectList("Edificio.todosEdificios");//NAMESPACE.ID
            System.out.println(edificios.get(0).getNombre());
            return edificios;
        } catch(Exception e){
            e.printStackTrace();
            throw e;
        } finally{
            if(sqlSession != null){
                sqlSession.close();
            }
        }
    }
}
