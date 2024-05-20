package consesionario;

import java.util.Objects;

public class Coche implements Comparable<Coche> {
    private String matricula;
    private String modelo;
    private int anyo;
    private int precio;

    public Coche(String matricula, String modelo, int anyo, int precio) {
        setMatricula(matricula);
        this.modelo = modelo;
        setAnyo(anyo);
        setPrecio(precio);
    }


    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        String regex="[0-9]{4}[A-Z]{3}";
        if (matricula.matches(regex)) {
            this.matricula = matricula;
        }else {
            throw new IllegalArgumentException("Error: Matricula incorrecta");
        }
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        if (anyo>=0) {
            this.anyo = anyo;
        }else {
            throw new IllegalArgumentException("Error: año incorrecto");
        }
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        if (precio>=0) {
            this.precio = precio;
        }else {
            throw new IllegalArgumentException("Error: precio incorrecto");
        }
    }
    @Override
    public String toString() {
        return "Coche{" +
                "matricula='" + matricula + '\'' +
                ", modelo='" + modelo + '\'' +
                ", anyo=" + anyo +
                ", precio=" + precio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coche coche = (Coche) o;
        return Objects.equals(matricula, coche.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(matricula);
    }

    @Override
    public int compareTo(Coche o) {
        return modelo.compareTo(o.modelo);
    }
}
