package mx.com.app.data.dao.salon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.com.app.data.model.salon.Salon;
import mx.com.app.data.sql.SqlConnectionFactory;
import org.apache.ibatis.session.SqlSession;

public class SalonDAO {

    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<Salon> listarSalones() throws Exception {
        List<Salon> salones = null;
        try {
            sqlSession = SqlConnectionFactory.getSessionFactory().openSession(); //con false para transacción
            salones = sqlSession.selectList("Salon.todosSalones");//NAMESPACE.ID
            return salones;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    public void guardarSalon(Salon salon) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("capacidad", salon.getCapacidad());
        param.put("tipo_salon", salon.getTipoSalon());
        param.put("edificio_id", salon.getEdificio().getEdificioId());
        param.put("nombre", salon.getNombre());
        try {
            sqlSession = SqlConnectionFactory.getSessionFactory().openSession();
            sqlSession.selectOne("Salon.guardarSalon", param);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    public void editarSalon(Salon salon) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("salon_id", salon.getSalonID());
        param.put("capacidad", salon.getCapacidad());
        param.put("tipo_salon", salon.getTipoSalon());
        param.put("edificio_id", salon.getEdificio().getEdificioId());
        param.put("nombre", salon.getNombre());
        try {
            sqlSession = SqlConnectionFactory.getSessionFactory().openSession();
            sqlSession.selectOne("Salon.editarSalon", param);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    public void eliminarSalon(int id) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("salon_id", id);
        
        try {
            sqlSession = SqlConnectionFactory.getSessionFactory().openSession();
            sqlSession.selectOne("Salon.eliminarSalon", param);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
