package guiHorizon;

public class Util_Euclidean {

	public static double euclidean_distance(double coord1X, double coord1Y, double coord2X, double coord2Y) {

		double xDiff = coord1X - coord2X;
		double yDiff = coord1Y - coord2Y;
		return Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));

	}
}
