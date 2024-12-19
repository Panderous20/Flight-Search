# Flight-Search  written in Kotlin
- lifecycle_version = "2.8.7"
- room_version = "2.6.1"
- coreKtx = "1.13.0"
# What I Use in This App
- Room: Room is a persistence library that provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
  + In this app, I use Room to modify and access the database from the flight_search.db file.
  + This database file contains all information about airports and saves your favorite trips.
- DataStore: DataStore is a data storage solution that allows you to store key-value pairs or typed objects using protocol buffers.
  + In this project, I use Preferences DataStore to store the queries users write in the search field.
  + This query will be automatically pre-filled when the app restarts.
- Coroutines: Coroutines are a concurrency design pattern in Android that simplifies code for asynchronous execution.
  + This is crucial because database operations cannot run on the main thread.
  + To improve performance, I use Coroutines along with Room to handle database operations in a separate thread.
- MVVM Architecture: Model-View-ViewModel (MVVM) is an architectural pattern that separates the development of the graphical user interface (View) from the business logic (Model), ensuring the View does not depend on the Model's platform.
  + The ViewModel helps organize the app better and manage its lifecycle effectively.
  + This architecture also prevents unexpected behavior when users rotate their devices.
- Dependency Injection: Dependency Injection is a design pattern that allows creating dependencies outside a class. It makes dependencies explicit, simplifies testing, and improves code flexibility and reusability.
  + I use Dependency Injection to test if the ViewModel works correctly.
  + It also makes classes less dependent on each other.
