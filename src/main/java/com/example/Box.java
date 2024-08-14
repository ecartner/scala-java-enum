package com.example;

class Box {
  private final Color color;

  Box() {
    color = Color.Blue;
  }

  Box(Color color) {
    this.color = color;
  }

  boolean isBlue() {
    return color.equals(Color.Blue);
  }
}
