package jumpin.model.solver;

import java.util.ArrayList;
import java.util.List;

import jumpin.model.board.Board;
import jumpin.model.board.tile.RabbitHole;
import jumpin.model.exception.IllegalMoveException;
import jumpin.model.move.Move;
import jumpin.model.move.MoveSet;
import jumpin.model.util.BoardUtilities;
import jumpin.model.util.Position;
import jumpin.util.TreeNode;

public class Solver {

	private Board board;
	private TreeNode<MoveState> root;
	public Solver(Board board) throws CloneNotSupportedException {
		this.board = board;
		root =new TreeNode<MoveState>(new MoveState(new ArrayList<MoveSet>(), BoardUtilities.getRabbitsToWin(board),board, 0), null);
	}

	public void populateMoveTree() throws CloneNotSupportedException {
		hereWeGo(root,board,0);
		System.out.println(root);
	}
	static int i=0;
	private void hereWeGo(TreeNode<MoveState> node,Board board,int depth) throws CloneNotSupportedException {
		Board newboard=board.clone();
		if(depth==3||BoardUtilities.getRabbitsToWin(newboard)==0) {
			return;
		}
		depth++;
		int height = newboard.getModel().getHeight();
		int width = newboard.getModel().getWidth();
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				Position pos = new Position(j, i);
				if(newboard.getTile(pos).getPiece() != null && !(newboard.getTile(pos) instanceof RabbitHole)) {
					newboard.selectPiece(pos);
					for(MoveSet moveSet : newboard.getValidMoveSets()) {
						try {
							newboard.movePiece(moveSet.get(0));
							TreeNode<MoveState> t= new TreeNode<MoveState>(new MoveState(new ArrayList<MoveSet>(),BoardUtilities.getRabbitsToWin(newboard),newboard,depth),node);
							node.addChild(t);
							hereWeGo(t,newboard,depth);
							newboard.undoMove();
						}catch (IllegalMoveException e) {
								e.printStackTrace();
						}
					}
				}
			}
		}
	}
	private void populateChildren(TreeNode<MoveState> parent) throws CloneNotSupportedException {
		if(parent.data().getRabbitsToWin() == 0 || parent.data().getDepth() == 0) {
			return;  
		}
		
		Board boardCopy = board.clone();
		//boardCopy.selectPiece(parent.data().getMoveSet().get(0).getOldPos());
		//try {
			//boardCopy.movePiece(parent.data().getMoveSet().get(0));
		//} catch (IllegalMoveException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		int height = boardCopy.getModel().getHeight();
		int width = boardCopy.getModel().getWidth();
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				Position pos = new Position(j, i);
				if(boardCopy.getTile(pos).getPiece() != null && !(boardCopy.getTile(pos) instanceof RabbitHole)) {
					boardCopy.selectPiece(pos);
					for(MoveSet moveSet : boardCopy.getValidMoveSets()) {
						if(changesOtherMoves(moveSet) || movesToRabbitHole(moveSet)) {
						//	TreeNode<MoveState> branch = parent.addChild(new MoveState(moveSet, BoardUtilities.getRabbitsToWin(boardCopy), parent.data().getDepth()-1));
						//	populateChildren(branch);
						}
					}
				}
			}
		}
		
	}
	
	private boolean changesOtherMoves(MoveSet selfMoveSet) throws CloneNotSupportedException {
		Board boardCopy = board.clone();
		List<Position> toOmit = new ArrayList<Position>();
		for(Move move : selfMoveSet) {
			toOmit.add(move.getOldPos());
		}
		List<MoveSet> otherMoves = boardCopy.getAllValidMoveSets(toOmit);
		
		try {
			boardCopy.selectPiece(selfMoveSet.get(0).getOldPos());
			boardCopy.movePiece(selfMoveSet.get(0));
		} catch (IllegalMoveException e) {
			e.printStackTrace();
		}
		
		toOmit.clear();
		for(Move move : selfMoveSet) {
			toOmit.add(move.getNewPos());
		}
		List<MoveSet> newOtherMoves = boardCopy.getAllValidMoveSets(toOmit);
		return !newOtherMoves.containsAll(otherMoves);// && newOtherMoves.size() < otherMoves.size()
	}
	
	/**
	 * When you move a piece, its own moves will always change, so remove them
	 * 
	 * @param selfMove
	 * @param moveSets
	 * @return
	 */
	private List<MoveSet> stripSelfMoves(MoveSet selfMove, List<MoveSet> moveSets) {
		List<MoveSet> toRemove = new ArrayList<MoveSet>();
		for(MoveSet moveSet : moveSets) {
			for(Move move : selfMove) {
				for(Move newMove : moveSet) {
					if(newMove.getOldPos().equals(move.getNewPos()) || move.equals(newMove)) {
						toRemove.add(moveSet);
					}
				}
			}
		}
		moveSets.removeAll(toRemove);
		return moveSets;
	}
	
	private boolean movesToRabbitHole(MoveSet moveSet) {
		for(Move move : moveSet) {
			if(board.getTile(move.getNewPos()) instanceof RabbitHole) {
				return true;
			}
		}
		return false;
	}

	
}
