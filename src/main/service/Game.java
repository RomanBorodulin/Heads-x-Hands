package main.service;

import main.model.Creature;
import main.model.Monster;
import main.model.Player;

import java.util.Arrays;
import java.util.Random;

public class Game implements Action {
    private Creature player;
    private Creature monster;
    private final Random rnd = new Random();
    private byte attack;
    private byte defense;
    private int health;
    private int[] damage;

    public Game() {
        createMonster();
    }

    private void createAttributes() {
        attack = (byte) (rnd.nextInt(30) + 1);
        defense = (byte) (rnd.nextInt(30) + 1);
        health = Math.abs(rnd.nextInt());
        damage = new int[]{Math.abs(rnd.nextInt()), Math.abs(rnd.nextInt())};
        Arrays.sort(damage);
    }

    public void createPlayer() {
        createAttributes();
        player = new Player(attack, defense, health, damage);
    }

    private void createMonster() {
        createAttributes();
        monster = new Monster(attack, defense, health, damage);
    }

    private byte getAttackModifier(byte attacker, byte defender) {
        return (attacker > defender) ? (byte) (attacker - defender + 1) : (byte) 1;
    }

    private boolean isSuccessfulThrow(byte attacker, byte defender) {
        for (byte i = 0; i < getAttackModifier(attacker, defender); i++) {
            byte diceValue = (byte) (rnd.nextInt(6) + 1);
            if (diceValue >= 5) {
                return true;
            }
        }
        return false;
    }

    private void attack(Creature attacker, Creature defender) {
        if (!isSuccessfulThrow(attacker.getAttack(), defender.getDefense())) {
            System.out.println("Промах!");
            return;
        }
        int diff = Arrays.stream(attacker.getDamage()).reduce((x, y) -> y - x).getAsInt();
        int value = rnd.nextInt(diff + 1) + attacker.getDamage()[0];
        defender.setHealth(defender.getHealth() - value);
        System.out.println(defender + " получил урон - " + value);
        if (defender.getHealth() <= 0) {
            System.out.println(defender + " умер!");
        }

    }

    public void attackTheMonster() {
        System.out.println("Ход игрока...");
        attack(player, monster);
        if (monster.getHealth() <= 0) {
            System.out.println("Победа!");
            throw new DeathException("Конец игры!");
        }
        attackThePlayer();
    }

    private void attackThePlayer() {
        System.out.println("Ход монстра...");
        attack(monster, player);
        if (player.getHealth() <= 0) {
            System.out.println("Поражение!");
            throw new DeathException("Конец игры!");
        }
        System.out.println("Осталось здоровья - " + player.getHealth());
    }

    public void heal() {
        ((Player) player).heal();
        System.out.println("Исцеление...");
        System.out.println("Осталось здоровья - " + player.getHealth());

    }

    public Creature getPlayer() {
        return player;
    }
}
