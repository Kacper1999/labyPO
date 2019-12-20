package agh.cs.lab2;

public class World {
    public static void main(String[] args) {
        try {
            int width = 8;
            int height = 8;
            int jungleWidth = 2;
            int jungleHeight = 2;
            int startEnergy = 100;
            int moveEnergy = 5;
            int fruitEnergy = 3;

            RolledMapWithJungle map = new RolledMapWithJungle(width, height, jungleWidth, jungleHeight, fruitEnergy);
            Vector2d oldPosition = new Vector2d(2, 0);
            Vector2d move = new Vector2d(0, -1);


        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
