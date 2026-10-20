package EJ2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BaseDatos {

    private static final String URL = "jdbc:sqlite:estudiantes.db";

    public static Connection conectar() throws Exception {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection(URL);
    }

    public static void crearTablaYDatos() {
        try (Connection con = conectar(); Statement stmt = con.createStatement()) {
            System.out.println("Se creó y/o abrió la base de datos.");

            stmt.execute("CREATE TABLE IF NOT EXISTS estudiante (id integer PRIMARY KEY, nombre text, carrera text, nota real);");
            System.out.println("Tabla lista.");

            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM estudiante");
            rs.next();
            int cantidad = rs.getInt(1);
            rs.close();

            if (cantidad == 0) {
                try (PreparedStatement ps = con.prepareStatement("INSERT INTO estudiante VALUES (?, ?, ?, ?)")) {
                    insertar(ps, 1, "Ana", "Sistemas", 16.5);
                    insertar(ps, 2, "Luis", "Civil", 14.0);
                    insertar(ps, 3, "Carla", "Sistemas", 18.0);
                    insertar(ps, 4, "Diego", "Industrial", 12.5);
                    insertar(ps, 5, "Marta", "Civil", 17.0);
                    insertar(ps, 6, "José", "Industrial", 15.5);
                }
                System.out.println("Inserción exitosa.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void insertar(PreparedStatement ps, int id, String nombre, String carrera, double nota) throws Exception {
        ps.setInt(1, id);
        ps.setString(2, nombre);
        ps.setString(3, carrera);
        ps.setDouble(4, nota);
        ps.executeUpdate();
    }

    public static ArrayList<Estudiante> leerTodos() {
        ArrayList<Estudiante> lista = new ArrayList<>();
        try (Connection con = conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM estudiante")) {

            while (rs.next()) {
                lista.add(new Estudiante(rs.getInt("id"), rs.getString("nombre"), rs.getString("carrera"), rs.getDouble("nota")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }
}