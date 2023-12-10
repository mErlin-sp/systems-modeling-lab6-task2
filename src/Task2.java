import PetriObj.*;

import java.util.ArrayList;
import java.util.Objects;

public class Task2 {

    private static final int timeModeling = 1000;

    private static final double avgItemArrival = 15, avgProcessTime = 60, avgMoveTime = 60;

    public static void main(String[] args) throws ExceptionInvalidTimeDelay {
        PetriObjModel model = getModel();
        model.setIsProtokol(false);
        model.go(timeModeling);

        System.out.println("Input values:");
        System.out.println("Average Item Arrival: " + avgItemArrival);
        System.out.println("Average Item Process Time: " + avgProcessTime);
        System.out.println("Average Item Move Time: " + avgMoveTime);
        System.out.println("\nOutput values:");

        double avgDevice1load = 0.0, avgDevice2load = 0.0, avgDevice3load = 0.0, avgDevice4load = 0.0, avgDevice5load = 0.0;

        for (PetriP p : model.getListObj().getFirst().getNet().getListP()) {
            if (Objects.equals(p.getName(), "P6")) {
                avgDevice1load = (1 - p.getMean());
                System.out.printf("Average Device 1 Load: %f \n", avgDevice1load);
            } else if (Objects.equals(p.getName(), "P7")) {
                avgDevice2load = (1 - p.getMean());
                System.out.printf("Average Device 2 Load: %f \n", avgDevice2load);
            } else if (Objects.equals(p.getName(), "P8")) {
                avgDevice3load = (1 - p.getMean());
                System.out.printf("Average Device 3 Load: %f \n", avgDevice3load);
            } else if (Objects.equals(p.getName(), "P9")) {
                avgDevice4load = (1 - p.getMean());
                System.out.printf("Average Device 4 Load: %f \n", avgDevice4load);
            } else if (Objects.equals(p.getName(), "P10")) {
                avgDevice5load = (1 - p.getMean());
                System.out.printf("Average Device 5 Load: %f \n", avgDevice5load);
            } else if (Objects.equals(p.getName(), "P11")) {
                System.out.printf("Items processed: %d \n", p.getMark());
            }
        }

        System.out.printf("Average Device Busy: %f \n", 5.0 - ((1 - avgDevice1load) + (1 - avgDevice2load) + (1 - avgDevice3load) + (1 - avgDevice4load) + (1 - avgDevice5load)));

    }

    public static PetriObjModel getModel() throws ExceptionInvalidTimeDelay {
        ArrayList<PetriSim> list = new ArrayList<>();
        list.add(new PetriSim(getNet(avgItemArrival, avgProcessTime, avgMoveTime)));
        return new PetriObjModel(list);
    }


    public static PetriNet getNet(double avgItemArrival, double avgProcessTime, double avgMoveTime) throws ExceptionInvalidTimeDelay {
        ArrayList<PetriP> d_P = new ArrayList<>();
        ArrayList<PetriT> d_T = new ArrayList<>();
        ArrayList<ArcIn> d_In = new ArrayList<>();
        ArrayList<ArcOut> d_Out = new ArrayList<>();
        d_P.add(new PetriP("P0", 1));
        d_P.add(new PetriP("P1", 0));
        d_P.add(new PetriP("P2", 0));
        d_P.add(new PetriP("P3", 0));
        d_P.add(new PetriP("P4", 0));
        d_P.add(new PetriP("P5", 0));
        d_P.add(new PetriP("P6", 1));
        d_P.add(new PetriP("P7", 1));
        d_P.add(new PetriP("P8", 1));
        d_P.add(new PetriP("P9", 1));
        d_P.add(new PetriP("P10", 1));
        d_P.add(new PetriP("P11", 0));
        d_T.add(new PetriT("T1", avgItemArrival));
        d_T.add(new PetriT("T2", avgMoveTime));
        d_T.add(new PetriT("T3", avgMoveTime));
        d_T.add(new PetriT("T4", avgMoveTime));
        d_T.add(new PetriT("T5", avgMoveTime));
        d_T.add(new PetriT("T6", avgMoveTime));
        d_T.add(new PetriT("T7", avgProcessTime));
        d_T.get(6).setDistribution("exp", d_T.get(6).getTimeServ());
        d_T.get(6).setParamDeviation(0.0);
        d_T.get(6).setPriority(1);
        d_T.add(new PetriT("T8", avgProcessTime));
        d_T.get(7).setDistribution("exp", d_T.get(7).getTimeServ());
        d_T.get(7).setParamDeviation(0.0);
        d_T.get(7).setPriority(1);
        d_T.add(new PetriT("T9", avgProcessTime));
        d_T.get(8).setDistribution("exp", d_T.get(8).getTimeServ());
        d_T.get(8).setParamDeviation(0.0);
        d_T.get(8).setPriority(1);
        d_T.add(new PetriT("T10", avgProcessTime));
        d_T.get(9).setDistribution("exp", d_T.get(9).getTimeServ());
        d_T.get(9).setParamDeviation(0.0);
        d_T.get(9).setPriority(1);
        d_T.add(new PetriT("T11", avgProcessTime));
        d_T.get(10).setDistribution("exp", d_T.get(10).getTimeServ());
        d_T.get(10).setParamDeviation(0.0);
        d_T.get(10).setPriority(1);
        d_In.add(new ArcIn(d_P.get(8), d_T.get(8), 1));
        d_In.add(new ArcIn(d_P.get(3), d_T.get(8), 1));
        d_In.add(new ArcIn(d_P.get(2), d_T.get(2), 1));
        d_In.add(new ArcIn(d_P.get(1), d_T.get(1), 1));
        d_In.add(new ArcIn(d_P.get(6), d_T.get(6), 1));
        d_In.add(new ArcIn(d_P.get(1), d_T.get(6), 1));
        d_In.add(new ArcIn(d_P.get(10), d_T.get(10), 1));
        d_In.add(new ArcIn(d_P.get(5), d_T.get(10), 1));
        d_In.add(new ArcIn(d_P.get(0), d_T.get(0), 1));
        d_In.add(new ArcIn(d_P.get(9), d_T.get(9), 1));
        d_In.add(new ArcIn(d_P.get(4), d_T.get(9), 1));
        d_In.add(new ArcIn(d_P.get(3), d_T.get(3), 1));
        d_In.add(new ArcIn(d_P.get(5), d_T.get(5), 1));
        d_In.add(new ArcIn(d_P.get(4), d_T.get(4), 1));
        d_In.add(new ArcIn(d_P.get(7), d_T.get(7), 1));
        d_In.add(new ArcIn(d_P.get(2), d_T.get(7), 1));
        d_Out.add(new ArcOut(d_T.get(8), d_P.get(8), 1));
        d_Out.add(new ArcOut(d_T.get(8), d_P.get(11), 1));
        d_Out.add(new ArcOut(d_T.get(2), d_P.get(3), 1));
        d_Out.add(new ArcOut(d_T.get(1), d_P.get(2), 1));
        d_Out.add(new ArcOut(d_T.get(6), d_P.get(6), 1));
        d_Out.add(new ArcOut(d_T.get(6), d_P.get(11), 1));
        d_Out.add(new ArcOut(d_T.get(10), d_P.get(10), 1));
        d_Out.add(new ArcOut(d_T.get(10), d_P.get(11), 1));
        d_Out.add(new ArcOut(d_T.get(0), d_P.get(0), 1));
        d_Out.add(new ArcOut(d_T.get(0), d_P.get(1), 1));
        d_Out.add(new ArcOut(d_T.get(9), d_P.get(9), 1));
        d_Out.add(new ArcOut(d_T.get(9), d_P.get(11), 1));
        d_Out.add(new ArcOut(d_T.get(3), d_P.get(4), 1));
        d_Out.add(new ArcOut(d_T.get(5), d_P.get(1), 1));
        d_Out.add(new ArcOut(d_T.get(4), d_P.get(5), 1));
        d_Out.add(new ArcOut(d_T.get(4), d_P.get(5), 1));
        d_Out.add(new ArcOut(d_T.get(7), d_P.get(7), 1));
        d_Out.add(new ArcOut(d_T.get(7), d_P.get(11), 1));
        PetriNet d_Net = new PetriNet("task2", d_P, d_T, d_In, d_Out);
        PetriP.initNext();
        PetriT.initNext();
        ArcIn.initNext();
        ArcOut.initNext();

        return d_Net;
    }

}

