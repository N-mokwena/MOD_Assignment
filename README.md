# MOD_Assignment
Using Android Studio:(Summary of what the project was about)
The Smart Pantry Manager is a Java-based Android application developed in Android Studio to help users reduce food waste by managing the ingredients available in their pantry and suggesting recipes that can be prepared using only those ingredients. The application must allow users to add, edit, view, and delete pantry items while storing data persistently using SQLite, Firebase, or PostgreSQL. It should contain at least five screens, including Pantry List, Add/Edit Ingredient, Suggested Recipes, Recipe Detail, and Settings screens, with navigation implemented through Intents and a navigation component. The app must include a database containing 15 to 20 preloaded recipes and implement strict recipe-matching logic, where a recipe is suggested only if all required ingredients and quantities are available in the user's pantry. A RecyclerView or ListView with a custom Adapter must display dynamic data, and all forms should include input validation. The user interface should be clear, responsive, and suitable for mobile devices. The project must be developed under GitHub version control with a minimum of ten meaningful commits and a README file containing project details and setup instructions. In addition, students must submit a 5 to 7-minute video demonstration showing the application's functionality, CRUD operations, GitHub history, database implementation, and explanation of key concepts. A written report must also be provided, including an introduction, system design diagrams, screenshots of all screens and features, selected code snippets, challenges encountered, solutions implemented, conclusions, and references. The final submission must be delivered as a single ZIP file containing the Android Studio project, demonstration video, and report.

The Smart Pantry Manager is a Java-based Android application designed to help users manage pantry items, track expiry dates, and receive recipe suggestions based on ingredients currently available. The app is useful for reducing food waste by allowing users to record their stock, monitor items approaching expiry, and quickly identify possible meals they can prepare. For the database, I chose Room Database because it is a lightweight, efficient, and Android-native option that works well for local storage in mobile applications. It provides a structured way to store pantry data, supports Java-based app development, and is well-suited for managing ingredient records in a simple offline app.

Setup and Run Instructions:

Open Android Studio.
Create a new project using Empty Views Activity.
Name the app Smart Pantry Manager and set the package name to com.example.smartpantry.
Choose Java as the programming language and set the minimum SDK to API 24 or above.
Add the required dependencies in the app-level Gradle file:
AndroidX AppCompat
Material Components
RecyclerView
Room Runtime
Room Compiler
Create the Java classes for Ingredient, PantryDao, PantryDatabase, and PantryAdapter.
Design the user interface in activity_main.xml with fields for ingredient name, quantity, unit, expiry date, favorite checkbox, Add Ingredient button, Suggest Recipes button, and RecyclerView.
Implement the logic in MainActivity.java to add ingredients to the database, display them in the list, and suggest recipes based on pantry matching.
Connect an Android device through USB debugging or start an Android emulator.
Click Run in Android Studio and select the connected device or emulator.
The app will install and launch automatically.
Enter pantry items and test the recipe suggestion feature.

SQL was a good choice for this project because it provides a reliable and structured way to store pantry items such as ingredient name, quantity, unit, expiration date, and favourite status. Using a database like SQLite or Room Database allows the app to manage data efficiently, keep information persistent across app sessions, and support fast retrieval when the pantry list or recipe suggestions are displayed. SQL is especially suitable for this project because the app stores repeated data records and needs to organize them in a consistent, easy-to-query format. This makes the app more dependable, easier to maintain, and better suited for future features such as editing items, deleting products, or sorting by expiry date. It also the only database option I was familiar with and could make 
