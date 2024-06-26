# Happy Hours
## Table of Contents

- [Introduction](https://github.com/kaganatreviro/android/tree/main#introduction)
- [Background](https://github.com/kaganatreviro/android/tree/main#background)
- [Purpose](https://github.com/kaganatreviro/android/tree/main#purpose)
- [Features](https://github.com/kaganatreviro/android/tree/main#features)
- [Installation](https://github.com/kaganatreviro/android/tree/main#installation)
- [Usage](https://github.com/kaganatreviro/android/tree/main#usage)
- [Configuration](https://github.com/kaganatreviro/android/tree/main#configuration)
- [Screenshots](https://github.com/kaganatreviro/android/tree/main?tab=readme-ov-file#screenshots)
- [Technologies Used](https://github.com/kaganatreviro/android/tree/main?tab=readme-ov-file#technologies-used)
- [Contributing](https://github.com/kaganatreviro/android/tree/main#contributing)


## Introduction
In response to the prevalent issue faced by many restaurants and cafes during off-peak hours, the concept of a "happy hour" or discounted beverage offerings might become a popular strategy for attracting customers. This document outlines a specialized approach to this concept, focusing on providing users with access to free beverages during designated times.

## Background
Many restaurants and cafes encounter a common challenge during certain hours of the day when customer traffic is minimal, typically during midday and between the end of lunch service and the start of dinner. To address this issue, businesses are willing to offer discounted beverages to attract customers, as the cost of acquiring a customer is often outweighed by the potential revenue generated.

## Purpose
The purpose of this project is to implement a subscription-based model wherein customers can enjoy free beverages at participating businesses during specified hours. The aim is to increase foot traffic and revenue for participating venues.

## Features
- User Registration and Authentication: Users can register and log in using their email and password, with provisions for password recovery.
- Profile Management: Users can manage their profile details, including name, date of birth, and avatar.
- Establishment and Menu Access: Users can access a list of participating cafes and restaurants, view menus of available beverages, and browse establishments based on location and beverage availability.
- QR Code Integration: The application enables users to scan QR codes at partner establishments to access the beverage menu and place orders.
- Search and Filter Functionality: Users can search for establishments or beverages using text-based search and apply filters based on location and availability status.

## Installation

### Prerequisites
- Android Studio
- Kotlin

#### Steps
1. Clone the repository:
```bash
git clone https://github.com/username/happy-hours.git
```
2. Navigate to the project directory:
```bash
cd happy-hours
```
3. Open the project in Android Studio.
4. Sync the project with Gradle.
5. Run the project on an emulator or a real device.

## Configuration
### Configuration Files
The project uses a 'local.properties' file to store configurations. Example content:
```bash
apiKey=your_google_maps_api_key
```
### Environment Variables
- apiKey: Your API key for Google Maps.

## Usage
### Running the Project
#### To run the project in Android Studio:

1. Open the project.
2. Select the run configuration.
3. Click "Run".

#### Examples
- Registration and Login: Create an account and log in.
- Searching Establishments: Use the search feature to find nearby cafes and restaurants with free beverages.
- QR Code Scanning: Scan QR codes at establishments to access beverage menus and place orders.

## Screenshots
![Login Screen](screens/login_screen.jpeg)
*Login Screen*

![Establishment Screen](screens/establishments_screen.jpeg)
*Establishment Screen*

![Establishment Detail Screen](screens/establishments_detail_screen.jpeg)
*Establishment Detail Screen*

![Search Screen](screens/search_screen.jpeg)
*Search Screen*

![Search Map Screen](screens/search_map_screen.jpeg)
*Search Map Screen*

![Order History Screen](screens/order_history_screen.jpeg)
*Order History Screen*

![Profile Screen](screens/profile_screen.jpeg)
*Profile Screen*

![Subscription Screen](screens/subscription_screen.jpeg)
*Subscription Screen*

## Technologies Used
- Kotlin: Programming language used for Android development.
- Android Studio: Integrated Development Environment (IDE) for Android development.
- MVVM (Model-View-ViewModel): Architectural pattern used to separate the UI (View) from the business logic (Model) and to manage the state and data (ViewModel).
- Clean Architecture: For maintaining a clear separation of concerns and making the codebase more modular, testable, and maintainable.
- Koin: Dependency injection framework for Kotlin.
- Coroutines: For asynchronous programming and managing background tasks.
- KotlinFlow: For handling streams of data asynchronously.
- LiveData: A lifecycle-aware data holder used to observe changes in the UI.
- Retrofit: HTTP client for making API requests.
- OkHttp: HTTP & HTTP/2 client for network operations.
- Google Maps API: For displaying maps and locations.
- Glide: Image loading and caching library.
- Material Design Components: For implementing modern UI components. 
- And much more (etc.)

### Dependency Management
All dependencies are managed and stored in the libs.versions.toml file.

## Contributing
#### Guidelines
- Follow the established coding standards.
- Write clear commit messages.
- Ensure your changes are tested before creating a PR.
#### How to Contribute
1. Fork the repository.
2. Create a new branch:
```bash
git checkout -b feature-branch 
```
3. Make your changes and commit them:
```bash
git commit -m "Added new feature"
```
4. Push your changes to GitHub:
```bash
git push origin feature-branch
```
5. Create a pull request.

