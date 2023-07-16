# Travel Agency
Travel Agency is a web application that provides various trips to users. It offers features such as checking available flights, searching for hotels in a city or specific location, and making reservations. Additionally, the app includes a newsletter feature to notify users about newly added trips.
### Basic flow of an app:
* Registration: Users can create an account to access the app's functionalities.
* Trip Selection: Users can browse and select their desired trip from the available options.
* Flight Selection: Users can check and choose from the available flights for their selected trip.
* Hotel Search: Users can search for hotels either in a specific city or based on a particular location.
* Hotel Selection: Users can choose a hotel from the available options in their desired place.
* Reservation: Users can make a reservation for their selected trip, including flights and hotel.

### Requirements
* JVM: Java Virtual Machine should be installed.
* Docker: Docker software should be installed to run the application in containers.
* MySQL: A MySQL database should be available for the app to store and retrieve data.
### Launching the Project
To launch the project, follow these steps:

* create database `travel-agency` in MySQL
* Run `./gradlew build -x test` - which build our app without tests
* `docker compose up ` which create a docker container of our app using our `Dockerfile` with database in MySQl

### Mappings:
### Authentication Controller
* Description: Handles authentication of user.
* Base Path: `/jwt/login`

This controller is responsible for authentication of user, and providing a JWT token
**Endpoint Descriptions:**

* **Method: POST**
  * **Path: `/jwt/login`**
  * Description: Authenticate a user.
  * Request Body: AuthenticationRequest (data transfer object containing user/login details)
  * Example: `POST /jwt/login`

### Booked Trip Controller
* Description: Handles booking, retrieval, deletion, and updating of booked trips.
* Base Path: `/book`

This controller is responsible for managing booked trips and provides endpoints for booking a trip, retrieving booked trips, deleting a booked trip, and updating a booked trip.

**Endpoint Descriptions:**

* **Method: POST**
    * **Path: `/book/{userId}`**
    * Description: Books a trip for the specified user.
    * Request Body: BookingRequest (data transfer object containing booking details)
    * Path Variable: userId (ID of the user)
    * Example: `POST /book/123`

* **Method: GET**
    * **Path: `/book/{userId}`**
    * Description: Retrieves all booked trips for the specified user.
    * Path Variable: userId (ID of the user)
    * Example: `GET /book/123`
    * Response Body: List of BookedTripDto (data transfer objects containing booked trip details)

* **Method: DELETE**
    * **Path: `/book/{bookedTripId}`**
    * Description: Deletes a specific booked trip.
    * Path Variable: bookedTripId (ID of the booked trip)
    * Example: `DELETE /book/456`

* **Method: PUT**
    * **Path: `/book/{tripId}/{userId}`**
    * Description: Updates a specific booked trip.
    * Request Body: BookingRequest (data transfer object containing updated booking details)
    * Path Variable: tripId (ID of the trip), userId (ID of the user)
    * Example: `PUT /book/789`
### Booking Hotel Controller
* Description: Handles hotel booking and retrieval of available hotels.
* Base Path: `/hotels`

This controller is responsible for managing hotel bookings and provides endpoints for retrieving available hotels in a city and retrieving hotels by coordinates.

**Endpoint Descriptions:**

* **Method: GET**
    * **Path: `/hotels/{destination}`**
    * Description: Retrieves available hotels in the specified destination city.
    * Path Variable: destination (name of the destination city)
    * Example: `GET /hotels/Paris`
    * Response Body: List of BookingAvailableHotelsInCity (data transfer objects containing available hotel information)

* **Method: POST**
    * **Path: `/hotels`**
    * Description: Retrieves hotels based on the provided coordinates.
    * Request Body: BookingHotelRequest (data transfer object containing booking details)
    * Example: `POST /hotels`
    * Response Body: HotelInfo (data transfer object containing hotel information)
    * 
### NewsLetter Controller
* Description: Handles newsletter creation, retrieval, and subscription management.
* Base Path: `/newsLetter`

This controller is responsible for managing newsletters and provides endpoints for creating a newsletter, retrieving all newsletters, subscribing to a newsletter, and unsubscribing from a newsletter.

**Endpoint Descriptions:**

* **Method: POST**
    * **Path: `/newsLetter`**
    * Description: Creates a new newsletter.
    * Request Body: NewsLetter (data transfer object containing news letter details)
    * Example: `POST /newsLetter`

* **Method: GET**
    * **Path: `/newsLetter`**
    * Description: Retrieves all newsletters.
    * Example: `GET /newsLetter`
    * Response Body: List of NewsLetterDto (data transfer objects containing newsletter details)

* **Method: POST**
    * **Path: `/newsLetter/{newsletterId}`**
    * Description: Subscribes a subscriber to the specified newsletter.
    * Path Variable: newsletterId (ID of the newsletter)
    * Request Body: Subscriber (data transfer object containing subscriber details)
    * Example: `POST /newsLetter/123`

* **Method: DELETE**
    * **Path: `/newsLetter/{newsletterId}`**
    * Description: Unsubscribes a subscriber from the specified newsletter.
    * Path Variable: newsletterId (ID of the newsletter)
    * Request Body: Subscriber (data transfer object containing subscriber details)
    * Example: `DELETE /newsLetter/123`
### Subscriber Controller
* Description: Handles retrieval of subscribers.
* Base Path: `/subscribers`

**Endpoint Description:**

* **Method: GET**
    * **Path: `/subscribers`**
    * Description: Retrieves all subscribers.
    * Example: `GET /subscribers`
    * Response Body: List of SubscriberDto (data transfer objects containing subscriber details)
### Register Controller
* Description: Handles user registration.
* Base Path: `/register`

This controller is responsible for managing user registration and provides an endpoint for registering a new user.

**Endpoint Description:**

* **Method: POST**
    * **Path: `/register`**
    * Description: Registers a new user.
    * Request Body: RegisterRequest (data transfer object containing user registration details)
    * Example: `POST /register`
    * Response: ResponseEntity
        - Success: 200 OK
        - Failure: Throws EmptyFieldsException with a message "All fields must be filled"
### Trip Controller
* Description: Handles trip management and retrieval.
* Base Path: `/trips`

This controller is responsible for managing trips and provides endpoints for adding a trip, retrieving a list of trips, and retrieving trip information.

**Endpoint Descriptions:**

* **Method: POST**
    * **Path: `/trips`**
    * Description: Adds a new trip.
    * Request Body: Trip (data transfer object containing trip details)
    * Example: `POST /trips`

* **Method: GET**
    * **Path: `/trips`**
    * Description: Retrieves a list of all trips.
    * Example: `GET /trips`
    * Response Body: List of TripDto (data transfer objects containing trip details)

* **Method: GET**
    * **Path: `/trips/{tripId}/{userId}`**
    * Description: Retrieves detailed information about a specific trip available for a user.
    * Path Variable: tripId (ID of the trip), userId (ID of the user)
    * Example: `GET /trips/123/123`
    * Response Body: TripInfo (data transfer object containing trip information)
### User Controller
* Description: Handles user management and retrieval.
* Base Path: `/users`

This controller is responsible for managing users and provides endpoints for retrieving all users, retrieving a specific user, deleting a user, and updating a user.

**Endpoint Descriptions:**

* **Method: GET**
    * **Path: `/users`**
    * Description: Retrieves a list of all users.
    * Example: `GET /users`
    * Response Body: List of UserDto (data transfer objects containing user details)

* **Method: GET**
    * **Path: `/users/{userId}`**
    * Description: Retrieves information about a specific user.
    * Path Variable: userId (ID of the user)
    * Example: `GET /users/123`
    * Response Body: UserDto (data transfer object containing user details)

* **Method: DELETE**
    * **Path: `/users/{userId}`**
    * Description: Deletes a specific user.
    * Path Variable: userId (ID of the user)
    * Example: `DELETE /users/123`

* **Method: PUT**
    * **Path: `/users/{userId}`**
    * Description: Updates information for a specific user.
    * Path Variable: userId (ID of the user)
    * Request Body: UpdateUserRequest (data transfer object containing updated user details)
    * Example: `PUT /users/123`
    * Response Body: UserDto (data transfer object containing updated user details)




