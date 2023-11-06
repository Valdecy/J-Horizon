package guiHorizon;

import jspritCoreAlgorithm.VehicleRoutingAlgorithm;
import jspritCoreAlgorithmBox.Jsprit;
import jspritCoreProblem.Location;
import jspritCoreProblem.VehicleRoutingProblem;
import jspritCoreProblemJob.Service;
import jspritCoreProblemSolution.VehicleRoutingProblemSolution;
import jspritCoreProblemVehicle.VehicleImpl;
import jspritCoreProblemVehicle.VehicleImpl.Builder;
import jspritCoreProblemVehicle.VehicleType;
import jspritCoreProblemVehicle.VehicleTypeImpl;
import jspritCoreUtil.Solutions;
import java.util.Collection;


public class Util_WarmUp {
	public static void engines() {

        VehicleTypeImpl.Builder vehicleTypeBuilder = VehicleTypeImpl.Builder.newInstance("vehicleType").addCapacityDimension(0, 2);
        VehicleType vehicleType = vehicleTypeBuilder.build();
        Builder vehicleBuilder = VehicleImpl.Builder.newInstance("vehicle");
        vehicleBuilder.setStartLocation(Location.newInstance(10, 10));
        vehicleBuilder.setType(vehicleType);
        VehicleImpl vehicle = vehicleBuilder.build();

        Service service1 = Service.Builder.newInstance("1").addSizeDimension(0, 1).setLocation(Location.newInstance(5, 7)).build();
        Service service2 = Service.Builder.newInstance("2").addSizeDimension(0, 1).setLocation(Location.newInstance(1, 7)).build();

        VehicleRoutingProblem.Builder vrpBuilder = VehicleRoutingProblem.Builder.newInstance();
        vrpBuilder.addVehicle(vehicle);
        vrpBuilder.addJob(service1).addJob(service2);

        VehicleRoutingProblem problem = vrpBuilder.build();
        VehicleRoutingAlgorithm algorithm = Jsprit.createAlgorithm(problem);

        Collection<VehicleRoutingProblemSolution> solutions = algorithm.searchSolutions();
        Solutions.bestOf(solutions);
    }
}
