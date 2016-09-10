BattleBot Core
==============

BattleBot Core is a scala library used to build bots for the [Battleline ai engine](https://bitbucket.org/patrick_viafore/boardgameaiengine). 

This project is the core from [jonathanhood/battlebot](https://github.com/jonathanhood/battlebot).

Building and Running the Tests
------------------------------

This project uses SBT for its builds. SBT and the JVM must be installed for this project. Once those two things are available, simply clone and use the test target.

``` bash
sbt test
```

Creating Library Jar
--------------------

These tasks can be used to create binary and source versions of the library:

``` bash
sbt package packageSrc
```

