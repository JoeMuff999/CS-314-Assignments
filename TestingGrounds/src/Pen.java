public class Pen extends WritingImplement {
private String color;
private int cap;
public Pen(String c) { color = c; cap = 5; }
public void test() { cap -= 1; }
public void refill(int c) { cap += c; }
public int uses() { return cap * 10; }
public void show() { System.out.print(color + " "); }
}