📱 ConnectU

ConnectU is an Android application built with Jetpack Compose that helps users connect with others, manage events, and explore social connections through a modern, intuitive interface. The app includes user authentication, navigation between multiple screens, and a local storage system to persist user data.

🚀 Features
🔐 Authentication

Login Screen: Allows users to sign in using a username and password.

Register Screen: Enables new users to create an account.

Authentication data is stored locally using Room.

🏠 Home Screen

Displays upcoming events using rounded cards.

Each event card shows:

Event title

Participant name

Date and time

Duration

🤝 Connect Screen

Shows suggested friends in card format.

Each card includes:

Name

Interests or skills

Availability or location

👤 Profile Screen

Placeholder screen for future user profile details.

🧭 Navigation

Seamless navigation between screens using Jetpack Compose Navigation.

Bottom navigation bar for Home, Connect, and Profile screens.

🗂️ Architecture & Design

MVVM Architecture

ViewModel handles business logic

Repository manages data operations

Room (SQLite) handles local persistence

UI Components

Reusable composables such as Event cards and Friend cards

Rounded cards and Material 3 styling for a modern look

🛠️ Technology Stack

Language: Kotlin

UI Framework: Jetpack Compose 

IDE: Android Studio

Architecture: MVVM

Local Database: Room (SQLite)

Minimum SDK: API 24 (Android 7.0)
