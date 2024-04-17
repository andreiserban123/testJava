import seminar.seminar2.g1064.Agent;
import seminar.seminar2.g1064.Apartament;
import seminar.seminar2.g1064.Zona;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LucruCuStreams {
    public static void main(String[] args) {
        seminar.seminar2.g1064.Main.citireDate("apartamente.csv");
        List<Apartament> apartamente = seminar.seminar2.g1064.Main.apartamente;
        System.out.println("Apartamente:");
//        for (Apartament apartament:apartamente){
//            System.out.println(apartament);
//        }

//        apartamente.forEach(new Consumer<Apartament>() {
//            @Override
//            public void accept(Apartament apartament) {
//                System.out.println(apartament);
//            }
//        });

//        apartamente.forEach( apartament -> System.out.println(apartament) );

        apartamente.forEach(System.out::println);

//        Cerinta 1
        List<Apartament> cerinta1 = apartamente.stream().
                filter(apartament -> apartament.getZona().equals(Zona.AVIATIEI)).
                toList();
        System.out.println("Cerinta 1:");
        cerinta1.forEach(System.out::println);

        double pretMinim = 80000, pretMaxim = 150000;
        List<Apartament> cerinta2 = apartamente.stream()
                .filter(apartament -> apartament.getPret() >= pretMinim && apartament.getPret() <= pretMaxim)
                .toList();
        System.out.println("Cerinta 2:");
        cerinta2.forEach(System.out::println);

        List<Apartament> cerinta3 = apartamente.stream()
                .filter(apartament -> Arrays.stream(apartament.getDotari()).anyMatch(dotare -> dotare.toLowerCase().contains("parcare")))
                .toList();
        System.out.println("Cerinta 3:");
        cerinta3.forEach(System.out::println);

        List<Apartament> cerinta4 = apartamente.stream().sorted().toList();
        System.out.println("Apartamentele sortate dupa pret:");
        cerinta4.forEach(System.out::println);

        List<Apartament> cerinta4_ = apartamente.stream()
                .sorted(((apartament1, apartament2) -> Integer.compare(apartament2.getSuprafataUtila(), apartament1.getSuprafataUtila())))
                .toList();
        System.out.println("Apartamentele sortate descrescator dupa suprafata:");
        cerinta4_.forEach(System.out::println);

        List<Agent> agenti = seminar.seminar2.g1064.Main.agenti;
        System.out.println("Lista agenti:");
        agenti.forEach(System.out::println);
        long cnpAgent = 1671010288599L;

        Optional<Agent> lista = agenti.stream().filter(agent -> agent.getCnp() == cnpAgent).findFirst();
        if (lista.isPresent()) {
            Agent agent = lista.get();
            Set<String> cerinta5 = apartamente.stream().filter(apartament -> Arrays.stream(agent.getImobile()).anyMatch(id -> apartament.getId() == id))
                    .map(apartament -> apartament.getTelefonP()).collect(Collectors.toSet());
            System.out.println("Cerinta 5:");
            cerinta5.forEach(System.out::println);
        }

        Map<Integer, String> cerinta6 = apartamente.stream()
                .collect(Collectors.toMap(Apartament::getId, Apartament::getTelefonP));
        System.out.println("Cerinta 6");
        cerinta6.keySet().forEach(id -> System.out.println(id + ":" + cerinta6.get(id)));

        Map<Zona, List<Apartament>> cerinta7 = apartamente.stream()
                .collect(Collectors.groupingBy(Apartament::getZona));
        System.out.println("Cerinta 7:");
        cerinta7.keySet().forEach(zona -> {
            System.out.println(zona);
            cerinta7.get(zona).forEach(System.out::println);
        });

        Map<Zona, List<Integer>> cerinta8 = apartamente.stream()
                .collect(Collectors.groupingBy(
                        Apartament::getZona,
                        Collectors.mapping(Apartament::getId, Collectors.toList())
                ));

        System.out.println("Cerinta 8:");
        cerinta8.keySet().forEach(zona -> System.out.println(zona + ":" + cerinta8.get(zona)));

        Map<Zona, Double> cerinta9 = apartamente.stream()
                .collect(Collectors.groupingBy(
                        Apartament::getZona,
                        Collectors.averagingDouble(Apartament::getPret)));
        System.out.println("Cerinta 9:");
        cerinta9.keySet().forEach(zona -> System.out.println(zona + ":" + cerinta9.get(zona)));

//        Modificare: colectare zona si pret/mp
        Map<Integer, ?> cerinta10 = apartamente.stream()
                .collect(Collectors.toMap(Apartament::getId, apartament -> new Object() {
                    Zona zona = apartament.getZona();
                    double pret = apartament.getPret();
                    int suprafata = apartament.getSuprafataUtila();

                    @Override
                    public String toString() {
                        return zona + "," + pret / suprafata;
                    }
                }));
        System.out.println("Cerinta 10:");
        cerinta10.keySet().forEach(id -> System.out.println(id + ":" + cerinta10.get(id)));

//        Map<Long, Set<String>> cerinta11 = agenti.stream()
//                .collect(new Supplier<Map<Long, Set<String>>>() {
//                    @Override
//                    public Map<Long, Set<String>> get() {
//                        return new HashMap<>();
//                    }
//                }, new BiConsumer<Map<Long, Set<String>>, Agent>() {
//                    @Override
//                    public void accept(Map<Long, Set<String>> longSetMap, Agent agent) {
//                        Set<String> telefoane = new HashSet<>();
//                        Arrays.stream(agent.getImobile()).forEach(id -> {
//                            telefoane.add(
//                                    apartamente.get(apartamente.indexOf(new Apartament(id))).getTelefonP());
//                        });
//                        longSetMap.put(agent.getCnp(), telefoane);
//                    }
//                }, new BiConsumer<Map<Long, Set<String>>, Map<Long, Set<String>>>() {
//                    @Override
//                    public void accept(Map<Long, Set<String>> longSetMap, Map<Long, Set<String>> longSetMap2) {
//                        longSetMap.putAll(longSetMap2);
//
//                    }
//                });
        Map<Long, Set<String>> cerinta11 = agenti.stream().collect(
                new Supplier<Map<Long, Set<String>>>() {
                    @Override
                    public Map<Long, Set<String>> get() {
                        return new HashMap<>();
                    }
                },
                new BiConsumer<Map<Long, Set<String>>, Agent>() {
                    @Override
                    public void accept(Map<Long, Set<String>> longSetMap, Agent agent) {
                        Set<String> telefonane = new HashSet<>();
                        Arrays.stream(agent.getImobile()).forEach(id -> {
                            telefonane.add(apartamente.get(apartamente.indexOf(new Apartament(id))).getTelefonP());
                        });
                        longSetMap.put(agent.getCnp(), telefonane);
                    }
                },
                new BiConsumer<Map<Long, Set<String>>, Map<Long, Set<String>>>() {
                    @Override
                    public void accept(Map<Long, Set<String>> longSetMap, Map<Long, Set<String>> longSetMap2) {

                    }
                }
        );

        System.out.println("Cerinta 11:");
        cerinta11.keySet().forEach(cnp -> System.out.println(cnp + ":" + cerinta11.get(cnp)));
//        Map<Long, Set<String>> cerinta11_ = agenti.stream()
//                .collect(
//                        HashMap::new,
//                        (longSetMap, agent) -> {
//                            Set<String> telefoane = new HashSet<>();
//                            Arrays.stream(agent.getImobile()).forEach(id -> {
//                                telefoane.add(
//                                        apartamente.get(apartamente.indexOf(new Apartament(id))).getTelefonP());
//                            });
//                            longSetMap.put(agent.getCnp(), telefoane);
//                        }, HashMap::putAll
//                );
//        System.out.println("Cerinta 11_:");
//        cerinta11_.keySet().forEach(cnp -> System.out.println(cnp + ":" + cerinta11_.get(cnp)));

    }
}
