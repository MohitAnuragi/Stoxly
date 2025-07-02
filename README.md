Stoxly - Stock Market Companion App 📈
---
Stoxly is a modern, responsive, and intuitive Android application for exploring stock and ETF data, tracking your watchlist, and analyzing market trends — powered by Firebase, Jetpack Compose, and Alpha Vantage APIs.

🚀 Features
---
📈 Explore top Gainers & Losers with graph charts

🔍 View all stocks in paginated form

📊 Interactive Line Chart for each stock using MPAndroidChart

💼 Add/remove stocks to/from personalized watchlists

🔐 Email & Password Signup/Login with DOB support

☁️ Firebase Authentication integration

⚙️ Retrofit + Serialization for fetching API data

📦 Room DB for local watchlist persistence

🎨 Built with Jetpack Compose using a custom theme

🧪 Tech Stack & Tools
---
| Technology                  | Purpose                                               |
| --------------------------- | ----------------------------------------------------- |
| **Jetpack Compose**         | Modern declarative UI toolkit for building native UIs |
| **Kotlin**                  | Primary language for development                      |
| **Material 3**              | UI components (M3) for modern design                  |
| **Firebase Auth**           | Email/Password-based authentication                   |
| **Firebase Analytics**      | Track user behavior and events (optional)             |
| **Retrofit**                | HTTP client to interact with Alpha Vantage APIs       |
| **Kotlinx Serialization**   | JSON parsing for API responses                        |
| **OkHttp**                  | Network layer with request logging                    |
| **Room**                    | Local database for storing watchlist data             |
| **Hilt**                    | Dependency Injection for scalable architecture        |
| **Accompanist Pager**       | Tabbed screen management with swipe support           |
| **MPAndroidChart**          | Graph and chart rendering for price trends            |
| **Navigation Compose**      | Manage navigation between screens                     |
| **Material Icons Extended** | Custom icons in UI                                    |
| **Gson**                    | JSON converter (optional fallback for Retrofit)       |
| **Play Services Auth**      | Support for Google authentication if needed later     |


🛠️ Build Configuration
---
Gradle Plugins

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("com.google.gms.google-services")
}

🔑 Authentication Flow
---
Signup Screen:

Full Name, DOB (with picker), Email, Password + Confirm Password

Login Screen:

Email + Password with Firebase validation

FirebaseAuth manages secure authentication

Email Verification and Password Reset (optional feature you can add)

📊 API Integration
---
Powered by Alpha Vantage API

Fetches:

  1. Company overview

  2. Ticker data

  3. Top gainers & losers

Retrofit + Serialization manages all API interactions

🎓 Learning Outcomes
---

✅ Built a full-featured Android app using Kotlin and Jetpack Compose

✅ Designed modern UIs with Material 3 and custom color themes

✅ Implemented Firebase Authentication (Email & Password)

✅ Created a Signup screen with DOB picker and validation

✅ Used Retrofit with Kotlinx Serialization and Gson for API handling

✅ Integrated Alpha Vantage API to fetch stock/ETF market data

✅ Displayed stock price trends using MPAndroidChart

✅ Implemented Room Database to persist user's watchlist locally

✅ Used OkHttp & Logging Interceptor for debugging network calls

✅ Structured the project using MVVM architecture

✅ Managed screen flow using Navigation Compose

✅ Added tabbed navigation with Accompanist Pager

✅ Followed clean architecture with dependency injection (Hilt)

✅ Handled UI states: loading, empty, error

✅ Gained real-world experience with modular app development

📸 Screenshots
---
<p align="center">
  <img src="https://github.com/user-attachments/assets/0a2c19d2-f438-48c2-a00a-501e42b4eb52" alt="Signup" width="30%" />
  <img src="https://github.com/user-attachments/assets/062ed51f-f6ef-4ef2-b595-9fb55c3f8dc9" alt="Explore" width="30%" />
  <img src="https://github.com/user-attachments/assets/372e06bf-563b-4a01-a01a-fcff608abb39" alt="Chart" width="30%" />
</p>





