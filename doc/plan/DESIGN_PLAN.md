# SLogo Design Plan

### Team Number

9

### Names

Martha Aboagye(mfa23)

Joshua Petitma (jmp157)

Oliver Rodas (oar5)

Jiyang Tang (jt304)


## Introduction - Oliver
The team is trying to create a set of APIs that together implement a coding environment that uses Simple Logo commands. The team's goal is to create APIs that are clear and extensive, enabling the team (and future maintainers) to introduce new functionality to our environment. 

To accomplish this goal, the backend API must be open to adding new commands, but not be affected by how those commands are implemented. The backend's APIs should be also able to handle the addition of multiple Turtle objects. 

The FrontEnd's API must able to handle multiple representations of turtle-like objects and represent new commands as well.

The our program will be structured as follows:
The FrontEnd will use its internal API to create the User Interface. The FrontEnd will add listeners onto the model so that the model can update the View directly. The FrontEnd will also add listeners on its control elements (buttons, text fields) to allow the controller to call methods on the model. The controller will use the model's external API to change the interal state of the turtle. When the model finishes updating, it will send data to the view so that it can update its visual representation. 

## Overview - Josh
General Packages:
![](https://i.imgur.com/DidBCsz.png)
The model package exposes a "Trackable Environment" class
which the view will be able to use to get updates on the model's state.
![](https://i.imgur.com/4Irlp8b.png)
The events package contains all the info on update events that occur within
the model.
![](https://i.imgur.com/w3cVVwX.png)
The view package contains all the classes for the userinterface
and observing the model. It also includes the model controller
which is responsible for translating commands into the language used
within the model.

This is how we would start and initiate the application.
```java=
start(Stage stage) {
    ModelController con = new ModelController();
    MainView view = new MainView(stage, con);
}

//....View class

public View(Stage stage, Contorller con) {
    TurtleModel model = TurtleFactory.createModel(); // maybe configurations if needed
    con.setModel(model);
    model.setOnUpdate( (e) -> ...)
}
```


## User Interface - together


## Design Details - together

Frontend  External.
- UpdateTurtle.
- Update comands
- UpdateFunctions
- Package for Error Exceptions. 
- TurtleInfo ( for user questions )
- CommandsInfo (for commands).

Implementation of view/frontend internal
- Setup Main Display Window where turtle moves.
- Setup text area.
- Setup Window that displays commands, variables and user defined functions.
- Setup window that displays help informayion when a user calls for it.
- Create Turtle object.
- Create Display bar for user to customize ide appearancce.
- 


BackendExternal(what can the backend classes do)

**Trackable Turter** is our backened external interface. 
    It has the following abstract methods:
    * setOnTutleUpdate
    * setonVariableUpdates
    * setOnCommandUpdate
    * runCommand

TrackableEnvironment calls the environment which works in the following way: 
- ParseUserText 
    - Recognize Error Information and send information - Parser Class
- ExecuteCommand 
    - Recognize Commands and execute the command and send the command back when needed - Executioner Class
    - Recognize User functions, execute and send the command - Executioner class works with the lookup table
    - Recognize When user is asking for help on command and send appropriate information back.
    
- Throw error Package
    - We will create a throw error package that has an interface that backened classes can implement to throwerrors. An example of the errors might be: 
        - Error from unrecognized command
        - Error from comment in the middle of command
   

- Implementation of BackendInternal


The parser would create an abstract syntax tree (AST) where each node is a child of the ASTNode abstract class. The parser would receive a string containing the user input commands. The parser would recursively evaluate each token and its parameters. Variables and functions would be converted to lookup type commands, and literal values would be converted to literal type commands. The parser would handle basic syntax checking, but not check if variables exist. Each command would be an extension of the ASTNode class. 

For example "FD FD 50" would be converted to an AST tree with the root node being a forward object whose child is another forward node. The child of the second forward node is the a literal command node that contains 50. 

After parsing, the model will ask AST to evaluate itself, passing the variable/function environment as parameters. The AST evaluate the entire tree in a recursive way. In other words, a root node first evaluates all of its children, then use return values to evaluate itself. While evaluating, new variables and functions are added to the environment, and the position and orientation of turtle is modified. In the end, the return value of the entire command is the return value of the root node.

For example, FD FD 50 is evaluated in a way:

1. 50 evalutes first, returns 50
2. FD 50 then evaluates, change the turtle position and return 50
3. FD FD 50 evalutes the last, change the turtle position and return 50

If a error is encountered (such as variable not being found), AST should raise exception explaining why and where.


## Design Considerations.


- **Describe any issues which need to be addressed or resolved before attempting to devise a complete design solution.**
    -	 Flesh out how the backend external api is setup. 
            + We know that controller will pass information from the view to the backend by calling the model's external api. As of now, we have seperated backend external into 3 different Interfaces. They are the parser intercace, executioner inteface and model interface. We also have a trackableturtle interface which is the main backend external interface. 
    - We also need to work through and identify all the possible errors that can and should be through when parsing a user text.  We will create an Error package to handles all the errors we encounter. 

    - Outline how we are going to setup change in language both internally within the view and within the model
- Where to place event listeners.
	+ WITHIN CONTROLLER.
	     - In this system, the controller would place event handlers on the various elements of view and then call the appropriate backend external methods when a listener is activated.
	 - View sets it own listeners.
	     - In this system, view sets it own listeners perhaps in a different event handler class. When an event is activated, view can either call the appropriate external model or call a method in controller which calls the appropriate external model.
	- View set listeners on the model.
	    - In this system, the view sets listeners so that it knows when to update turtle or the command window or the function window or display help information etc.
	    
-  **Include any major design decisions that the group talked about in detail.**
    -  For our design, all the points listed under the frontend external are implemented as functional interfaces. We came to this decision by going through and discussing the various different ways to setup the MVC architecture. The first design setup we discussed was completely separating the model and view. With that design setup, the model and view would only communicate with each through the controller. The controller would interact with the views's api and send request back and forth to the model. We ultimately decided against this way of setting things up because we thought that the controller would be too big and handle too many responsibilities which violates the single responsibility responsible. We ultimately decided that the controller would only handle user action. This way, the controller has one single purpose which is interacting with the frontend external api to send commands to the appropriate model api. 
-   **describe at least one alternative in detail (including pros and cons from all sides of the discussion**
    -   As of now each of the interfaces listed is a functional interface which means that it  but depending on what issues we face when we start implementing our code, we  might make a singular interface that have all of the functional interfaces as methods.
    -   We can then have a model class and a view class that implements their respective interfaces. Although we don't yet have the exact number of view and model classes we are going to need, we think we are going to have one class that handles one or more functions that the front end and the backend should be able to do. Within the model and the view class, it can create instances of the model and view class to help it implement each of the methods that the interface requires.
-   **Describe any assumptions or dependencies regarding the program that impact the overall design.**

One major assumption we have made is that there will be only one instance of the lookup table in the backend classes. That instance will be in the executioner. The parser class only does strict syntax checking and then calls executioner to access the lookup table to execute the command. The executioner class is the main class for all the backend internal classes.  


## Test Plan

### Strategies

Since we don't consider too much about performance, java exceptions are a powerful tool for creating easily testable code.

To make it easier for us to distinguish different types of errors, we will make subclasses of `Exception` and with different error massage formats. Preferably each component of the project will have a clear structure, and they all raise different types of exceptions so that it's easier for us to pinpoint the bugs. For the testing, we would use JUnit to perform automated tests on backend and TestFX for fronted. Human are prone to errors, so a predefined sets of tests can help us a lot.

In addition, we can use Test Driven Development when implementing small components, like the command textbox or varaible table display.

NOTE by Jiyang: I don't have any experience with JUnit or TestFX, I've only used GoogleTest and pytest before. If any of you are familiar with Java testing, feel free to switch the testing framework or to add stuff above

### Test Scenarios

#### Feature 1: Parsing Commands

- Commands should be parsed into a correct abstract syntax tree
- Commands should work in both upper case and lower case (or mixed)
- Parser should support all commands listed in https://www2.cs.duke.edu/courses/compsci308/spring21/assign/03_parser/commands.php
- When encoutering incorrect commands, parser should raise an exception stating what's wrong with the command and where

#### Feature 2: Command Input

- Command input textbox should support multiline command
- The textbox should send the command string to the backend correctly if user presses "send" button
- The textbox should empty itself after sending the commands
- The current command should be added to the command history list
- If nothing is entered, refuse to send the command and notify the user

#### Feature 3: Drawing Turtle

- The turtle should starts at the center of the screen
- The turtle's movement and orientation should be correctly controlled by commands
- The pen should follow along turtle's tail path, and when the pen is down, a line should be drawn on the canvas
- When the turtle goes beyond the screen, either scale the canvas or move the "camera" along

#### Feature 4: Displaying Variables and Custom Commands

- When the user defines a new variable or command, it should be shown on the display
- If it's a variable, shown its value. If it's a custom command, show its name and the number of parameters
- User should be able to click on the function to quickly enter the command into the command input
- If a command is invalid, it shouldn't be added to the displaysplaysplay

## Team Responsibilities

 * Team Member #1
 Jiyang Tang - Backend

 * Team Member #2
Oliver Rodas - Backend


 * Team Member #3
Martha Aboagye - Frontend
 * Team Member #4
Josh - Frontend

```java=
public abstract class ASTNode {
    public abstract evaluate(GetStuff thing);
}

Map<String, AST>
public class Variable extends ASTNode {
    String toLookUp;
    public double evaluate(Turtle, lookUp) {
    lookUpTable thing.getLookUp
    return lookUpTable.get(toLookUp).evaluate();
    }

}

public class Forward extends ASTNode {
    
}

interface GetStuff {
    Turtle getTurtle()
    getFuncLookup()
}
```