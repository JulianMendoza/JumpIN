package jumpin.model.solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jumpin.model.board.Board;
import jumpin.model.board.tile.RabbitHole;
import jumpin.model.exception.IllegalMoveException;
import jumpin.model.move.MoveSet;
import jumpin.model.util.BoardUtilities;
import jumpin.model.util.Position;
import jumpin.util.TreeNode;

/**
 * Class to find a potential solution to the board using a recursive algorithm
 * 
 * @author Julian, Giuseppe
 */
public class Solver {

	private Board board;
	private TreeNode<MoveState> root;
	private List<TreeNode<MoveState>> childNodes;
	private List<MoveSet> bestMoves;

	public Solver(Board board) throws CloneNotSupportedException {
		this.board = board;
	}

	/**
	 * Method to populate the tree structure
	 * 
	 * @param threshold the depth of the tree to populate and search for a solution
	 */
	public void populateMoveTree(int threshold) throws CloneNotSupportedException {
		root = new TreeNode<MoveState>(
				new MoveState(new ArrayList<MoveSet>(), BoardUtilities.getRabbitsToWin(board), 0), null);
		childNodes = new ArrayList<TreeNode<MoveState>>();
		createAllBranches(root, board, 0, threshold);
		bestMoves = getBestMove(threshold);
		System.out.println("Number of branches: " + childNodes.size() + "\nBest move: \n" + bestMoves);
	}

	/**
	 * 
	 * @return the best moves to get to a solution
	 */
	public List<MoveSet> getBestMoves() {
		return bestMoves;
	}

	/**
	 * Searches all childNodes and finds a solution
	 * 
	 * @param leastDepth
	 * @return the best List of movesets or null if nothing is found
	 */
	private List<MoveSet> getBestMove(int leastDepth) {
		List<MoveSet> bestmoves = null;
		for (TreeNode<MoveState> node : childNodes) {
			MoveState data = (MoveState) node.data();
			if (data.getRabbitsToWin() == 0 && data.getDepth() <= leastDepth) {
				leastDepth = data.getDepth();
				bestmoves = data.getMoveSet();
			}
		}
		return bestmoves;
	}

	private void createAllBranches(TreeNode<MoveState> node, Board board, int depth, int threshold)
			throws CloneNotSupportedException {
		Board newboard = board.clone();
		if (depth == threshold || BoardUtilities.getRabbitsToWin(newboard) == 0) {
			// the node is a child
			childNodes.add(node);
			return;
		}
		depth++; // increment the depth
		int height = newboard.getModel().getHeight();
		int width = newboard.getModel().getWidth();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Position pos = new Position(j, i);
				if (newboard.getTile(pos).getPiece() != null && !(newboard.getTile(pos) instanceof RabbitHole)) {
					newboard.selectPiece(pos);
					for (MoveSet moveSet : newboard.getValidMoveSets()) {
						try {
							newboard.movePiece(moveSet.get(0));
							List<MoveSet> movesTogetHere = new ArrayList<MoveSet>(
									((MoveState) node.data()).getMoveSet()); // Data for the node
							Collections.copy(movesTogetHere, ((MoveState) node.data()).getMoveSet()); // copy the old
																										// moves
							movesTogetHere.add(moveSet); // add the new move
							TreeNode<MoveState> t = new TreeNode<MoveState>(
									new MoveState(movesTogetHere, BoardUtilities.getRabbitsToWin(newboard), depth),
									node); // create a new node
							node.addChild(t); // append node as a child
							createAllBranches(t, newboard, depth, threshold); // recursive call to find all possible
																				// moves on the board
							newboard.undoMove(); // undo so all other pieces on the board has the exact same board
						} catch (IllegalMoveException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
