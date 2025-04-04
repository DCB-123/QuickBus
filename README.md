# QuickBusReserve – Full Stack Application

A full-stack "Bus Reservation System" developed using "React" for the frontend and **Spring Boot** for the backend. This system provides secure and efficient functionality for booking bus tickets, managing routes, and user access through role-based authentication.

---

## 🔹 Features

- JWT-based user authentication and authorization
- Role-based access control (Admin, User)
- Browse and book buses
- Manage journeys and seat reservations
- Admin dashboard for managing buses, stops, and bookings
- Toast notifications for real-time feedback

---

## ⚙️ Tech Stack

### Frontend:
- React 18
- Axios
- React Router DOM
- React Toastify

### Backend:
- Spring Boot
- Spring Security with JWT
- JPA/Hibernate
- MySQL
- RESTful APIs

---

## 📂 Project Structure

### Frontend
```
bus-reservation-system-frontend/
├── public/
├── src/
│   ├── components/
│   ├── pages/
│   ├── services/
│   ├── App.js
│   └── index.js
└── package.json
```

### Backend
```
bus-reservation-system-backend/
├── src/main/java/com/yourcompany/busreservation/
│   ├── controller/
│   ├── entity/
│   ├── repository/
│   ├── service/
│   ├── security/
│   └── BusReservationApp.java
├── src/main/resources/application.properties
└── pom.xml
```

---

## 🚀 Getting Started

### 1. Clone the repository
```bash
git clone https://github.com/your-username/bus-reservation-system.git
cd bus-reservation-system
```

---

### 2. Backend Setup
Navigate to the backend directory and configure:

#### Prerequisites:
- Java 17+
- Maven
- MySQL

#### application.properties
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bus_reservation
spring.datasource.username=root
spring.datasource.password=yourpassword

jwt.secret=your_jwt_secret_key
jwt.expirationMs=3600000
```

#### Run the server
```bash
mvn spring-boot:run
```
Backend will run on `http://localhost:8080`

---

### 3. Frontend Setup
Navigate to the frontend directory:
```bash
npm install
npm start
```
Frontend will run on `http://localhost:3000`

Ensure Axios is set to your backend API URL:
```javascript
axios.defaults.baseURL = "http://localhost:8080/api";
```

---

## 🔐 Authentication
- Uses JWT tokens for securing APIs
- Tokens stored in localStorage
- Authenticated requests managed by Axios interceptors
- Separate roles for Admin and Users

---

## 🔧 Testing

### Backend:
```bash
mvn test
```

### Frontend:
```bash
npm test
```
## 📝 License
This project is licensed under the MIT License.

---

## 🙌 Contributing
Contributions are welcome! Feel free to submit a pull request or open an issue for suggestions and bugs.

