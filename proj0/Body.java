public class Body {
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;
	static final double G = 6.67 * Math.pow(10, -11);

	public Body(double xP, double yP, double xV,
              double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b) {
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b) {
		double dx = b.xxPos - xxPos;
		double by = b.yyPos - yyPos;
		return Math.pow(Math.pow(dx, 2) + Math.pow(by, 2), 0.5);
	}

	public double calcForceExertedBy(Body b) {
		double dx = b.xxPos - xxPos;
		double by = b.yyPos - yyPos;
		return G * mass * b.mass / (Math.pow(dx, 2) + Math.pow(by, 2));
	}

	public double calcForceExertedByX(Body b) {
		double dx = b.xxPos - xxPos;
		return calcForceExertedBy(b) * dx / calcDistance(b);
	}

	public double calcForceExertedByY(Body b) {
		double dy = b.yyPos - yyPos;
		return calcForceExertedBy(b) * dy / calcDistance(b);
	}

	public double calcNetForceExertedByX(Body[] allBodies) {
		double FnetX = 0;
		for (int i = 0; i < allBodies.length; i += 1) {
			if (!allBodies[i].equals(this)) {
				FnetX += calcForceExertedByX(allBodies[i]);
			}
		}
		return FnetX;
	}

	public double calcNetForceExertedByY(Body[] allBodies) {
		double FnetY = 0;
		for (int i = 0; i < allBodies.length; i += 1) {
			if (!allBodies[i].equals(this)) {
				FnetY += calcForceExertedByY(allBodies[i]);	
			}
		}
		return FnetY;
	}

	public void update(double dt, double fX, double fY) {
		double aNetX = fX / mass;
		double aNetY = fY / mass;

		xxVel = xxVel + dt * aNetX;
		yyVel = yyVel + dt * aNetY;

		xxPos = xxPos + dt * xxVel;
		yyPos = yyPos + dt * yyVel;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
}