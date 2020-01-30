import java.util.*;

public class ResolverImpl implements PuzzleResolver {

    private Map<Integer, Integer> allPaths = new HashMap<>(); // all available paths
    private ArrayDeque<Integer> states;  // all available states of array
    private Map<Integer, Integer> checked; // checked paths

    private int template[] = {1,2,3,4,0,5,6,7}; // target state
    private final int MAX_VERT = 8; // max number of vertices

    @Override
    public int[] resolve(int[] start) {
        if(start == template) return new int[0];

        int startState = arrToInt(start);

        checked = new HashMap<>();
        checked.put(startState, 0);

        states = new ArrayDeque<>();
        states.addLast(startState);

        // filling all possible states of vertices and all paths
        while (!states.isEmpty()) {
            int curr = states.remove();
            int[] curState = intToArr(curr, new int[MAX_VERT]);
            for (Integer edge : getNullEdgesStates(curState)) {
                if (!checked.containsKey(edge)) {
                    checked.put(edge, checked.get(curr) + 1);
                    states.addLast(edge);
                    allPaths.put(edge, curr);
                }
                if (edge == arrToInt(template)) {
                    states.clear();
                    break;
                }
            }

        }

        int result[] = getShortPath(arrToInt(template), startState);
        return result;
    }


    // searching of shortest path from all available
    private int[] getShortPath(int target, int start) {
        int[] shortPath;
        List<Integer> indexList = new ArrayList<>();
        List<Integer> valueList = new ArrayList<>();

        int temp = target;
        while (temp != start) {
            int prev = allPaths.get(temp);
            indexList.add(temp);
            valueList.add(prev);
            temp = prev;
        }

        shortPath = new int[indexList.size()];

        for (int i = 0; i < indexList.size(); i++) {
            int[] indices = new int[MAX_VERT], values = new int[MAX_VERT];

            intToArr(indexList.get(indexList.size() - 1 - i), indices);
            intToArr(valueList.get(indexList.size() - 1 - i), values);

            int ind = getNullIndex(indices);

            shortPath[i] = values[ind];
        }
        return shortPath;
    }

    // convert an array to eight-digit number
    private int arrToInt(int[] arr) {
        int res = 0;
        for (int value : arr) {
            res += value;
            res *= 10;
        }
        return res / 10;
    }

    // convert an eight-digit number to array
    private int[] intToArr(int source, int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = source % 10;
            source /= 10;
        }
        return arr;
    }

    // returning the index of empty cell from values array
    private int getNullIndex(int[] array) {
        int result = -1;
        for (int index = 0; index < array.length - 1; index++) {
            if (array[index] == 0) {
                result = index;
            }
        }
        return result;
    }


    // returns the edge between two values ​​as an 8-digit number
    private int createEdge(int[] inp, int i, int j) {
        int res = arrToInt(exchange(inp, i, j));
        exchange(inp, i, j);
        return res;
    }

    // replacement of values ​​of two array indices
    private int[] exchange(int[] inp, int i, int j) {
        int tmp = inp[i];
        inp[i] = inp[j];
        inp[j] = tmp;
        return inp;
    }

    // searches for all neighbors of zero and returns their 8-digit states
    public Set<Integer> getNullEdgesStates(int[] arr) {
        int emptyIndex = getNullIndex(arr);
        HashSet<Integer> edgeSet = new HashSet<>();

        switch(emptyIndex) {
            case(0) :
                edgeSet.add(createEdge(arr, 0, 1));
                edgeSet.add(createEdge(arr, 0, 2));
                break;
            case(1) :
                edgeSet.add(createEdge(arr, 1, 0));
                edgeSet.add(createEdge(arr, 1, 2));
                edgeSet.add(createEdge(arr, 1, 3));
                break;
            case(2) :
                edgeSet.add(createEdge(arr, 2, 0));
                edgeSet.add(createEdge(arr, 2, 1));
                edgeSet.add(createEdge(arr, 2, 5));
                break;
            case(3) :
                edgeSet.add(createEdge(arr, 3, 1));
                edgeSet.add(createEdge(arr, 3, 4));
                edgeSet.add(createEdge(arr, 3, 6));
                break;
            case(4) :
                edgeSet.add(createEdge(arr, 4, 3));
                edgeSet.add(createEdge(arr, 4, 5));
                break;
            case(5) :
                edgeSet.add(createEdge(arr, 5, 2));
                edgeSet.add(createEdge(arr, 5, 4));
                edgeSet.add(createEdge(arr, 5, 7));
                break;
            case(6) :
                edgeSet.add(createEdge(arr, 6, 3));
                edgeSet.add(createEdge(arr, 6, 7));
                break;
            case(7) :
                edgeSet.add(createEdge(arr, 7, 6));
                edgeSet.add(createEdge(arr, 7, 5));
                break;
        }
        return edgeSet;
    }
}