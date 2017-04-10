{\rtf1\ansi\ansicpg1252\cocoartf1504\cocoasubrtf760
{\fonttbl\f0\fswiss\fcharset0 Helvetica;\f1\fswiss\fcharset0 ArialMT;}
{\colortbl;\red255\green255\blue255;\red26\green26\blue26;\red255\green255\blue255;}
{\*\expandedcolortbl;;\cssrgb\c13333\c13333\c13333;\cssrgb\c100000\c100000\c100000;}
\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\pard\tx560\tx1120\tx1680\tx2240\tx2800\tx3360\tx3920\tx4480\tx5040\tx5600\tx6160\tx6720\pardirnatural\partightenfactor0

\f0\fs24 \cf0 Nate Conroy\
nconroy@u.rochester,edu{\field{\*\fldinst{HYPERLINK "mailto:lsherma5@u.rochester.edu"}}{\fldrslt \
}}CSC 214, Project 2\
TA: 
\f1\fs25\fsmilli12800 \cf2 \cb3 \expnd0\expndtw0\kerning0
Mariana Flores Kim
\f0\fs24 \cf0 \cb1 \kerning1\expnd0\expndtw0 \
\
--------- Description ---------\
For project 2, I created a social media app called Hooli Plus (Silicon Valley season 4 out this month!) using the MVC style. The app opens to an activity that prompts the user to sign up or log in. In order to sign up the user must enter in some basic information, which is then stored in a SQLite database. When the user logs in, they must provide their email and password, and if it matches a record in the database, that user is logged in and redirected to an activity displaying their profile. From here on, every activity that the user can reach while logged in can be reached using the action bar menu. The user's profile shows their name and profile picture, and also contains buttons for changing the profile picture or writing a new post. When the user chooses to write a post, they are redirected to a new activity to do so. They may create a post with text or a picture or both. When they submit it, they are redirected back to their profile, and it becomes visible in the bottom half of the screen, which is a view pager that can toggle between the user's posts (a RecyclerView) and bio. The user's feed, by default shows an empty RecyclerView of the user's favorite's posts. The user can add favorites by swiping to the right and clicking other user's names, which opens up their profile and can be favorited by clicking the star icon.  After that, all posts from the user get displayed in the feed. Users can be unfavorited and hidden from the feed by following the same process again. In the RecyclerView of all users (the second tab of the ViewPager on the feed), favorite users are highlighted in blue, while all other users have a white background.\
\
I used 3 databases. One that represents users containing their personal information, one that represents posts and their content and timestamp, and one that represents favorites and contains a user ID of the "favoriter" (person doing the favoriting) and a user ID of the "favoritee" (person being favorited). All data is stored persistently via these databases.\
\
--------- Honor Pledge ---------\
I affirm that I did not give or receive any unauthorized help on this project, and that all work is my own.}