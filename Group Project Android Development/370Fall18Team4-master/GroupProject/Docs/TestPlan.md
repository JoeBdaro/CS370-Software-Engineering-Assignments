# Test Plan

**Author**: Shangshang Zhu

## 1 Testing Strategy

### 1.1 Overall strategy

Mainly the testing process will follow a pipeline format going from Unit Testing > Integration Testing > System Testing  > Acceptance Testing. Regressive testing will be incorporated during our four test phases. The following are concise strategies for each of our four testing phases:

Unit Testing: breaking the program into small pieces, then test each
              piece of the program by writing the test cases manually.
              Tests should return the expected results.
              The test is used to identify, document and stress all the
              behaviors in the classes.

	1. add a new reminder
		- check if it is added
	2. add a new type
		- check if it is added
	3. add a new subType
		- check if it is added
	4. add a note to reminder
		- check if it is added
	5. edit the reminder
		- check if the reminder is changed
	6. delete the reminder
		- check if the reminder is deleted
	7. check the reminder
		- check if the reminder is checked
	8. uncheck the reminder 
		- check if the reminder is unchecked
	9. search specific reminder
		- check if the specific reminder is found



Integration Testing: To test the interaction between integrated units that individual units are tested as a group. It is tested when a component has some sort of relationship with other components.

	1. create a new reminder
	2. create a new type for the reminder
	3. create a new subType for the reminder
	4. add notes to the reminder
	5. edit the type/subtype/notes for the reminder
	6. check the reminder
	7. uncheck the reminder
	8. search the reminder 
	9. delete the reminder
                     

System Testing: a complete and integrated application is tested to see if it fits all the requirements. Run the whole applications to see if it runs as expected.

    -ran multiple times, each time tested different cases and then rebooted the system, rinse and repeat strategy.

Acceptance Testing: a system is tested for acceptability that check the application is deliverable.
	
	 - deliver the reminder application to the phone and run it or run the application on the Android Emulator and then test the application.

### 1.2 Test Selection

Black box: System Testing (from software specification). Run all components as a group and test them one by one.

	1. create a new reminder
	2. create a new type for the reminder
	3. create a new subType for the reminder
	4. add notes to the reminder
	5. edit the type/subtype/notes for the reminder
	6. check the reminder
	7. uncheck the reminder
	6. delete the reminder
	
Do such steps one by one, so we could test this application is runnable and functions of the application work correctly.

White box: Unit testing & integrated testing (from code)

  1. Unit testing: to test each function individually.
  		- add Type
  		- add subType
  		- add notes
  		- edit the reminder
  		- check the reminder
  		- uncheck the reminder
  		- delete the reminder
  		
  		
  2. Integrated testing: to test related components of the application together.
  	 - run or test the function as a group that each function(step) is depended on the previous steps. 

### 1.3 Adequacy Criterion

Set of obligation that to check all the tests are passed.

unit Testing: test each function or feature individually.

	-Statement coverage:
              executing all the statement at least once by giving notation
              that to know the result is passed or failed.



system Testing:	to test the related functions as a group

	- Decision coverage(while, switch, for, do):
                  executing each decision has a true or false outcome at least
                  once that each statement is executed at least once.

	- Path coverage:
               all control flow paths are executed at least once.
               it counts the number of full paths from input to output.
           
In our condition, we would test the application manually, so we would create Test cases table to record the procedure of testing; therefore, the test cases table contains the way of testing, and each component would be tested.

### 1.4 Bug Tracking

Unit testing: test each component one at the time, so if there is errors, we could know which function or which part should be fixed. 

Integrated testing: to evaluate the interaction between connected components. If the application passed the Unit testing, and it did not pass the Integrated testing, then we know there error occurs when we connect them.

System testing: If the Unit testing and Integrated passed, and if error occurs after running the the whole complete application, we would know which part should be fixed.


### 1.5 Technology

JUnit(API).

However, in this case, we would test the reminder application manually, so each function would be tested manually and the detail would be provided in the Test cases Table. 


## 2 Test Cases

Test cases are executed to verify a particular feature or functionality of the
software that should return expected results if the application works correctly.

In this reminder application, we would test the features and functions manually. Therefore, Test case table would show the procedure of the testing.


| Test description   	| Test case ID      	| Test case Description                                              	| Pre-condition      	| Test data      	| Post-condition        	| Expected result       	| Actual result                     	| Status 	|   	|
|--------------------	|-------------------	|--------------------------------------------------------------------	|--------------------	|----------------	|-----------------------	|-----------------------	|-----------------------------------	|--------	|---	|
| verify addReminder 	| addReminder#11/12 	| 1.addReminder 2.finish the following add functions 3.click add btn 	| Login              	| user account   	| a new reminder        	| reminder is created   	| no result                         	| Fail   	|   	|
|                    	| addReminder#11/17 	| 1.addReminder 2.finish the following add functions 3.click add btn 	| Login              	| user account   	| a new reminder        	| reminder is created   	| is added                          	| Pass   	|   	|
|                    	| addReminder#11/19 	| 1.addReminder 2.finish the following add functions 3.click add btn 	| Login              	| user account   	| a new reminder        	| reminder is created   	| is added                          	| Pass   	|   	|
| verify addType     	| addType#11/12     	| 1.click reminder 2.addType                                         	| a reminder         	| Appointment    	| reminder has type     	| new type is added     	| no result                         	| Fail   	|   	|
|                    	| addType#11/17     	| 1.click reminder 2.addType                                         	| a reminder         	| Appointment    	| reminder has type     	| new type is added     	| is added                          	| Pass   	|   	|
|                    	| addType#11/19     	| 1.click reminder 2.addType                                         	| a reminder         	| Appointment    	| reminder has type     	| new type is added     	| is added                          	| Pass   	|   	|
| verify addSubType  	| addSubType#11/12  	| 1.click reminder 2.add sub type                                    	| a reminder         	| doctor         	| reminder has sub type 	| new sub type is added 	| no result                         	| Fail   	|   	|
|                    	| addSubType#11/17  	| 1.click reminder 2.add sub type                                    	| a reminder         	| doctor         	| reminder has sub type 	| new sub type is added 	| is added                          	| Pass   	|   	|
|                    	| addSubType#11/19  	| 1.click reminder 2.add sub type                                    	| a reminder         	| doctor         	| reminder has sub type 	| new sub type is added 	| is added                          	| Pass   	|   	|
| verify addNote     	| addNote#11/12     	| 1.click reminder 2.add note                                        	| a reminder         	| company needed 	| reminder has notes    	| note is added         	| no result                         	| Fail   	|   	|
|                    	| addNote#11/17     	| 1.click reminder 2.add note                                        	| a reminder         	| campany needed 	| reminder has notes    	| note is added         	| is added                          	| Pass   	|   	|
|                    	| addNote#11/19     	| 1.click reminder 2.add note                                        	| a reminder         	| campany needed 	| reminder has notes    	| note is added         	| is added                          	| Pass   	|   	|
| verify edit        	| edit#11/12        	| 1.click reminder 2.edit reminder                                   	| a reminder         	| dentist        	| content changed       	| sub type is dentist   	| no result                         	| Fail   	|   	|
|                    	| edit#11/17        	| 1.click reminder 2.edit reminder                                   	| a reminder         	| dentist        	| content changed       	| sub type is dentist   	| is added                          	| Pass   	|   	|
|                    	| edit#11/19        	| 1.click reminder 2.edit reminder                                   	| a reminder         	| dentist        	| content changed       	| sub type is dentist   	| is added                          	| Pass   	|   	|
| verify delete      	| delete#11/12      	| 1.click reminder 2.delete reminder                                 	| a reminder         	|                	| reminder is deleted   	| reminder is deleted   	| no result                         	| Fail   	|   	|
|                    	| delete#11/17      	| 1.click reminder 2.delete reminder                                 	| a reminder         	|                	| reminder is deleted   	| reminder is deleted   	| deleted                           	| Pass   	|   	|
|                    	| delete#11/19      	| 1.click reminder 2.delete reminder                                 	| a reminder         	|                	| reminder is deleted   	| reminder is deleted   	| deleted                           	| Pass   	|   	|
| verify check       	| check#11/12       	| 1.a reminder 2.check                                               	| a reminder         	|                	| reminder is checked   	| reminder is checked   	| no result                         	| Fail   	|   	|
|                    	| check#11/17       	| 1.a reminder 2.check                                               	| a reminder         	|                	| reminder is checked   	| reminder is checked   	| no result                         	| Fail   	|   	|
|                    	| check#11/19       	| 1.a reminder 2.check                                               	| a reminder         	|                	| reminder is checked   	| reminder is checked   	| reminder is checked               	| Pass   	|   	|
| verify clearCheck  	| clearCheck#11/12  	| 1.a checked reminder 2.clear check                                 	| a checked reminder 	|                	| reminder is unchecked 	| reminder is unchecked 	| no result                         	| Fail   	|   	|
|                    	| clearCheck#11/17  	| 1.a checked reminder 2.clear check                                 	| a checked reminder 	|                	| reminder is unchecked 	| reminder is unchecked 	| no result                         	| Fail   	|   	|
|                    	| clearCheck#11/19  	| 1.a checked reminder 2.clear check                                 	| a checked reminder 	|                	| reminder is unchecked 	| reminder is unchecked 	| the checked reminder is unchecked 	| Pass   	|   	|
| verify search      	| search#11/12      	| 1.exists a reminder 2.search                                       	| a reminder         	| dentist        	| reminder is found     	| found the reminder    	| no result                         	| Fail   	|   	|
|                    	| search#11/17      	| 1.exists a reminder 2.search                                       	| a reminder         	| dentist        	| reminder is found     	| found the reminder    	| no result                         	| Fail   	|   	|
|                    	| search#11/19      	| 1.exists a reminder 2.search                                       	| a reminder         	| dentist        	| reminder is found     	| found the reminder    	| no result                         	| Fail   	|   	|

