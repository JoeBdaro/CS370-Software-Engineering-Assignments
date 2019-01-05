1. A list consisting of reminders the users want to be aware of. The application must allow
users to add reminders to a list, delete reminders from a list, and edit the reminders in
the list.

--This was implemented by creating a class called listReminders which has the following methods respectively named to allow the user to add reminders to a list, delete reminders from a list, and edit the reminders 
addReminders(reminderName)
deleteReminder(reminderName)
editReminder(reminderName)
selectRemindersForCheckOff(reminderName)
clearCheckOffMarks()

2. The application must contain a database (DB) of reminders and corresponding data .

--Implemented but not modeled as its not part of UML guidelines, a database of reminders

3. Users must be able to add reminders to a list by picking them from a hierarchical list,
where the first level is the reminder type (e.g., Appointment), and the second level is the
name of the actual reminder (e.g., Dentist Appointment).

--When the user goes to invoke the addReminders method there is a class called ReminderType that has a list of predefined reminderType in which the user can choose from, at which point there's another class called Reminders that handles the creation of reminders as objects.

4. Users must also be able to specify a reminder by typing its name. In this case, the
application must look in its DB for reminders with similar names and ask the user
whether that is the item they intended to add. If a match (or nearby match) cannot be
found, the application must ask the user to select a reminder type for the reminder, or
add a new one, and then save the new reminder, together with its type, in the DB.

--Not fully displayed as it's a UI feature more than a UML but in the listView class there is a method called SearchReminders in which a user can search for a reminder by directly querying the database across multiple lists which is handled by the rearchReminders method. The addReminderType method also lets users add reminder types to be used when creating new reminders in the ReminderTypeSet class 


5. The reminders must be saved automatically and immediately after they are modified.

--Once a reminder is created the listReminders class will immediately update the database as the CRUD for reminders in the DB line illustrates

6. Users must be able to check off reminders in the list (without deleting them).

--Each object of a Reminder class has an attribute of type boolean that will identify whether its crossed off or not as per the isCheckedOff attribute.

7. Users must also be able to clear all the check-off marks in the reminder list at once.

--There is a method in listReminders class that is responsible for obtaining all reminders in the DB pertaining to the list by identifying every item that shares the value of belongingList attribute in each Reminder object and resets there boolean value of isCheckedOff to flase


8. Check-off marks for the reminder list are persistent and must also be saved immediately.

--isCheckedOff attribute in the Reminder class is saved and carried with the object to the database as soon as the object is finished being edited via the CRUD for reminders in DB connection


9. The application must present the reminders grouped by type.

--Since this is a UI element it won't fully reflect in the UML but each Reminder object has a reminderType attribute that is saved with the reminder thus making each reminder to be grouped by their reminder type when needed


10. The application must support multiple reminder lists at a time (e.g., Weekly, Monthly,
Kid's Reminders). Therefore, the application must provide the users with the ability to
create, (re)name, select, and delete reminder lists.

--The listView class is a collection of objects of type listReminders which also lets the users have access to the following methods to create, (re)name, select, and delete reminder lists
createList(listName)
renameList(selectList, newListName)
deleteList(listName)

11. The application should have the option to set up reminders with day and time alert. If this
option is selected allow option to repeat the behavior.

--There are three attributes that handle this aspect in the Reminder class. The isAllDay boolean determines whether the reminder will just be an all day instance and will set the dateTime attribute to null, however if isAllDay is set to false it will ask for a dateTime to be entered and the repeatsReminder attribute executes the RepeatsReminder class in which a user can set an endingDate for the series as well as how concurrently this reminder repeats

12. Extra Credit: Option to set up reminder based on location.

--Location attribute in the Reminders class calls the setLocation() method in which it invokes the google maps API class

13. The User Interface (UI) must be intuitive and responsive.

--Not displayed or modeled in the UML as its a UI element however the goal is to adhere to the simplicity and structure of the Google Material Design guidelines.
