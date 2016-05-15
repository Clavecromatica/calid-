package mx.com.app.data.dao.puesto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.com.app.data.model.Puesto.Puesto;
import mx.com.app.data.sql.SqlConnectionFactory;
import org.apache.ibatis.session.SqlSession;

public class PuestoDAO {
    private SqlSession sqlSession;
    
    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
    public List<Puesto> todosPuestos() throws Exception{
        List<Puesto> puestos = null;
        try{
            sqlSession = SqlConnectionFactory.getSessionFactory().openSession(); //con false para transacción
            puestos = sqlSession.selectList("Puesto.todosPuestos");//NAMESPACE.ID
            return puestos;
        } catch(Exception e){
            e.printStackTrace();
            throw e;
        } finally{
            if(sqlSession != null){
                sqlSession.close();
            }
        }
    }
    
    public void guardarPuesto(Puesto puesto) throws Exception{
        Map<String, Object> param = new HashMap<>();
        param.put("puesto_nombre", puesto.getNombre());
        try{
            sqlSession = SqlConnectionFactory.getSessionFactory().openSession();
            sqlSession.selectOne("Puesto.guardarPuesto", param);
        } catch(Exception e){
            e.printStackTrace();
            throw e;
        } finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
    }
    public void editarPuesto(Puesto puesto) throws Exception{
        Map<String, Object> param = new HashMap<>();
        param.put("puesto_id", puesto.getId());
        param.put("puesto_nombre", puesto.getNombre());
        try{
            sqlSession = SqlConnectionFactory.getSessionFactory().openSession();
            sqlSession.selectOne("Puesto.editarPuesto", param);
        } catch(Exception e){
            e.printStackTrace();
            throw e;
        } finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
    }
    
    public void eliminarPuesto(Puesto puesto) throws Exception{
        Map<String, Object> param = new HashMap<>();
        param.put("puesto_id", puesto.getId());
        try{
            sqlSession = SqlConnectionFactory.getSessionFactory().openSession();
            sqlSession.selectOne("Puesto.eliminarPuesto", param);
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
