# GameAppOriginal App Design Project
===

# GAMEDATA

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
An app that displays information about different games, and other relevant information about gaming topics. If users are interested in a particular game, the app will direct them to the Steam page to purchase or look at the game. The app can be used to look at different games and relevant news in the industry. 

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** Gaming / News
- **Mobile:** This app would be primarily developed for mobile but would perhaps be just as viable on a computer, such as Steam. Functionality wouldn't be limited to mobile devices, however mobile version could potentially have more features.
- **Story:** Using different APIs, the app will be a place to consolidate news, game information, and other topics. Users can also be directed to other places to get more information or to purchase games.
- **Market:** Any individual could choose to use this app, and to keep it a safe environment, people would be organized into age groups to restrict them based off of rating or country.
- **Habit:** This app could be used as often or unoften as the user wanted depending on what games they are interested in, and what exactly they’re looking for.
- **Scope:** First, we can start by listing the top 20 most popular games and all their relevant information, then broaden the usage to include all games that are listed, and search for different games.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

- [x] User can log in of their account
- [x] User can log out of their account
- [x] User will be able to see a list of games in a landing page
- [x] User will be able to click on a game to display a detailed view about that game
- [x] User can pick what genre of games (Like Steam tags)
- [x] Will be directed to Steam page to purchase games if they want

**Optional Nice-to-have Stories**

- [ ] User will be able to optionally log in to keep track of games they want
- [ ] A like or track feature to keep track of games that they are interested in
- [ ] A randomize button to view random games within the database
- [ ] Profile page to change preferences and location of games
- [x] Clean and pleasing UI
- [x] Storing game information into local storage to be able to be used offline

### 2. Screen Archetypes

- [x] Landing Page - Displays current popular games
- [x] Upon opening the app, the user will be able to see the most popular current games
- [x] Each game will be clickable and bring the user to a detailed view of the game that was clicked .
- [ ] Profile Page
- [ ] user is prompted to log in to access and change their profile information
- [ ] Lets the user upload a picture
- [ ] Lets the user edit display information such as genre, age group, etc.
- [x] Game Page - Displays detailed information about the game
- [x] Relevant game information will be displayed, such as rating, age group, genre, and other information
- [ ] Will be linked to a steam page of the game to purchase

### 3. Navigation

**Tab Navigation** (Tab to Screen)

- [x] Landing Page
- [ ] Profile

Optional:
- [ ] Search

**Flow Navigation** (Screen to Screen)

- [x] Landing page -> Game page
- [ ] Profile page -> Login/Signup page
- [ ] Game page -> external site

## Wireframes
Here's a walkthrough of implemented of our low fidelty design:

<img src="walkthrough5.gif" width=450><br>

GIF created with [LiceCap](http://www.cockos.com/licecap/).
### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 
### Models

#### Game

| Property | Type | Description          |
|--- | --- | ---  |
|objectID   |Number  |ID of the game in IGDB |
|genre      |String  |Genre of the game      |
|title      |String  |Title of the game      |
|age        |Number  |PEGI/ESRB rating       |
|rating     |Number  |Rating of the game     |
|description|String  |Description of the game|
|releaseDate|DateTime|Release date of game   |
|tags       |List    |list of tags of game   |
|developer  |String  |Developer of the game  |
|image      |File    |Cover of the game      |


#### User

| Property | Type | Description |
|--- | --- | --- |
|username  |String  |unique name of user         |
|profile   |File    |profile image               |
|tags      |List    |liked tags to filter results|
|bio       |String  |Short decription about them |
|liked     |List    |List of liked games         |



### Networking
#### List of network requests by screen
- **Home Feed Screen**
  -(Create/POST) Query list of popular games from IGDB
  ```
  HttpResponse<JsonNode> jsonResponse = Unirest.post("https://api.igdb.com/v4/games")
  .header("Client-ID", "Client ID")
  .header("Authorization", "Bearer access_token")
  .header("Accept", "application/json")
  .body(fields name,category,age_ratings,involved_companies,genres,checksum,rating,first_release_date,cover,summary,dlcs,artworks;sort rating desc; where rating <100; limit 20;)
  .asJson();
  ```
  -(Create/POST) Create a new like on a game
  -(Delete existing like)
- **Profile Screen**
  - (Update/PUT) Update user profile image
  - (Read/GET) Query logged in user object
  - (Create/POST) Create new description
- **News Screen**
  - (Read/GET) get news stories from IGDB API
### Existing API Endpoints
- Game data - https://api.igdb.com/v4/games
- Genre data - https://api.igdb.com/v4/genres
