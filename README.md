# PantherBuddy-JSP
Advanced Software Engineering Project (Fall 2016) project

This is the project created for my Advanced Software Engineering Project (Fall 2016).<br></br>
PantherBuddy was developed as a forum for collection of information on life and studies at Florida International University.

The project was built using <b>Java and JSP</b>.<br>
The backend database was implemented in <b>MySQL</b>.<br>
We used <b>JUnit and Mockito</b> for our testing. We did the unit test, subsystem test and the system test for the project.<br>
We used <b>"Bootstrap"</b> to make the application mobile device compatible.

The project "PantherBuddyUML" contains the UML diagrams created during the requirements and design phase. It was done using the "Papyrus" plugin for eclipse.
The code was generated from the class diagram and then worked upon.


Other major Parts:<br></br>
We use AES 128 bit encryption for password encryption. This module is used during login and user verification.<br></br>
We configured a remote email server on wildfly server to get email session from JNDI. This is used to send the new password and to recover password for a user.<br>
We created a filter to set header in data so that the back button is not allowed unless a user session is active.<br></br>
