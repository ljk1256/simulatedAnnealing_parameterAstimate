package Algorithm;

import java.util.ArrayList;
import java.util.Random;

public class SimulatedAnnealing {
    private double t;   
    private double a;   
    private int niter;  
    public ArrayList<Double> hist;
    public ArrayList<Double> hista;
    public ArrayList<Double> histb;

    public SimulatedAnnealing(double t, double a, int niter) {
        this.t = t;
        this.a = a;
        this.niter = niter;
        hist = new ArrayList<>();
        hista = new ArrayList<>();
        histb = new ArrayList<>();
    }

    public double solve(Problem p, double lower, double upper) {
        Random r = new Random();
        double a0 = r.nextDouble() * (upper - lower) ; 
        double b0 = r.nextDouble() * (upper - lower) * 50 ;      
        double cost0 = p.fit(a0, b0);                                   
        hist.add(cost0);
        hista.add(a0);
        histb.add(b0);

        for(int i=0; i<niter; i++) {    
            int kt = (int) Math.round(t * 10);
            for(int j=0; j<kt; j++) {
                double a1 = r.nextDouble() * (upper - lower) ;
                double b1 = r.nextDouble() * (upper - lower) * 50 ;   
                double cost1 = p.fit(a1, b1);
                if(p.isNeighborBetter(cost0, cost1)) {   
                    a0 = a1;
                    b0 = b1;
                    cost0 = cost1;
                    hist.add(cost0);
                    hista.add(a0);
                    histb.add(b0);
                    
                } else {    
                    double d = cost1 - cost0;	
                    double p0 = Math.exp(-d/t);
                    if(r.nextDouble() < p0) {
                        a0 = a1;
                        b0 = b1;
                        cost0 = cost1;
                        hist.add(cost0);
                        hista.add(a0);
                        histb.add(b0);
                    }
                }
            }
            t *= a;
        }
        return a0;
    }
    
    public interface Problem {
        double fit(double a, double b);
        boolean isNeighborBetter(double f0, double f1);
    }


	public static void main(String[] args) {
		 SimulatedAnnealing sa = new SimulatedAnnealing(1, 0.97, 1000);
		    sa.solve(new Problem() {
	            @Override
	            public double fit(double a, double b) {
	                return 10000*a*a + 200*a*b + b*b - 140000*a - 1400*b + 490000 ;  
	            }

	            @Override
	            public boolean isNeighborBetter(double f0, double f1) {
	                return f1 > f0;
	            }
	        }, 1, 10);

	        System.out.println(sa.hist);
	        System.out.println(sa.hista);
	        System.out.println(sa.histb);

	    }
	}
