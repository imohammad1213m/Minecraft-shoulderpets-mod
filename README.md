# Minecraft-shoulderpets-mod
A mod where you can put the pet on your shoulder
# Shoulder Pets

A small **client-side cosmetic mod** for Minecraft **1.21.11** (Fabric) that lets you wear a tiny pet on your shoulder.

> Only you can see your pet. The mod does not need to be installed on the server and does not change gameplay.

## Features

- Pets sitting on your shoulder:
  - **Fox** (red, snow)
  - **Cat** (all 11 vanilla colors, including black)
  - **Mini Warden**
- Works with both **Classic (Steve)** and **Slim (Alex)** skin models. The pet position adjusts automatically.
- Put the pet on the **left shoulder, right shoulder, or both**.
- Pet follows your crouching pose.
- All controls are normal Minecraft key binds, so you can **rebind them** in the controls menu.
- Your choices are saved between sessions.
- Uses vanilla models and textures, so no extra resource pack is needed.

## Requirements

- Minecraft **1.21.11**
- [Fabric Loader](https://fabricmc.net/use/installer/) 0.18.1 or newer
- [Fabric API](https://modrinth.com/mod/fabric-api) for 1.21.11
- Java 21

## Installation

1. Install Fabric Loader for Minecraft 1.21.11.
2. Download `shoulderpets-1.0.0.jar` from the [Releases](../../releases) page.
3. Put the jar and Fabric API into your `mods` folder:
   - Windows: `%appdata%\.minecraft\mods`
   - macOS: `~/Library/Application Support/minecraft/mods`
   - Linux: `~/.minecraft/mods`
4. Start the game with the Fabric profile.

## Controls

Default keys (change them in **Options > Controls > Key Binds > Shoulder Pets**):

| Key | Action                                |
|-----|---------------------------------------|
| `H` | Turn the pet on or off                |
| `J` | Next pet type                         |
| `K` | Next color / variant                  |
| `N` | Switch shoulder (left, right, both)   |

A short message appears above the hotbar each time you change something.

## Configuration

Settings are stored in:

```
config/shoulderpets.json
```

```json
{
  "enabled": true,
  "type": "FOX",
  "variant": 0,
  "shoulder": "LEFT"
}
```

- `type`: `FOX`, `CAT`, or `WARDEN`
- `variant`: color index for the chosen pet (starts at 0)
- `shoulder`: `LEFT`, `RIGHT`, or `BOTH`

## Known limitations

- Client-side only: other players cannot see your pet.
- The pet is not shown in first person view.
- Pets use a fixed sitting pose with idle animation from the vanilla model.
- Rendering code depends on Minecraft internals that change often, so other versions may need changes (see `PetLayer.java`).

## Ideas for later

- In-game selection screen
- More pets (wolf, parrot, and others)
- Custom models and textures
- Syncing pets so other players can see them

## Contributing

Bug reports and pull requests are welcome. Please include your Minecraft version, Fabric Loader version, and the relevant part of `logs/latest.log` when reporting a crash.

## License

MIT. See the `LICENSE` file.

## Disclaimer

This is an unofficial fan project. It is not affiliated with or endorsed by Mojang or Microsoft. Minecraft is a trademark of Mojang Synergies AB. The mod uses the game's own models and textures at runtime and does not redistribute them.

---

<div dir="rtl">

## توضیح فارسی

**Shoulder Pets** یه مود کاسمتیک و فقط سمت کلاینت برای ماینکرافت **1.21.11** (فابریک) هست که بهت اجازه می‌ده یه پت کوچولو روی شونه‌ات داشته باشی.

پت‌ها: روباه (قرمز و برفی)، گربه (همه‌ی رنگ‌ها از جمله سیاه) و واردن کوچولو.

- با اسکین Classic و Slim هر دو کار می‌کنه.
- می‌تونی پت رو روی شونه‌ی چپ، راست یا هر دو بذاری.
- کلیدها داخل تنظیمات ماینکرافت قابل تغییرن.
- فقط خودت پت رو می‌بینی و روی سرور تغییری ایجاد نمی‌کنه.

**نصب:** فابریک و Fabric API رو نصب کن و فایل jar مود رو بذار توی پوشه‌ی `mods`.

**کلیدها:** `H` روشن و خاموش، `J` پت بعدی، `K` رنگ بعدی، `N` تغییر شونه.

</div>
