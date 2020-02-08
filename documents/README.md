#Domain Scenario				
This document describes a fictional educational software company called FirstGrade. FirstGrade creates tests for K-12 schools that may be administered either online or via printed tests with bubble sheets for automated grading. The company grades tests, makes them available online for teachers, and runs various metrics on the tests.			

##Creating tests
The company provides tools for creating tests with the usual types of questions: multiple choice, fill in the blank, matching, grid, and artwork (where the student draws an answer and the teacher assigns a score).				

Schools may create and enter their own test content, or they may use test content purchased from other educational software companies or created by other school systems. If the school creates content, then the test content must be tagged with information that describes the type of content (e.g., “math”), the recommended grade (e.g., “8th grade geometry”), the correct answer, and false answers. Some schools may wish to review the content before making it available for teachers to use, so other teachers and school administrators may comment on and approve or disapprove of the content’s use.				

Once content is available, a teacher may use FirstGrade’s software to create a test. The software provides a search facility to locate possible questions given a grade level, subject, and topic. The teacher may arrange the questions and save the test as a draft copy. Once the teacher is finished with the test, they may create either an online test, or they may print out the test questions and bubble sheets.			

If the teacher decides to print the test, they are presented with options to configure the page size (e.g., letter size, legal size) and page layout (e.g., portrait or landscape). The teacher may also decide whether to print the child’s name, creating customized tests to hand out. Each page is marked with the test name, the teacher’s name, the date, the page number, and an id marker that may be scanned to identify the test.
					

##Administering tests (online)				
If the teacher decides to administer a test online, then the teacher takes the class to a computer room and has them log in to the FirstGrade software. The teacher makes the test link available, and each child is presented with an interactive test. The software tracks each child’s responses, even if the child repeatedly changes their answer, and saves the information periodically (in case the connection drops). The teacher may optionally set a timer for the test: if so, the test is locked after the specified number of minutes so that the class may no longer edit the tests. The teacher may then mark the test as “complete”.				

##Administering tests (offline)			

Some teachers may prefer to administer tests offline. To do that, the teacher prints the tests and hands them out to the students. Once the students have handed in their tests, the teacher takes the bubble sheets to the school office and faxes them to FirstGrade.	

FirstGrade receives the images of the bubble sheets and performs some image analysis on them (finding the identifying marker so that the metadata about the test may be found, rotating and deskewing the image, normalizing the light levels, etc.). Next, the FirstGrade system uses the metadata about the page to locate the answer locations on the test, and then decides which bubble(s) the student has marked, and which ones the student may have marked and then erased. The answers are saved for later use. If a page cannot be read, then the page image is saved and an alert is generated for the school.			

Later, the teacher may log in and examine any bad pages (e.g., the page may have been put in the copier backward, it may have jammed in the copier, or the teacher may have left a sticky note on top of a page). The teacher may then manually enter data for that page.			

##Grading tests			

At the conclusion of an online test, the answers are graded and the results are stored in a database. For an offline test, each page is graded as it arrives; once all of the pages for a given student and test have arrived, the entire test is graded and the results are stored in a database.

Once all tests have been graded, the teacher receives a notification that the results are ready. The teacher may log in and print the results. They may also relay the test results to the individual students.				

##Test metrics				
The teacher and other school administrators with the appropriate permissions may log in and look at various test metrics. The metrics should be customizable: e.g., the results of a test may be compared to other teachers in the school, other teachers in the school system, or in the state. Test scores may be correlated by various categories: e.g., by gender, race, age, etc. Teachers may also look at historical records: e.g., did the child progress or regress over the school year? Did a child do better in certain subjects than others.				

School administrators may access additional metrics to determine trends in the school. E.g., do elderly teachers perform better than younger teachers? Do Hispanic children learn better from Hispanic teachers, from teachers of other races, or is the matter of race unrelated to test scores. Do certain teachers have higher rates of student indecision (identified by children changing answers during a test, or erasing answers and writing new ones)? Is there any evidence of teachers changing grades? 
				
			
		

