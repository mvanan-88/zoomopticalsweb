# ZoomOpticals

A responsive eyewear storefront starter built with Kotlin/Wasm and Compose Multiplatform. It follows the attached editorial reference with a warm neutral palette, frame collections, brand story, reviews, and footer. Frame drawings and sample product data are native Compose components, ready to be replaced with brand photography and catalog data.

## Open and run in IntelliJ IDEA

1. Open this project folder in IntelliJ IDEA and import it as a Gradle project.
2. In **Settings → Build, Execution, Deployment → Build Tools → Gradle**, set **Gradle JVM** to JDK 17 or 21.
3. Run the Gradle task `:composeApp:wasmJsBrowserDevelopmentRun` from the Gradle tool window or the project terminal.
4. Open the localhost URL printed by Gradle (usually `http://localhost:8080`). Use a recent Chrome/Chromium or Firefox browser with WebAssembly GC support.

To create the production website files, run `:composeApp:wasmJsBrowserDistribution`. The output is in `composeApp/build/dist/wasmJs/productionExecutable` and can be deployed to a static web host.

## Project structure

- `composeApp/` — Kotlin/Wasm storefront.
- `backend/` — notes for a future Kotlin server module.
- `admin/` — notes for a future admin interface.

The storefront currently uses sample data and has no backend integration, checkout, or authentication. Add backend and admin Gradle modules when you are ready to build those features.
