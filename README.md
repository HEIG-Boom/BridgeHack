# BridgeHack
MCR project. A Dungeon Crawler Rogue like.

## For Players

### Launch the game

* Download the zip archive on the [release page](https://github.com/HEIG-Boom/BridgeHack/releases).
* Unzip it in your folder of choice.
* [Windows] Run the `game.bat` script and play !
* [MacOS and Linux] Run the `game.sh` script and play !

### Commands

The story and the aim are explained in the game. In this file, you will find the different commands used to play the game.

+ Arrows : move the player
+ a + arrow : attack in the selected direction
+ q + "number" : drink object "number" in inventory
+ t : take an object on the ground or look in a chest
+ x + "number" : delete object "number" in inventory
+ e + "number" : equip object "number" in inventory
+ d : climb down the ladder

Have fun, and share your experience with #BridgeHack.

## For Developers

 * Clone the repository in your favorite folder.
 * Run `maven package` or `maven install` in said folder.
 * Import the maven project in your IDE.
 * Edit your debug configuration (menu Run, Debug configurations...), on the "Arguments" tab, "VM Arguments" field, enter `-Djava.library.path=target/natives`.
 * Work !
