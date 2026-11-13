# BG voz – red vožnje (Android)

Nezvanična Android aplikacija sa redom vožnje BG:VOZ (važi 09.09.–12.12.2026.), sa AdMob banerom na dnu ekrana.

- Pretraga polazaka između dve stanice, sa odbrojavanjem do polaska
- Svi polasci sa jedne stanice
- Radni dan / vikend / državni praznik se prepoznaju automatski
- Omiljene relacije
- Radi bez interneta (osim reklame)

## Preuzimanje APK-a

Svaka izmena na `main` grani automatski pravi APK (GitHub Actions).
Najnoviji APK: **Releases → Najnovija verzija → `bg-voz.apk`**.

## AdMob

U `app/src/main/res/values/admob.xml` su trenutno **Google test ID-jevi** (prikazuju probnu reklamu).
Pre objave na Google Play zameni ih svojim:

1. Na <https://admob.google.com> dodaj aplikaciju (Android).
2. Kopiraj **App ID** (`ca-app-pub-…~…`) u `admob_app_id`.
3. Napravi **Ad unit → Banner** i kopiraj ID (`ca-app-pub-…/…`) u `admob_banner_id`.
4. Commit → GitHub sam napravi novi APK.

Ne klikći svoje prave reklame dok testiraš – AdMob to kažnjava.

## Struktura

| Putanja | Šta je |
|---|---|
| `app/src/main/assets/index.html` | Cela aplikacija (HTML/CSS/JS + podaci) |
| `app/src/main/java/.../MainActivity.kt` | WebView + AdMob baner |
| `tools/template.html`, `tools/trips.json` | Izvor: šablon i podaci o vozovima |
| `tools/build_html.py` | Spaja šablon i podatke u `index.html` |

Novi red vožnje: izmeni `tools/trips.json`, pokreni `python3 tools/build_html.py`, commit.

## Napomena

Podaci su prepisani iz PDF reda vožnje BG:VOZ 2025/2026. Aplikacija nije zvanična aplikacija Srbija Voza.
