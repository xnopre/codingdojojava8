package fr.remiguitreau.codingdojo.java8;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CDJava8Test {

	@InjectMocks
	private CDJava8 cdjava8;
	
	@Mock
	private MeasurementSubmitter measurementSubmitterMock;
	
	@Test
	public void test_submit_only_positive_measurements_and_by_decreasing_order() {
		final Measurement meas1 = new Measurement(new Date(1000), -3f);
		final Measurement meas2 = new Measurement(new Date(2000), 5f);
		final Measurement meas3 = new Measurement(new Date(3000), 1f);
		final Measurement meas4 = new Measurement(new Date(4000), 2f);
		final Measurement meas5 = new Measurement(new Date(5000), 0.7f);
		
		cdjava8.submitPositiveMeasurementByDecreasingValueOrder(Arrays.asList(meas1, meas2, meas3, meas4, meas5));
		
		final InOrder check = Mockito.inOrder(measurementSubmitterMock);
		check.verify(measurementSubmitterMock).submitMeasurement(meas2);
		check.verify(measurementSubmitterMock).submitMeasurement(meas4);
		check.verify(measurementSubmitterMock).submitMeasurement(meas3);
		check.verify(measurementSubmitterMock).submitMeasurement(meas5);
		check.verifyNoMoreInteractions();
	}
	
	@Test
	public void test_retrieve_only_values_that_at_least_good_quality() {
		final Measurement meas1 = new Measurement(new Date(1000), -3f, Quality.BAD);
		final Measurement meas2 = new Measurement(new Date(2000), 5f, Quality.EXCELLENT);
		final Measurement meas3 = new Measurement(new Date(3000), 1f, Quality.GOOD);
		final Measurement meas4 = new Measurement(new Date(4000), 2f);
		final Measurement meas5 = new Measurement(new Date(5000), 0.7f, Quality.GOOD);
		
		Assert.assertEquals(Arrays.asList(5f, 1f, 0.7f), cdjava8.onlyGoodValues(Arrays.asList(meas1, meas2, meas3, meas4, meas5)));
	}
	
	@Test
	public void test_organize_measurements_by_quality_and_sort_by_date() {
		final Measurement meas1 = new Measurement(new Date(1000), -3f, Quality.BAD);
		final Measurement meas2 = new Measurement(new Date(2000), 5f, Quality.EXCELLENT);
		final Measurement meas3 = new Measurement(new Date(3000), 1f, Quality.GOOD);
		final Measurement meas4 = new Measurement(new Date(4000), 2f);
		final Measurement meas5 = new Measurement(new Date(5000), 0.7f, Quality.GOOD);
		
		final Map<Quality, List<Measurement>> measByQuality = cdjava8.organizeMeasurementsByQualityAndSortedByTimestamp(Arrays.asList(meas4, meas2, meas5, meas1, meas3));
		
		Assert.assertEquals(3, measByQuality.size());
		Assert.assertEquals(Arrays.asList(meas2), measByQuality.get(Quality.EXCELLENT));
		Assert.assertEquals(Arrays.asList(meas3, meas5), measByQuality.get(Quality.GOOD));
		Assert.assertEquals(Arrays.asList(meas1), measByQuality.get(Quality.BAD));
	}
}
