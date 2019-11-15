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
	
	public Solver(Board board) {
		this.board = new Board(board);
	}

	public void populateMoveTree() {
		TreeNode<MoveState> root = new TreeNode<MoveState>(new MoveState(null, BoardUtilities.getRabbitsToWin(board)), null);
		int height = board.getModel().getHeight();
		int width = board.getModel().getWidth();
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				Position pos = new Position(j, i);
				if(board.getTile(pos).getPiece() != null && !(board.getTile(pos) instanceof RabbitHole)) {
					board.selectPiece(pos);
					for(MoveSet moveSet : board.getValidMoveSets()) {
						System.out.println(moveSet);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(changesOtherMoves(moveSet)) {
							TreeNode<MoveState> branch = root.addChild(new MoveState(moveSet, BoardUtilities.getRabbitsToWin(board)));
							populateChildren(branch);
						}
					}
				}
			}
		}
		System.out.println(root);
	}
	
	private void populateChildren(TreeNode<MoveState> parent) {
		Board boardCopy = new Board(board);
		int height = boardCopy.getModel().getHeight();
		int width = boardCopy.getModel().getWidth();
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				Position pos = new Position(j, i);
				if(boardCopy.getTile(pos).getPiece() != null && !(boardCopy.getTile(pos) instanceof RabbitHole)) {
					boardCopy.selectPiece(pos);
					for(MoveSet moveSet : boardCopy.getValidMoveSets()) {
						if(changesOtherMoves(moveSet)) {
							TreeNode<MoveState> branch = parent.addChild(new MoveState(moveSet, BoardUtilities.getRabbitsToWin(boardCopy)));
							populateChildren(branch);
						}
					}
				}
			}
		}
		
	}
	
	private boolean changesOtherMoves(MoveSet moveSet) {
		Board boardCopy = new Board(board);
		List<MoveSet> otherMoves = new ArrayList<MoveSet>();
		List<MoveSet> newOtherMoves = new ArrayList<MoveSet>();
		int height = boardCopy.getModel().getHeight();
		int width = boardCopy.getModel().getWidth();
		for(int i = 0; i < height; i++) {
			Loop : for(int j = 0; j < width; j++) {
				Position pos = new Position(j, i);
				for(Move move : moveSet) {
					if(pos.equals(move.getNewPos()) || pos.equals(move.getOldPos())) {
						continue Loop;
					}
				}
				if(boardCopy.getTile(pos).getPiece() != null && !(boardCopy.getTile(pos) instanceof RabbitHole)) {
					boardCopy.selectPiece(pos);
					otherMoves.addAll(boardCopy.getValidMoveSets());
				}
			}
		}
		try {
			boardCopy.selectPiece(moveSet.get(0).getOldPos());
			System.out.println(board);
			boardCopy.movePiece(moveSet.get(0));
		} catch (IllegalMoveException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < height; i++) {
			Loop : for(int j = 0; j < width; j++) {
				Position pos = new Position(j, i);
				for(Move move : moveSet) {
					if(pos.equals(move.getNewPos()) || pos.equals(move.getOldPos())) {
						continue Loop;
					}
				}
				if(boardCopy.getTile(pos).getPiece() != null && !(board.getTile(pos) instanceof RabbitHole)) {
					boardCopy.selectPiece(pos);
					newOtherMoves.addAll(boardCopy.getValidMoveSets());
				}
			}
		}
		
		
		return !newOtherMoves.equals(otherMoves);
	}
	
}
