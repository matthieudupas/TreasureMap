package org.treasuremap.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Simulation {
    private final TreasureMap map;
    private final List<Adventurer> adventurers;

    public Simulation(TreasureMap map, List<Adventurer> adventurers) {
        this.map = map;
        this.adventurers = adventurers;
    }

    public static void main(String[] args) {
        // Example to show how the simulation would be initialized and run
        TreasureMap map = new TreasureMap(3, 4); // Example size, replace with actual input data
        map.addMountain(new Position(1, 1));
        map.addMountain(new Position(2, 2));
        map.addTreasure(new Position(0, 3), 2);
        map.addTreasure(new Position(1, 3), 1);

        List<Adventurer> adventurers = new ArrayList<>();
        adventurers.add(new Adventurer("Indiana", new Position(1, 1), Orientation.SOUTH, "AADADA"));

        Simulation simulation = new Simulation(map, adventurers);
        simulation.run();
        simulation.printResults();
    }

    public void run() {
        boolean movementsRemaining = true;

        while (movementsRemaining) {
            movementsRemaining = false;

            // Pour suivre les nouvelles positions réservées ce tour
            Set<Position> reservedPositions = new HashSet<>();

            // Pour suivre les positions actuelles
            Set<Position> occupiedPositions = adventurers.stream()
                    .map(Adventurer::getPosition)
                    .collect(Collectors.toSet());

            for (Adventurer adventurer : adventurers) {
                if (adventurer.hasNextMove()) {
                    movementsRemaining = true;
                    Movement move = adventurer.getNextMove();

                    switch (move) {
                        case FORWARD:
                            Position newPosition = adventurer.getNextPosition();
                            // Vérifie si la position est accessible ET libre
                            if (map.isAccessible(newPosition)
                                    && !occupiedPositions.contains(newPosition)
                                    && !reservedPositions.contains(newPosition)) {

                                reservedPositions.add(newPosition);
                                occupiedPositions.remove(adventurer.getPosition());
                                occupiedPositions.add(newPosition);

                                adventurer.moveForward(newPosition);
                                map.collectTreasure(newPosition, adventurer);
                            }
                            // Sinon : mouvement ignoré comme prévu
                            break;

                        case TURN_LEFT:
                            adventurer.turnLeft();
                            break;

                        case TURN_RIGHT:
                            adventurer.turnRight();
                            break;
                    }
                }
            }
        }
    }

    public void printResults() {
        System.out.println("Final state of the map:");
        map.printMap();

        System.out.println("\nFinal state of adventurers:");
        for (Adventurer adventurer : adventurers) {
            System.out.println(adventurer);
        }
    }
}
