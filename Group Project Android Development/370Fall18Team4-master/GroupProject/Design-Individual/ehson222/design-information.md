
*****************************************************************************************************

Note: Nouns are classes/entities, they will have verbs(attributes) stating what actions they possess, and operations represent the class behaviors.

*****************************************************************************************************

Requirements

1. A list consisting of reminders the users want to be aware of. The application must allow users to add reminders to a list, delete reminders from a list, and edit the reminders in the list.

Nouns: List, Reminder, Application Manager, and User.
Verbs: ID, password
Behaviours: addReminder, deleteReminder, editReminder, addRemList, deleteRemList, editRemList.

So far we have information on basic UML class diagram consisting of Classes and 3 behaviors.

2. The application must contain a database (DB) of  reminders  and corresponding  data .

The DB as stated by the assignment, does not have to be implemented just referenced.  Therefore it is not shown in our diagram as a class, only as a behaviour.
  
3. Users must be able to add reminders to a list by picking them from a hierarchical list, where the first level is the reminder type (e.g., Appointment), and the second level is the name of the actual reminder (e.g., Dentist Appointment).

Nouns: Reminder Hierarchy, Reminder Type, Reminder Name
Verbs: type, name
Behaviours: updateDB, accessDB

We have broken down the Reminder Hierarchy class into its subclasses.  Both of whom contains the necessary arguments that make up our heirarchal list of reminders.

4. Users must also be able to specify a reminder by typing its name. In this case, the application must look in its DB for reminders with similar names and ask the user whether that is the item they intended to add. If a match (or nearby match) cannot be found, the application must ask the user to select a reminder type for the reminder, or add a new one, and then save the new reminder, together with its type, in the DB.

Nouns: 
Verbs: 
Behaviours: askUser, match, updateHierch

5. The reminders must be saved automatically and immediately after they are modified.

The operation updateDB will update the database as stated above.

6. Users must be able to check off reminders in the list (without deleting them).

Nouns: 
Verbs: 
Behaviours: checkReminder

This operation passes down from application Manager to user to Reminderlist to Reminder.  Seems unnecessary but easier to visual when trying to code our diagram.

7. Users must also be able to clear all the check-off marks in the reminder list at once.

Nouns: 
Verbs:
Behaviours: clearChecks

Refer to requirement 6 for explanation.

8. Check-off marks for the reminder list are persistent and must also be saved immediately.

the operation checkReminder will possess the above behaviours.

9. The application must present the reminders grouped by type.

Nouns: 
Verbs: nameOfList, typeOfList
Behaviours:

When the user makes a list, the application manager will do the visuals as stated above.  

10. The application must support multiple reminder lists at a time (e.g., “Weekly”, “Monthly”, “Kid’s Reminders”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete reminder lists.

Nouns: 
Verbs: Age, collectionOfReminders
Behaviours: newReminderList, reName, reNameList

The operation updateDB will handle the date issue. Kids reminders is simply a verb as shown in the example video pertaining to the child to adult library card issue.  Multiple reminders will be handled by app managers collectionOfReminders attribute.

11. The application should have the option to set up reminders with day and time alert. If this option is selected allow option to repeat the behavior.

Nouns: 
Verbs: alertBoolean
Behaviours:

Another example from the video presented by Georgia Tech, a boolean attribute will represent an on or off state for alerts.

12. Extra Credit:   Option to set up reminder based on location. 

Nouns: GPS
Verbs: location
Behaviours: coordinates

Reminder class will optionally (chosen by the user) to allow GPS tracking to be allowed.

13. The User Interface (UI) must be intuitive and responsive.

Will be done at the design stage later, it is not useful to show on the UML class diagram.
