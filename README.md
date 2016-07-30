BattleBot
=========

BattleBot is a scala engine for playing the Battleline game. Bots can be pitted against each other by using the [battleline ai engine](https://bitbucket.org/patrick_viafore/battlelineaiengine). 

[![Coverage Status](https://coveralls.io/repos/jonathanhood/battlebot/badge.svg?branch=master&service=github)](https://coveralls.io/github/jonathanhood/battlebot?branch=master) [![Build Status](https://travis-ci.org/jonathanhood/battlebot.svg?branch=master)](https://travis-ci.org/jonathanhood/battlebot)

Building and Running the Tests
------------------------------

This project uses SBT for its builds. SBT and the JVM must be installed for this project. Once those two things are available, simply clone and use the test target.

``` bash
sbt test
```

Creating a Distributable Jar
----------------------------

The assembly task can be used to create a single JAR distribution of the bot. The
bot can be executed with a simple ``java -jar`` command.

``` bash
sbt assembly
java -jar target/scala-2.11/battlebot_scala-assembly-1.0.jar
```

