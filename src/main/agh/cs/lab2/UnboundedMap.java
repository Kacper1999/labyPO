package agh.cs.lab2;

import java.util.List;

class UnboundedMap extends AbstractWorldMap implements IWorldMap {
    private MapBoundary mapBoundary;

    UnboundedMap(List<IMapElement> mapElements) {
        super(mapElements);
        mapBoundary = new MapBoundary();
        for (IMapElement element : mapElements) {
            ((AbstractMapElement) element).addObserver(mapBoundary);
            mapBoundary.add(element.getPosition());
        }
    }

    @Override
    protected Vector2d calcUpperRightBoundary() {
        return mapBoundary.getUpperRight();
    }

    @Override
    protected Vector2d calcLowerLeftBoundary() {
        return mapBoundary.getLowerLeft();
    }

    @Override
    public boolean place(IMapElement mapElement) {
        if (super.place(mapElement)) {
            mapBoundary.add(mapElement.getPosition());
            ((AbstractMapElement)mapElement).addObserver(mapBoundary);
            return true;
        }
        return false;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }
}
