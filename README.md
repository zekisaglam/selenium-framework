# Selenium UI Automation Framework (sel‑fw)

A production‑ready Selenium WebDriver automation framework built with:

- **Java 21**, Maven
- **TestNG** + **Cucumber** (BDD)
- **Allure** reporting
- **Log4j2** logging
- **WebDriverManager** for driver binaries
- Thread‑local driver management for parallel execution
- Page Object Model with a clean utility layer

## Project Structure

```
src
 └─ main
 │   ├─ java
 │   │   └─ com.example.framework
 │   │       ├─ config          (BrowserType, ConfigReader)
 │   │       ├─ driver          (DriverFactory, DriverManager)
 │   │       ├─ pages           (BasePage, LoginPage, DashboardPage, …)
 │   │       ├─ listeners       (TestListener)
 │   │       ├─ utilities       (WaitUtility, ScreenshotUtility, PropertyUtility, …)
 │   │       └─ tests           (BaseTest)
 │   └─ resources
 │       ├─ config.properties
 │       └─ allure.properties
 └─ test
     ├─ java
     │   └─ com.example.framework
     │       ├─ hooks           (Hooks)
     │       ├─ stepdefinitions (LoginSteps, …)
     │       └─ runners         (CucumberTestNGRunner)
     └─ resources
         ├─ features               (login.feature, …)
         └─ testdata               (loginData.json, …)
```

## Getting Started

1. **Clone** the repository.
2. **Ensure Java 21** is installed.
3. Run the test suite:

   ```bash
   mvn clean verify
   ```

   TestNG will execute Cucumber scenarios defined under `src/test/resources/features`.

4. **Allure Report**

   After a successful run, generate and open the report:

   ```bash
   mvn allure:serve
   ```

## Configuration

All configurable values live in `src/main/resources/config.properties`:

| Property          | Description                               |
|-------------------|-------------------------------------------|
| `browser`         | chrome / firefox / edge / headless_chrome |
| `url`             | Base URL for the application               |
| `username`        | Default user name (optional)               |
| `password`        | Default password (optional)                |
| `timeout`         | Implicit wait timeout (seconds)            |
| `headless`        | Run browsers in headless mode              |
| `environment`     | dev / qa / prod etc.                       |
| `parallel`        | Enable/disable parallel execution          |
| `retryCount`      | Number of retries for flaky tests          |
| `windowSize`      | Width,Height for the browser window         |
| `executionMode`   | local / remote etc.                        |

## Extending the Framework

- **Add new pages** by extending `BasePage` and defining locators & actions.
- **Create step definitions** in the `stepdefinitions` package; keep them thin and delegate to page objects.
- **Utilities**: add reusable helpers in `utilities` (e.g., JSONUtility, ExcelUtility, Faker data generators).
- **Listeners**: customize logging or reporting by extending `TestListener`.

## CI/CD

The repository includes a GitHub Actions workflow (`.github/workflows/ci.yml`) that:

1. Checks out the code.
2. Sets up JDK 21.
3. Caches Maven dependencies.
4. Runs `mvn clean verify`.
5. Generates and uploads Allure results and report as artifacts.

## Parallel Execution

Parallelism is driven by TestNG configuration (`testng.xml`) and the `parallel` flag in `config.properties`. `DriverManager` provides a `ThreadLocal<WebDriver>` guaranteeing each test thread works with its own driver instance.

---

*Designed and implemented by a Senior QA Automation Architect – adhering to SOLID, Clean Code, DRY, and KISS principles.*