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
Assignment 3\
\
The purpose of this assignment was to familiarize us with the lifecycle of Android applications. The first thing that I did was override the onCreate, onStart, onResume, onRestart, onPause, onStop, and onDestroy methods to log a message every time one of those methods was called with a custom tag. This was helpful with analyzing exactly when these methods are called in the lifecycle. For this assignment I created 2 different layouts, one for portrait orientation and one for landscape orientation. The layouts consisted of an ImageButton, an ImageView, and a TextView in which the ImageButton changed the image within the ImageView to cycle through a set of custom images, and the TextView displays the count of the number of times the orientation changed. In order to do both of these things and preserve the app's state when changing orientation, I had to use a Bundle to save the state of the app before the app's onDestroy method was called and restore the state of the app during the onCreate method.}