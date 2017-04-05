package guiHorizon;

//Copyright @ 2017 by Valdecy Pereira

//This file is part of J-Horizon.
//
//J-ELECTRE is free software: you can redistribute it and/or modify
//it under the terms of the GNU General Public License as published by
//the Free Software Foundation, either version 3 of the License, or
//(at your option) any later version.
//
//J-Horizon is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.
//
//You should have received a copy of the GNU General Public License
//along with J-Horizon.  If not, see <http://www.gnu.org/licenses/>.

public class Util_Manhattan {

	public static double manhattan_distance(double coord1X, double coord1Y, double coord2X, double coord2Y) {

		return Math.abs(coord1X - coord2X) + Math.abs(coord1Y - coord2Y);

	}
}
