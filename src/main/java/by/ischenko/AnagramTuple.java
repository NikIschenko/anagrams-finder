package by.ischenko;

import java.util.Objects;

public class AnagramTuple<X, Y> {
	public final X x;
	public final Y y;
	public AnagramTuple(X x, Y y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AnagramTuple)) return false;

		AnagramTuple<?, ?> anagramTuple = (AnagramTuple<?, ?>) o;

		if (!Objects.equals(x, anagramTuple.x) && (!Objects.equals(x, anagramTuple.y)))  return false;
		return (!Objects.equals(y, anagramTuple.y) && (!Objects.equals(y, anagramTuple.x)));
	}

	@Override
	public int hashCode() {
		int result = x != null ? x.hashCode() : 0;
		result = 31 * result + (y != null ? y.hashCode() : 0);
		return result;
	}
}