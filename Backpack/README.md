# Backpack Plugin

A Minecraft Paper plugin that allows players to have personal backpacks.

## Features
- Personal backpack inventory for each player
- Simple `/backpack` command to open
- Configurable backpack size
- Permission-based access

## Commands
- `/backpack` or `/bp` - Open your backpack

## Permissions
- `backpack.use` - Allows players to use backpacks (default: true)
- `backpack.admin` - Admin permissions (default: op)

## Building
```bash
mvn clean package
```

The compiled plugin will be in `target/Backpack-1.0.0.jar`

## Installation
1. Build the plugin or download the jar
2. Place the jar in your server's `plugins` folder
3. Restart your server
4. Configure settings in `plugins/Backpack/config.yml`
