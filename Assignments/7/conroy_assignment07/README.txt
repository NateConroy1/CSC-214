{\rtf1\ansi\ansicpg1252\cocoartf1504\cocoasubrtf760
{\fonttbl\f0\fswiss\fcharset0 Helvetica;\f1\fswiss\fcharset0 ArialMT;}
{\colortbl;\red255\green255\blue255;\red26\green26\blue26;\red255\green255\blue255;}
{\*\expandedcolortbl;;\cssrgb\c13333\c13333\c13333;\cssrgb\c100000\c100000\c100000;}
\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0

\f0\fs24 \cf0 Nathan Conroy\
CSC 214\
TA: 
\f1\fs25\fsmilli12800 \cf2 \cb3 \expnd0\expndtw0\kerning0
Mariana Flores Kim\
Assignment 7\
\
The purpose of this assignment was to introduce us to the concept of using SQLite databases to persistently store data in an android application. I used a slightly modified version of the model that I used for the last assignment in which I created a "Runner" class and a class to represent a collection of runners. I also created a RecyclerView to display the entries within the database, although it is initially empty. In order to add an entry to the database, the user must click the "Add Runner" button on the action bar. The user is then redirected to a new activity where they can enter information for a new Runner object, which is then created, added to the database, and displayed in the RecyclerView. When the user clicks "submit" they are redirected back to the main activity where the new entry is visible, however if they click "cancel" or the "Recycler View" button in the action bar menu, they are redirected back to the main activity and the entry is not entered into the database.}