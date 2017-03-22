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
Assignment 5\
\
The purpose of this assignment was to familiarize ourselves with the fragment lifecycle as well as how to properly get data from and send data to fragments. I created a class called FragmentLifecycleLogger which overrode each of the lifecycle methods and logged a message as they were called in order to track the behaviors of the fragments as they went through their lifecycles. This lab involved 2 fragments, which were dynamically added to the main activity. The top fragment had a textview which displayed a message typed within an EditText in the main activity. This information was sent and received using the setArguments and getArguments methods. The top fragment also had an EditText and button which sent a message to the bottom fragment when pressed. This was done by creating an interface within the top fragment class which contained a method that was called on the button click. The interface was implemented by the main activity, and the implemented method called another method within the bottom fragment which updated its TextView with the given string. Please note that I locked the orientation of the main activity to portrait because there was too much on the screen in a vertical pattern to fit and look clean in landscape mode.}