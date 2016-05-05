package mx.com.app.data.sql;

import java.io.Reader;
import java.nio.charset.StandardCharsets;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlConnectionFactory {
    public static final String PERSISTENCE_CONFIG_FILE = "mx/com/app/data/cfg/mybatis-config.xml";

    private static SqlSessionFactory sqlSessionFactory;

    /**
     * Carga el archivo de configuración de MyBatis para poder establecer las
     * conexiones de persistencia.
     * 
     * @throws Exception
     *             Si el archivo de configuración no existe o no puede ser
     *             accedido.
     */
    private static synchronized void loadConfiguration() throws Exception{
        if (sqlSessionFactory == null) {
            Reader reader = null;
            try {
                Resources.setCharset(StandardCharsets.UTF_8);
                reader = Resources.getResourceAsReader(PERSISTENCE_CONFIG_FILE);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Obtiene el componente factory para crear las sesiones de persistencia.
     * 
     * @return SqlSessionFactory
     * @throws Exception
     *             Si el archivo de configuración no existe o no puede ser
     *             accedido.
     */
    public static SqlSessionFactory getSessionFactory() throws Exception{
        if (sqlSessionFactory == null) {
            loadConfiguration();
        }
        return sqlSessionFactory;
    }
}
