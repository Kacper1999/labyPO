package agh.cs.lab2;

enum MapDirection {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;

    public String toString() {
        switch (this) {
            case NORTH:
                return "North";
            case NORTHEAST:
                return "Northeast";
            case EAST:
                return "East";
            case SOUTHEAST:
                return "Southeast";
            case SOUTH:
                return "South";
            case SOUTHWEST:
                return "Southwest";
            case WEST:
                return "West";
            case NORTHWEST:
                return "Northwest";
            default:
                return "";
        }
    }

    Vector2d toUnitVector() {
        switch (this) {
            case NORTH:
                return new Vector2d(0, 1);
            case NORTHEAST:
                return new Vector2d(1, 1);
            case EAST:
                return new Vector2d(1, 0);
            case SOUTHEAST:
                return new Vector2d(1, -1);
            case SOUTH:
                return new Vector2d(0, -1);
            case SOUTHWEST:
                return new Vector2d(-1, -1);
            case WEST:
                return new Vector2d(-1, 0);
            case NORTHWEST:
                return new Vector2d(-1, 1);
            default:
                return new Vector2d(0, 0);
        }
    }

    MapDirection next() {  // next() and previous() doesn't support northeast e.t.c
        switch (this) {
            case NORTH:
                return EAST;
            case EAST:
                return SOUTH;
            case SOUTH:
                return WEST;
            case WEST:
                return NORTH;
            default:
                return this;
        }
    }

    MapDirection previous() {
        switch (this) {
            case NORTH:
                return WEST;
            case EAST:
                return NORTH;
            case SOUTH:
                return EAST;
            case WEST:
                return SOUTH;
            default:
                return this;
        }
    }
}
