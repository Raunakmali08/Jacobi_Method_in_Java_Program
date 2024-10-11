import java.util.Scanner;
import java.util.Arrays;

public class Jacobi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input matrix size
        System.out.print("Enter the number of variables: ");
        int n = scanner.nextInt();

        // Initialize matrix A and vector b
        double[][] A = new double[n][n];
        double[] b = new double[n];

        // Input matrix A
        System.out.println("Enter the coefficients matrix A:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = scanner.nextDouble();
            }
        }

        // Input vector b
        System.out.println("Enter the constant vector b:");
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextDouble();
        }

        // Input tolerance and maximum iterations
        System.out.print("Enter the tolerance: ");
        double tolerance = scanner.nextDouble();

        System.out.print("Enter the maximum number of iterations: ");
        int maxIterations = scanner.nextInt();

        // Initialize guess vector x0
        double[] x0 = new double[n];
        Arrays.fill(x0, 0); // Start with an initial guess of 0

        // Call the Jacobi method
        jacobiMethod(A, b, x0, tolerance, maxIterations);
        
        scanner.close();
    }

    public static void jacobiMethod(double[][] A, double[] b, double[] x0, double tolerance, int maxIterations) {
        int n = b.length;
        double[] x1 = new double[n];  // To store updated solution
        Arrays.fill(x1, 0);           // Start with an initial guess of 0
        double[] xOld = new double[n]; // Store the previous iteration values
        
        for (int iter = 0; iter < maxIterations; iter++) {
            System.arraycopy(x0, 0, xOld, 0, n);  // Copy current guesses to old values

            // Iterate over each equation
            for (int i = 0; i < n; i++) {
                double sum = b[i];
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        sum -= A[i][j] * x0[j];
                    }
                }
                x1[i] = sum / A[i][i]; // Update xi
            }

            // Calculate error (maximum difference between current and previous iterations)
            double maxError = 0.0;
            for (int i = 0; i < n; i++) {
                maxError = Math.max(maxError, Math.abs(x1[i] - xOld[i]));
            }

            // Print iteration info
            System.out.println("Iteration " + (iter + 1) + ": " + Arrays.toString(x1));

            // Check if the solution has converged
            if (maxError < tolerance) {
                System.out.println("Converged after " + (iter + 1) + " iterations.");
                return;
            }

            // Update x0 for the next iteration
            System.arraycopy(x1, 0, x0, 0, n);
        }

        System.out.println("Maximum iterations reached without convergence.");
    }
}

