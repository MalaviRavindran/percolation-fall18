import java.util.Arrays;

public class PercolationUF implements IPercolate{
	private boolean[][] myGrid;
	private int myOpenCount;
	private IUnionFind myFinder;
	private final int VTOP;
	private final int VBOTTOM;

	public PercolationUF(int size, IUnionFind finder){
		myGrid = new boolean[size][size];
		for (boolean[] row: myGrid) {
			Arrays.fill(row, false);
		}
		finder.initialize((size*size)+2);
		myFinder = finder;
		myOpenCount = 0;
		VTOP = size*size;
		VBOTTOM =  (size*size)+1;

		
	}
	public boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}

	public void open(int row, int col) {
		if (!inBounds(row,col)) {
			throw new IndexOutOfBoundsException("cell out of bounds");
		}
		int cell = row*myGrid.length + col;
		if (! isOpen(row, col)) {
			myGrid[row][col] = true;
			myOpenCount += 1;
			if (row == 0) {
				myFinder.union(cell, VTOP);
			}
			if (row == myGrid.length-1) {
				myFinder.union(cell, VBOTTOM);
			}
			if (inBounds(row-1,col) && isOpen(row - 1, col)) {
				int cell2 = (row-1)*myGrid.length + col;
				myFinder.union(cell, cell2);
				
			}
			if (inBounds(row,col-1) && isOpen(row,col-1)) {
				int cell3 = (row)*myGrid.length + col-1;
				myFinder.union(cell, cell3);

			}
			if (inBounds(row,col+1) && isOpen(row,col+1)) {
				int cell4 = (row)*myGrid.length + col+1;
				myFinder.union(cell, cell4);
			
			}
			if (inBounds(row+1,col) && isOpen(row+1,col)) {
				int cell5 = (row+1)*myGrid.length + col;
				myFinder.union(cell, cell5);
			}
		}
		
	}

	@Override
	public boolean isOpen(int row, int col) {
		if (!inBounds(row,col)) {
			throw new IndexOutOfBoundsException("cell out of bounds");
		}
		return myGrid[row][col];
		
	}

	@Override
	public boolean isFull(int row, int col) {
		//checks if the (row,col) cell is connected to VTOP.
		if (!inBounds(row,col)) {
			throw new IndexOutOfBoundsException("cell out of bounds");
		}
		int cell =  (row*myGrid.length) + col;
		return myFinder.connected(VTOP,cell);
	}

	@Override
	public boolean percolates() {
		 return myFinder.connected(VTOP,VBOTTOM);
	}

	@Override
	public int numberOfOpenSites() {
		return myOpenCount;
	}

}
