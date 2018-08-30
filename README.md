# Coolpay API

Deployed to AWS: http://18.219.168.192:8080/CoolpayREST/

Coolpay is a new company that allows to easily send money to friends through their API.

You work for Fakebook, a successful social network. You’ve been tasked to integrate Coolpay inside Fakebook. A/B tests show that users prefer to receive money than pokes!

You can find Coolpay documentation here: http://docs.coolpayapi.apiary.io/

The app can do the following:

- Authenticate to Coolpay API
- Add recipients
- Send them money
- Check whether a payment was successful

### Technologies Used
- Java
- [Coolpay API](https://coolpayapi.docs.apiary.io/)
- Gradle
- Hibernate
- Angular
- JUnit
- Bootstrap

### Stretch Goals
- Implement a database to store recipients and payments.
- Fix send money as currently works in back-end but not in front-end.
- Create more tests.
- Store hardcoded call links in application.properties. I had to time-box debugging @Value not working.

### License
This program is licensed under the MIT license.
Copyright (c) 2018 Wilson Lou.
