# 🎨 f3d-manager

A **web-based system** for managing **3D printing filaments**, built with **Spring Boot, Thymeleaf, and Kotlin**.  
Easily **track available stock, log filament consumption**, and **monitor costs** with a simple and lightweight interface.

---

## 🚀 Features
✅ Register and manage 3D printing filaments (brand, type, color, weight, cost).  
✅ Track filament consumption in a **non-granular way**.  
✅ Record **filament purchases** to update stock dynamically.  
✅ View a list of available filaments and **remaining stock**.  
✅ Manage **brands, filament types, and colors**.  
✅ **Multi-language support (English 🇺🇸 | Portuguese 🇧🇷)**.  
✅ Simple and intuitive **Bootstrap-based UI**.

---

## 🏗 Tech Stack
- **Backend:** Spring Boot (Kotlin)
- **Frontend:** Thymeleaf + Bootstrap
- **Database:** PostgreSQL (or H2 for testing)
- **Build Tool:** Gradle with Kotlin DSL
- **Containerization:** Docker & Docker Compose
- **Internationalization:** Spring Boot i18n (English 🇺🇸 | Portuguese 🇧🇷)

---

## 📦 Installation & Setup

### 1️⃣ Clone the repository:
```bash
git clone https://github.com/PauloMilk/f3d-manager.git
cd f3d-manager
```

### 2️⃣ Run the database using Docker Compose:
```bash
docker-compose up -d
```
This will start a **PostgreSQL** container along with **pgAdmin** for database management.
- PostgreSQL will be available at `localhost:5432`
- pgAdmin can be accessed at [http://localhost:5050](http://localhost:5050)
  - **Email:** admin@admin.com
  - **Password:** admin

### 3️⃣ Configure the database in `application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/f3d_manager
    username: admin
    password: admin
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        format_sql: true
        show_sql: true
```

### 4️⃣ Build and run the project:
```bash
./gradlew bootRun
```

### 5️⃣ Access the application:
- Open your browser and go to:  
  `http://localhost:8080`

---

## 🌍 Multi-Language Support
F3D Manager supports **multiple languages** (English 🇺🇸 | Portuguese 🇧🇷).  
The language can be changed dynamically via the **dropdown** at the top of the page.

---

## 📸 Screenshots (Optional)
_Add some images here to showcase the UI._

---

## 📌 Roadmap (Future Enhancements)
- [ ] Implement a notification system for **low stock alerts**.
- [ ] Add **filtering and sorting** options for filaments.
- [ ] Enhance UI with **Tailwind or Bootstrap improvements**.
- [ ] Support for **multiple users (authentication & roles)**.

---

## 🤝 Contributing
Feel free to contribute! Fork the repo, create a branch, and submit a PR.

1. Fork the project
2. Create your feature branch: `git checkout -b feature/new-feature`
3. Commit your changes: `git commit -m "Add new feature"`
4. Push to the branch: `git push origin feature/new-feature`
5. Open a pull request

---

## 📜 License
This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for details.

---

## 💌 Contact
- **Author:** Paulo Costa
- **GitHub:** [github.com/PauloMilk](https://github.com/PauloMilk)  
