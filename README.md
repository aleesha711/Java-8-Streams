# Streams
Java 8 came with the most useful and fast computational features undoubtedly that are lamdas and streams API.

The whole idea of streams is to enable functional-style operations on streams of elements. A stream is an abstraction, it’s not a data structure. It’s not a collection where you can store elements. The most important difference between a stream and a structure is that a stream doesn’t hold the data. For example you cannot point to a location in the stream where a certain element exists. You can only specify the functions that operate on that data. A stream is an abstraction of a non-mutable collection of functions applied in some order to the data.

A stream represents a pipeline through which the data will flow and the functions to operate on the data. The streams API gives us the power to specify a sequence of operations on the data in individual steps. We don’t specify any conditional processing code, we are not tempted to write large complex functions, we don’t care about the data flow. In fact, we only bother ourselves with one data processing step at a time: we compose the functions and the data flows through the functions by itself by the power of the streams framework.
```
List<Integer> numbers = Arrays.asList(1, 2, 3, 4); 
List<Integer> result = numbers.stream()
  .filter(e -> (e % 2) == 0)
  .map(e -> e * 2)
  .collect(toList());
  ```
  
# Common operations on streams
In Java 8 you can easily obtain a stream from any collection by calling the stream() method. After that there are a couple of fundamental functions that you’ll encounter all the time.

 * **Filter** returns a new stream that contains some of the elements of the original. It accepts the predicate to compute which elements should be returned in the new stream and removes the rest. In the imperative code we would employ the conditional logic to specify what should happen if an element satisfies the condition. In the functional style we don’t bother with ifs, we filter the stream and work only on the values we require.
 * **Map** transforms the stream elements into something else, it accepts a function to apply to each and every element of the stream and returns a stream of the values the parameter function produced. This is the bread and butter of the streams API, map allows you to perform a computation on the data inside a stream.
 * **Reduce** (also sometimes called a fold) performs a reduction of the stream to a single element. You want to sum all the integer values in the stream – you want to use the reduce function. You want to find the maximum in the stream – reduce is your friend.
 * **Collect** is the way to get out of the streams world and obtain a concrete collection of values, like a list in the example above.

# Intermediate and terminal operations
One of the virtues of streams is that they are lazily evaluated. Some operations on the streams, particularly the functions that return an instance of the stream: filter, map, are called intermediate. This means that they won’t be evaluated when they are specified. Instead the computation will happen when the result of that operation is necessary.

This means that if we just specify the code like:
```
Stream<String> names = people.stream()
  .filter(p -> p.getGender() == Gender.FEMALE)
  .map(Person::getName)
  .map(String::toUpperCase);
  ```
All operations that return something other than a stream are terminal. Operations like forEach, collect, reduce are terminal. This makes streams particularly efficient at handling large amounts of data.


