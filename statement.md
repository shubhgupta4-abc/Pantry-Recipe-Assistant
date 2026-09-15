# Problem Statement

## Problem

People who cook at home regularly often lose track of exactly what ingredients they have in stock. This leads to two common problems: buying things they already have (wasting money) or starting to cook a dish only to discover midway that a key ingredient is missing. There's no simple, offline way to quickly check "can I make this right now?" without manually going through the kitchen and comparing it to a recipe.

## Scope

This project is a command-line Java application that acts as a lightweight personal kitchen assistant. It is scoped to:

- Maintaining a single user's pantry inventory (ingredient name, quantity, unit)
- Maintaining a personal recipe collection (name + required ingredients)
- Matching pantry stock against saved recipes to suggest what's cookable
- Generating a shopping list of missing ingredients for a chosen recipe

It does not cover multi-user accounts, nutritional information, online recipe fetching, or a graphical interface — the focus is on the core CRUD and matching logic, run entirely from the terminal.

## Target Users

- Students or individuals living independently who cook for themselves and want a simple way to plan meals around what they already have
- Anyone who wants to reduce food waste and unnecessary grocery trips
- Small households that maintain a shared pantry and want a quick digital reference

## High-Level Features

1. **Pantry Management** — add, remove, and view ingredients with quantity and unit
2. **Recipe Management** — add and view recipes along with their required ingredients
3. **Recipe Matching** — rank saved recipes by how close the pantry is to having everything needed
4. **Shopping List Generation** — produce a list of only the missing ingredients for a chosen recipe, saved to a file for reuse
5. **Persistent Storage** — pantry and recipe data is saved to disk automatically and reloaded on the next run
