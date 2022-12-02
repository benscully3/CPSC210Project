# Galaxy builder

## What will this project do:
My project will provide a sort of sandbox environment to users in
which they can build and save their own galaxy. In a galaxy the 
user will be able to add elements such as:
- Solar systems
  - with various central bodies *e.g. Star, Black Hole, Binary system*
  - Can specify details about the central bodies
- Planets
  - Specify type, orbit size, moons

Based on the details for each element chosen by the user, simple
physics calculations will complete resulting details such as 
mass or luminosity for example.
Depending on various conditions, the project will account for
consequences. For example, if two planets have the same orbit, they will
collide and form one new planet.
This project will also provide fun facts about elements added
as they are used and accessed.


## Who will use it:
This project is designed to be used by anyone curious about the
components that make up a galaxy, as well as anyone who wants to
learn some basic info about astronomy.

## Why is this of interest to me:
I chose to do a project based around astronomy as I am completing my
undergraduate degree in Physics and Astronomy and plan to pursue a
career in this field. By choosing a project in this field I will
stay interested and motivated and will get to apply some of what I
have learned in my studies.


## User stories:
- As a user, I want to be able to add solar systems to my galaxy.
- As a user, I want to be able to choose the type of central body in 
a solar system.
- As a user, I want to be able to add multiple planets to a solar system
- As a user, I want to be able to remove planets from a solar system
- As a user, I want to be able to make a star go supernova
- As a user, I want to be able to see my solar systems
- As a user, I want to be able to save the galaxy I build
- As a user, I want to be able to load the galaxy I have saved

## Instructions For Grader
*Important:* Do not save a galaxy until after first test  

- Because there is no way to add planets in the GUI currently, the current save file (galaxy.json)
has a galaxy with planets in it. Please load this file first with the 
load button, and then look at the solar system data from the menu bar "Solar System data".
This allows you to see how planets are displayed in this part of the GUI
- It might make the most sense now to restart the program (to go from scratch), but it is not necessary
- To add new solar systems you press "New Solar System" and follow the prompts
The solar systems will appear in the top left panel
- You can change the name of the galaxy with the "Change galaxy name button"
- Once there is at least one solar system in the galaxy the "Edit Solar systems" (not yet implemented) and "Solar System data"
menu bar become enabled. Use the menu bar to select for which solar system you want the data to be displayed.

## Phase 4: Task 2
*Example event log output*

Tue Nov 29 11:24:15 PST 2022                                                                                    
Created Galaxy: MilkyWay

Tue Nov 29 11:24:27 PST 2022     
Created Giant star: Sun

Tue Nov 29 11:24:34 PST 2022     
Created solar system: Home

Tue Nov 29 11:24:34 PST 2022     
Added solar system to galaxy

Tue Nov 29 11:24:44 PST 2022     
Changed galaxy's name to andromeda

## Phase 4: Task 3
There are 3 ways I would refactor my project.       
The first is that both the galaxy and solar system classes could be split up into two different classes,
a Galaxy class and a SolarSystemManager class (a SolarSystem and PlanetManager class for
the solar system class). This is because these two classes have more than one responsibility each. They have to store
and manage the data about themselves but also must manage the lists of the elements they contain. The 
details of these methods could be contained in a second class.     
Another refactoring option would be to format the galaxy class according to the singleton pattern
because there should only ever be one galaxy when the system is run.     
Finally, in the UML diagram we note that the relationship between Binary is and CentralBody is somewhat
circular. Though there is a REQUIRES clause in Binary that says a Binary cannot have a binary in it. However 
I cannot think of a way to avoid the circular relationship, but it would be better to make the constructor of 
the Binary throw an exception if an input CentralBody is a Binary.
