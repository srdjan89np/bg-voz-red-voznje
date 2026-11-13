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

Aplikacija je povezana sa AdMob aplikacijom **BG voz - red vožnje** i oglasnom jedinicom **Baner dole**
(ID-jevi su u `app/src/main/res/values/admob.xml`).

Ne klikći svoje prave reklame dok testiraš – AdMob to kažnjava.

## Google Play

1. Dodaj 4 GitHub Secrets (`KEYSTORE_BASE64`, `KEYSTORE_PASSWORD`, `KEY_ALIAS`, `KEY_PASSWORD`) u
   *Settings → Secrets and variables → Actions*.
2. Posle sledećeg build-a, u Releases se pojavi i **`bg-voz-google-play.aab`** – to se šalje u Play Console.
3. Politika privatnosti: <https://srdjan89np.github.io/bg-voz-red-voznje/privacy.html>

Bez secrets-a build pravi običan debug APK.

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
