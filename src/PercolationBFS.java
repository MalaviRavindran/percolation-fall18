import java.util.LinkedList;
import java.util.Queue;

public class PercolationBFS extends PercolationDFSFast {

	public PercolationBFS(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void dfs(int row, int col) {
		int[] rowDelta = {-1,1,0,0};
        int[] colDelta = {0,0,-1,1};
		Queue<Integer> qp = new LinkedList<>(); 
		myGrid[row][col] = FULL; 
		qp.add(row*myGrid.length + col);
		while (qp.size() != 0){
			Integer p = qp.remove();
			for(int k=0; k < rowDelta.length; k++){
                row =  p/myGrid.length + rowDelta[k];
                col = p%myGrid.length + colDelta[k];
                if (inBounds(row,col) && isOpen(row, col) && !isFull(row, col)){
                	myGrid[row][col] = FULL; 
                	qp.add(row*myGrid.length + col);
                }       
			}
		}
		
	}

}

