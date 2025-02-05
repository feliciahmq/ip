# Claudia User Guide

![claudia.ui.Claudia Chatbot](./Claudia_Chatbot.png)

Claudia is a Personal Assistant Chatbot to help the user keep track of their tasks using the *Command Line Interface (CLI)*.

## Table of Contents

- [Features](#features)
    - [Adding ToDo](#adding-todo)
    - [Adding Deadline](#adding-deadline)
    - [Adding Event](#adding-todo)
    - [Listing all tasks](#listing-all-tasks)
    - [Mark/Unmark a tasks](#markunmark-a-task)
    - [Delete a task](#delete-a-task)
    - [Exit Claudia Chatbot](#exit-claudia-chatbot)

## Features

### Adding ToDo

ToDo: task without any date/time attached to it

**Command**: `todo DESCRIPTION`

**Example**: `todo borrow book`

**Expected Output**:
```
Got it. I've added this task:
 [T][ ] borrow book
Now you have 1 tasks in the list.
```

### Adding Deadline

Deadline: task that needs to be done before a specific date/time

**Command**: `deadline DESCRIPTION /by DATE`

**Example**: `deadline return book /by 05/02/2025 1000`

**Expected Output**:
```
Got it. I've added this task:
 [D][ ] return book (by: Feb 05 2025, 1000)
Now you have 2 tasks in the list.
```

### Adding Event

Event: task that starts at a specific date/time and ends at a specific date/time

**Command**: `event DESCRIPTION /from DATE /to DATE`

**Example**: `event project meeting /from 06/02/2025 1000 /to 06/02/2025 1200`

**Expected Output**:
```
Got it. I've added this task:
 [E][ ] project meeting (from: Feb 06 2025, 1000 to: Feb 06 2025, 1200)
Now you have 3 tasks in the list.
```

### Listing all tasks

Displays a list of all the user's tasks.

**Command**: `list`

**Expected Output**:
```
Here are the tasks in your list:
1.[T][ ] borrow book
2.[D][ ] return book (by: Feb 05 2025, 1000)
3.[E][ ] project meeting (from: Feb 06 2025, 1000 to: Feb 06 2025, 1200)
```

### Mark/Unmark a task

**Mark** task as *done*.

**Command**: `mark ID`

**Example**: `mark 2`

**Expected Output**:
```
Nice! I've marked this task as done:
 [D][X] return book (by: Feb 05 2025, 1000)
```

**Unmark** to change the status back to *not done*.

**Command**: `unmark ID`

**Example**: `unmark 2`

**Expected Output**:
```
OK, I've marked this task as not done yet:
 [D][ ] return book (by: Feb 05 2025, 1000)
```

### Delete a task

Delete a task from the list.

**Command**: `delete ID`

**Example**: `delete 3`

**Expected Output**:
```
Noted. I've removed this task:
 [E][ ] project meeting (from: Feb 06 2025, 1000 to: Feb 06 2025, 1200)
Now you have 2 tasks in the list.
```

### Find a task

Find a list of tasks that contains one or more search keywords.

**Command**: `find KEYWORDS...`

**Example**: `find book`

**Expected Output**:
```
Here are the matching tasks in your list:
1. [T][ ] borrow book
2. [D][ ] return book (by: Feb 05 2025, 1000)
```

### Exit Claudia Chatbot

Terminates Claudia Chatbot session.

**Command**: `bye`

**Expected Output**:
```
Bye. Hope to see you again soon!
```