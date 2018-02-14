# Awok Junior

In this project I have tried to make the clone of the main page of awok application. The sailent features of this project are as follow:

- Flash Sale Products
- Main Products with pagination 

# Libraries Used

- **ButterKnife** - For the binding of view UI components with activities
- **Retrofit** - For the network calls
- **RxJava** - For making 2 Async Retrofit calls at the same time and combining their responses
- **Picasso** - For rendering images

# Project Structure

- com.myown.juniorawok.activities - Contains all the activities.
- com.myown.juniorawok.adapters - Contails all the adapter. In this project only the Recycler Adapters.
- com.myown.juniorawok.adapters.viewholders - Contains all views holder of the adapters.
- com.myown.juniorawok.models - Contains the model classes of the entities in the project.
- com.myown.juniorawok.network - Contains everything about network implementation.
- com.myown.juniorawok.utils - Contains all helpers supporting code.

# MVP

The MPV pattren is used to develop this application. In each activity package you can find a presentaion layer for that particular activity. All of the application logic and functionality about any particular activity is implemented in it's respective presenter. So, essentially I tried to seperate my application logic from my views. If there is any change in future or some additional feature needs to be added then we shall be sure about where exactly the change is going to happen in the code.

Using MVP pattren makes this application highly extensible and maintainable.

## Authors

* **Abdullah Nasim**

