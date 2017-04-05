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

import jspritCoreAlgorithm.VehicleRoutingAlgorithm;
import jspritCoreAlgorithmBox.Jsprit;
import jspritCoreAlgorithmState.StateManager;
import jspritCoreProblem.Location;
import jspritCoreProblem.VehicleRoutingProblem;
import jspritCoreProblem.VehicleRoutingProblem.FleetSize;
import jspritCoreProblemConstraint.ConstraintManager;
import jspritCoreProblemConstraint.ServiceDeliveriesFirstConstraint;
import jspritCoreProblemCost.VehicleRoutingTransportCosts;
import jspritCoreProblemJob.Break;
import jspritCoreProblemJob.Delivery;
import jspritCoreProblemJob.Pickup;
import jspritCoreProblemJob.Service;
import jspritCoreProblemJob.Shipment;
import jspritCoreProblemSolution.VehicleRoutingProblemSolution;
import jspritCoreProblemSolutionRouteActivity.TimeWindow;
import jspritCoreProblemVehicle.VehicleImpl;
import jspritCoreProblemVehicle.VehicleTypeImpl;
import jspritCoreReporting.SolutionPrinter;
import jspritCoreUtil.Solutions;
import jspritCoreUtil.VehicleRoutingTransportCostsMatrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Scanner;

public class Model_Builder_and_Solver_01 {
	
	public static void VRP_(double [][]array, int depots, int clients) {

		double quantity = 0;

		for (int i = 0; i < depots; i++){
			quantity = quantity + array[i][18];
		}
		
		if (quantity == 0){
			quantity = 1;
		}


		VehicleRoutingProblem.Builder vBuilder = VehicleRoutingProblem.Builder.newInstance();

		double capacity = 0;
		double V        = 0;
		double B1       = 0;

		for (int i = depots; i < array.length; i++){
			capacity = capacity +  array[i][1];
		}

		if (capacity == 0){
			capacity = clients;
		}
		
		boolean RouteType = true;
		if (InterfaceHorizon.Route_Type == 1){
			RouteType = true;
		}else if (InterfaceHorizon.Route_Type == 2){
			RouteType = false;
		}
		
		for (int j = 0; j < InterfaceHorizon.TypesVehicles; j++){
			for (int i = 0; i < depots; i++){

				V  = array[i][18 + 7*j] + array[i][19 + 7*j] + array[i][20 + 7*j] + array[i][21 + 7*j];
				B1 = array[i][22 + 7*j] + array[i][23 + 7*j] + array[i][24 + 7*j];

				for (int k = 0; k < quantity; k++){

					if (V == 0){
						break;
					}

					if (InterfaceHorizon.Time_Window == 1){

						if (B1 == 0){

							if(InterfaceHorizon.Vehicle_Capacity == 1){

								array[i][19 + 7*j] = (int) capacity;

							}

							VehicleTypeImpl vt  = VehicleTypeImpl
									.Builder
									.newInstance("DT_" + i + "_VT-" + Integer.toString(j + 1) + "#" + (k + 1))
									.setFixedCost((double) array[i][20 + 7*j])
									.setCostPerDistance((double) array[i][21 + 7*j])
									.addCapacityDimension(0, (int) array[i][19 + 7*j])
									.build();

							VehicleImpl vp = VehicleImpl
									.Builder
									.newInstance("DT_" + i + "_VT-" + Integer.toString(j + 1) + "#" + (k + 1))
									.setStartLocation(Location.newInstance(array[i][2], array[i][3]))
									.setType(vt)
									.setReturnToDepot(RouteType)
									.build();

							vBuilder.addVehicle(vp);

						}else if(B1 != 0){

							if(InterfaceHorizon.Vehicle_Capacity == 1){

								array[i][19 + 7*j] = (int) capacity;

							}

							VehicleTypeImpl vt  = VehicleTypeImpl
									.Builder
									.newInstance("DT_" + i + "_VT-" + Integer.toString(j + 1) + "#" + (k + 1))
									.setFixedCost((double) array[i][20 + 7*j])
									.setCostPerDistance((double) array[i][21 + 7*j])
									.addCapacityDimension(0, (int) array[i][19 + 7*j])
									.build();

							Break myFirstBreak = Break
									.Builder.newInstance("brk_" + "DT_" + i + "_VT-" + Integer.toString(j + 1) + "#" + (k + 1))
									.setTimeWindow(TimeWindow.newInstance(array[i][22 + 7*j], array[i][23 + 7*j]))
									.setServiceTime(array[i][24 + 7*j]).build();

							VehicleImpl vp = VehicleImpl
									.Builder
									.newInstance("DT_" + i + "_VT-" + Integer.toString(j + 1) + "#" + (k + 1))
									.setStartLocation(Location.newInstance(array[i][2], array[i][3]))
									.setType(vt)
									.setBreak(myFirstBreak)
									.setReturnToDepot(RouteType)
									.build();

							vBuilder.addVehicle(vp);

						}

					}

					if (InterfaceHorizon.Time_Window == 2){

						if (B1 == 0){

							if(InterfaceHorizon.Vehicle_Capacity == 1){

								array[i][19 + 7*j] = (int) capacity;

							}

							VehicleTypeImpl vt  = VehicleTypeImpl
									.Builder
									.newInstance("DT_" + i + "_VT-" + Integer.toString(j + 1) + "#" + (k + 1))
									.setCostPerWaitingTime(array[i][5])
									.setFixedCost((double) array[i][20 + 7*j])
									.setCostPerDistance((double) array[i][21 + 7*j])
									.addCapacityDimension(0, (int) array[i][19 + 7*j])
									.build();

							VehicleImpl vp = VehicleImpl
									.Builder
									.newInstance("DT_" + i + "_VT-" + Integer.toString(j + 1) + "#" + (k + 1))
									.setStartLocation(Location.newInstance(array[i][2], array[i][3]))
									.setType(vt)
									.setReturnToDepot(RouteType)
									.setEarliestStart(array[i][6])
									.setLatestArrival(array[i][7])
									.build();

							vBuilder.addVehicle(vp);

						}else if(B1 != 0){

							if(InterfaceHorizon.Vehicle_Capacity == 1){

								array[i][19 + 7*j] = (int) capacity;

							}

							VehicleTypeImpl vt  = VehicleTypeImpl
									.Builder
									.newInstance("DT_" + i + "_VT-" + Integer.toString(j + 1) + "#" + (k + 1))
									.setCostPerWaitingTime(array[i][5])
									.setFixedCost((double) array[i][20 + 7*j])
									.setCostPerDistance((double) array[i][21 + 7*j])
									.addCapacityDimension(0, (int) array[i][19 + 7*j])
									.build();
							
							Break myFirstBreak = Break
									.Builder.newInstance("brk_" + "DT_" + i + "_VT-" + Integer.toString(j + 1) + "#" + (k + 1))
									.setTimeWindow(TimeWindow.newInstance(array[i][22 + 7*j], array[i][23 + 7*j]))
									.setServiceTime(array[i][24 + 7*j]).build();

							VehicleImpl vp = VehicleImpl
									.Builder
									.newInstance("DT_" + i + "_VT-" + Integer.toString(j + 1) + "#" + (k + 1))
									.setStartLocation(Location.newInstance(array[i][2], array[i][3]))
									.setType(vt)
									.setBreak(myFirstBreak)
									.setReturnToDepot(RouteType)
									.setEarliestStart(array[i][6])
									.setLatestArrival(array[i][7])
									.build();

							vBuilder.addVehicle(vp);

						}

					}
					if (k + 1 == (int) array[i][18 + 7*j]){
						k = 0;
						break;
					}
				}       
			}
		}	

		if (InterfaceHorizon.Fleet_Size == 1){

			vBuilder.setFleetSize(FleetSize.FINITE);

		} else if (InterfaceHorizon.Fleet_Size == 2){

			vBuilder.setFleetSize(FleetSize.INFINITE);

		}

		if (InterfaceHorizon.Time_Window == 1){

			for( int i = 0; i < array.length - depots; i++ ) {

				if(InterfaceHorizon.Pickup_Delivery == 1){

					Service service = Service
							.Builder
							.newInstance(Integer.toString(i + 1))
							.addSizeDimension(0, (int) array[i + depots][1])
							.setLocation(Location.newInstance(array[i + depots][2], array[i  + depots][3]))
							.build();

					vBuilder.addJob(service);

				}else if (InterfaceHorizon.Pickup_Delivery == 2){

					if (InterfaceHorizon.Backhaul == 1){

						Shipment shipment = Shipment
								.Builder
								.newInstance(Integer.toString(i + 1))
								.addSizeDimension(0,(int) array[i + depots][1])
								.setPickupLocation(Location.newInstance(array[i + depots][8], array[i  + depots][9]))
								.setDeliveryLocation(Location.newInstance(array[i + depots][13], array[i  + depots][14]))
								.build();

						vBuilder.addJob(shipment);

					}else if (InterfaceHorizon.Backhaul == 2){

						Pickup pickup = Pickup
								.Builder
								.newInstance("pck_" + Integer.toString(i + 1))
								.addSizeDimension(0, (int) array[i + depots][1])
								.setLocation(Location.newInstance(array[i + depots][8], array[i  + depots][9]))
								.build();
						Delivery delivery = Delivery
								.Builder
								.newInstance("dlv_" + Integer.toString(i + 1))
								.addSizeDimension(0, (int) array[i + depots][1])
								.setLocation(Location.newInstance(array[i + depots][13], array[i  + depots][14]))
								.build();

						vBuilder.addJob(pickup);
						vBuilder.addJob(delivery);

					}

				}

			}
		} else if (InterfaceHorizon.Time_Window == 2){

			for( int i = 0; i < array.length - depots; i++ ) {

				if(InterfaceHorizon.Pickup_Delivery == 1){

					Service service = Service
							.Builder
							.newInstance(Integer.toString(i + 1))
							.addSizeDimension(0, (int) array[i + depots][1])
							.addTimeWindow(array[i + depots][6], array[i + depots][7])
							.setServiceTime(array[i + depots][4])
							.setLocation(Location.newInstance(array[i + depots][2], array[i  + depots][3]))
							.build();

					vBuilder.addJob(service);

				}else if (InterfaceHorizon.Pickup_Delivery == 2){

					if (InterfaceHorizon.Backhaul == 1){

						Shipment shipment = Shipment
								.Builder
								.newInstance(Integer.toString(i + 1))
								.addSizeDimension(0, (int) array[i + depots][1])
								.addPickupTimeWindow(array[i + depots][10], array[i + depots][11])
								.setPickupServiceTime(array[i + depots][12])
								.setPickupLocation(Location.newInstance(array[i + depots][8], array[i  + depots][9]))
								.addDeliveryTimeWindow(array[i + depots][15], array[i + depots][16])
								.setDeliveryServiceTime(array[i + depots][17])
								.setDeliveryLocation(Location.newInstance(array[i + depots][13], array[i  + depots][14]))
								.build();

						vBuilder.addJob(shipment);

					}else if (InterfaceHorizon.Backhaul == 2){

						Pickup pickup = Pickup
								.Builder
								.newInstance("pck_" + Integer.toString(i + 1))
								.addSizeDimension(0, (int) array[i + depots][1])
								.addTimeWindow(array[i + depots][10], array[i + depots][11])
								.setServiceTime(array[i + depots][12])
								.setLocation(Location.newInstance(array[i + depots][8], array[i  + depots][9]))
								.build();
						Delivery delivery = Delivery
								.Builder
								.newInstance("dlv_" + Integer.toString(i + 1))
								.addTimeWindow(array[i + depots][15], array[i + depots][16])
								.setServiceTime(array[i + depots][17])
								.addSizeDimension(0, (int) array[i + depots][1])
								.setLocation(Location.newInstance(array[i + depots][13], array[i  + depots][14]))
								.build();

						vBuilder.addJob(pickup);
						vBuilder.addJob(delivery);

					}			
				}
			}
		}

		VehicleRoutingProblem problem = vBuilder.build();

//		VehicleRoutingAlgorithm algorithm = Jsprit.createAlgorithm(problem);
		VehicleRoutingAlgorithm algorithm = Jsprit
                .Builder
                .newInstance(problem)
                .setProperty(Jsprit.Parameter.FIXED_COST_PARAM, "2.")
//              .setProperty(Jsprit.Parameter.THREADS,"5")
//              .setProperty(Jsprit.Parameter.THRESHOLD_INI, "0.1")
//              .setProperty(Jsprit.Strategy.CLUSTER_REGRET, "0.")
//              .setProperty(Jsprit.Strategy.CLUSTER_BEST, "0.")
//              .setProperty(Jsprit.Strategy.WORST_REGRET, "0.")
//              .setProperty(Jsprit.Strategy.RANDOM_REGRET, "0.")
//              .setProperty(Jsprit.Strategy.RADIAL_REGRET, "0.")
//              .setProperty(Jsprit.Parameter.CONSTRUCTION, Jsprit.Construction.BEST_INSERTION.toString())
                .buildAlgorithm();


		if (InterfaceHorizon.Backhaul == 2){
			StateManager stateManager = new StateManager(problem);
			ConstraintManager constraintManager = new ConstraintManager(problem, stateManager);
			constraintManager.addConstraint(new ServiceDeliveriesFirstConstraint(), ConstraintManager.Priority.CRITICAL);

			algorithm = Jsprit
					.Builder
					.newInstance(problem)
					.setProperty(Jsprit.Parameter.FIXED_COST_PARAM, "2.")
//					.setProperty(Jsprit.Parameter.THREADS,"5")
//	                .setProperty(Jsprit.Parameter.THRESHOLD_INI, "0.1")
//	                .setProperty(Jsprit.Strategy.CLUSTER_REGRET, "0.")
//	                .setProperty(Jsprit.Strategy.CLUSTER_BEST, "0.")
//	                .setProperty(Jsprit.Strategy.WORST_REGRET, "0.")
//	                .setProperty(Jsprit.Strategy.RANDOM_REGRET, "0.")
//	                .setProperty(Jsprit.Strategy.RADIAL_REGRET, "0.")
//	                .setProperty(Jsprit.Parameter.CONSTRUCTION, Jsprit.Construction.BEST_INSERTION.toString())
					.setStateAndConstraintManager(stateManager,constraintManager)
					.buildAlgorithm();
		}
		
//		algorithm.setMaxIterations(2000);  // default = 2000
//		TimeTermination prematureTermination = new TimeTermination(2.); //in seconds
//		vBuilder.setPrematureAlgorithmTermination(prematureTermination);
//		vBuilder.addListener(prematureTermination);
//		vBuilder.setPrematureAlgorithmTermination(new IterationWithoutImprovementTermination(200));
		

		Collection<VehicleRoutingProblemSolution> solutions = algorithm.searchSolutions();
		VehicleRoutingProblemSolution bestSolution = Solutions.bestOf(solutions);

		String path = new File("maps").getAbsolutePath();

		PrintStream standard = System.out;
		PrintStream out;

		try {
			out = new PrintStream(new FileOutputStream(path + "\\" + "output_.txt"));
			System.setOut(out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		SolutionPrinter.print(problem, bestSolution, SolutionPrinter.Print.VERBOSE);

		System.setOut(standard);

		Scanner file;
		PrintWriter writer;

		try {
			file = new Scanner(new File(path + "\\" + "output_.txt"));
			writer = new PrintWriter(path + "\\" + "output.txt");
			while (file.hasNext()) {
				String line = file.nextLine();
				writer.write(line.trim());
				writer.write("\n");
			}
			file.close();
			writer.close();
		} catch (FileNotFoundException ex) {
		}

	}

	public static void TSP_CostMatrix(double [][] array, double [][]arrayDistance, double [][]arrayTime, int depots, int clients) {

		double quantity = 0;

		for (int i = 0; i < depots; i++){
			quantity = quantity + array[i][18];
		}
		
		if (quantity == 0){
			quantity = 1;
		}

		VehicleRoutingProblem.Builder vBuilder = VehicleRoutingProblem.Builder.newInstance();

		double capacity =    0;
		double V        =    0;
		double B1       =    0;
		String LCT      = null;
		String LCT_pck  = null;
		String LCT_dlv  = null;

		for (int i = depots; i < array.length; i++){
			capacity = capacity +  array[i][1];
		}

		if (capacity == 0){
			capacity = clients;
		}
		
		boolean RouteType = true;
		if (InterfaceHorizon.Route_Type == 1){
			RouteType = true;
		}else if (InterfaceHorizon.Route_Type == 2){
			RouteType = false;
		}

		for (int j = 0; j < InterfaceHorizon.TypesVehicles; j++){
			for (int i = 0; i < depots; i++){

				V  = array[i][18 + 7*j] + array[i][19 + 7*j] + array[i][20 + 7*j] + array[i][21 + 7*j];
				B1 = array[i][22 + 7*j] + array[i][23 + 7*j] + array[i][24 + 7*j];
				LCT = Integer.toString(i);

				for (int k = 0; k < quantity; k++){

					if (V == 0){
						break;
					}
					
					if (InterfaceHorizon.Time_Window == 1){

						if (B1 == 0){

							if(InterfaceHorizon.Vehicle_Capacity == 1){

								array[i][19 + 7*j] = (int) capacity;

							}

							VehicleTypeImpl vt  = VehicleTypeImpl
									.Builder
									.newInstance("DT_" + i + "_VT-" + Integer.toString(j + 1) + "#" + (k + 1))
									.setFixedCost((double) array[i][20 + 7*j])
									.setCostPerDistance((double) array[i][21 + 7*j])
									.addCapacityDimension(0, (int) array[i][19 + 7*j])
									.build();

							VehicleImpl vp = VehicleImpl
									.Builder
									.newInstance("DT_" + i + "_VT-" + Integer.toString(j + 1) + "#" + (k + 1))
									.setStartLocation(Location.newInstance(LCT))
									.setType(vt)
									.setReturnToDepot(RouteType)
									.build();

							vBuilder.addVehicle(vp);

						}else if(B1 != 0){

							if(InterfaceHorizon.Vehicle_Capacity == 1){

								array[i][19 + 7*j] = (int) capacity;

							}

							VehicleTypeImpl vt  = VehicleTypeImpl
									.Builder
									.newInstance("DT_" + i + "_VT-" + Integer.toString(j + 1) + "#" + (k + 1))
									.setFixedCost((double) array[i][20 + 7*j])
									.setCostPerDistance((double) array[i][21 + 7*j])
									.addCapacityDimension(0, (int) array[i][19 + 7*j])
									.build();

							Break myFirstBreak = Break
									.Builder.newInstance("brk_" + "DT_" + i + "_VT-" + Integer.toString(j + 1) + "#" + (k + 1))
									.setTimeWindow(TimeWindow.newInstance(array[i][22 + 7*j], array[i][23 + 7*j]))
									.setServiceTime(array[i][24 + 7*j]).build();

							VehicleImpl vp = VehicleImpl
									.Builder
									.newInstance("DT_" + i + "_VT-" + Integer.toString(j + 1) + "#" + (k + 1))
									.setStartLocation(Location.newInstance(LCT))
									.setType(vt)
									.setBreak(myFirstBreak)
									.setReturnToDepot(RouteType)
									.build();

							vBuilder.addVehicle(vp);

						}

					}

					if (InterfaceHorizon.Time_Window == 2){

						if (B1 == 0){

							if(InterfaceHorizon.Vehicle_Capacity == 1){

								array[i][19 + 7*j] = (int) capacity;

							}

							VehicleTypeImpl vt  = VehicleTypeImpl
									.Builder
									.newInstance("DT_" + i + "_VT-" + Integer.toString(j + 1) + "#" + (k + 1))
									.setCostPerWaitingTime(array[i][5])
									.setFixedCost((double) array[i][20 + 7*j])
									.setCostPerDistance((double) array[i][21 + 7*j])
									.addCapacityDimension(0, (int) array[i][19 + 7*j])
									.build();

							VehicleImpl vp = VehicleImpl
									.Builder
									.newInstance("DT_" + i + "_VT-" + Integer.toString(j + 1) + "#" + (k + 1))
									.setStartLocation(Location.newInstance(LCT))
									.setType(vt)
									.setReturnToDepot(RouteType)
									.setEarliestStart(array[i][6])
									.setLatestArrival(array[i][7])
									.build();

							vBuilder.addVehicle(vp);

						}else if(B1 != 0){

							if(InterfaceHorizon.Vehicle_Capacity == 1){

								array[i][19 + 7*j] = (int) capacity;

							}

							VehicleTypeImpl vt  = VehicleTypeImpl
									.Builder
									.newInstance("DT_" + i + "_VT-" + Integer.toString(j + 1) + "#" + (k + 1))
									.setCostPerWaitingTime(array[i][5])
									.setFixedCost((double) array[i][20 + 7*j])
									.setCostPerDistance((double) array[i][21 + 7*j])
									.addCapacityDimension(0, (int) array[i][19 + 7*j])
									.build();
							
							Break myFirstBreak = Break
									.Builder.newInstance("brk_" + "DT_" + i + "_VT-" + Integer.toString(j + 1) + "#" + (k + 1))
									.setTimeWindow(TimeWindow.newInstance(array[i][22 + 7*j], array[i][23 + 7*j]))
									.setServiceTime(array[i][24 + 7*j]).build();

							VehicleImpl vp = VehicleImpl
									.Builder
									.newInstance("DT_" + i + "_VT-" + Integer.toString(j + 1) + "#" + (k + 1))
									.setStartLocation(Location.newInstance(LCT))
									.setType(vt)
									.setBreak(myFirstBreak)
									.setReturnToDepot(RouteType)
									.setEarliestStart(array[i][6])
									.setLatestArrival(array[i][7])
									.build();

							vBuilder.addVehicle(vp);

						}

					}
					if (k + 1 == (int) array[i][18 + 7*j]){
						k = 0;
						break;
					}
				}       
			}
		}	

		if (InterfaceHorizon.Fleet_Size == 1){

			vBuilder.setFleetSize(FleetSize.FINITE);

		} else if (InterfaceHorizon.Fleet_Size == 2){

			vBuilder.setFleetSize(FleetSize.INFINITE);

		}
		
		if (InterfaceHorizon.Time_Window == 1){

			for( int i = 0; i < array.length - depots; i++ ) {
               
				LCT     = Integer.toString(i + depots);
				LCT_pck = Integer.toString(2*i + depots);
				LCT_dlv = Integer.toString(2*i + depots + 1);
				
				if(InterfaceHorizon.Pickup_Delivery == 1){

					Service service = Service
							.Builder
							.newInstance(Integer.toString(i + 1))
							.addSizeDimension(0, (int) array[i + depots][1])
							.setLocation(Location.newInstance(LCT))
							.build();

					vBuilder.addJob(service);

				}else if (InterfaceHorizon.Pickup_Delivery == 2){

					if (InterfaceHorizon.Backhaul == 1){

						Shipment shipment = Shipment
								.Builder
								.newInstance(Integer.toString(i + 1))
								.addSizeDimension(0, (int) array[i + depots][1])
								.setPickupLocation(Location.newInstance(LCT_pck))
								.setDeliveryLocation(Location.newInstance(LCT_dlv))
								.build();

						vBuilder.addJob(shipment);

					}else if (InterfaceHorizon.Backhaul == 2){

						Pickup pickup = Pickup
								.Builder
								.newInstance("pck_" + Integer.toString(i + 1))
								.addSizeDimension(0, (int) array[i + depots][1])
								.setLocation(Location.newInstance(LCT_pck))
								.build();
						Delivery delivery = Delivery
								.Builder
								.newInstance("dlv_" + Integer.toString(i + 1))
								.addSizeDimension(0, (int) array[i + depots][1])
								.setLocation(Location.newInstance(LCT_dlv))
								.build();

						vBuilder.addJob(pickup);
						vBuilder.addJob(delivery);

					}

				}

			}
		} else if (InterfaceHorizon.Time_Window == 2){

			for( int i = 0; i < array.length - depots; i++ ) {
				
				LCT     = Integer.toString(i + depots);
				LCT_pck = Integer.toString(2*i + depots);
				LCT_dlv = Integer.toString(2*i + depots + 1);

				if(InterfaceHorizon.Pickup_Delivery == 1){

					Service service = Service
							.Builder
							.newInstance(Integer.toString(i + 1))
							.addSizeDimension(0, (int) array[i + depots][1])
							.addTimeWindow(array[i + depots][6], array[i + depots][7])
							.setServiceTime(array[i + depots][4])
							.setLocation(Location.newInstance(LCT))
							.build();

					vBuilder.addJob(service);

				}else if (InterfaceHorizon.Pickup_Delivery == 2){

					if (InterfaceHorizon.Backhaul == 1){

						Shipment shipment = Shipment
								.Builder
								.newInstance(Integer.toString(i + 1))
								.addSizeDimension(0, (int) array[i + depots][1])
								.addPickupTimeWindow(array[i + depots][10], array[i + depots][11])
								.setPickupServiceTime(array[i + depots][12])
								.setPickupLocation(Location.newInstance(LCT_pck))
								.addDeliveryTimeWindow(array[i + depots][15], array[i + depots][16])
								.setDeliveryServiceTime(array[i + depots][17])
								.setDeliveryLocation(Location.newInstance(LCT_dlv))
								.build();

						vBuilder.addJob(shipment);

					}else if (InterfaceHorizon.Backhaul == 2){

						Pickup pickup = Pickup
								.Builder
								.newInstance("pck_" + Integer.toString(i + 1))
								.addSizeDimension(0, (int) array[i + depots][1])
								.addTimeWindow(array[i + depots][10], array[i + depots][11])
								.setServiceTime(array[i + depots][12])
								.setLocation(Location.newInstance(LCT_pck))
								.build();
						Delivery delivery = Delivery
								.Builder
								.newInstance("dlv_" + Integer.toString(i + 1))
								.addTimeWindow(array[i + depots][15], array[i + depots][16])
								.setServiceTime(array[i + depots][17])
								.addSizeDimension(0, (int) array[i + depots][1])
								.setLocation(Location.newInstance(LCT_dlv))
								.build();

						vBuilder.addJob(pickup);
						vBuilder.addJob(delivery);

					}			
				}
			}
		}

		VehicleRoutingTransportCostsMatrix.Builder costMatrixBuilder = VehicleRoutingTransportCostsMatrix.Builder.newInstance(false);

		for (int i = 0; i < arrayDistance.length; i++){
			for (int j = 0; j < arrayDistance.length; j++){
				if (i != j){
					costMatrixBuilder.addTransportDistance(Integer.toString(i), Integer.toString(j), arrayDistance[i][j]);
					costMatrixBuilder.addTransportTime(Integer.toString(i), Integer.toString(j), arrayTime[i][j]);
				}
			}
		}

		VehicleRoutingTransportCosts costMatrix = costMatrixBuilder.build();
		vBuilder.setRoutingCost(costMatrix)
		.setRoutingCost(costMatrix);

		VehicleRoutingProblem problem = vBuilder.build();

		VehicleRoutingAlgorithm algorithm = Jsprit
				                                  .Builder
				                                  .newInstance(problem)
				                                  .setProperty(Jsprit.Parameter.FIXED_COST_PARAM, "2.")
//				                                  .setProperty(Jsprit.Parameter.THREADS,"5")
//				                                  .setProperty(Jsprit.Parameter.THRESHOLD_INI, "0.1")
//				                                  .setProperty(Jsprit.Strategy.CLUSTER_REGRET, "0.")
//				                                  .setProperty(Jsprit.Strategy.CLUSTER_BEST, "0.")
//				                                  .setProperty(Jsprit.Strategy.WORST_REGRET, "0.")
//				                                  .setProperty(Jsprit.Strategy.RANDOM_REGRET, "0.")
//				                                  .setProperty(Jsprit.Strategy.RADIAL_REGRET, "0.")
//				                                  .setProperty(Jsprit.Parameter.CONSTRUCTION, Jsprit.Construction.BEST_INSERTION.toString())
				                                  .buildAlgorithm();

		if (InterfaceHorizon.Backhaul == 2){
			StateManager stateManager = new StateManager(problem);
			ConstraintManager constraintManager = new ConstraintManager(problem, stateManager);
			constraintManager.addConstraint(new ServiceDeliveriesFirstConstraint(), ConstraintManager.Priority.CRITICAL);

			algorithm = Jsprit
					.Builder
					.newInstance(problem)
					.setProperty(Jsprit.Parameter.FIXED_COST_PARAM, "2.")
//					.setProperty(Jsprit.Parameter.THREADS,"5")
//	                .setProperty(Jsprit.Parameter.THRESHOLD_INI, "0.1")
//	                .setProperty(Jsprit.Strategy.CLUSTER_REGRET, "0.")
//	                .setProperty(Jsprit.Strategy.CLUSTER_BEST, "0.")
//	                .setProperty(Jsprit.Strategy.WORST_REGRET, "0.")
//	                .setProperty(Jsprit.Strategy.RANDOM_REGRET, "0.")
//	                .setProperty(Jsprit.Strategy.RADIAL_REGRET, "0.")
//	                .setProperty(Jsprit.Parameter.CONSTRUCTION, Jsprit.Construction.BEST_INSERTION.toString())
					.setStateAndConstraintManager(stateManager,constraintManager)
					.buildAlgorithm();
		}
		
//		algorithm.setMaxIterations(2000); // default = 2000
//		TimeTermination prematureTermination = new TimeTermination(2.); //in seconds
//		vBuilder.setPrematureAlgorithmTermination(prematureTermination);
//		vBuilder.addListener(prematureTermination);
//		vBuilder.setPrematureAlgorithmTermination(new IterationWithoutImprovementTermination(200));
		
		Collection<VehicleRoutingProblemSolution> solutions = algorithm.searchSolutions();
		VehicleRoutingProblemSolution bestSolution = Solutions.bestOf(solutions);

		String path = new File("maps").getAbsolutePath();

		PrintStream standard = System.out;
		PrintStream out;
		try {
			out = new PrintStream(new FileOutputStream(path + "\\" + "output_.txt"));
			System.setOut(out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		SolutionPrinter.print(problem, bestSolution, SolutionPrinter.Print.VERBOSE);
		
		System.setOut(standard);

		Scanner file;
		PrintWriter writer;

		try {
			file = new Scanner(new File(path + "\\" + "output_.txt"));
			writer = new PrintWriter(path + "\\" + "output.txt");
			while (file.hasNext()) {
				String line = file.nextLine();
				writer.write(line.trim());
				writer.write("\n");
			}
			file.close();
			writer.close();
		} catch (FileNotFoundException ex) {
		}
	}
}
