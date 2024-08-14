# Scala - Java Enum Interop

After seeing some erratic behavior in a mixed Java and Scala application
I found that Java code checking for equality of Scala enums was failing
when the should have passed. I've created this project as a demonstration
of this behavior. This was with Eclipse Adoptium 21.0.2 and Scala 3.4.2.

*Update - turns out this is [a bug that's been known about for three
frickin' years](https://github.com/scala/scala3/issues/12637) without
so much as an on-by-the-way in the docs. Nice.*

## The Enum

A dead simple Scala enum, that extend `java.lang.Enum` to provide Java
compatibility. This is a single valued enum but this was only done for
clarity. The behavior happens with multi-value enums and enums with
parameters too.

```scala
enum Color extends Enum[Color]:
    case Blue
```

## The Java Class

A basic Java class with one final instance of the Color enum, two constructors
and an `isBlue()` boolean returning method:

```java
class Box {
    private final Color color;

    Box() { color = Color.Blue; }

    Box(Color color) { this.color = color; }

    boolean isBlue() { return color.equals(Color.Blue); }
}
```

## The Tests

### No Argument Constructor

If we use the no-argument constructor _isBlue_ works as expected.
**This test passes:**

```scala
val box = new Box()
assert(box.isBlue())
```

### Color argument constructor

However, if we pass in `Color.Blue` (which is the only value in the enum)
to the constructor, _isBlue()_ returns false.
**This test fails:**

```scala
val box = new Box(Color.Blue)
assert(box.isBlue())
```

### Both Constructors

If we construct two boxes, one using the no-arg constructor and the other
by passing in `Color.Blue`, _isBlue()_ returns true from both.
**This test passes:**

```scala
val box0 = new Box()
val box1 = new Box(Color.Blue)
assert(box0.isBlue())
assert(box1.isBlue())
```

If we flip the order and use the single argument constructor first we get
different behavior depending on the order of the tests.
**This test fails**

```scala
val box1 = new Box(Color.Blue)
val box0 = new Box()
assert(box1.isBlue())
assert(box0.isBlue())
```

While **this test throws a null pointer exception**

```scala
val box1 = new Box(Color.Blue)
val box0 = new Box()
assert(box0.isBlue())
assert(box1.isBlue())
```
