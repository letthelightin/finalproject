#MPC: CSIS10a: Final Project: J2048
###https://github.com/letthelightin/finalproject/
###created by Daniel Retta of Monterey Peninsula College Fall 2014

##Project Outline:
This is a remake of the computer game 2048 for a Java class hosted by
Monterey Peninsula College of Monterey, California, USA taught by
Brian Schlining.

An example of the game can be found at:
http://gabrielecirulli.github.io/2048/

##About the Game:

###Summary
2048 is a game involving blocks with numeric values of 2 to the nth 
power (1,2,4,8,... 2048... etc) moving in unison around a board, 
combining when two blocks of the same value are pushed together.

###Initial State  
The game starts with one or two blocks of the lowest or second
lowest value(s) randomly placed.

###Process
A process consists of the user pushing an arrow key on their keyboard,
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

###Information of J2048
The methods/constructors of Board have mostly been left in order of use 
(and creation) as that is how I best remember their locations - via their 
execution flow.

The game is played by running the class RunMe. The other classes, Board 
and Block, may be run to provide illustrative examples of themselves.

A Block is a rectangle and centered label with an integer value displayed
via the label and is capable of displaying itself via JavaFX

A Board is fundamentally a square 2D array capable of displaying itself 
via JavaFX. This board is four places wide by four places high and 
is displayed at 400 pixels wide by 400 pixels high.

At the start of the game the Board's block array is filled with empty Blocks
and the background of the Board is drawn. A randomly selected place has its 
Block's value set to 2. The Board then draws all Blocks with a value greater 
than 0.

Class RunMe's key handling works the rest: when a directional key on the 
keyboard is pressed the handler traverses the Board's array creating, 
executing, and  disposing of pathTransitions for each Block that should be 
moved.