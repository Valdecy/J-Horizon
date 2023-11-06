package guiHorizon;

public class Util_Manhattan {

	public static double manhattan_distance(double coord1X, double coord1Y, double coord2X, double coord2Y) {

		return Math.abs(coord1X - coord2X) + Math.abs(coord1Y - coord2Y);

	}
}
