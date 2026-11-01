# Shoulder Pets

A **client-side cosmetic mod** for Minecraft **1.21.11** (Fabric) that lets you wear a small animated pet on your shoulder.

> Only you can see your pet. The mod does not need to be installed on the server and does not change gameplay.

**Current version: 1.0.1**

## Features

- **Pets**
  - **Fox** (red, snow)
  - **Cat** (black, tuxedo, white, orange, gray, siamese, calico, cream)
  - **Mini Warden**
  - **Ender Dragon**
  - **Ice Dragon** (white and icy blue)
- **Settings screen** with a live preview of your own character wearing the pet.
- **Shoulder choice**: left, right, or both.
- **Classic / Slim arms**: detected automatically, or set by hand if the pet sits wrong.
- **Size and position sliders**: make the pet bigger or smaller and move it sideways, up/down, and forward/back.
- **Animations**: blinking, tail wagging, ear twitching, and a pulsing glowing heart on the Warden.
- **Dragon poses**: sitting with a wagging tail, or hovering above your shoulder while flapping its wings.
- Pets follow your crouching pose.
- Your choices are saved between sessions.

## Requirements

- Minecraft **1.21.11**
- [Fabric Loader](https://fabricmc.net/use/installer/) 0.18.1 or newer
- [Fabric API](https://modrinth.com/mod/fabric-api) for 1.21.11
- Java 21

## Installation

1. Install Fabric Loader for Minecraft 1.21.11.
2. Download `shoulderpets-1.0.1.jar` from the [Releases](../../releases) page.
3. Put the jar and Fabric API into your `mods` folder:
   - Windows: `%appdata%\.minecraft\mods`
   - macOS: `~/Library/Application Support/minecraft/mods`
   - Linux: `~/.minecraft/mods`
4. If you have an older version of Shoulder Pets in that folder, delete it.
5. Start the game with the Fabric profile.

## Usage

Press **`H`** in game to open the settings screen. You can rebind this key in **Options > Controls > Key Binds > Shoulder Pets**.

| Setting   | What it does                                          |
|-----------|-------------------------------------------------------|
| Pet       | Choose the pet type                                   |
| Color     | Choose the color or variant of the pet                |
| Shoulder  | Left, right, or both shoulders                        |
| Arms      | Auto, Classic, or Slim                                |
| Pose      | Sitting or flying (dragons only)                      |
| Pet       | Turn the pet on or off                                |
| Size      | 40% to 200%                                           |
| Side      | Move the pet away from or toward your body            |
| Height    | Move the pet up or down                               |
| Front/Back| Move the pet forward or backward                      |
| Animations| Turn animations on or off                             |

Move the mouse inside the settings screen to turn your character and check the pet from different angles.

## Configuration

Settings are saved in:

```
config/shoulderpets.json
```

```json
{
  "enabled": true,
  "type": "FOX",
  "variant": 0,
  "shoulder": "LEFT",
  "arms": "AUTO",
  "flying": false,
  "animations": true,
  "size": 1.0,
  "offsetX": 0.0,
  "offsetY": 0.0,
  "offsetZ": 0.0
}
```

- `type`: `FOX`, `CAT`, `WARDEN`, `ENDER_DRAGON`, or `ICE_DRAGON`
- `variant`: color index for the chosen pet (starts at 0)
- `shoulder`: `LEFT`, `RIGHT`, or `BOTH`
- `arms`: `AUTO`, `CLASSIC`, or `SLIM`
- `flying`: dragons only
- `size`: multiplier from 0.4 to 2.0
- `offsetX`, `offsetY`, `offsetZ`: position offsets in pixels

You normally do not need to edit this file by hand. Use the settings screen instead.

## Building from source

Requirements: JDK 21.

```
git clone https://github.com/imohammad1213m/shoulderpets.git
cd shoulderpets
./gradlew build
```

On Windows use `.\gradlew build`. The jar is created in `build/libs/`. Use the file **without** the `-sources` suffix.

## Known limitations

- Client-side only: other players cannot see your pet.
- The pet is not shown in first person view.
- Pet models are simple blocky models made for this mod, not the vanilla mob models.
- Rendering code depends on Minecraft internals that change often, so other Minecraft versions may need changes (see `PetLayer.java`).

## Ideas for later

- More pets and more colors
- Syncing pets so other players can see them
- More dragon animations

## Contributing

Bug reports and pull requests are welcome. Please include your Minecraft version, Fabric Loader version, and the relevant part of `logs/latest.log` when reporting a crash.

## License

MIT. See the `LICENSE` file.

## Disclaimer

This is an unofficial fan project. It is not affiliated with or endorsed by Mojang or Microsoft. Minecraft is a trademark of Mojang Synergies AB.

---

<div dir="rtl">

## توضیح فارسی

**Shoulder Pets** یه مود کاسمتیک و فقط سمت کلاینت برای ماینکرافت **1.21.11** (فابریک) هست که بهت اجازه می‌ده یه پت کوچولوی انیمیشن‌دار روی شونه‌ات داشته باشی.

پت‌ها: روباه، گربه (چند رنگ)، واردن کوچولو، اندر دراگون و دراگون یخی.

- کلید `H` صفحه‌ی تنظیمات رو باز می‌کنه و کاراکتر خودت رو همراه پت نشون می‌ده.
- می‌تونی شونه، رنگ، سایز و جای پت رو تنظیم کنی.
- با اسکین Classic و Slim هر دو کار می‌کنه.
- دراگون‌ها دو حالت دارن: نشسته یا در حال پرواز بالای شونه.
- فقط خودت پت رو می‌بینی و روی سرور تغییری ایجاد نمی‌کنه.

**نصب:** فابریک و Fabric API رو نصب کن و فایل jar مود رو بذار توی پوشه‌ی `mods`.

</div>
