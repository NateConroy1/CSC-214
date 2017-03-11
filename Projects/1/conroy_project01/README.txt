{\rtf1\ansi\ansicpg1252\cocoartf1504\cocoasubrtf760
{\fonttbl\f0\fswiss\fcharset0 Helvetica;\f1\fswiss\fcharset0 ArialMT;}
{\colortbl;\red255\green255\blue255;\red26\green26\blue26;\red255\green255\blue255;}
{\*\expandedcolortbl;;\cssrgb\c13333\c13333\c13333;\cssrgb\c100000\c100000\c100000;}
\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\pard\tx560\tx1120\tx1680\tx2240\tx2800\tx3360\tx3920\tx4480\tx5040\tx5600\tx6160\tx6720\pardirnatural\partightenfactor0

\f0\fs24 \cf0 Nate Conroy\
nconroy@u.rochester,edu{\field{\*\fldinst{HYPERLINK "mailto:lsherma5@u.rochester.edu"}}{\fldrslt \
}}CSC 214, Project 1\
TA: 
\f1\fs25\fsmilli12800 \cf2 \cb3 \expnd0\expndtw0\kerning0
Mariana Flores Kim
\f0\fs24 \cf0 \cb1 \kerning1\expnd0\expndtw0 \
\
--------- Description ---------\
I created an app using the MVC format that allows two users to play Hotter/Colder, Hangman, Connect 4, and Checkers. The app opens to a landing page where both users are required to type in their names. When they click the "submit" button, they are redirected to an activity where they can select the game from a series of buttons. On this activity, a fragment is also displayed showing the current scores. This fragment is constant through all of the game activities at the top of the screen (each game has its own activity). I implemented a "New Game" feature for every game by click of the button. Every game also has a "quit" button to take you back to the ChooseGame activity. The HotterColderGame.java, HangmanGame.java, Connect4Game.java, and CheckersGame.java represent the Model classes, and are implemented using a Singleton.\
\
Note: I locked the orientation to portrait for the Connect 4 and Checkers games only, simply because the board would have been very small and hard to read if I implemented a landscape layout. Both boards are 8x8.\
\
EXTRA CREDIT:\
\
- use of fragment for the scoreboard which is displayed across all of the game activities and the choose game activity\
\
- implementation of a fourth (and slightly more complicated) game, Checkers}