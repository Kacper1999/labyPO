package agh.cs.lab2;

import java.util.List;

class UnboundedMap extends AbstractWorldMap implements IWorldMap {
    private MapBoundary mapBoundary;

    UnboundedMap(List<IMapElement> mapElements) {
        super(mapElements);
        mapBoundary = new MapBoundary();
        for (IMapElement element : mapElements) {
            mapBoundary.add(element);
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
            mapBoundary.add(mapElement);
            return true;
        }
        return false;
    }
}
