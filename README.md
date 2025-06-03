# Meteorite Project
Meteorite Data Analyzer
A Java program that reads NASA meteorite data and lets you search through it.
What it does

Loads meteorite data from JSON files
Saves data locally for faster loading
Search meteorites by name or ID
Find the biggest meteorites
See recent discoveries
View meteorite types

Setup
You need:

Java 8+
Gson library
NASA meteorite JSON file

Add Gson to your project:
xml<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.10.1</version>
</dependency>
How to use
Run the program and pick from the menu:

Load data - Import the JSON file
Show all - Display all meteorites
Save - Save to binary file
Search name - Find by meteorite name
Search ID - Find by ID number
Biggest - Show heaviest meteorites
Recent - Show newest discoveries
Types - Group by classification

Example
Enter your choice: 4
Enter meteorite name: Aachen
Found: Aachen (ID: 1, Class: L5, Mass: 21g, Year: 1880)
That's it. Load your data, then search and explore.
