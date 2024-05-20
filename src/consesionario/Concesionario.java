package consesionario;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Concesionario {
    private ArrayList<Coche> coches;

    public Concesionario() {
        coches = new ArrayList<>();
        coches.add(new Coche("1111AAA", "Seat Ibiza", 2011, 1000));
        coches.add(new Coche("3333CCC", "VW golf", 2020, 5000));
        coches.add(new Coche("6666GGG", "Seat Leon", 1321, 5500));
        Collections.sort(coches);
    }

    public void elimina(String matricula) {

        coches.remove(new Coche(matricula, "", 0, 0));
        //tambíen podríamos hacerlo con un bucle for
        // for (int i=0; i<coches.size(); i++){
        //     if (coches.get(i).getMatricula().equals(matricula)){
        //         coches.remove(i);
        //     }
        // }
        //también mediante una lambda
        // coches.removeIf(coche -> coche.getMatricula().equals(matricula));

    }

    public void nuevo(Coche coche) {
        int indice = coches.indexOf(coche);
        if (indice >= 0) {
            coches.set(indice, coche);
        } else {
            coches.add(coche);
        }
    }

    public void porPrecio(int min, int max) {
        coches.stream()
                .filter(coche -> coche.getPrecio() >= min && coche.getPrecio() <= max)
                .sorted(Comparator.comparingInt(Coche::getAnyo))
                .forEach(System.out::println);
    }

    public List<Coche> porMarca(String marca) {
        List<Coche> cocheMarca = coches.stream()
                .filter(coche -> coche.getModelo().contains(marca))
                .sorted(Comparator.comparingInt(Coche::getPrecio))
                .collect(Collectors.toList());
        return cocheMarca;
    }

    public void subidaPrecios(int porcentaje) {
        Iterator<Coche> it = coches.iterator();
        while (it.hasNext()) {
            Coche coche = it.next();
            int precio = coche.getPrecio();
            precio = precio + (precio * porcentaje) / 100;
            coche.setPrecio(precio);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Coche c: coches){
            sb.append(c.toString()+"\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Concesionario concesionario = new Concesionario();
        try {
            concesionario.nuevo(new Coche("4444VVV", "WV Passat", 2019, 7000));
            System.out.println(concesionario);
            System.out.println("Entre 1500 y 6000");
            concesionario.porPrecio(1500, 6000);
            System.out.println("Coches Seat");
            List<Coche> seat = concesionario.porMarca("Seat");
            seat.forEach(System.out::println);
            System.out.println("Subida 10%");
            concesionario.subidaPrecios(10);
            System.out.println(concesionario);
            System.out.println("Elimina 4444VVV");
            concesionario.elimina("4444VVV");
            System.out.println(concesionario);
            System.out.println("------Error-------------------");
            concesionario.nuevo(new Coche("BBB2222", "BMW GTU", 2022, 5000));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
