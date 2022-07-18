# **Project Zapdos**



---



### Team Members

* Vijay Kumar: Game Engine, A.I Logic, U.I, Items, Projectiles, Menu Bar, Game Assets/Objects
* Bikram Chatterjee: Game Engine, Level Loading/Saving via JSON, Player & Environment Movement, Player Collision, Menu Bar, Music Synchronization, Code Refactoring
* Jordan R Somers: Puzzle Mechanics, Level Design, Gameplay Scripting, Puzzle Design/Objects
* Shahbaj Singh Sohal: Puzzle Mechanics, Level Design, Gameplay Scripting, Puzzle Design, Menu System



---


### Project Objectives
The core objective of Project Zapdos is to complete the development of an application as prescribed by the customer pitch. These parameters include several logic based puzzles, numerous stages, consistent UI & thematic design, as well as a streamlined navigation system.

Here are the primary objectives of this project:

* Create a 2D puzzle platformer
* Develop interesting logical puzzles
* Design a high contrast UI using 3-4 main colors and consistent design elements
* Levels should have a sense of progression by only having the first level unlocked in the beginning and the rest of the levels should unlock as the player progresses through the game
* Game should increase in difficulty as the player progresses from one level to the next
* Develop a tutorial screen



---


### Project Design

### Team Goals: 
 - The main goal of the team is to achieve the working standards and requirements provided by the customer pitch. 
 - This includes a thematically coherent g.u.i design, numerous stages (15), logic based puzzles, graphically coherent stage design and ability to navigate all menus with ease. 
 - To achieve this, our team has instituted weekly scrum meetings and has upheld a basic standard of communication through which ideas, updates and roadblocks are identified. 
 
 
### User Stories Completed: 
 
 | Stories                     | Acceptance Criteria                                                                                                                                                                        | Sprint |
|-----------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------|
| Implement Main Menu         | Implement Main Menu that contains buttons for: Play, Options, Tips Exit. Main Menu should also follow a visually compelling design paradigm.                                               | 1      |
| Implement Stage Select Menu | Design and implement a stage select menu that contains all playable stages, and allows the player to load into Stage One.                                                                  | 1      |
| Implement Options Menu      | Implement an options menu that contains buttons (options) for the  player to manipulate audio (background audio) [togglable]                                                               | 1      |
| Implement Pause Menu        | Implement a pause menu that pauses the current stage & navigates  to a pause menu.                                                                                                         | 1      |
| Implement Stage One         | Design and implement Stage One, where the length of stage one (block out) is created.                                                                                                      | 1      |
| Implement Player Class      | Design and implement player movement system: Player should be  able to move left and right on key press. Player should also be able to jump up and fall down on key press.                 | 1      |
| Implement Puzzle One        | Design and implementation of puzzle one, where the mechanics of puzzle one are articulated and implemented in a manner such that puzzle success/fail conditions are registered correctly.  | 1      |

#### What was completed:

#####  Estimated story points: 6 stories 26 Points | Completed story points: 3 Stories 9 Points

* Main Menu, Stage Select Menu and Character movement were implemented. The current stories are too broad to properly illustrate completed elements
* Main Menu: Completed the main menu design and implement logic to available buttons
* Stage Select: Completed the Stage Select design and implemented logic to load into stages
* Player Movement: Completed the left and right movement mechanics
* Player Movement: Added gravity mechanic for player
* Implemented Music Toggling


| SPRINT 2  |
| --------  |
|Added two puzzles |
Implemented a proper character sprite
Player can move left if the user presses the "A" key
Player can move right if the user presses the "D" key
Player slows down before stopping when the user releases the "A" key or "D" key
Player does not move if the user presses both the "A" key and "D" key
Player image changes to left or right image accordingly
Background has left and right boundary detection for movement
Background does not allow players go beyond the starting point
Background can move vertically if the player falls or jumps too much
The state of the level can be saved for playing it later
Player can jump if the user presses the "W" key or the Space key
Player lands on the object below after jumping
Player falls down using a gravity effect
Adjusted jump physics and collision detection
Music can now be toggled on/off from any pane
Levels can now be properly saved to the disc
Vertical scrolling properly implemented
Enemies with unique AI added  |

Estimated story points: 7 stories 40 Points | Completed story points: 9 Stories 51 Points




| SPRINT 3 |
| -------- |
| Fixed the menubar in which it adds objects, saves the levels, and loads panes
Spike are now an object that can be added in the level creator and saved in a JSON
Player shooting --> player can now shoot with 'r' and kills enemies
implemented a tips screen
Designed and incorporated 3 levels in the game with fully functioning puzzles!     |

Estimated story points: 8 stories 39 Points | Completed story points: 10 Stories 66 Points




| SPRINT 4 |
| -------- |
| Added SFX to the menu and the game
Updated the main menu graphics/visuals
Updated the tips screen
Have levels reset on with spike collision
Fixed the doors and have them open when level is completed
Created two more levels that follow the button method
User can go straight into next level after solving the current puzzle and going through the door
Added player fall sprite
Added music synchronization
Fixed vertical player collision     |



---

### Implementation Details: 

1. Java was used as the primary programming language to develop this project. 

2. Project Zapdos uses the ACM library for graphics, alongside built in Java apis like JavaFX and Java.Swing. 

3. One of the initial challenges our team face was the integration of an engine that allowed for the flexibility desired to contruct levels speedily, alongside swift testing and design updates. This was mitigated with the implementation of many engine features, in addition to the ability for the team to save and load entire levels with a single click. Another challenge we faced was figuring out player collision and how it would interact with all of the platforms in the game such as platforms, spikes, enemies, moving platforms, etc. Bikram spent a lot of time figuring and tinkering with the player collison to get it to a level that we and customer liked and was bug-free. Additionally, we faced challenges with implementing all of these different aspects into "level creator" in which we could create these levels in minutes and save them to JSON files. Bikram and Vijay worked through this code for many, many hours and through many bugs to get it working. The biggest challenge we faced, however, was as a team and working to provide the customer with a project he would be happy with. The first challenge we faced was building a game engine, but it was not totally what the customer wanted. We also struggled to get good feedback from the customer and we were inexperienced on how to question the customer to get the feedback we needed. Th solution to this came a little late into the project but we ended up talking with the customer more and checking in with with "prototypes". We also had a lot of talks with professor who helped us figure out what we needed to do and how to do it.  

In regards to the customer pitch, our team struggled to perceive the desired puzzle mechanics throughout previous development sprints, though after a lengthy group discussion and coordinated communication with the customer, these ambiguous concepts were rectified. Armed with this newfound clarity, our team was able to produce levels meeting the requirements of the customer. 
    
4. The were a lot of features that were implemented into this game, but some of the most important features were a menu system, stage selector, creating 6 unique levels with interesting puzzles, a "level creator", stage locking, player movement, player collision, player shooting, level resetting.

5. Execute the runnable JAR File.



---

### **Testing**

**Testing Plan & Strategies**

The team integrated w/ the following testing strategies:
 - Unit testing
 - Gradle CI
 - ZenHub / Github Issues




### **Test Cases**

### Integration Tests:
Story #/Name: Puzzle 1: Five Lamps Puzzle
Tester’s Name: Jordan
Description of Acceptance Criteria to be followed or Acceptance Test to Perform:
Generating lights in all lamps triggers the condition, otherwise the puzzle remains to be solved. Ability to manipulate/generate lights by clicking on designated buttons. If the puzzle is solved, where all lights are turned on, the ability to exit the puzzle and return to the game functions correctly.
Assumptions one needs to make: 



| Variations                               | Expected Results | Comments | Done? |
| ---------------------------------------- | ---------------- | -------- | ----- |
| Click buttons to access puzzle elements. | Puzzle lights updated accordingly.             | Functioning correctly     |  Cosmetic changes may be required.     |
| Use gameplay elements (jumping on buttons) to access/update puzzle elements.                                         |     Puzzle lights updated accordingly.    |    Functioning correctly      | Cosmetic changes may be required.      |




Story #/Name: Player Movement
Tester’s Name: Bikram C.
Description of Acceptance Criteria to be followed or Acceptance Test to Perform:
Player can move left or right and can jump.
Assumptions one needs to make: Collision and Jumping mechanics are working



| Variations | Expected Results | Comments | Done? |
| ---------- | ---------------- | -------- | ----- |
| User presses W, w, or space key      | Player jumps             | Functioning correctly     | Done      |
|   User presses A or a         | Player moves left                 | Functioning correctly         | Done      |
|     User presses D or d       |  Player moves right                |Functioning correctly          |  Done     |
|      User presses both A and D or a and d      |   Player does not move               | Functioning correctly         | Done      |
|   User presses W, w, or space key while pressing both A and D or a and d         |    Player does not move              |  Functioning correctly        |   Done    |


Story #/Name: Vertical and Horizontal Environment Movement
Tester’s Name: Bikram C.
Description of Acceptance Criteria to be followed or Acceptance Test to Perform:
Environment moves vertically or horizontally when player reaches certain borders of the level
Assumptions one needs to make: 
Collision and Jumping mechanics are working




| Variations | Expected Results | Comments | Done?    |
| -------- | -------- | -------- | --- |
| Environment has left and right boundary detection for movement    | Environment moves left or right when player hits left or right boundary     | Functioning correctly     |  Done.   |
|  Environment has vertical boundary detection for movement        |   Environment moves up or down when the player hits the top or bottom boundary .       |    Functioning correctly      |  Done  |



### Unit Tests


**Unit Test 1 (reverbotLeft):** This test is designed to discern whether or not the reverbot enemy’s sprite asset is indeed updating to the correct asset when the reverbot enemy is moving left. 


![](https://i.imgur.com/dHCW6AV.png)


*Test is checking whether or not reverbot image assets are updating correctly based on reverbot direction.*


[![Image from Gyazo](https://i.gyazo.com/283da35c36022eba5154e95f925932b0.gif)](https://gyazo.com/283da35c36022eba5154e95f925932b0)

*Test is checking whether or not reverbot image assets are updating correctly based on reverbot direction.*




**Unit Test 2 (reverbotRight):** This test is designed to discern whether or not the reverbot enemy’s sprite asset is indeed updating to the correct asset when the reverbot enemy is moving right.

![](https://i.imgur.com/g5eBA2T.png)



*Test is checking whether or not reverbot image assets are updating correctly based on reverbot direction.*

[![Image from Gyazo](https://i.gyazo.com/283da35c36022eba5154e95f925932b0.gif)](https://gyazo.com/283da35c36022eba5154e95f925932b0)

*Test is checking whether or not reverbot image assets are updating correctly based on reverbot direction.*



**Unit Test 3 (reverbotSeePlayer):** This test is designed to discern whether or not the reverbot enemy is able to see / not see the player.

![](https://i.imgur.com/WeHdSDD.png)


*Test is checking whether or not the reverbot unsees/sees the player, indicated by the points at which the reverbot stops following/chasing the player.*
	
[![Image from Gyazo](https://i.gyazo.com/db5beca69b2c7352defb7afd51faa6e9.gif)](https://gyazo.com/db5beca69b2c7352defb7afd51faa6e9)    

*Test is checking whether or not the reverbot unsees/sees the player, indicated by the points at which the reverbot stops following/chasing the player.*


**Unit Test 4 (reverbotPlaySound):** This test is designed to discern when the reverbot enemy is allowed to play a sound effect, depending on whether or not it has seen the player. 


*This test is checking whether or not the reverbot can play a sound effect, indicating that it has seen the player. Once the reverbot has unseen the player, its sound condition is reset.*


**Unit Test 5 (Level Creator Test)**

beforeClass:  A new baseStage object is created and one platform, one vertical platform, and two reverbots at certain coordinates are added. Next, the stage contents are saved to a JSON file. Finally, the stage is reset and the previously created JSON file is loaded again.
testNumReverBots: This test checks if the number of rever bots that are saved in the JSON file via the level creator is exactly the same as the rever bot positions which are loaded again from the same JSON file.
testPlatformCoords: This test checks if the platform position that is saved in the JSON file via the level creator is exactly the same when the platform position is loaded again from the same JSON file.
testPlatformVertCoords: This test checks if the vertical platform position that is saved in the JSON file via the level creator is exactly the same as the vertical platform position which is loaded again from the same JSON file. 
testReverBotCoords: This test checks if the position of the rever bots that are saved in the JSON file via the level creator are exactly the same when the reverbots are loaded again from the same JSON file. 
afterClass: The test JSON file is deleted after the tests are completed.



---

### Project Highlights (Retrospective)

The team is proud of the many accomplishments and strides made throughout the development process, including: a **robust** and **powerful** game/level design **engine** that allowed the team to **design, produce and iterate stages/levels quickly** and with ease, complex gameplay features including both horizontal & vertical camera scrolling, parallax animation, tight platforming mechanics, complex collision system, a pseudo artificial intelligence state machine, puzzle mechanics, integration w/ CI tools in addition to many other aspects of the software. 

Our ability to communicate regularly, articulate concerns clearly, update frequently and dilineate tasks were some of the core attributes of collaboration our team implemented and maintained very well. 

The largest struggle we had, aside from few developmental woes (quickly rectified), were in relation to our understanding of the true desires sought by the customer. An enigma to be sure, but with continued evaluations, reevaluations, increased scrutiny and coordinated questioning, this lingering enigma was soon unburdened.



---

### Things To Be Improved (Also Retrospective)

The team would mostly agree, that many aspects of development successfully achieved were done very well. That isn't to say, that these implementations couldn't be improved. In regards to the game engine, our team would seek to vastly improve and implement a u.i. system that would allow the user to import, update and manipulate assets more speedily (introduction of a content browser that auto-generates class objects of a particular type on user import), the ability to scroll, pan and zoom throughout any given scene with the mouse in addition to various other qol updates, built-in user docs etc. 

In regards to the customer pitch, the team would seek to improve level design elements, adding additional enemy types, and working to implement more logically complicated puzzle mechanics. 

Aspects of our development pipeline that could also be improved includes a more aggressive focus on testing implementations, iteration and updates through ZenHub and perhaps an additional meeting throughout each sprint. Another thing that we could have done was do more pair programming and work on improving code ownership.



---

### Lessons Learned


Many things were learned throughout the development of Project Zapdos that have aided the team to become better developers in both a programming and team environment. Relationship with a customer, where development is constrained to the parameters between which the 'design document', or in this case, a customer pitch specifies was also a unique learning experiences that brought with it some significant challenges in areas unexpected. Our team has learned to glean specific facts from the customer, develop with the understanding that these facts are malleable & will probably change over time, and that software development in this particular environment is meant to achieve the goals of the customer, rather than the developer. Although some of these concepts sways the passion pendulum further away from the development team, this emulates perhaps, how development pipelines and customer products are produced in the industry and so serves as a useful learning experience.

Advice for future 129 students: Make sure to leave as little room for interpretation between you and the customer. Utilize tools such as acceptance criteria to make sure you and your customer agree on what needs to be done. Also, try to keep refactoring as you develop your codebase further. Towards the end of our project, development largely revolved around using one class as a sort-of "god class", which caused us some trouble. Try to find some time to go through your code and make sure everything is written nicely *before* it becomes a problem. We would also recommend to keep the communication up because in a large project is easy to have mix-ups or forget about something because someone thought the other person would do it.
