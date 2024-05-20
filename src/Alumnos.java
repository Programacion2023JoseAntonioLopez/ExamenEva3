import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Predicate;

public class Alumnos {
    HashMap<String, Integer> alumnos;

    public Alumnos() {
        alumnos=new HashMap<>();
    }
    public void addAlumno(String nombre, int nota){
        if (nota>10 || nota<0){
            throw new IllegalArgumentException("Error: Nota incorrecta");
        }
        if (alumnos.containsKey(nombre)){
            throw new IllegalArgumentException("Error: Este alumno ya existe");
        }else {
            alumnos.put(nombre,nota);
        }
    }
    public void delAlumno(String alumno){
        if (alumnos.containsKey(alumno)){
            alumnos.remove(alumno);
        }else {
            throw new IllegalArgumentException("Error: Este alumno no existe");
        }
    }
    public ArrayList<String> buscarAlumnos(Predicate<Integer> condicion){
        ArrayList<String> alumnosCoincidentes= new ArrayList<>();
        alumnos.forEach((nombre, nota)->{
            if (condicion.test(nota)){
                alumnosCoincidentes.add(nombre);
            }
        });
        return alumnosCoincidentes;
    }

    public static void main(String[] args) {
        Alumnos prueba = new Alumnos();
        try {
            prueba.addAlumno("Pepe",10);
            prueba.addAlumno("Maria",4);
            prueba.alumnos.forEach((nombre,nota)-> System.out.println(nombre+": "+nota));
            ArrayList<String> alumnosCoincidentes=prueba.buscarAlumnos(nota->nota>5);
            alumnosCoincidentes.forEach(System.out::println);
            prueba.delAlumno("Pepe");
            prueba.alumnos.forEach((nombre,nota)-> System.out.println(nombre+": "+nota));
            prueba.addAlumno("Tere",-5);
        }catch (IllegalArgumentException e){
            System.out.println(e.toString());
        }
        try {
            prueba.delAlumno("Paca");
        }catch (IllegalArgumentException e){
            System.out.println(e.toString());
        }

    }


}
