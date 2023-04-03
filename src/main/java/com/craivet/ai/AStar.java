package com.craivet.ai;

import com.craivet.Game;

import java.util.ArrayList;

import static com.craivet.utils.Constants.*;

public class AStar {

	private final Game game;

	Node[][] node;
	Node startNode, goalNode, currentNode;

	ArrayList<Node> openList = new ArrayList<>();
	public ArrayList<Node> pathList = new ArrayList<>();

	boolean goalReached;
	int step;

	public AStar(Game game) {
		this.game = game;
		initNodes();
	}

	// TODO El problema de esto es que la busqueda se actualiza cada 60 segundos
	public boolean search() {
		while (!goalReached && step < 500) {

			currentNode.checked = true;
			openList.remove(currentNode);

			int row = currentNode.row;
			int col = currentNode.col;
			if (row - 1 >= 0) openNode(node[row - 1][col]);
			if (col - 1 >= 0) openNode(node[row][col - 1]);
			if (row + 1 < MAX_WORLD_ROW) openNode(node[row + 1][col]);
			if (col + 1 < MAX_WORLD_COL) openNode(node[row][col + 1]);

			int bestNodeIndex = 0;
			int bestNodefCost = 999;
			for (int i = 0; i < openList.size(); i++) {
				if (openList.get(i).fCost < bestNodefCost) {
					bestNodeIndex = i;
					bestNodefCost = openList.get(i).fCost;
				} else if (openList.get(i).fCost == bestNodefCost)
					if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) bestNodeIndex = i;
			}

			if (openList.isEmpty()) break;

			currentNode = openList.get(bestNodeIndex);

			if (currentNode == goalNode) {
				goalReached = true;
				trackThePath();
			}

			step++;
		}

		return goalReached;
	}

	/**
	 * Establece el nodo inicial, el nodo objetivo y los nodos solidos.
	 */
	public void setNodes(int startRow, int startCol, int goalRow, int goalCol) {
		resetNodes(); // TODO No haria falta resetear los nodos en caso de que el objetivo sea fijo

		startNode = node[startRow][startCol];
		goalNode = node[goalRow][goalCol];
		currentNode = startNode;

		openList.add(currentNode);

		// Establece los nodos solidos verificando los tiles solidos y los tiles interactivos destructibles
		for (int row = 0; row < MAX_WORLD_ROW; row++) {
			for (int col = 0; col < MAX_WORLD_COL; col++) {
				int tileIndex = game.tileManager.tileIndex[game.map][row][col];
				if (game.tileManager.tile[tileIndex].solid) node[row][col].solid = true;

				for (int i = 0; i < game.iTile[1].length; i++) {
					if (game.iTile[game.map][i] != null && game.iTile[game.map][i].destructible) {
						int itRow = game.iTile[game.map][i].worldY / tile_size;
						int itCol = game.iTile[game.map][i].worldX / tile_size;
						node[itRow][itCol].solid = true;
					}
				}
				getCost(node[row][col]);
			}
		}

	}

	private void initNodes() {
		node = new Node[MAX_WORLD_ROW][MAX_WORLD_COL];
		for (int row = 0; row < MAX_WORLD_ROW; row++)
			for (int col = 0; col < MAX_WORLD_COL; col++)
				node[row][col] = new Node(row, col);
	}

	private void resetNodes() {
		for (int row = 0; row < MAX_WORLD_ROW; row++) {
			for (int col = 0; col < MAX_WORLD_COL; col++) {
				node[row][col].open = false;
				node[row][col].checked = false;
				node[row][col].solid = false;
			}
		}
		openList.clear();
		pathList.clear();
		goalReached = false;
		step = 0;
	}

	private void getCost(Node node) {
		// G Cost
		int xDistance = Math.abs(node.col - startNode.col);
		int yDistance = Math.abs(node.row - startNode.row);
		node.gCost = xDistance + yDistance;
		// H Cost
		xDistance = Math.abs(node.col - goalNode.col);
		yDistance = Math.abs(node.row - goalNode.row);
		node.hCost = xDistance + yDistance;
		// F Cost
		node.fCost = node.gCost + node.hCost;
	}

	private void openNode(Node node) {
		if (!node.open && !node.checked && !node.solid) {
			node.open = true;
			node.parent = currentNode;
			openList.add(node);
		}
	}

	private void trackThePath() {
		Node current = goalNode;
		while (current != startNode) {
			// Con esta lista cualquier entidad puede rastrear la ruta
			pathList.add(0, current); // Agrega el nodo al primer slot para que el ultimo nodo agregado este en [0]
			current = current.parent;
		}
	}

}
