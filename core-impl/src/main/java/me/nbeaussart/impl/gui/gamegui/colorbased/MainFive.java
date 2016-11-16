package me.nbeaussart.impl.gui.gamegui.colorbased;

import me.nbeaussart.engine.model.Color;
import me.nbeaussart.engine.model.Cord;
import me.nbeaussart.engine.util.MathUtil;
import me.nbeaussart.engine.view.GameScreen;
import me.nbeaussart.engine.view.GameSquareClicked;
import me.nbeaussart.engine.view.MapPrinter;

import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Optional;

import static me.nbeaussart.engine.view.GameScreen.createGameScreen;

/**
 * @author Nicolas Beaussart
 * @since 13/11/16
 */
public class MainFive {

    private final static Color COLOR_WALL=new Color(0,0,0);
    private final static Color COLOR_ROOM=new Color(255,255,255);
    private final static Color COLOR_DOOR=new Color(255,0,0);

    public static void main(String ... args){

        GameMap gm ;
        gm = new GameMap(60, 60, 20, 20);
        for (int y = 0; y < gm.sizeY(); y++) {
            for (int x = 0; x < gm.sizeX(); x++) {
                gm.add(new GameSquare(COLOR_WALL, Cord.get(x,y), gm));

            }
        }
        gm.setChanged(null);
        MapPrinter<GameSquare> mp = new MapPrinter<>(gm);
        GameScreen hello = createGameScreen("hello", mp);
        MathUtil.BresenhamAlgorithm(Cord.get(20,20), Cord.get(40,28))
                .stream()
                .map(gm::getFromCords)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(gameSquare -> gameSquare.setColor(COLOR_DOOR));

        mp.addGameClicked(new GameSquareClicked<GameSquare>() {
            @Override
            public void mouseMoved(GameSquare square, MouseEvent e) {
                gm.getMapData().values().forEach(gameSquare -> gameSquare.setColor(COLOR_WALL));
                List<Cord> cordList = MathUtil.BresenhamAlgorithm(Cord.get(20, 20), square.getCord());

                cordList
                        .stream()
                        .map(gm::getFromCords)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .forEach(gameSquare -> gameSquare.setColor(COLOR_DOOR));
                System.out.println(cordList);
            }
        });

        System.out.println("Done");
    }
}
