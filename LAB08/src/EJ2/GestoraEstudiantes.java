package EJ2;

import java.util.ArrayList;

public class GestoraEstudiantes {
    private ArrayList<Estudiante> registros = new ArrayList<>();

    public void cargarDesdeBD() {
        registros = BaseDatos.leerTodos();
        System.out.println("Registros cargados en el arreglo: " + registros.size());
    }
    private String obtenerValor(Estudiante e, String campo) {
        switch (campo) {
            case "id":
                return String.valueOf(e.getId());
            case "nombre":
                return e.getNombre();
            case "carrera":
                return e.getCarrera();
            case "nota":
                return String.valueOf(e.getNota());
            default:
                throw new IllegalArgumentException("Campo no válido: " + campo);
        }
    }
    private int comparar(Estudiante a, Estudiante b, String campo) {
        switch (campo) {
            case "id":
                return Integer.compare(a.getId(), b.getId());
            case "nombre":
                return a.getNombre().compareToIgnoreCase(b.getNombre());
            case "carrera":
                return a.getCarrera().compareToIgnoreCase(b.getCarrera());
            case "nota":
                return Double.compare(a.getNota(), b.getNota());
            default:
                throw new IllegalArgumentException("Campo no válido: " + campo);
        }
    }
    private ArrayList<Estudiante> ordenar(ArrayList<Estudiante> lista, String campoOrden, boolean ascendente) {
        ArrayList<Estudiante> copia = new ArrayList<>(lista);

        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = 0; j < copia.size() - 1 - i; j++) {
                int resultado = comparar(copia.get(j), copia.get(j + 1), campoOrden);
                if (!ascendente) {
                    resultado = -resultado;
                }
                if (resultado > 0) {
                    Estudiante temporal = copia.get(j);
                    copia.set(j, copia.get(j + 1));
                    copia.set(j + 1, temporal);
                }
            }
        }
        return copia;
    }
    private ArrayList<Estudiante> limitar(ArrayList<Estudiante> lista, int limite) {
        if (limite <= 0 || limite >= lista.size()) {
            return lista;
        }
        return new ArrayList<>(lista.subList(0, limite));
    }
    public void consultar(String[] campos, String campoFiltro, String valorFiltro,
                          String campoOrden, boolean ascendente, int limite) {
        ArrayList<Estudiante> resultado = new ArrayList<>();
        for (Estudiante e : registros) {
            if (campoFiltro == null || obtenerValor(e, campoFiltro).equalsIgnoreCase(valorFiltro)) {
                resultado.add(e);
            }
        }
        if (campoOrden != null) {
            resultado = ordenar(resultado, campoOrden, ascendente);
        }
        resultado = limitar(resultado, limite);
        for (Estudiante e : resultado) {
            String linea = "";
            for (String campo : campos) {
                linea = linea + obtenerValor(e, campo) + " | ";
            }
            System.out.println(linea);
        }
    }
}