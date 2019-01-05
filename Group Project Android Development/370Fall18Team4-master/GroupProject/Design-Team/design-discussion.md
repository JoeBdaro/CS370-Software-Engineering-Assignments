Design 1: ehson assani

<!-- embedded figure -->
![alt text](design1.JPG)

Pros:<br>
1)Good use of encapsulation regarding database.<br>
2)Design layout from UML to code is well thought.out<br>
3)App Manager creates a collection of reminders that holds lists of reminders.

Cons:<br>
1)Heirarchichel level as in priority based alerts, should be non-priority.

****

Design 2: Joseph Bdaro

![alt text](design2.png)

Pros:<br>
1)Repeats reminders based on specified date intervals.<br>
2)Good use of encapsulation regarding database.
CRUD (Create, Read, Update, Delete)<br>
3)ListView class creates instances of other listReminders classes in which each listReminder holds reminders (arraylist of an arraylist).

cons:<br>
1)Heavily dependant if one class were to fail, it would compromise the whole application.

****
Design 3: Shangshang Zhu

![alt text](design3.png)

Pros:<br>
1)Reminder notes detailing the reminder optinal location class. 

Cons:<br>
1)There are too many connections made to the database, one one or two classes should have access to db.

****

Design 4: Mohammad Chowderhury

![alt text](design4.png)

Pros:<br>
1)listView class creates instances of other listReminders classes in which each listReminder holds reminders (arraylist of an arraylist).<br>
2)Database is well encapsulated as there's only one connection to it.

Cons:<br>
1)Does not have alert atribute in the reminder object.<br>
2)Heirarchichel level as in priority based alerts.

****

Design 5: Adnan Salimi

![alt text](design5.png)

Pros:<br>
1)Minimalistic design

Cons:<br>
1)Does not specify starting class in diagram. <br>
2)Dependencies are not specified in the line. <br>
3)Discusses UI elements in UML diagram (should not be discussing UI).

************

Team Design:

![alt text](design.png)

Reasoning behind the design:<br>
Minimalist design (quick and easy transition from UML diagram to final code implementation) while meeting the customer requirements.

Commonalities between individuals design:<br>
1) All designs included some sort of a view of all created lists.<br>
2) All designs generally satisfied the requirements to some degree.

Differences between individuals design:<br>
1) Implementation of notes. <br>
2) Some designs included support for multiple users while others didn't. <br>
3) Some implemented features are designed as a class while others were designed as methods. <br>
4) interactions between each class differed in terms of design (i.e certain classes inherrited from one another while other were composition of one another).

Difference between team design and individual design:<br>
1) Number of classes were reduced to provide easibility of UML design and implementation of said design.
2) In some designs the heirarchy was implemented with multiple classes where the final design.
simplified the functionality to only one class.  This issue was later cleared by professor Abreu.<br>
3) Some heirarchy designs included reminder priorities, this idea was scrapped as it was not specified by the requirements.


************

Summary:<br>


There were different conceptions of how the classes and ultimately the application were to operate. Certain class relationships were inefficient while others caused convoluted dependencies of interlocking classes which then lead to other problems with modularity. This required regular group meetings to ensure an open line of communication and an understanding of a unified UML design. 
We all decided to meet and demo our individual designs in our group meetings.  We outlined the pros and cons of each design.  Ideas were met with open considerations and then evaluated as if implemented in the final product.  This then lead us to re-design as in the slides discussed in class (spiral process and evolutionary prototyping).
The best aspects of each design and re-designs in our view (no pun intented) were incorporated in the final UML class diagram design.


************
