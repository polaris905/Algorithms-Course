+ Iterators
  + Iterable interface
  ```java
  public interface Iterable<Item> {
    Iterator<Item> iterator();
  }
  ```
  + Iterator interface
  ```java
  public interface Iterator<Item> {
    boolean hasNext();
    Item next();
  }
  ```
  + foreach statement
  ```java
  for (String s : stack)
    StdOut.println(s);
  ```
  + equivalent code
  ```java
  Iterator<String> i = stack.iterator();
  while (i.hasNext()) {
    String s = i.next();
    StdOut.println(s);
  }
  ```
