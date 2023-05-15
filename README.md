# Instagram_API 👋

## Frameworks and Language used:
 - Spring
 - Spring boot
 - My-Sql
 - Java


 ## Data Flow
 #### Controller
 ---UsersController----
 - signUp
 - signIn
 - updateUser
 
 ---PostsControllers----
 - getAllPosts
 - postData

 #### Service
 ---AUthenticationService----
 - saveToken
 - authenticate

 ---PostsService----
 - postUserData
 - getAllUserPosts

 #### Repository
 ---Users----

 IUsersRepository

 ---Posts----

 IPostsRepository

 ---Authentication----

 IAuthenticationRepository

 #### Model
 ---Users----
 - userId
 - firstName
 - lastName
 - age
 - email
 - password
 - phoneNumber
 
 ---Posts----
 - postId
 - createdDate
 - updatedDate
 - postDate
 - users
 
 #### DTO
 ---SignUpInput----
 - userFIrstname
 - userLastName
 - userEmail
 - userAge
 - userPassword
 - userContact

 ---SignUpOutput----
 - status
 - message

 ---SignInInput
 - userEmail
 - userPassword

 ---SignInOutput
 - status
 - message
 
### Project Summary
Created a simple Instagram project to autehntication user using sign up & sign in also provide functionality for a user to posts data.Implementing many to one mapping.

### How to use in your system?
 - Just Simply clone this reposit
 - Start your server
 - Perform operations 
