package me.nbeaussart.impl.gui.gamegui;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import me.nbeaussart.engine.model.Color;
import me.nbeaussart.engine.model.Cord;
import me.nbeaussart.engine.model.interfaces.IColoredSquare;
import me.nbeaussart.engine.model.interfaces.ICoordinateSquare;
import me.nbeaussart.engine.model.interfaces.IGameMap;

/**
 * @author Nicolas Beaussart
 * @since 20/10/16
 */
public class GameSquare implements IColoredSquare, ICoordinateSquare {

    private final Cord cord;
    private final IGameMap<GameSquare> gameMap;
    private Color color;

    public GameSquare(Color color, Cord cord, IGameMap<GameSquare> gameMap) {
        this.color = color;
        this.cord = cord;
        this.gameMap = gameMap;
    }


    @Override
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setUpdated();
    }

    @Override
    public IGameMap<GameSquare> getGameMap() {
        return gameMap;
    }

    @Override
    public void setUpdated() {
        gameMap.setChanged(this);
    }

    public Cord getCord() {
        return cord;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("color", color)
                .add("cord", cord)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameSquare that = (GameSquare) o;
        return Objects.equal(color, that.color) &&
                Objects.equal(cord, that.cord);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(color, cord);
    }
}