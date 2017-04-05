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

public class Util_Mercator {

	    final private static double R_MAJOR = 6378137.0;     // in meters on the equator 
	    final private static double R_MINOR = 6356752.3142;  // in meters on the equator 

	    public static double[] merc(double x, double y) {
	        return new double[] {mercX(x), mercY(y)};
	    }

	    public static double  mercX(double lon) {
	        return R_MAJOR * Math.toRadians(lon);
	    }

	    public static double mercY(double lat) {
	        if (lat > 89.5) {
	            lat = 89.5;
	        }
	        if (lat < -89.5) {
	            lat = -89.5;
	        }
	        double temp = R_MINOR / R_MAJOR;
	        double es = 1.0 - (temp * temp);
	        double eccent = Math.sqrt(es);
	        double phi = Math.toRadians(lat);
	        double sinphi = Math.sin(phi);
	        double con = eccent * sinphi;
	        double com = 0.5 * eccent;
	        con = Math.pow(((1.0-con)/(1.0+con)), com);
	        double ts = Math.tan(0.5 * ((Math.PI*0.5) - phi))/con;
	        double y = 0 - R_MAJOR * Math.log(ts);
	        return y;
	    }
	}
