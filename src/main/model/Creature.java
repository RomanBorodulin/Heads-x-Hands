package main.model;

import java.util.Arrays;

public abstract class Creature {
    protected byte attack;
    protected byte defense;
    protected int health;
    protected int[] damage;

    public Creature(byte attack, byte defense, int health, int[] damage) {
        this.attack = attack;
        this.defense = defense;
        this.health = health;
        this.damage = damage;
    }

    public byte getAttack() {
        return attack;
    }

    public void setAttack(byte attack) {
        this.attack = attack;
    }

    public byte getDefense() {
        return defense;
    }

    public void setDefense(byte defense) {
        this.defense = defense;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int[] getDamage() {
        return Arrays.copyOf(damage, damage.length);
    }

    public void setDamage(int[] damage) {
        this.damage = damage;
    }
}
