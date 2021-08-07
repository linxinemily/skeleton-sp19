public class NBody {
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);

		String filename = args[2];

		Body[] bodies = readBodies(filename);
		double radius = readRadius(filename);

		int init_time = 0;

		while (init_time <= T) {
			double[] xForces = new double[bodies.length];
			double[] yForces = new double[bodies.length];

			// calculate the net x and y forces for each Body
			for (int i = 0; i < bodies.length; i+=1) {
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}

			// update each body’s position, velocity, and acceleration
			for (int j = 0; j < bodies.length; j+=1) {
				bodies[j].update(dt, xForces[j], yForces[j]);
			}

			// draw background
			StdDraw.enableDoubleBuffering();
			StdDraw.setScale(-radius, radius);
			StdDraw.clear();
			StdDraw.picture(0, 0, "images/starfield.jpg");

			for (int k = 0; k < bodies.length; k+=1) {
				bodies[k].draw();
			}

			// copy the offscreen canvas to the onscreen canvas
			StdDraw.show();

			// wait for a short while
			StdDraw.pause(10);

			init_time += dt;
		}
		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int m = 0; m < bodies.length; m++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
              bodies[m].xxPos, bodies[m].yyPos, bodies[m].xxVel,
              bodies[m].yyVel, bodies[m].mass, bodies[m].imgFileName
            );
		}
	}

	public static double readRadius(String file_name) {
		In in = new In(file_name);
		in.readLine();
		return in.readDouble();
	}

	public static Body[] readBodies(String file_name) {
		In in = new In(file_name);

		int length = in.readInt();

		Body[] bodies = new Body[length];

		in.readDouble(); // radius

		for (int i = 0; i < length; i+=1) {

			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();

			Body body = new Body(
				xxPos,
				yyPos,
				xxVel,
				yyVel,
				mass,
				imgFileName
			);

			bodies[i] = body;
		}
		return bodies;
	}
}