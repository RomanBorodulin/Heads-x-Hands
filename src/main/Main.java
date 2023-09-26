package main;

import main.model.Player;
import main.service.Action;
import main.service.DeathException;
import main.service.Game;
import main.service.GameException;

import java.util.Scanner;

public class Main {
    int gameNumber = 0;

    public static void main(String[] args) {
        Main main = new Main();
        main.printMenu();
        Action game = new Game();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.next());
                Player player = (Player) ((Game) game).getPlayer();
                switch (choice) {
                    case 1 -> {
                        if (player == null) {
                            game.createPlayer();
                            player = (Player) ((Game) game).getPlayer();
                            System.out.println("Персонаж создан.");
                            System.out.println("Атака: " + player.getAttack());
                            System.out.println("Защита: " + player.getDefense());
                            System.out.println("Здоровье: " + player.getHealth());
                            System.out.printf("Урон: %d-%d\n", player.getDamage()[0], player.getDamage()[1]);

                        } else {
                            throw new GameException("Персонаж был создан ранее");
                        }
                    }
                    case 2 -> {
                        game = new Game();
                        main.printMenu();
                    }
                    case 3 -> {
                        if (player == null) {
                            throw new GameException("Персонаж еще не создан!");
                        }
                        game.attackTheMonster();
                    }
                    case 4 -> {
                        if (player == null) {
                            throw new GameException("Персонаж еще не создан!");
                        }
                        game.heal();
                    }
                    case 0 -> System.exit(1);
                    default -> System.out.println("Неизвестная команда");
                }
            } catch (DeathException ex) {
                game = new Game();
                main.printMenu();

            } catch (RuntimeException ex) {
                ex.printStackTrace();
            }

        }

    }

    void printMenu() {
        System.out.println("Новая игра #" + (++gameNumber));
        System.out.println("\nМеню игры");
        System.out.println("1. Создать персонажа");
        System.out.println("2. Рестарт");
        System.out.println("3. Провести атаку");
        System.out.println("4. Исцелиться");
        System.out.println("0. Выход из игры");
    }
}
