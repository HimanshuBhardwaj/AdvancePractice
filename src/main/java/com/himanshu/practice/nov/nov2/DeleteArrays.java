package com.himanshu.practice.nov.nov2;

import lombok.AllArgsConstructor;

import java.util.*;

/**
 * Created by himanshubhardwaj on 02/11/19.
 */
public class DeleteArrays {

    public static void main(String[] args) {
        DeleteArrays selection = new DeleteArrays();
        long[] val = new DeleteArrays().doDelete(100000, 5, 8, 424242, 474747, 123456789);
        System.out.println(val[0]);
        System.out.println(val[1]);
    }

    public long[] doDelete(int a, int b, int c, long x, long y, long z) {
        long A[] = new long[a];
        long B[] = new long[b];
        long C[] = new long[c];


        A[0] = 33;
        A[1] = 42;
        for (int i = 2; i < a; i++) {
            A[i] = (5 * A[i - 1] + 7 * A[i - 2]) % 1000000007 + 1;
        }


        B[0] = 13;
        for (int i = 1; i < b; i++) {
            B[i] = ((11 * B[i - 1]) % 1000000007) + 1;
        }


        C[0] = 7;
        C[1] = 2;
        for (int i = 2; i < c; i++) {
            C[i] = ((5 * C[i - 1] + 7 * C[i - 2]) % 1000000007) + 1;
        }

        Arrays.sort(A);
        Arrays.sort(B);
        Arrays.sort(C);


        long minimumRemaining = -1;
        long[] output = new long[2];
        TreeSet<State> statesCol = new TreeSet<>();

        if (((long) a + b + c) % 2 == 1) {
            if (A[0] <= B[0] && A[0] <= C[0]) {
                statesCol.add(new State(1, 1, 1, B[0] + C[0] + y));
                minimumRemaining = A[0];
            } else if (B[0] <= C[0] && B[0] <= A[0]) {
                statesCol.add(new State(1, 1, 1, A[0] + C[0] + z));
                minimumRemaining = B[0];
            } else {
                statesCol.add(new State(1, 1, 1, A[0] + B[0] + x));
                minimumRemaining = C[0];
            }
        } else {
            statesCol.add(new State(0, 1, 1, B[0] + C[0] + y));
            statesCol.add(new State(1, 0, 1, A[0] + C[0] + z));
            statesCol.add(new State(1, 1, 0, A[0] + B[0] + x));
        }

        output[0] = (minimumRemaining == -1) ? 0 : minimumRemaining;
        output[1] = getminimumCost(A, B, C, x, y, z, statesCol);
        return output;
    }

    private long getminimumCost(long[] A, long[] B, long[] C, long x, long y, long z, TreeSet<State> statesCol) {
        Comparator<State> comparator = new Comparator<State>() {
            @Override
            public int compare(State o1, State o2) {
                if (o1.aP != o2.aP) {
                    return Integer.compare(o1.aP, o2.aP);
                } else if (o1.bP != o2.bP) {
                    return Integer.compare(o1.bP, o2.bP);
                } else {
                    return Integer.compare(o1.cP, o2.cP);
                }
            }
        };
        TreeSet<State> allSets = new TreeSet<>(comparator);
        allSets.addAll(statesCol);


        while (!statesCol.isEmpty()) {
            State state = statesCol.first();
            statesCol.remove(statesCol.first());

            if (state.aP == A.length && state.bP == B.length && state.cP == C.length) {
                return state.cost;
            }

            ArrayList<State> nextStates = (ArrayList) nextStates(A, B, C, x, y, z, state);
            for (State nx : nextStates) {
                State actualState = allSets.ceiling(nx);

                if (actualState == null) {
                    allSets.add(nx);
                    statesCol.add(nx);

                } else if (!nx.equals(actualState)) {
                    allSets.add(nx);
                    statesCol.add(actualState);
                } else if (actualState.cost > nx.cost) {
                    allSets.remove(nx);
                    statesCol.remove(nx);
                    actualState.cost = nx.cost;

                    allSets.add(actualState);
                    statesCol.add(actualState);
                }
            }
        }

        return -1;
    }

    private List<State> nextStates(long[] a, long[] b, long[] c, long x, long y, long z, State state) {
        ArrayList<State> returnV = new ArrayList<>();
        if (state.aP == a.length) {
            if (state.bP == b.length) {
            } else {
                if (state.cP == c.length) {

                } else {
                    State state1 = new State(state.aP, state.bP + 1, state.cP + 1, state.cost + b[state.bP] + c[state.cP] + y);
                    returnV.add(state1);
                }
            }
        } else if (state.bP == b.length) {
            if (state.cP == c.length) {

            } else {
                if (state.aP == a.length) {

                } else {
                    State state1 = new State(state.aP + 1, state.bP, state.cP + 1, state.cost + a[state.aP] + c[state.cP] + z);
                    returnV.add(state1);
                }
            }
        } else if (state.cP == c.length) {
            State state1 = new State(state.aP + 1, state.bP + 1, state.cP, state.cost + a[state.aP] + b[state.bP] + x);
            returnV.add(state1);
        } else {
            State state1 = new State(state.aP, state.bP + 1, state.cP + 1, state.cost + b[state.bP] + c[state.cP] + y);
            State state2 = new State(state.aP + 1, state.bP, state.cP + 1, state.cost + a[state.aP] + c[state.cP] + z);
            State state3 = new State(state.aP + 1, state.bP + 1, state.cP, state.cost + a[state.aP] + b[state.bP] + x);
            returnV.add(state1);
            returnV.add(state2);
            returnV.add(state3);
        }
        return returnV;
    }
}


@AllArgsConstructor
class State implements Comparable<State> {
    int aP;
    int bP;
    int cP;
    long cost;


    @Override
    public int compareTo(State o) {
        if (this.cost != o.cost) {
            return Long.compare(this.cost, o.cost);
        } else if (aP != o.aP) {
            return Integer.compare(this.aP, o.aP);
        } else if (bP != o.bP) {
            return Integer.compare(this.bP, o.bP);
        } else {
            return Integer.compare(this.cP, o.cP);
        }
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof State)) return false;
        final State other = (State) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.aP != other.aP) return false;
        if (this.bP != other.bP) return false;
        if (this.cP != other.cP) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.aP;
        result = result * PRIME + this.bP;
        result = result * PRIME + this.cP;
        final long $cost = this.cost;
        result = result * PRIME + (int) ($cost >>> 32 ^ $cost);
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof State;
    }
}
