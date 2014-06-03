#Final Project: J2048
####https://github.com/letthelightin/finalproject/
####Daniel Retta of Monterey Peninsula College Fall 2014


##Project Outline:

This is a remake of the computer game 2048 for a Java class hosted by
Monterey Peninsula College of Monterey, California, USA and taught by
Brian Schlining.

An example of the game can be found at:
http://gabrielecirulli.github.io/2048/

##About the Game:

###Summary
2048 is a game involving blocks with numeric values of 2 to the nth 
power (1,2,4,8, etc).

###Initial State  
The game starts with one or two blocks randomly placed.

###Process       
A loop consists of the user pushing an arrow key on their keyboard,
and all of the blocks moving in the direction as far as space allows.
If two blocks of the same value are pushed together, they combine to 
double their value. The loop is ended by a new block of the lowest or 
second lowest value being randomly generated and placed randomly on 
the board.

###Finish
The game is won when a block of 2048 is created. The game is lost when 
no further moves can be made for lack of space. The game process does 
not need to end after the game is won, but an announcement is expected
when the 2048 tile is achieved.
