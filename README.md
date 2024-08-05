# Plann.er - Travel Itinerary Application

[![NPM License](https://img.shields.io/npm/l/react)](https://github.com/kauahaymon/planner-springboot/blob/main/LICENSE)

## About the Project

This project is a fully back-end application designed to help users organize their travels for work, holidays, or weekend leisure.

Users can create trips by inserting the name, start and end dates. Once a trip is created, users can plan all activities they wish to do each day during the travel. Additionally, users can add important links to avoid forgetting anything.

This project was developed during the NLW Journey week from RocketSeat. The base is done, but I am personally adding new features and learning with it, including effective exception handling, validation, and authentication.

## Features

<details>
  <summary>Functional Requirements</summary>
  <ol>
    <li>The user registers a trip by informing the destination location, start date, end date, guests' emails, and their full name and email address.</li>
    <li>The trip creator receives an email to confirm the new trip via a link. Upon clicking the link, the trip is confirmed, guests receive attendance confirmation emails, and the creator is redirected to the trip page.</li>
    <li>Guests, upon clicking the presence confirmation link, are redirected to the application where they must enter their name (in addition to the pre-filled email) and will then be confirmed on the trip.</li>
    <li>On the event page, trip participants can add important travel links such as AirBnB reservations, places to visit, etc.</li>
    <li>On the event page, the creator and guests can add activities that will occur during the trip with title, date, and location.</li>
    <li>New participants can be invited within the event page via email and must go through the encryption flow like any other guest.</li>
  </ol>
</details>

## Technologies & Frameworks

- **Framework:** Spring Boot
- **Language:** Java
- **ORM:** JPA / Hibernate

## Database Management

- **Test Database:** H2
- **Database Migration:** Flyway

## Development Tools

- **Build Tool:** Maven
- **Data Transfer:** Java Records
- **Code Simplification:** Lombok

## Conceptual Model

![Conceptual Model](https://github.com/kauahaymon/illustrative-images/blob/master/planner-model.svg)

## Database Diagram

![Database Diagram](https://github.com/kauahaymon/illustrative-images/blob/master/planner-dbdiagram.svg)

## Trip Entity

**Body of a request**

```json
{
  "destination": {
    "type": "string",
    "minLength": 4,
    "description": "The trip destination."
  },
  "starts_at": {
    "type": "string",
    "format": "date-time",
    "description": "The start date and time of the trip in RFC 3339 format."
  },
  "ends_at": {
    "type": "string",
    "format": "date-time",
    "description": "The end date and time of the trip in RFC 3339 format."
  },
  "emails_to_invite": {
    "type": "array",
    "items": {
      "type": "string",
      "format": "email"
    },
    "description": "A list of emails to invite to the trip."
  },
  "owner_name": {
    "type": "string",
    "description": "The name of the trip owner."
  },
  "owner_email": {
    "type": "string",
    "format": "email",
    "description": "The email of the trip owner."
  }
}
```
## Figma
Access the [**Figma link**](https://www.figma.com/community/file/1392277205162897872/nlw-journey-roteiro-de-viagem) here!

## Author

- [**Kauã Haymon**](https://www.linkedin.com/in/kauahaymon/) on LinkedIn.

## Thanks to

- [**RocketSeat**](https://app.rocketseat.com.br/)
