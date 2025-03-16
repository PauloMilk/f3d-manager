# 🎨 f3d-manager

A simple web system for managing 3D printing filaments. Track available stock, log filament consumption, and monitor costs efficiently. Built with **Spring Boot, Thymeleaf, and Kotlin**.

## 🚀 Features
- Register and manage 3D printing filaments (brand, type, color, weight, cost).
- Track filament consumption in a **non-granular way**.
- View a list of available filaments and remaining stock.
- Simple and lightweight web-based interface.

---

## 🛠️ Tech Stack
- **Backend:** Spring Boot (Kotlin)
- **Frontend:** Thymeleaf + Bootstrap
- **Database:** PostgreSQL (or H2 for testing)
- **Build Tool:** Gradle with Kotlin DSL

---

## 📦 Installation & Setup

### 1️⃣ Clone the repository:
```bash
git clone https://github.com/PauloMilk/f3d-manager.git
cd f3d-manager

```

### 2️⃣ Configure the database:
- Update `application.yml` with your **PostgreSQL** settings or use **H2** for a quick test.

### 3️⃣ Build and run the project:
```bash
./gradlew bootRun
```

### 4️⃣ Access the application:
- Open your browser and go to:  
  `http://localhost:8080`

---

## 📸 Screenshots (Optional)
_Add some images here to showcase the UI._

---

## 📌 Roadmap (Future Enhancements)
- [ ] Implement a notification system for low stock.
- [ ] Add advanced filtering and sorting options.
- [ ] Improve UI with Tailwind or Bootstrap.
- [ ] Support for multiple users (authentication).

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