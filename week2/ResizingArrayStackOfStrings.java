public class ResizingArrayStackOfStrings()
{
  private String[] s;

  public ResizingArrayStackOfStrings()
  {
    s = new string[1];
  }
  
  public String pop()
  {
    String item = s[--N];
    s[N] = null;
    if (N > 0 && N == s.length/4) resize(s.length/2);
    return item;
  }
  
  private void resize(int capacity)
  {
    String[] copy = new String[capcity];
    for (int i = 0; i < N; i++)
      copy[i] = s[i];
    s = copy;
  }
}
