import android.os.StrictMode
import android.util.Log
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class ConnectSql {
    private val ip = "192.168.20.24:62338"
    private val db = "DATEVENTURE"
    private val username = "sa"
    private val password = "Colombia2023*"

    fun dbConn(): Connection? {
        var conn: Connection? = null
        val connString: String
        try {
            // Permitir todas las operaciones en el hilo principal (No recomendado en producción)
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            // Cargar el controlador JDBC
            Class.forName("net.sourceforge.jtds.jdbc.Driver")

            // Establecer la cadena de conexión
            connString =
                "jdbc:jtds:sqlserver://$ip;databaseName=$db;user=$username;password=$password"

            // Establecer la conexión
            conn = DriverManager.getConnection(connString)
        } catch (ex: SQLException) {
            Log.e("Error: ", ex.message!!)
        } catch (ex1: ClassNotFoundException) {
            Log.e("Error: ", ex1.message!!)
        } catch (ex2: Exception) {
            Log.e("Error: ", ex2.message!!)
        } finally {
            // Devolver la conexión (puede ser nula)
            return conn
        }
    }
}