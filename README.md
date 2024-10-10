# BOOT COUNTER APP

Requirements:
https://docs.google.com/document/d/1i0uFS5IcYaZIDv3rqPZRxVzdZLTOQbrJnDItvvbDZmc/edit?usp=sharing

## Application Tech Stack
- Kotlin
- XML
- ROOM
- COROUTINES

## Decomposition & Estimation

**Total estimation = 3 hours**
 - [x] Set up base app package structure
 - [x] Implementation of base activity
 - [x] Add broadcast receiver
 - [x] Add Room library, set up DAO, Entities, etc.
 - [x] Add Room to Broadcast receiver
 - [x] Set up WorkManager to schedule notifications
 - [x] Add simple recyclerView to MainActivity to show boots history
 - [] Implement DI
 - [] Implement ViewModel, UseCases, Repository to work with Room data
 - [] Move strings and dp to resources (make string res, make dimens res)
 - [] Improve DateUtils
 - [] Avoid using DBO, make ui model, create mapper and map inside repository
 - [] Update UI
