NurWebShop

Enkel Webshop Backend med Java Spring Boot

Detta projekt är en enkel webbshop-backend som skapats som en skoluppgift. Den hanterar produkter och kundbeställningar med hjälp av REST API, lagrar data i minnet med Java Collections, och innehåller validering, felhantering och enhetstester.

 Tekniker och Ramverk

Java 17

Spring Boot 3.5.0

Maven

JUnit 5 & Mockito (för tester)

In-memory datalagring

🚀 Hur man kör applikationen

Klona projektet:

git clone (https://github.com/nurhussein2024/nurwebshop.git)
cd nurwebshop

Bygg och kör:

mvn clean install
mvn spring-boot:run

Backend körs nu på:

http://localhost:8080

 API-endpoints

 Produkter /api/products

Metod

URL

Beskrivning

GET

/api/products

Hämta alla produkter

GET

/api/products/{id}

Hämta produkt med ID

 Beställningar /api/orders

Metod

URL

Beskrivning

POST

/api/orders

Skapa en ny beställning

Exempel på JSON för en ny beställning:

{
  "customerInfo": {
    "name": "Nurhussein",
    "email": "nurhussein2003@ahoo.com",
    "address": "Stockholm"
  },
  "items": [
    {
      "productId": 1,
      "quantity": 2
    },
    {
      "productId": 2,
      "quantity": 1
    }
  ]
}

📁 Projektstruktur

NurWebShop
├── model/             → Domänklasser (Product, Order, CustomerInfo, OrderItem)
├── controller/        → REST-kontrollers
├── service/           → Tjänstelager (affärslogik)
├── repository/        → In-memory datalagring
├── exception/         → Egna undantag och global hantering
├── test/              → Enhetstester (JUnit + Mockito)
└── NurWebShopApplication.java → Main-klass

✅ Testning

Kör testerna med:

mvn test

Testade tjänster:

ProductServiceTest

OrderServiceTest

OrderControllerTest

 Möjlig vidareutveckling

Anslut till en riktig databas (t.ex. MySQL eller MongoDB)

Lägg till användarautentisering (Spring Security)

Lägg till frontend med React eller Angular

Internationellt språkstöd (i18n)

