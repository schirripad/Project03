package Simulation;

public class Food {
	public class Chip extends Food {
		private final int TYPE_1 = 0, TYPE_2 = 1, TYPE_3 = 3;
		private int type;

		public Chip(int type) {
			this.type = type;
		}

		public int getType() {
			return type;
		}
	}

	public class Drink extends Food {
		private final int TYPE_1 = 0, TYPE_2 = 1, TYPE_3 = 3;
		private int type;

		public Drink(int type) {
			this.type = type;
		}

		public int getType() {
			return type;
		}
	}

	public class Sandwich extends Food {
		private final int TYPE_1 = 0, TYPE_2 = 1, TYPE_3 = 3;
		private int type;

		public Sandwich(int type) {
			this.type = type;
		}

		public int getType() {
			return type;
		}
	}

}
