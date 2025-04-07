## Android Git Caching APP

This is an Android application built with **Kotlin** that allows users to search for queries and view results in a WebView. The app demonstrates a clean flow of using local storage and API integration:

- Loads the **last searched query** from a local Room database
- Fetches new results from an API on new searches
- Displays results in a WebView
- Saves every successful query to the local database
  
---

## Features

- **Persistent Search** – Automatically loads and shows the last search result.
- **Live Search** – Searches for new queries from an API.
- **Local Database** – Stores each searched query and result using Room DB.
- **WebView Integration** – Displays web content related to the query.
- **Material Design** – Clean UI using Material Components.

---

## Tech Stack

- **Kotlin**
- **Room Database**
- **WebView**
- **ViewModel & LiveData**
- **MVVM Architecture**
- **Material Components**

---

## How It Works

### On App Launch:
1. The app fetches the **last searched query** from the local Room database.
2. On click of list item tt loads the corresponding URL or content into the WebView.

### On New Search:
1. User types a query in the input field.
2. Hits the **search button** or presses **Enter**.
3. The app sends the query to an **API**.
4. The result is retrieved and Saved to the Room database as the latest search
5. The UI is updated to reflect the new result.

## Flow Diagram

![LJI_Assignment](https://github.com/user-attachments/assets/bcc4b2df-6b12-4e1c-b975-abc42a33d427)

