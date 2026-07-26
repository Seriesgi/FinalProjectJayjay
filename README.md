# Automation Test Framework

Satu repository untuk automation test API dan Web UI menggunakan Java, Gradle, Cucumber, Rest Assured, dan Selenium.

## Target test

* Web UI: [Demoblaze](https://www.demoblaze.com/). Skenario `@web` melakukan alur end-to-end: membuka produk, menambahkannya ke cart, lalu checkout sebagai guest.
* API: [DummyAPI](https://dummyapi.io/docs). Skenario `@api` memvalidasi daftar tag serta lifecycle user (create, get by ID, update, delete).

## Stack

* Java 17 and Gradle
* Cucumber (Gherkin, JUnit Platform) dengan report HTML dan JSON
* Selenium WebDriver + WebDriverManager (Page Object Model)
* Rest Assured untuk validasi status code dan response body API
* GitHub Actions untuk trigger manual dan Pull Request

## Struktur proyek

```
src/test/java/id/seriesgi/api/          # API client dan step definitions
src/test/java/id/seriesgi/web/pages/    # Page Object Model Web UI
src/test/java/id/seriesgi/web/steps/    # Web step definitions dan hooks
src/test/resources/features/api/        # Feature API
src/test/resources/features/web/        # Feature Web UI
.github/workflows/                      # CI workflow
```

## Menjalankan test

Pastikan Java 17 tersedia. Dari root repository jalankan:

```bash
# Semua API scenario bertag @api
./gradlew apiTest

# Semua Web UI scenario bertag @web (headless secara default)
./gradlew webTest

# Menampilkan browser saat menjalankan Web UI test
./gradlew webTest -Dheadless=false
```

Untuk menggunakan app-id DummyAPI lain, tambahkan `-Ddummy.api.app.id=<APP_ID>`.

Report tersedia setelah test selesai:

* HTML: `reports/cucumber/api.html` atau `reports/cucumber/web.html`
* JSON: `reports/cucumber/api.json` atau `reports/cucumber/web.json`

## CI

Workflow **Automation Tests** berjalan melalui `workflow_dispatch` dan setiap Pull Request. Kedua job menjalankan Gradle task terpisah dan mengunggah Cucumber report sebagai artifact, termasuk saat test gagal.

## Hasil verifikasi lokal

Pada verifikasi terakhir, kedua task berhasil dijalankan:

```text
./gradlew apiTest  -> BUILD SUCCESSFUL
./gradlew webTest  -> BUILD SUCCESSFUL
```

Report hasil eksekusi lokal dibuat otomatis di `reports/cucumber/`, sehingga dapat disertakan pada repository. Di GitHub Actions, report tersebut juga dapat diunduh dari bagian **Artifacts** pada workflow run.
