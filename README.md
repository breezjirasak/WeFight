# We Fight! Game Project

## Project Overview

Welcome to We Fight! - an engaging and strategy-based tank battle game developed in Java. In this game, two players compete against each other, aiming to destroy the opponent's tank. The gameplay involves collecting coins, upgrading tanks, spawning soldiers of different types, and using a special attack known as the "Ultimate". The graphical user interface (GUI) for We Fight! is implemented using Java Swing.

## Getting Started

To join the exciting world of We Fight!, you first need to install the Java Development Kit (JDK) on your computer. Once you have the JDK installed, you can download the game's source code and compile it using the command line.

## Game Features and How to Play

### 1. Upgrade Your Base KingDom

In the game, your tank represents your progress. Upgrading your tank allows you to collect coins faster, giving you an edge over your opponent. To upgrade your tank, press 'X' if you're the green player and 'I' if you're the red player.

### 2. Spawn Soldiers

You have the option to spawn two types of soldiers to help fight your battles:

- **Sword Soldier:** This soldier can be spawned by pressing 'C' for the green player and 'O' for the red player.
- **Armed Soldier:** This soldier can be spawned by pressing 'V' for the green player and 'P' for the red player.

The cost in coins for spawning each type of soldier will be displayed on the screen.

### 3. Unleash the Ultimate

The "Ultimate" is a powerful weapon that can significantly damage the opponent's tank. It can be activated by pressing 'Z' for the green player and 'U' for the red player.

### 4. Engage in Player-vs-Player (PvP) Mode

We Fight! provides a PvP mode where you compete against another player in real-time. The aim is to strategically collect coins, upgrade your tank, spawn soldiers, and use your Ultimate to attack the opponent's tank and defend your own. The game concludes when one player's tank is destroyed.

## Design Patterns Implemented

We Fight! incorporates several design patterns to ensure efficient performance and scalability:

1. **State Pattern:** This pattern is used to represent different states of the game, such as Menu, Playing, Pausing, and Result, making the game adaptable to varying situations.

2. **Factory Pattern:** This pattern is used for the creation of Sword Soldier and Armed Soldier objects without having to specify their exact classes. A SoldierFactory class with a createSoldier() method spawns a soldier as per the player's choice.

3. **Flyweight Pattern:** This pattern is used to optimize memory usage by sharing intrinsic properties, like movement speed and attack range, between Soldier objects. The SoldierFactory maintains a cache of previously created Soldier objects and reuses them whenever possible.

## Conclusion

We Fight! offers a fun and strategic gameplay experience that is easy to understand and play, making it suitable for players of all ages. With the game's source code available for download, players can customize the game to their liking. Join us in this thrilling tank battle and enhance your strategic skills while having fun!
