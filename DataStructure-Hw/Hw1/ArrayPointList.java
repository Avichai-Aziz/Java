package lab01;

import java.awt.Point;

public class ArrayPointList implements PointList {
	private Point[] points;
	private int size;
	private int cursor;
	
	public ArrayPointList() {
		this(MAX_SIZE);
	}

	public ArrayPointList(int maxSize) {
		points = new Point[maxSize];
		this.size = 0;
		this.cursor = 0;
	}
	
	@Override
	public void append(Point newPoint) {
        if(!isFull())
        {
        	points[this.size] = newPoint;
        	this.cursor = this.size;
        	this.size++;
        }
        else {
        	System.out.println("Cannot append, the list is full.");
        }
	}

	@Override
	public void clear() {
        this.size = 0;
        this.cursor = 0;
	}

	@Override
	public boolean isEmpty() {
		return (this.size == 0);
	}

	@Override
	public boolean isFull() {
		return (this.size == points.length);
	}

	@Override
	public boolean goToBeginning() {
		if(!isEmpty())
		{
			this.cursor = 0;
			return true;
		}
		return false;
	}

	@Override
	public boolean goToEnd() {
		if(!isEmpty())
		{
			this.cursor = (this.size-1);
			return true;
		}
		return false;
	}

	@Override
	public boolean goToNext() {
		if(this.cursor+1 < this.size)
		{
			this.cursor++;
			return true;
		}
		return false;
	}

	@Override
	public boolean goToPrior() {
		if(this.cursor != 0)
		{
			this.cursor--;
			return true;
		}
		return false;
	}

	@Override
	public Point getCursor() {
		if(isEmpty())
		{
			return null;
		}
		return new Point(points[this.cursor]);
	}

	@Override
	public String toString() {
		String str = "";
		if(isEmpty())
		{
			str = "Empty list";
			return str;
		}
		for(int i = 0; i < this.size; i++)
		{
			 System.out.println('(' + getCursor().x + ',' + getCursor().y + ')');
		}
		return str;
	}

}
