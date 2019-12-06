package leave.nucleus;

import org.junit.Assert;
import org.junit.Test;

public class TestNullAssignment {
	
	@Test(expected = NullPointerException.class)
	public void testNullAssignmentToPrimitiveDataType() {
		Integer nullInteger = null;
		int nullPrimitiveInt = nullInteger;
	}

	@Test(expected = NumberFormatException.class)
	public void testStringValueOfNull() {
		Integer creditPeriodDays = null;
		String creditDays = creditPeriodDays != null ? String.valueOf(creditPeriodDays) : null;
		Assert.assertEquals(Integer.valueOf(creditDays), creditPeriodDays);
	}

}