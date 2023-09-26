package main.model;

import main.service.GameException;

public class Player extends Creature {
    private final float HEALTH_BOOST = 0.3F;
    private byte numberOfHealing = 4;
    private final int maxHealth;

    public Player(byte attack, byte defense, int health, int[] damage) {
        super(attack, defense, health, damage);
        maxHealth = health;
    }

    public void heal() {
        if (numberOfHealing == 0) {
            throw new GameException("Превышено количество исцелений");
        }
        if (health <= 0) {
            throw new GameException("Исцеление невозможно при здоровье <= 0");
        }
        health += maxHealth * HEALTH_BOOST;
        if (health > maxHealth) {
            health = maxHealth;
        }
        numberOfHealing--;
    }

    @Override
    public String toString() {
        return "Player{}";
    }
}
