public LinkedQueueOfStrings {
  private Node first, last;
  
  private class Node {
  // the same as in StackOfStrings
  }
  
  public boolean isEmpty() {
    return first == null;
  }
  
  public void enqueue(String item) {
    Node oldlast = last;
    last = new Node();
    last.item = item;
    last.next = null;
    if (isEmpty()) first = last;
    else oldnext.next = last;
    
  public String dequeue() {
    String item = first.item;
    first = first.next;
    if (isEmpty()) last = null;
    return item;
  }
}
