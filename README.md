# CRUD-Application

## Overview
A simple CRUD Android application that allows users to create, view, edit, and delete posts, featuring upvoting and downvoting functionalities.[download lastest app here](https://drive.google.com/drive/folders/1xcq2mLwPIWCT83XMmzHxPjilalwDnFKY?usp=sharing)


## Features
- **Create Post:** Form with proper validation to create a new post and store it using Room Database.
- **View Post:** Display post details with options for editing and deleting.
- **Edit Post:** Edit existing posts with pre-filled details and update changes in the Room Database.
- **Delete Post:** Confirmation popup before deleting a post from the Room Database.
- **Upvote/Downvote:** Ability to upvote or downvote posts, updating the like or dislike count.

## Technologies Used
- **Language:** JAVA
- **Local Database:** Room Library
- **Design Pattern:** MVVM

## App Flow

### All Posts
Displays all created posts.
- Clicking on the upvote or downvote buttons changes the counter and reorders the posts accordingly.
- <img src="https://github.com/TejasDhole/CRUD-Application/assets/98216813/24d0197e-365d-4d64-bc4f-dfa6d4b6cd68" alt="addPost" width="400"/>

### View Post
Shows detailed information of a single post.
- Provides options to edit or delete the post.
- <img src="https://github.com/TejasDhole/CRUD-Application/assets/98216813/44a121fc-82a7-4a10-af68-561dcc91119c.jpg" alt="viewPost" width="400"/>


### Edit Page
Edit existing posts with pre-filled details.
- Save changes in the Room Database and redirect to the All Posts page upon success.
- <img src="https://github.com/TejasDhole/CRUD-Application/assets/98216813/8b9af30c-7d53-48e1-82ab-c0eebe53d5c9.png" alt="editPost" width="400"/>

### Create Post
Form for creating a new post with validation.
- Saves the post in the Room Database, shows error/success messages, and redirects to the All Posts page on success.
- <img src="https://github.com/TejasDhole/CRUD-Application/assets/98216813/24d0197e-365d-4d64-bc4f-dfa6d4b6cd68.png" alt="addPost" width="400"/>


### Delete Confirmation
Confirmation popup before deleting a post.
- On confirmation, deletes the post from the Room Database and redirects to the All Posts page.
- Cancel option closes the popup window.
- <img src="https://github.com/TejasDhole/CRUD-Application/assets/98216813/6a954aa0-8eb4-49d2-ac78-aaa408182ab5.png" alt="deletePost" width="400"/>
