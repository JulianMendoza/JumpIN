package jumpin.controller.builder.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.TransferHandler;

import jumpin.view.game.piece.PieceView;

public class DragListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		PieceView pieceView = (PieceView) e.getSource();

		TransferHandler th = pieceView.getTransferHandler();
		th.exportAsDrag(pieceView, e, TransferHandler.COPY);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
