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
Assignment 4\
\
The purpose of this assignment was to introduce us to the concept of using multiple activities within an app, as well as using fragments. The app also allowed for practice of Model-View-Controller style, which I used to model interaction between the player and the app. I used a singleton model for both the FontModel and FontView classes so that I would be able to create one instance of each and use it to across all of the activities. Therefore I was able to preserve data throughout the lifecycle process by setting and getting variables within the model. My first activity contains just a TextView and 2 buttons labelled "Change Font" and "Change Text". Clicking the first button launches a new activity that allows the user to select font style attributes (bold, italic, underline using checkboxes, font color using radio buttons, text size using an EditText). Changes are applied when the "OK" button is pressed, and discarded when "Cancel" is pressed. When the second button is pressed in the main activity, a third activity is launched that contains an EditText to change the string in the main activity. The activity also contains a dynamically added Fragment that serves as a "preview". As the user types in the EditText, a TextView within the Fragment updates to display the string being typed with the font styling selected.}