package main.model;

public class Monster extends Creature {
    public Monster(byte attack, byte defense, int health, int[] damage) {
        super(attack, defense, health, damage);
    }

    @Override
    public String toString() {
        return "Monster{}";
    }
}
