package streams;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
public class JavaStreams {
	/*
	 * Code followed from tutorial: 
	 * https://www.youtube.com/watch?v=t1-YZ6bF-g0&list=PLWgmznqebXE7z6iT3ULyg5FsSt6THEKFp&index=1
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		System.out.println("\n---------------------");
		
		// 1. Integer Stream
		IntStream
			.range(1, 10)
			.forEach(System.out::print);
		
		System.out.println("\n---------------------");
		
		/*
		  2. Integer Stream with skip 
			(skips the first five elements of the stream)
		 */
		IntStream
			.range(1, 10)
			.skip(5)
			.forEach(x -> System.out.println(x));
		
		System.out.println("\n---------------------");
		
		// 3. Integer Stream with sum
		System.out.print("Sum: ");
		System.out.println(
			IntStream
			.range(1,5)
			.sum()
		);
		
		System.out.println("\n---------------------");
		
		// 4. Stream.of, sorted and find first
		Stream.of("Jordan", "Dylan", "Amy", "Cat")
			.sorted()
			.findFirst()
			.ifPresent(System.out::print);
		
		System.out.println("\n---------------------");
		
		// 5. Stream from array, sort, filter, print
		String[] names = {
				"Al", "Ankit", "Kushal", 
				"Brent", "Sarika", "amanda", 
				"Hans", "Shivika", "Sarah"};
		// Stream.of(names) can also be used in this instance
		Arrays.stream(names)
			.filter(x -> x.startsWith("S"))
			.sorted()
			.forEach(System.out::println);
	
		System.out.println("\n---------------------");
		
		// 6. Average of squares in a integer array
		Arrays.stream(new int[] {2,4,6,8,10})
			.map(x -> x*x)
			.average()
			.ifPresent(System.out::println);
		
		System.out.println("\n---------------------");
		
		// 7. Stream from list, filter and print
		List<String> people = Arrays.asList(
				"Al", "Ankit", "Kushal", 
				"Brent", "Sarika", "amanda", 
				"Hans", "Shivika", "Sarah");
		
		people
			.stream()
			.map(String::toLowerCase)
			.filter(x -> x.startsWith("a"))
			.forEach(System.out::println);
		
		System.out.println("\n---------------------");
		
		List<Integer> nums = Arrays.asList(1,2,3,4,5);
		int total = nums.stream().reduce(0, Integer::sum);
		System.out.println(total);
		
		System.out.println("---------------------");
		
		// 8. Stream rows from text file, sort, filter and print
		
		Stream<String> bands = Files.lines(Paths.get("bands.txt"));
		bands
			.sorted()
			.filter(x -> x.length() > 13)
			.forEach(System.out::println);
		bands.close();
		
		System.out.println("---------------------");
		
		// 9. Stream rows from text file and save to list
		Stream<String> bands2 = Files.lines(Paths.get("bands.txt"));
		List<String> ls = bands2
			.sorted()
			.filter(x -> x.contains("jit"))
			.collect(Collectors.toList());
		ls.stream().forEach(System.out::println);
		
		System.out.println("---------------------");
		
		// 10. Stream rows from CSV (Comma Separated Values) file and count
		Stream<String> rows1 = Files.lines(Paths.get("data.txt"));
		
		int count = (int)rows1
			.map(x -> x.split(","))
			.filter(x -> x.length == 3)
			.count();
		System.out.println("Number of rows: " + count);
		rows1.close();

		System.out.println("---------------------");
		
		// 11. Stream rows from a CSV file, parsed data from rows
		/*
		 *  In the second column of the data filter values that are
		 * 	greater than 15
		 * 	print the three columns of each row
		 */
		
		
		Stream<String> rows2 = Files.lines(Paths.get("data.txt"));
		rows2
			.map(x -> x.split(","))
			.filter(x -> x.length == 3)
			.filter(x -> Integer.parseInt(x[1]) > 15)
			.forEach(x -> System.out.println(x[0] + " " + x[1] + " " + x[2]));
		rows2.close();
		
		System.out.println("---------------------");
		
		// 12. Stream rows from CSV file, store fields in HashMap
		/* when the row is greater than three 
		 * when the second column value is greater than 15
		 */
		
		Stream<String> rows3 = Files.lines(Paths.get("data.txt"));
		Map<String, Integer> map = new HashMap<>();
		
		map = rows3
				.map(x -> x.split(","))
				.filter(x -> x.length == 3)
				.filter(x -> Integer.parseInt(x[1]) > 15)
				.collect(Collectors.toMap(
						x -> x[0], 
						x -> Integer.parseInt(x[1])));
		rows3.close();
		
		for(String k : map.keySet()) {
			System.out.println(k + " " + map.get(k));
		}
		
		System.out.println("---------------------");
		// 13. Reduce - Sum
		
		// Method 1: Using the sum keyword
		long longTotal = Stream.of(1L, 2L, 3L, 4L, 5L)
				.reduce(0L, Long::sum);
		
		int intTotal = Stream.of(1, 2, 3, 4, 5)
				.reduce(0, Integer::sum);
		
		double doubleTotal = Stream.of(1.1, 2.2, 3.3, 4.4, 5.5)
				.reduce(0.0, Double::sum);
		
		System.out.println(longTotal + ", " + intTotal + ", " + doubleTotal);
		
		// Method 2: Using lambda expression
		long longTotal2 = Stream.of(1L, 2L, 3L, 4L, 5L)
				.reduce(0L, (Long b, Long c) -> b + c);
		
		int intTotal2 = Stream.of(1, 2, 3, 4, 5)
				.reduce(0, (Integer a, Integer b) -> a + b);
		
		double doubleTotal2 = Stream.of(1.1, 2.2, 3.3, 4.4, 5.5)
				.reduce(0.0, (Double a, Double b) -> a + b);
		
		System.out.println(longTotal2 + ", " + intTotal2 + ", " + doubleTotal2);
		
		System.out.println("---------------------");
		
		// 14. Reduction - summary statistics
		// Only works for Integer values
		// .summaryStatistics() is a terminal operation
		IntSummaryStatistics summary = IntStream.of(7,2,19,88,73,4,10).summaryStatistics();
		System.out.println(summary);
		
		// Other variations
		// Returns the max value
		IntStream.of(7,2,19,88,73,4,10).summaryStatistics().getMax();
		
		// Returns the minimum value
		IntStream.of(7,2,19,88,73,4,10).summaryStatistics().getMin();
		
		// Returns the average value
		IntStream.of(7,2,19,88,73,4,10).summaryStatistics().getAverage();
		
		// Returns the sum of all the values
		IntStream.of(7,2,19,88,73,4,10).summaryStatistics().getSum();
	
		System.out.println("---------------------");		

	}	// End main

}	// End class