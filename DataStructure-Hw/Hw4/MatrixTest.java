import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public abstract class MatrixTest<T> {

	private MatrixFactory<T> matrixFactory = new MatrixFactory<T>();
	private Matrix<T> matrix3;
	private T defaultVal;
	private T newInstance2;
	private T newInstance3;

	/**
	 * @return a new instance of parameter T. Two instances which are created by
	 *         this method should be different according to "equals" method. For
	 *         example for T=Object, the implementation can be "return new
	 *         Object();".
	 */
	public abstract T getParameterInstance();

	@Before
	public void setUp() throws Exception {
		defaultVal = getParameterInstance();
		newInstance2 = getParameterInstance();
		newInstance3 = getParameterInstance();

		matrix3 = matrixFactory.getMatrix(3, defaultVal);

		if ((defaultVal.equals(newInstance2)) || (defaultVal.equals(newInstance3))
				|| (newInstance3.equals(newInstance2))) {
			fail("new instances should be different");
		}
	}

	@Test
	public void testSparseMatrixSize() {
		assertEquals(defaultVal, matrix3.get(1, 2));
		assertEquals(defaultVal, matrix3.get(1, 3));
		assertEquals(defaultVal, matrix3.get(3, 2));
		try {
			matrix3.get(1, 4);
			fail("Should not work");
		} catch (Exception e) {
		}
		try {
			matrix3.get(0, 1);
			fail("Should not work");
		} catch (Exception e) {
		}
		assertEquals(defaultVal, matrix3.get(1, 2));
		assertEquals(defaultVal, matrix3.get(1, 3));
		assertEquals(defaultVal, matrix3.get(3, 2));
		try {
			matrix3.get(1, 4);
			fail("Should not work");
		} catch (Exception e) {
		}
		try {
			matrix3.get(0, 1);
			fail("Should not work");
		} catch (Exception e) {
		}

		Matrix<T> matrixDefSize = matrixFactory.getMatrix(defaultVal);
		assertEquals(defaultVal, matrixDefSize.get(1, 2));
		assertEquals(defaultVal, matrixDefSize.get(1, 3));
		assertEquals(defaultVal, matrixDefSize.get(100, 100));
		try {
			matrix3.get(101, 1);
			fail("Should not work");
		} catch (Exception e) {
		}
		try {
			matrix3.get(0, 1);
			fail("Should not work");
		} catch (Exception e) {
		}

	}
	
	@Test
	public void testSparseMatrixSet() {
		// check if the elements insert to the matrix correctly
		matrix3.set(1, 1, newInstance3);
		matrix3.set(2, 1, newInstance2);
		assertEquals(newInstance3, matrix3.get(1, 1));
		assertEquals(newInstance2, matrix3.get(2, 1));

		matrix3.set(1, 1, newInstance2);
		assertEquals(newInstance2, matrix3.get(1, 1));
		assertEquals(defaultVal, matrix3.get(1, 2));

		// checking if can set a element at the out of range size
		try {
			matrix3.set(105, 102, newInstance2);
			fail("Should not work");
		}catch(Exception e) {}
		try {
			matrix3.set(0, 1, newInstance3);
			fail("Should not work");
		}catch(Exception e) {}	
		try {
			matrix3.set(0, -1, newInstance3);
			fail("Should not work");
		}catch(Exception e) {}		
	}
	
	@Test
	public void testSparseMatrixTranspose() {
		matrix3.set(2, 1, newInstance2);
		matrix3.set(2, 2, newInstance3);	

		matrix3.transpose();
		assertEquals(newInstance3, matrix3.get(2, 2));
		assertNotEquals(newInstance2, matrix3.get(2, 1));
		assertEquals(newInstance2, matrix3.get(1, 2));
		
		matrix3.transpose();
		assertNotEquals(newInstance2, matrix3.get(1, 2));
		assertEquals(newInstance2, matrix3.get(2, 1));
		assertEquals(newInstance3, matrix3.get(2, 2));
	}
	
	@Test
	public void testSparseMatrixGetTranspose() {
		matrix3.set(1, 2, newInstance3);
		matrix3.set(2, 3, newInstance2);
		Matrix<T> matrix3_transpose = matrix3.getTranspose();
		assertEquals(newInstance3,matrix3_transpose.get(2, 1));
		assertEquals(newInstance2,matrix3_transpose.get(3, 2));
		assertNotEquals(newInstance3,matrix3_transpose.get(1, 2));
		assertNotEquals(newInstance2,matrix3_transpose.get(2, 3));

		assertEquals(newInstance3,matrix3.get(1, 2));
		assertEquals(newInstance2,matrix3.get(2, 3));

	}
	

}
