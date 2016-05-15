package mx.com.app.data.dao.edificio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    public void guardarEdificio(Edificio edificio) throws Exception{
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("edificio_nombre", edificio.getNombre());
        param.put("posicion_x", edificio.getX());
        param.put("posicion_y", edificio.getY());
        try{
            sqlSession = SqlConnectionFactory.getSessionFactory().openSession();
            sqlSession.selectOne("Edificio.guardarEdificio", param);
        } catch(Exception e){
            e.printStackTrace();
            throw e;
        } finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
    }
    public void editarEdificio(Edificio edificio) throws Exception{
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("edificio_id", edificio.getEdificioId());
        param.put("edificio_nombre", edificio.getNombre());
        param.put("posicion_x", edificio.getX());
        param.put("posicion_y", edificio.getY());
        try{
            sqlSession = SqlConnectionFactory.getSessionFactory().openSession();
            sqlSession.selectOne("Edificio.editarEdificio", param);
        } catch(Exception e){
            e.printStackTrace();
            throw e;
        } finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
    }
    
    public void eliminarEdificio(Edificio edificio) throws Exception{
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("edificio_id", edificio.getEdificioId());
        try{
            sqlSession = SqlConnectionFactory.getSessionFactory().openSession();
            sqlSession.selectOne("Edificio.eliminarEdificio", param);
        } catch(Exception e){
            e.printStackTrace();
            throw e;
        } finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
    }
}
