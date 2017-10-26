package strategos.ui;

import org.junit.Test;
import strategos.hexgrid.Hex;
import strategos.hexgrid.Map;
import strategos.mapcreation.mapgeneration.terrain.*;
import strategos.model.*;
import strategos.model.units.ArchersImpl;
import strategos.model.units.CavalryImpl;
import strategos.model.units.EliteImpl;
import strategos.model.units.SpearmenImpl;
import strategos.terrain.*;
import strategos.units.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FreeRunTest {
    @Test
    public void run(){
        main();
    }

    private static void main() {
        List<Unit> entities = new ArrayList<>();
        MapLocation[][] map = setupMap();

        UnitOwner owner = new Player(false);

        Archers a = new ArchersImpl(owner, new Hex(5,1, true));
        Cavalry c = new CavalryImpl(owner, new Hex(2,1, true));
        Elite e = new EliteImpl(owner, new Hex(2,2, true));
        Spearmen s = new SpearmenImpl(owner, new Hex(3,3, true));

        owner.addUnit(a);
        owner.addUnit(c);
        owner.addUnit(e);
        owner.addUnit(s);

        entities.add(a);
        entities.add(c);
        entities.add(e);
        entities.add(s);

        Strategos model = new Strategos(null, null, null, null, null);

        Map gameBoard = new Map(10);
        World gameCollection = new World(gameBoard, entities);

//        gameBoard.setData(map);
        gameCollection.setMap(gameBoard);
//        model.setWorld(gameCollection);

        ArrayList<UnitOwner> unitOwners = new ArrayList<>();
        unitOwners.add(owner);
        gameCollection.setAllUnits(owner.getUnits());

//        model.setPlayers(unitOwners);

        ArrayList<Unit> attackRange = new ArrayList<>();
        attackRange.add(a);
        attackRange.add(c);
        attackRange.add(e);
        attackRange.add(s);
//        model.setAttackRange(attackRange);

        ArrayList<MapLocation> moveRange = new ArrayList<>();

        for (MapLocation[] aMap : map) {
            moveRange.addAll(Arrays.asList(aMap).subList(0, map[0].length));
        }

//        model.setMoveRange(moveRange);
        model.setThisInstancePlayer(owner);

        UI ui = new UI(model);
        ui.revealMap();
        ui.skipMenu();
    }

    public static MapLocation[][] setupMap() {
        Terrain[][] terrain = {
                {new MountainTile(), new MountainTile(), new MountainTile(),  new MountainTile(),  new MountainTile(),  new MountainTile(),   new MountainTile(),  new MountainTile(),  new MountainTile()},
                {new MountainTile(), new MountainTile(), new ForestTile(),    new ForestTile(),    new ForestTile(),    new ForestTile(),     new MountainTile(),  new MountainTile(),  new MountainTile()},
                {new MountainTile(), new MountainTile(), new ForestTile(),    new ForestTile(),    new ForestTile(),    new ForestTile(),     new ForestTile(),    new MountainTile(),  new MountainTile()},
                {new MountainTile(), new HillTile(),     new HillTile(),      new HillTile(),      new HillTile(),      new RiverTile(),      new HillTile(),      new MountainTile(),  new MountainTile()},
                {new MountainTile(), new HillTile(),     new HillTile(),      new HillTile(),      new HillTile(),      new HillTile(),       new HillTile(),      new HillTile(),      new MountainTile()},
                {new MountainTile(), new RiverTile(),    new RiverTile(),     new HillTile(),      new RiverTile(),      new HillTile(),      new HillTile(),      new MountainTile(),  new MountainTile()},
                {new MountainTile(), new MountainTile(), new HillTile(),      new HillTile(),      new HillTile(),      new HillTile(),       new HillTile(),      new MountainTile(),  new MountainTile()},
                {new MountainTile(), new MountainTile(), new PlainsTile(),    new PlainsTile(),    new PlainsTile(),    new PlainsTile(),     new MountainTile(),  new MountainTile(),  new MountainTile()},
                {new MountainTile(), new MountainTile(), new MountainTile(),  new MountainTile(),  new MountainTile(),  new MountainTile(),   new MountainTile(),  new MountainTile(),  new MountainTile()},
        };

        MapLocation[][] map = new MapLocation[terrain.length][terrain[0].length];

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                map[y][x] = new Hex(x, y, true);
                map[y][x].setTerrain(terrain[y][x]);
            }
        }

        return map;
    }
}
