package guiHorizon;

//Copyright @ 2017 by Valdecy Pereira

//This file is part of J-Horizon.
//
//J-Horizon is free software: you can redistribute it and/or modify
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class jsHorizon {
	public static void writeFile_VRP(String[][] latlong) throws IOException {
		String path = "";
		path =  "maps";
		File dir  = new File(path);
		
		FileWriter fw  = new FileWriter(new File(dir, "temp_.txt"));
		
		if (InterfaceHorizon.Map_Preferences == 1){
			fw  = new FileWriter(new File(dir, "latlong.js"));
		}else if (InterfaceHorizon.Map_Preferences == 2){
			fw  = new FileWriter(new File(dir, "xy.js"));
		}
		
		int ndepots = InterfaceHorizon.TotalDepots;
		
		if (InterfaceHorizon.Map_Preferences == 1){
			fw.write("\t" + "var depots = " + (ndepots) + ";" + "\n" + "\n" + "\t" + "var latlong = "
		             + "\n" + "\t" + "[");
		}else if (InterfaceHorizon.Map_Preferences == 2){
			fw.write("\t" + "var depots = " + (ndepots) + ";" + "\n" + "\n" + "\t" + "var xy = "
		             + "\n" + "\t" + "[");
		}
		
		for (int i = 0; i < latlong.length; i++) {
			if (i < latlong.length - 1){

				fw.write("\n" + "\t" + "  [" 
			              + latlong[i][2] + ", " 
						  + latlong[i][3] + ", " 
			              + "\"" + latlong[i][0] + "\"" 
						  + "],");	
			} else {
				fw.write("\n" + "\t" + "  [" 
			             + latlong[i][2] + ", " 
						 + latlong[i][3] + ", " 
			             + "\"" + latlong[i][0] + "\"" 
						 + "]" + "\n" + "\t" + "];");
			}
		}
		fw.close();
	}
	public static void writeFileRoute_VRP(String[][] latlong) throws IOException {
		String path = "";
		path =  "maps";
		File dir  = new File(path);
		
		FileWriter fw  = new FileWriter(new File(dir, "temp_.txt"));
		
		if (InterfaceHorizon.Map_Preferences == 1){
			fw  = new FileWriter(new File(dir, "latlong_route.js"));
		}else if (InterfaceHorizon.Map_Preferences == 2){
			fw  = new FileWriter(new File(dir, "xy_route.js"));
		}
		
		if (InterfaceHorizon.Map_Preferences == 1){
			fw.write("var latlong_route = "
		             + "\n" + "\t" + "[");
		}else if (InterfaceHorizon.Map_Preferences == 2){
			fw.write("var xy_route = "
		             + "\n" + "\t" + "[");
		}
	
			for (int i = 0; i < latlong.length; i++) {
				if (i < latlong.length - 1){
					fw.write("\n" + "\t" + "  [" 
				              + latlong[i][2] + ", " 
							  + latlong[i][3] + ", " 
				              + "\"" + latlong[i][0] + "\"" 
							  + "],");	
				} else {
					fw.write("\n" + "\t" + "  [" 
				             + latlong[i][2] + ", " 
							 + latlong[i][3] + ", " 
				             + "\"" + latlong[i][0] + "\"" 
							 + "]" + "\n" + "\t" + "];");
				}
			}
		fw.close();
	}
	public static void writeFileRoute_VRP_Complete(String[][] latlong) throws IOException {
		
		String path = "";
		path =  "maps";
		File dir  = new File(path);
		
		FileWriter fw  = new FileWriter(new File(dir, "temp_.txt"));
		
		if (InterfaceHorizon.Map_Preferences == 1){
			fw  = new FileWriter(new File(dir, "latlong_route_complete.js"));
		}else if (InterfaceHorizon.Map_Preferences == 2){
			fw  = new FileWriter(new File(dir, "xy_route_complete.js"));
		}
		
		if (InterfaceHorizon.Map_Preferences == 1){
			fw.write("var latlong_route_complete = "
		             + "\n" + "\t" + "[");
		}else if (InterfaceHorizon.Map_Preferences == 2){
			fw.write("var xy_route_complete = "
		             + "\n" + "\t" + "[");
		}
	
			for (int i = 0; i < latlong.length; i++) {
				if (i < latlong.length - 1){
					fw.write("\n" + "\t" + "  [" 
					         + latlong[i][2] + ", " 
							 + latlong[i][3] + ", " 
				             + "\"" + latlong[i][0] + "\"" + ", " 
				             + latlong[i][4] + ", " 
				             + "\"" + latlong[i][5] + "\"" + ", " 
				             + "\"" + latlong[i][6] + "\""
							 + "],");	
				} else {
					fw.write("\n" + "\t" + "  [" 
				             + latlong[i][2] + ", " 
							 + latlong[i][3] + ", " 
				             + "\"" + latlong[i][0] + "\"" + ", " 
				             + latlong[i][4] + ", " 
				             + "\"" + latlong[i][5] + "\"" + ", " 
				             + "\"" + latlong[i][6] + "\""
							 + "]" + "\n" + "\t" + "];");
				}
			}
		fw.close();
	}
	public static void writeFile_VRP_depots(String[][] latlong) throws IOException {
		
		String path = "";
		path =  "maps";
		File dir  = new File(path);
		
		FileWriter fw_depots  = new FileWriter(new File(dir, "temp_.txt"));
		
		if (InterfaceHorizon.Map_Preferences == 1){
			fw_depots  = new FileWriter(new File(dir, "latlong_depots.js"));
		}else if (InterfaceHorizon.Map_Preferences == 2){
			fw_depots  = new FileWriter(new File(dir, "xy_depots.js"));
		}
		
		if (InterfaceHorizon.Map_Preferences == 1){
			fw_depots.write("var latlong_depots = "
		             + "\n" + "\t" + "[");
		}else if (InterfaceHorizon.Map_Preferences == 2){
			fw_depots.write("var xy_depots = "
		             + "\n" + "\t" + "[");
		}
	
			for (int i = 0; i < InterfaceHorizon.TotalDepots; i++) {
				if (i < InterfaceHorizon.TotalDepots - 1){
					fw_depots.write("\n" + "\t" + "  [" 
				              + latlong[i][2] + ", " 
							  + latlong[i][3] + ", " 
				              + "\"" + latlong[i][0] + "\"" 
							  + "],");	
				} else {
					fw_depots.write("\n" + "\t" + "  [" 
				             + latlong[i][2] + ", " 
							 + latlong[i][3] + ", " 
				             + "\"" + latlong[i][0] + "\"" 
							 + "]" + "\n" + "\t" + "];");
				}
			}
		fw_depots.close();
	}
	public static void writeFile_VRP_pck(String[][] latlong) throws IOException {
		
		String path = "";
		path =  "maps";
		File dir  = new File(path);
		
		FileWriter fw_pck  = new FileWriter(new File(dir, "temp_.txt"));
		
		if (InterfaceHorizon.Map_Preferences == 1){
			fw_pck  = new FileWriter(new File(dir, "latlong_pck.js"));
		}else if (InterfaceHorizon.Map_Preferences == 2){
			fw_pck  = new FileWriter(new File(dir, "xy_pck.js"));
		}
		
		if (InterfaceHorizon.Map_Preferences == 1){
			fw_pck.write("var latlong_pck = "
		             + "\n" + "\t" + "[");
		}else if (InterfaceHorizon.Map_Preferences == 2){
			fw_pck.write("var xy_pck = "
		             + "\n" + "\t" + "[");
		}
	
			for (int i = InterfaceHorizon.TotalDepots; i < latlong.length; i++) {
				if (i < latlong.length - 1){
					fw_pck.write("\n" + "\t" + "  [" 
				              + latlong[i][8] + ", " 
							  + latlong[i][9] + ", " 
				              + "\"" + latlong[i][0] + "\"" 
							  + "],");	
				} else {
					fw_pck.write("\n" + "\t" + "  [" 
				             + latlong[i][8] + ", " 
							 + latlong[i][9] + ", " 
				             + "\"" + latlong[i][0] + "\"" 
							 + "]" + "\n" + "\t" + "];");
				}
			}
		fw_pck.close();
	}
	public static void writeFile_VRP_dlv(String[][] latlong) throws IOException {
		
		String path = "";
		path =  "maps";
		File dir  = new File(path);
		
		FileWriter fw_dlv  = new FileWriter(new File(dir, "temp_.txt"));
		
		if (InterfaceHorizon.Map_Preferences == 1){
			fw_dlv = new FileWriter(new File(dir, "latlong_dlv.js"));
		}else if (InterfaceHorizon.Map_Preferences == 2){
			fw_dlv  = new FileWriter(new File(dir, "xy_dlv.js"));
		}
		
		if (InterfaceHorizon.Map_Preferences == 1){
			fw_dlv.write("var latlong_dlv = "
		             + "\n" + "\t" + "[");
		}else if (InterfaceHorizon.Map_Preferences == 2){
			fw_dlv.write("var xy_dlv = "
		             + "\n" + "\t" + "[");
		}
	
			for (int i = InterfaceHorizon.TotalDepots; i < latlong.length; i++) {
				if (i < latlong.length - 1){
					fw_dlv.write("\n" + "\t" + "  [" 
				              + latlong[i][13] + ", " 
							  + latlong[i][14] + ", " 
				              + "\"" + latlong[i][0] + "\"" 
							  + "],");	
				} else {
					fw_dlv.write("\n" + "\t" + "  [" 
				             + latlong[i][13] + ", " 
							 + latlong[i][14] + ", " 
				             + "\"" + latlong[i][0] + "\"" 
							 + "]" + "\n" + "\t" + "];");
				}
			}
		fw_dlv.close();
	}
}
