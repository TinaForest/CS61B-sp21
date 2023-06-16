public class NBody {
    public static double readRadius(String s){
        In in = new In(s);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String s){
        In in =new In(s);
        int n = in.readInt();
        in.readDouble();
        Planet[] ps= new Planet[5];
        for (int i=0;i<n;i++){
            ps[i] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
        }
        return ps;
    }

    public static void main(String[] args){
        double T= Double.parseDouble(args[0]);
        double dt= Double.parseDouble(args[1]);
        String filename=args[2];
        double r=readRadius(filename);
        Planet[] ps=readPlanets(filename);
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-r, r);
        double t=0;
        double[] xForces=new double[ps.length];
        double[] yForces=new double[ps.length];
        while(t<T){
            StdDraw.clear();
            StdDraw.picture(0, 0, "./images/starfield.jpg");
            for(int i=0;i<ps.length;i++){
                xForces[i]=ps[i].calcNetForceExertedByX(ps);
                yForces[i]=ps[i].calcNetForceExertedByY(ps);
            }
            for(int i=0;i<ps.length;i++){
                ps[i].update(dt,xForces[i],yForces[i]);
                StdDraw.picture(ps[i].xxPos, ps[i].yyPos, "./images/"+ps[i].imgFileName);
            }
            StdDraw.show();
            StdDraw.pause(10);
            t+=dt;
        }
        StdOut.printf("%d\n", ps.length);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < ps.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    ps[i].xxPos, ps[i].yyPos, ps[i].xxVel,
                    ps[i].yyVel, ps[i].mass, ps[i].imgFileName);
        }
    }
}
