import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.treasuremap.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimulationTest {

    private TreasureMap map;
    private List<Adventurer> adventurers;
    private Simulation simulation;

    @BeforeEach
    void setUp() {
        // Créer une carte de test 3x4
        map = new TreasureMap(3, 4);

        // Ajouter des montagnes
        map.addMountain(new Position(1, 1));
        map.addMountain(new Position(2, 2));

        // Ajouter des trésors
        map.addTreasure(new Position(0, 3), 2); // 2 trésors en (0, 3)
        map.addTreasure(new Position(1, 3), 1); // 1 trésor en (1, 3)

        // Créer une liste d'aventuriers
        adventurers = new ArrayList<>();
    }

    @Test
    void testSingleAdventurerCollectsTreasures() {
        // Initialiser un aventurier avec des mouvements vers des trésors
        Adventurer adventurer = new Adventurer("Indiana", new Position(0, 2), Orientation.SOUTH, "AADA");
        adventurers.add(adventurer);

        // Initialiser la simulation
        simulation = new Simulation(map, adventurers);
        simulation.run();

        // Vérifier la position finale
        assertEquals(new Position(0, 3), adventurer.getPosition(), "Adventurer should end up at (0, 3)");

        // Vérifier le nombre de trésors collectés
        assertEquals(1, adventurer.getCollectedTreasures(), "Adventurer should have collected 1 treasure");

        // Vérifier les trésors restants sur la carte
        assertEquals(1, map.getTreasureCount(new Position(0, 3)), "There should be 1 treasure left at (0, 3)");
    }

    @Test
    void testMultipleAdventurersWithConflictingMovements() {
        // Ajouter deux aventuriers avec des parcours qui pourraient entrer en conflit
        Adventurer lara = new Adventurer("Lara", new Position(0, 2), Orientation.SOUTH, "AADA");
        Adventurer indiana = new Adventurer("Indiana", new Position(1, 2), Orientation.SOUTH, "AADA");
        adventurers.add(lara);
        adventurers.add(indiana);

        // Initialiser la simulation
        simulation = new Simulation(map, adventurers);
        simulation.run();

        // Vérifier les positions finales des aventuriers
        assertEquals(new Position(0, 3), lara.getPosition(), "Lara should end up at (0, 3)");
        assertEquals(new Position(1, 3), indiana.getPosition(), "Indiana should end up at (1, 3)");

        // Vérifier le nombre de trésors collectés par chaque aventurier
        assertEquals(1, lara.getCollectedTreasures(), "Lara should have collected 1 treasure");
        assertEquals(1, indiana.getCollectedTreasures(), "Indiana should have collected 1 treasure");

        // Vérifier les trésors restants sur la carte
        assertEquals(1, map.getTreasureCount(new Position(0, 3)), "1 treasure should be left at (0, 3)");
        assertEquals(0, map.getTreasureCount(new Position(1, 3)), "No treasure should be left at (1, 3)");
    }

    @Test
    void testAdventurerBlockedByMountain() {
        // Initialiser un aventurier avec un chemin bloqué par une montagne
        Adventurer adventurer = new Adventurer("Indiana", new Position(0, 0), Orientation.EAST, "AAA");
        adventurers.add(adventurer);
        map.addMountain(new Position(1, 0));

        // Initialiser la simulation
        simulation = new Simulation(map, adventurers);
        simulation.run();

        // Vérifier que l'aventurier est bloqué par la montagne et ne l'a pas traversée
        assertEquals(new Position(0, 0), adventurer.getPosition(),
                "Should be blocked immediately by mountain at (1, 0)");
        assertEquals(0, adventurer.getCollectedTreasures(), "Adventurer should not have collected any treasures");
    }
}
